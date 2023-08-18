package kr.klti.projectklti.repository;

import kr.klti.projectklti.domain.VideoTimeTracking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VideoTimeTrackingRepository extends JpaRepository<VideoTimeTracking,Long> {
    //추가구현예정

}
