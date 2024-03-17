package base.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {

    @GetMapping("/hello")
    public String helli(){
        return " <h2> Hey, everione  - 10!    <h2>";
    }

    @GetMapping("/user")
    public String user(){
        return " <h2> Hey, User  - 10!   <h2>";
    }

    @GetMapping("/admin")
    public String admin(){
        return " <h2> Hey, Adminuser  - 10!   <h2>";
    }
}
