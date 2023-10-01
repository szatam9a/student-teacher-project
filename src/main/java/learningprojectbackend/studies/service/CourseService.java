package learningprojectbackend.studies.service;

import learningprojectbackend.auth.service.JwtTokenDetailsService;
import learningprojectbackend.studies.controller.course.CreateCourseRequest;
import learningprojectbackend.studies.controller.course.PaginationRequest;
import learningprojectbackend.studies.repository.CourseRepository;
import learningprojectbackend.studies.service.entity.course.Course;
import learningprojectbackend.studies.service.entity.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CourseService {
    private final CourseRepository courseRepository;
    private final UserService userService;
    private final JwtTokenDetailsService jwtTokenDetailsService;

    public Course createCourse(CreateCourseRequest createCourseRequest) {
        User user = userService.getUserById(jwtTokenDetailsService.getUserIdFromJWTToken());
        Course courseToSave = new Course();
        courseToSave.setUser(user);
        courseToSave.setTitle(createCourseRequest.getTitle());
        return courseRepository.save(courseToSave);
    }

    public Page<Course> getPagedCourses(PaginationRequest paginationRequest) {
        Pageable pageable = PageRequest.of(
                paginationRequest.getPageNumber(),
                paginationRequest.getPageSize());
        Page<Course> course = courseRepository.findAll(pageable);
        return course;
    }
}
