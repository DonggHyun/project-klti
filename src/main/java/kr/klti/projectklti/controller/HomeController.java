package kr.klti.projectklti.controller;

import lombok.Getter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

//테스트용 초기 컨트롤러입니다.
//이름 html이름을 따서 만들었습니다.
@Controller
public class HomeController {
    @RequestMapping("/")
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
    @GetMapping("/loginview")
    public String loginview(){return "login";}

    @PostMapping("/login")
    public String login(){
        return "redirect:/";
    }
    @RequestMapping("/products")
    public String products(){
        return "products";
    }


}
