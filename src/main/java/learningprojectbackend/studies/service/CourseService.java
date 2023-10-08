package learningprojectbackend.studies.service;

import jakarta.transaction.Transactional;
import learningprojectbackend.auth.exception.NoAuthorizationToAccessResourcesException;
import learningprojectbackend.auth.service.JwtTokenDetailsService;
import learningprojectbackend.studies.controller.course.CreateCourseRequest;
import learningprojectbackend.studies.controller.course.PaginationRequest;
import learningprojectbackend.studies.controller.course.UpdateCourseRequest;
import learningprojectbackend.studies.exception.CourseNotFoundException;
import learningprojectbackend.studies.exception.CoursesNotFoundException;
import learningprojectbackend.studies.repository.CourseRepository;
import learningprojectbackend.studies.repository.LessonRepository;
import learningprojectbackend.studies.repository.TagRepository;
import learningprojectbackend.studies.service.entity.course.Course;
import learningprojectbackend.studies.service.entity.course.Lesson;
import learningprojectbackend.studies.service.entity.tag.Tag;
import learningprojectbackend.studies.service.entity.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CourseService {
    private final CourseRepository courseRepository;
    private final UserService userService;
    private final JwtTokenDetailsService jwtTokenDetailsService;
    private final LessonRepository lessonRepository;
    private final TagRepository tagRepository;

    public Course getCourseById(Long id) {
        Course course = getById(id);
        checkOwnerShip(course);
        return course;
    }

    public Page<Course> getPagedCourses(PaginationRequest paginationRequest) {
        Pageable pageable = PageRequest.of(paginationRequest.getPageNumber(), paginationRequest.getPageSize());
        return courseRepository.findAllByUserId(getUserId(), pageable);
    }

    public Course createCourse(CreateCourseRequest createCourseRequest) {
        User user = userService.getUserById(jwtTokenDetailsService.getUserIdFromJWTToken());
        Course courseToSave = new Course();
        user.addCourse(courseToSave);
        courseToSave.setTitle(createCourseRequest.getTitle());
        return courseRepository.save(courseToSave);
    }


    @Transactional
    public Course updateCourse(UpdateCourseRequest updateCourseRequest, Long courseId) {
        Long userId = getUserId();
        Course course = getCourseWithLessonAndTags(courseId);
        checkOwnerShip(course);
        List<Lesson> lessons = lessonRepository.findAllByIdInAndUserId(updateCourseRequest.getLessonIds(), userId);
        checkLessonsMismatch(lessons, updateCourseRequest.getLessonIds());
        List<Tag> tags = tagRepository.findAllByIdInAndUserId(updateCourseRequest.getTagIds(), userId);
        checkTagMisMatch(tags, updateCourseRequest.getTagIds());
        lessons.forEach(course.getLessons()::remove);
        tags.forEach(course.getTags()::remove);
        lessons.forEach(course::addLesson);
        tags.forEach(course::addTag);
        course.setTitle(updateCourseRequest.getTitle());
        return course;
    }

    private void checkTagMisMatch(List<Tag> tags, List<Long> tagIds) {
        if (tags.size() != tagIds.size()) {
            List<Long> missingIds = new LinkedList<>(tagIds);
            tags.forEach(e -> missingIds.remove(e.getId()));
            throw new CoursesNotFoundException(missingIds);
        }
    }

    private void checkLessonsMismatch(List<Lesson> lessons, List<Long> lessonIds) {
        if (lessons.size() != lessonIds.size()) {
            List<Long> missingIds = new LinkedList<>(lessonIds);
            lessons.forEach(e -> missingIds.remove(e.getId()));
            throw new CoursesNotFoundException(missingIds);
        }
    }


    private void checkOwnerShip(Course course) {
        boolean isUserAuthorizedToAccessCourse = getUserId().equals(course.getUser().getId());
        if (!isUserAuthorizedToAccessCourse) {
            throw new NoAuthorizationToAccessResourcesException("No Authorization to retrieve course with id: " + course.getId());
        }
    }

    private Course getCourseWithLessonAndTags(Long courseId) {
        return courseRepository.findById(courseId).orElseThrow(() -> new CourseNotFoundException(courseId));
    }

    private User getUser() {
        return userService.getUserById(jwtTokenDetailsService.getUserIdFromJWTToken());
    }

    private Long getUserId() {
        return jwtTokenDetailsService.getUserIdFromJWTToken();
    }

    private Course getById(Long id) {
        return courseRepository.findById(id).orElseThrow(() -> new CourseNotFoundException(id));
    }
}
