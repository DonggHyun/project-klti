package kr.klti.projectklti.service;

import kr.klti.projectklti.repository.MemberProgressRepository;
import kr.klti.projectklti.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;

}
