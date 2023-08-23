package kr.klti.projectklti.repository;

import kr.klti.projectklti.domain.Lesson;
import kr.klti.projectklti.domain.LessonControl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LessonControlRepository extends JpaRepository<LessonControl, Long> {


}
