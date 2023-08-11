package kr.klti.projectklti.controller;

import kr.klti.projectklti.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

//테스트용 초기 컨트롤러입니다.
//이름 html이름을 따서 만들었습니다.
@Controller
@RequiredArgsConstructor
public class HomeController {

    private final MemberService userService;

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
    public String loginview(){return "redirect:/";}
    @GetMapping("/admin")
    public String admin(){return "admin";}
    @GetMapping("/failLogin")
    public String failLogin(){return "fail";}

    @GetMapping("/join")
    public String joinview(){return "join";}

    @PostMapping("/login")
    public String login(){return "redirect:/";}



    //--------------------------------------------
    @RequestMapping("/products")
    public String products(){
        return "products";
    }
}
