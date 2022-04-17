package pharm.web.webPharm.Controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pharm.web.webPharm.Exceptions.QuantityError;
import pharm.web.webPharm.Models.MedicineEntity;
import pharm.web.webPharm.Models.OrderMedicineEntity;
import pharm.web.webPharm.Models.SoldEntity;
import pharm.web.webPharm.Services.PharmService;

import java.text.ParseException;
import java.util.List;

@Controller
@RequestMapping("/pharmacist")
public class PharmController {
    @Autowired
    private PharmService pharmService;


    @GetMapping("")
    public String getStart(Model model){

        model.addAttribute("org", pharmService.getOrganization());
        model.addAttribute("user", pharmService.getCurrentUser());
        model.addAttribute("medicines", pharmService.getAllMedicines());
        return "pharm";
    }

    @PostMapping("/search")
    public String getSearch(@RequestParam("search") String str, Model model){
        List<MedicineEntity> result = pharmService.getSearchResult(str);

        System.out.println(result.size());

        model.addAttribute("searchResult", result);
        return "redirect:/pharmacist/";
    }

    @GetMapping("/")
    public String getStartRes(Model model, @ModelAttribute("searchResult") List<MedicineEntity> result){

        System.out.println(result.size());
        model.addAttribute("org", pharmService.getOrganization());
        model.addAttribute("user", pharmService.getCurrentUser());
        model.addAttribute("medicines", result);
        return "pharm";
    }

    @PostMapping("/single/{medicine_id}")
    public String getSingle(@PathVariable("medicine_id") int id,Model model){

        MedicineEntity medicine = pharmService.getSingleMedicine(id);
        model.addAttribute("medicine", medicine);
        model.addAttribute("user", pharmService.getCurrentUser());
        model.addAttribute("org", pharmService.getOrganization());

        return "single";
    }

    @GetMapping("/order")
    public String order(Model model){

        model.addAttribute("org", pharmService.getOrganization());
        model.addAttribute("user", pharmService.getCurrentUser());
        model.addAttribute("orders", pharmService.getOrderedList());

        return "order";
    }

    @PostMapping("/order")
    public String makeOrder(@RequestParam("medicine_id")  int id, @RequestParam("quantity")  int quantity) throws ParseException {
        OrderMedicineEntity order = new OrderMedicineEntity();
        order.setMedicineId(id);
        order.setQuantity(quantity);
        pharmService.orderMedicine(order);
        return "redirect:/pharmacist/order";
    }

    @GetMapping("/sell")
    public String getSell(Model model){
        model.addAttribute("org", pharmService.getOrganization());
        model.addAttribute("user", pharmService.getCurrentUser());
        model.addAttribute("sold", pharmService.getSoldMedicines());

        return "sell";
    }

    @PostMapping("/sell")
    public String getSellPost(@RequestParam("medicine_id")  int id, @RequestParam("quantity")  int quantity) throws QuantityError, ParseException {
        SoldEntity sold = new SoldEntity();
        sold.setMedicineId(id);
        sold.setQuantity(quantity);
        pharmService.sellMedicine(sold);
        return "redirect:/pharmacist/sell";
    }
}
