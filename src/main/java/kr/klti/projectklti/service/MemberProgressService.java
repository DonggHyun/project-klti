package kr.klti.projectklti.service;

import kr.klti.projectklti.repository.LessonRepository;
import kr.klti.projectklti.repository.MemberProgressRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberProgressService {

    private final MemberProgressRepository memberProgressRepository;

}
