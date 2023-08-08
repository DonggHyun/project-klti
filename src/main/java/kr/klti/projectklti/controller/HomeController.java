package kr.klti.projectklti.controller;

import kr.klti.projectklti.domain.User;
import kr.klti.projectklti.dto.UserDto;
import kr.klti.projectklti.service.UserService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDateTime;

//테스트용 초기 컨트롤러입니다.
//이름 html이름을 따서 만들었습니다.
@Controller
@RequiredArgsConstructor
public class HomeController {

    private final UserService userService;

    @GetMapping("/")
    public String home(){
        return "index";
    }
    @RequestMapping("/accounts")
    public String accounts(){
        return "accounts";
    }
    @RequestMapping("/add")
    public String add_product(){
        return "add-product";
    }
    @RequestMapping("/edit")
    public String edit(){
        return "home";
    }
    //----------------------------------------
    @GetMapping("/loginview")
    public String loginview(){return "login";}
    @GetMapping("/admin")
    public String admin(){return "admin";}
    @GetMapping("/failLogin")
    public String failLogin(){return "fail";}

    @GetMapping("/join")
    public String joinview(){return "join";}

    @PostMapping("/login")
    public String login(){return "redirect:/";}
    @PostMapping("/join")
    public String join(@ModelAttribute UserDto userDto){
        userDto.setCreateReq(LocalDateTime.now().toString());
        userService.joinUser(userDto);
        return "redirect:/login";
    }
    //--------------------------------------------
    @RequestMapping("/products")
    public String products(){
        return "products";
    }
}
