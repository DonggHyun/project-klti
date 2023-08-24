package kr.klti.projectklti.service;

import kr.klti.projectklti.domain.Student;
import kr.klti.projectklti.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;

    public List<Student> findByMember(Long memId){
        return studentRepository.findByMember(memId);
    }
}
