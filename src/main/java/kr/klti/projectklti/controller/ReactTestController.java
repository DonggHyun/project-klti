package kr.klti.projectklti.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
//@CrossOrigin(origins = "*")
public class ReactTestController {

    @GetMapping("/hello")
    public List<String> Hello(){
        System.out.println("controller");
        return Arrays.asList("서버 포트는 8080", "리액트 포트는 3000");
    }

/*    @GetMapping("/loginview")
    public ResponseEntity<String> loginview() {
        String data = "Hello I'm data";
        return ResponseEntity.ok(data);
    }*/
    @GetMapping("/api/test")
    public ResponseEntity<String> test() {
        String data = "test string";
        return ResponseEntity.ok(data);
    }

/*    @GetMapping("/api/join")
    public ResponseEntity<String> join() {
        System.out.println("join controller");
        String data = "join page";
        return ResponseEntity.ok(data);
    }*/



}
