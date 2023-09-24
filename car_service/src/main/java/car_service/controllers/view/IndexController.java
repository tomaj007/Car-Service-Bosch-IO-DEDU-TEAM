package car_service.controllers.view;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/")
public class IndexController {

    @GetMapping
    public String getIndex(Model model) {
        final String welcomeMessage = "Welcome to the service System!";
        model.addAttribute("welcome", welcomeMessage);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        List<GrantedAuthority> grantedAuthorities = (List<GrantedAuthority>) authentication.getAuthorities();
        model.addAttribute("username", grantedAuthorities.get(0).getAuthority());

        return "index";
    }

    @GetMapping("login")
    public String login(Model model) {
        final String welcomeMessage = "WELCOME TO THE CAR SERVICE!";
        model.addAttribute("welcome", welcomeMessage);
        return "login";
    }

    @GetMapping("unauthorized")
    public String unauthorized(Model model) {
        final String welcomeMessage = "Sorry you do not have permission!";
        model.addAttribute("welcome", welcomeMessage);
        return "unauthorized";
    }

}