package re.session02.controller;

import java.util.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ReportController {

    @GetMapping("/report")
    public String showReport(Model model) {
        List<Map<String, Object>> students = new ArrayList<>();
        students.add(Map.of("name", "Nguyễn Thị Bình", "score", 92));
        students.add(Map.of("name", "Trần Minh Khoa", "score", 75));
        students.add(Map.of("name", "Lê Thu Hà", "score", 55));
        students.add(Map.of("name", "Phạm Duy An", "score", 48));

        model.addAttribute("studentList", students);
        model.addAttribute("reportTitle", "Báo cáo điểm cuối kỳ");

        return "report";
    }
}