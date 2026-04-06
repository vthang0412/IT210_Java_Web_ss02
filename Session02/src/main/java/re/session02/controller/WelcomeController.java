package re.session02.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WelcomeController {

    @GetMapping("/welcome")
    public String showWelcome(Model model) {
        model.addAttribute("staffName", "Đặng Văn Thắng");
        model.addAttribute("department", "Phòng Kỹ thuật");
        return "welcome";
    }
}
