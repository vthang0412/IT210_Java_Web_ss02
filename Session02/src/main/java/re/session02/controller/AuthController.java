package re.session02.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthController {

    @GetMapping("/login")
    public String loginForm() {
        return "login";
    }

    @PostMapping("/login")
    public String doLogin(@RequestParam String username,
                          @RequestParam String password,
                          Model model,
                          HttpSession session) {

            if(("admin".equals(username) && "admin123".equals(password)) ||
                ("staff".equals(username) && "staff123".equals(password))) {

            session.setAttribute("loggedUser", username);
            session.setAttribute("role", username.equals("admin") ? "Quản lý" : "Nhân viên");
            return "redirect:/orders";
        } else {
            model.addAttribute("loginError", "Tên đăng nhập hoặc mật khẩu sai!");
            return "login";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }
}