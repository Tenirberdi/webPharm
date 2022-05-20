package pharm.web.webPharm.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pharm.web.webPharm.Services.AdminService;
import pharm.web.webPharm.Services.PharmService;

import java.text.ParseException;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private PharmService pharmService;

    @Autowired
    private AdminService adminService;

    @GetMapping("")
    public String getStart(Model model){
        model.addAttribute("org", pharmService.getOrganization());
        model.addAttribute("user", pharmService.getCurrentUser());
        model.addAttribute("users", adminService.getUsers());

        return "admin";
    }

    @PostMapping("/register")
    public String register(@RequestParam("fullName") String fullName, @RequestParam("phone") int phone, @RequestParam("photo") String photo, @RequestParam("login") String login, @RequestParam("password") String pass, @RequestParam("role") String role) throws ParseException {

        adminService.register(login, pass, fullName, phone, photo, role);
        return "redirect:/admin";
    }

    @PostMapping("/delete/{emp_id}")
    public String delete(@PathVariable("emp_id") int id){
        adminService.delete(id);
        return "redirect:/admin";
    }
}
