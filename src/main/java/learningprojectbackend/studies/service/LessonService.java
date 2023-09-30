package learningprojectbackend.studies.service;

import learningprojectbackend.studies.controller.course.CreateLessonRequest;
import learningprojectbackend.studies.repository.LessonRepository;
import learningprojectbackend.studies.service.entity.course.Lesson;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LessonService {
    private final LessonRepository lessonRepository;

    public Lesson createLesson(CreateLessonRequest createLessonRequest) {
        Lesson lessonToSave = new Lesson();
        lessonToSave.setTitle(createLessonRequest.getTitle());
        return lessonRepository.save(lessonToSave);
    }
}
