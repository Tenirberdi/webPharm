package pharm.web.webPharm.Controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
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








}
