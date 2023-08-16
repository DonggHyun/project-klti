package kr.klti.projectklti.service;

import kr.klti.projectklti.repository.VideoTimeTrackingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class VideoTimeTrackingService {

    private final VideoTimeTrackingRepository videoTimeTrackingRepository;



}
