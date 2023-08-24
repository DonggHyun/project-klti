package kr.klti.projectklti.repository;

import kr.klti.projectklti.domain.Member;
import kr.klti.projectklti.domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    List<Student> findByMember(Long memId);


}
