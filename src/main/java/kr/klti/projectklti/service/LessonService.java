package kr.klti.projectklti.service;

import kr.klti.projectklti.domain.Lecture;
import kr.klti.projectklti.domain.Lesson;
import kr.klti.projectklti.dto.LectureDto;
import kr.klti.projectklti.dto.LessonDto;
import kr.klti.projectklti.repository.LessonControlRepository;
import kr.klti.projectklti.repository.LessonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LessonService {

    private final LessonRepository lessonRepository;

    public List<LessonDto> getLessonList(Long lectNo){
        Lecture lecture=Lecture.builder().lectNo(lectNo).build();
        List<Lesson> lessonList = lessonRepository.findByLectureOrderByLessNoAsc(lecture);

        return lessonList.stream()
                .map(LessonDto::of)
                .collect(Collectors.toList());
    }

}
