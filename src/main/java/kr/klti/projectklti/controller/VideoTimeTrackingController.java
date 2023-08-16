package kr.klti.projectklti.controller;

import kr.klti.projectklti.service.VideoTimeTrackingService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class VideoTimeTrackingController {
    private final VideoTimeTrackingService videoTimeTrackingService;
    //추가구현예정
}
