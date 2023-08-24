package kr.klti.projectklti.service;

import kr.klti.projectklti.repository.LessonControlRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LessonControlService {

    private final LessonControlRepository lessonControlRepository;

}
