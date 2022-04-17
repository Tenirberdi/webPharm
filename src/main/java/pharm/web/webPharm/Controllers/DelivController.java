package pharm.web.webPharm.Controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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


}
