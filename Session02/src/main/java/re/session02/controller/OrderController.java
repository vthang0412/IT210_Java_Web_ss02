package re.session02.controller;



import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
public class OrderController {

    @GetMapping("/orders")
    public String showOrders(HttpSession session, Model model) {
        String username = (String) session.getAttribute("loggedUser");
        String role = (String) session.getAttribute("role");
        if (username == null) {
            return "redirect:/login";
        }

        List<Map<String, Object>> orders = new ArrayList<>();
        orders.add(Map.of("code", "DH001", "product", "Laptop", "total", 15000000, "date", new Date()));
        orders.add(Map.of("code", "DH002", "product", "Điện thoại", "total", 7000000, "date", new Date()));
        orders.add(Map.of("code", "DH003", "product", "Bàn phím", "total", 1200000, "date", new Date()));

        model.addAttribute("orders", orders);
        model.addAttribute("username", username);
        model.addAttribute("role", role);

        ServletContext application = session.getServletContext();
        synchronized (application) {
            Integer count = (Integer) application.getAttribute("totalViewCount");
            if (count == null) count = 0;
            count++;
            application.setAttribute("totalViewCount", count);
        }

        return "orders";
    }
}