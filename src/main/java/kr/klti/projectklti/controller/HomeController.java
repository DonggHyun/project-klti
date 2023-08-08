package kr.klti.projectklti.controller;

import org.springframework.stereotype.Controller;
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
    @RequestMapping("/loginview")
    public String login(){return "login";}
    @RequestMapping("/products")
    public String products(){
        return "products";
    }


}
