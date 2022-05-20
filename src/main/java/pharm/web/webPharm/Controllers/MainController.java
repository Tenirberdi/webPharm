package pharm.web.webPharm.Controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pharm.web.webPharm.Models.MedicineEntity;
import pharm.web.webPharm.Services.PharmService;

@Controller
public class MainController {
    @Autowired
    private PharmService pharmService;
    @GetMapping({"/", "/login"})
    public String getLogin(){

        return "login";
    }

    @GetMapping("/error")
    public String getErrorPage(Model model){

        model.addAttribute("error", (String) model.asMap().get("error"));
        model.addAttribute("status", (String) model.asMap().get("status"));
        model.addAttribute("path", (String) model.asMap().get("path"));
        return "error";
    }








}
