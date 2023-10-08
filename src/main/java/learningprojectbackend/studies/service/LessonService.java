package learningprojectbackend.studies.service;

import learningprojectbackend.auth.service.JwtTokenDetailsService;
import learningprojectbackend.studies.controller.course.CreateLessonRequest;
import learningprojectbackend.studies.controller.course.PaginationRequest;
import learningprojectbackend.studies.model.ModelMapper;
import learningprojectbackend.studies.repository.LessonRepository;
import learningprojectbackend.studies.service.entity.course.Lesson;
import learningprojectbackend.studies.service.entity.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LessonService {
    private final LessonRepository lessonRepository;
    private final UserService userService;
    private final JwtTokenDetailsService jwtTokenDetailsService;
    private final ModelMapper modelMapper;

    public Page<Lesson> getPagedLessons(PaginationRequest paginationRequest) {
        Pageable pageable = PageRequest.of(paginationRequest.getPageNumber(), paginationRequest.getPageSize());
        return lessonRepository.findAllByUserId(getUserId(), pageable);
    }

    public Lesson createLesson(CreateLessonRequest createLessonRequest) {
        Lesson lessonToSave = new Lesson();
        lessonToSave.setUser(getuser());
        lessonToSave.setTitle(createLessonRequest.getTitle());
        return lessonRepository.save(lessonToSave);
    }

    private User getuser() {
        return userService.getUserById(jwtTokenDetailsService.getUserIdFromJWTToken());
    }

    private Long getUserId() {
        return jwtTokenDetailsService.getUserIdFromJWTToken();
    }
}
