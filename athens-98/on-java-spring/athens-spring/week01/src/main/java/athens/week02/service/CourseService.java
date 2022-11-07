package athens.week02.service;

import athens.week02.domain.Course;
import athens.week02.dto.CourseRequestDto;
import athens.week02.repository.CourseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class CourseService {
    private final CourseRepository courseRepository;

//    public CourseService(CourseRepository courseRepository) {
//        this.courseRepository = courseRepository;
//    }

    @Transactional
    public Long update(Long id, CourseRequestDto requestDto) {
        Course course1 = courseRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("no id!")
        );
        course1.update(requestDto);
        return course1.getId();
    }

}
