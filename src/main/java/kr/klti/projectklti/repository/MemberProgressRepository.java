package kr.klti.projectklti.repository;

import kr.klti.projectklti.domain.Lesson;
import kr.klti.projectklti.domain.MemberProgress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberProgressRepository extends JpaRepository<MemberProgress, Long> {


}
