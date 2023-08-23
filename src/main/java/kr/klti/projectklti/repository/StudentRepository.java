package kr.klti.projectklti.repository;

import kr.klti.projectklti.domain.MemberProgress;
import kr.klti.projectklti.domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {


}
