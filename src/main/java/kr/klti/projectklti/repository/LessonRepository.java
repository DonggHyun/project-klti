package kr.klti.projectklti.repository;

import kr.klti.projectklti.domain.Content;
import kr.klti.projectklti.domain.Lecture;
import kr.klti.projectklti.domain.Lesson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LessonRepository extends JpaRepository<Lesson, Long> {

    List<Lesson> findByLectureOrderByLessNoAsc(Lecture lecture);
}
