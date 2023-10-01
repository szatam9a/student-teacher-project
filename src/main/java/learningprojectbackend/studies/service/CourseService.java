package learningprojectbackend.studies.service;

import learningprojectbackend.auth.exception.NoAuthorizationToAccessResourcesException;
import learningprojectbackend.auth.service.JwtTokenDetailsService;
import learningprojectbackend.studies.controller.course.CreateCourseRequest;
import learningprojectbackend.studies.controller.course.PaginationRequest;
import learningprojectbackend.studies.controller.course.UpdateCourseRequest;
import learningprojectbackend.studies.exception.CourseNotFoundException;
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
import org.springframework.transaction.annotation.Transactional;

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
        Page<Course> course; //  = courseRepository.findAll(pageable);
        course = courseRepository.findAllByUserId(jwtTokenDetailsService.getUserIdFromJWTToken(), pageable);
        return course;
    }

    public Course createCourse(CreateCourseRequest createCourseRequest) {
        User user = userService.getUserById(jwtTokenDetailsService.getUserIdFromJWTToken());
        Course courseToSave = new Course();
        courseToSave.setUser(user);
        courseToSave.setTitle(createCourseRequest.getTitle());
        return courseRepository.save(courseToSave);
    }

    @Transactional
    public Course updateCourse(UpdateCourseRequest updateCourseRequest, Long courseId) {
        User user = getUser();
        Course course = getCourseById(courseId);
        checkOwnerShip(course);
        List<Lesson> lessons = lessonRepository.findAllByIdInAndUserId(updateCourseRequest.getLessonIds(), user.getId());
        List<Tag> tags = tagRepository.findAllByIdInAndUserId(updateCourseRequest.getTagIds(), user.getId());
        course.setLessonList(lessons);
        course.setTagList(tags);
        course.setTitle(updateCourseRequest.getTitle());
        return course;

    }

    private void checkOwnerShip(Course course) {
        boolean isUserAuthorizedToAccessCourse = getUser().getId().equals(course.getUser().getId());
        if (!isUserAuthorizedToAccessCourse) {
            throw new NoAuthorizationToAccessResourcesException("No Authorization to retrieve course with id: " + course.getId());

        }
    }

    private User getUser() {
        return userService.getUserById(jwtTokenDetailsService.getUserIdFromJWTToken());
    }

    private Course getById(Long id) {
        return courseRepository.findById(id).orElseThrow(() -> new CourseNotFoundException(id));

    }
}
