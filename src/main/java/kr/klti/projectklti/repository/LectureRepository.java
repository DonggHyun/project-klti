package kr.klti.projectklti.repository;

import kr.klti.projectklti.domain.Lecture;
import kr.klti.projectklti.domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LectureRepository extends JpaRepository<Lecture,Long> {

    @Override
    List<Lecture> findAll();

    List<Lecture> findByLectNo(Lecture lectNo);

}
