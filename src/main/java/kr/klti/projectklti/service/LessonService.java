package kr.klti.projectklti.service;

import kr.klti.projectklti.repository.LessonControlRepository;
import kr.klti.projectklti.repository.LessonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LessonService {

    private final LessonRepository lessonRepository;

}
