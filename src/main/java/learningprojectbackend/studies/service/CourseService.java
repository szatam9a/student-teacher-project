package learningprojectbackend.studies.service;

import learningprojectbackend.studies.repository.CourseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CourseService {
    private final CourseRepository courseRepository;
}
