package kr.klti.projectklti.repository;

import kr.klti.projectklti.domain.Content;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContentRepository extends JpaRepository<Content, Long> {

    /* videoId로 특정 콘텐츠 조회 */
    public Content findContentByContVideoId(String contVideoId);

}
