package project01.ecommerce.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import project01.ecommerce.model.User;
import project01.ecommerce.service.UserService;

@Controller
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/login")
    public String getLogin() {
        return "login";
    }

    @GetMapping(value = "/register")
    public String getRegisterForm(Model model, User user) {
        model.addAttribute("user", user);
        return "register";
    }

    @PostMapping(value = "/register")
    public String postRegisterForm(@ModelAttribute("user") User user, Model model) {
        User userCheck = userService.findUserByUsername(user.getUsername());
        if (userCheck != null) {
            model.addAttribute("messageUserExist", "Username is taken");
            return "register";
        }
        userService.save(user);
        model.addAttribute("message", "Registration Successfully !");
        return "register";
    }

    @GetMapping(value = "/home")
    public String homePage(){
        return "home";
    }
}
