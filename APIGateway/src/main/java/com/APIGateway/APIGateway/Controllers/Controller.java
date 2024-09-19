package com.APIGateway.APIGateway.Controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/")
public class Controller {
    // endpoint to test login for token verification
    @GetMapping("/testLogin")
    public String Login() {
        return "Login successful";
    }

}
