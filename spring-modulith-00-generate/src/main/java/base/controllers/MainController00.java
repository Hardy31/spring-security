package base.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController00 {
    @GetMapping("/hello")
    public String helli(){
        return " <h2> Hey, user00!   <h2>";
    }
}
