package pharm.web.webPharm.Controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pharm.web.webPharm.Exceptions.QuantityError;
import pharm.web.webPharm.Services.DelivService;
import pharm.web.webPharm.Services.PharmService;

import java.text.ParseException;

@Controller
@RequestMapping("/deliveryman")
public class DelivController {
    @Autowired
    PharmService pharmService;
    
    @Autowired
    DelivService delivService;

    @GetMapping("")
    public String getStart(Model model){


        model.addAttribute("org", pharmService.getOrganization());
        model.addAttribute("user", pharmService.getCurrentUser());
        model.addAttribute("orders", pharmService.getOrderedList());
        return "deliv";
    }
    
    @PostMapping("/deliver")
    public String deliver(@RequestParam("medicine_id") int id, @RequestParam("quantity") int quantity) throws QuantityError, ParseException {
        delivService.deliver(id, quantity);
        return "redirect:/deliveryman";
    }

    @GetMapping("/delivered")
    public String getDelivered(Model model){

        model.addAttribute("org", pharmService.getOrganization());
        model.addAttribute("user", pharmService.getCurrentUser());
        model.addAttribute("delivs", delivService.getDelivered());
        return "delivered";
    }

    @GetMapping("/earning")
    public String getEarning(Model model){
        model.addAttribute("org", pharmService.getOrganization());
        model.addAttribute("user", pharmService.getCurrentUser());

        if(model.asMap().get("result") == null) {
            model.addAttribute("cash", delivService.getEarningForEveryMonth());
        }else{
            model.addAttribute("cash", model.asMap().get("result"));
        }
        return "earning";
    }

    @PostMapping("/perYear")
    public String getPerYear(@RequestParam int year, RedirectAttributes redirectAttributes){
        redirectAttributes.addFlashAttribute("result", delivService.getEarningPerYear(year));
        return "redirect:/deliveryman/earning";
    }



}
