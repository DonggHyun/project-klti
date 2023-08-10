package kr.klti.projectklti.controller;

import kr.klti.projectklti.dto.UserDto;
import kr.klti.projectklti.service.UserService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);


    @PostMapping("/api/join")
    public ResponseEntity<String> join(@RequestBody UserDto userDto){
        logger.info("JOIN CONTROLLER");
        userDto.setCreateReq(LocalDateTime.now().toString());
        userService.joinUser(userDto);
        logger.info("JOIN DONE");
        return ResponseEntity.ok("Good");
    }





}
