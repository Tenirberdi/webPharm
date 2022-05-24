package pharm.web.webPharm.Controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pharm.web.webPharm.DTO.MedicineInfoDTO;
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

    @GetMapping("/single/{medicine_id}")
    public String getSingle(@PathVariable("medicine_id") int id, Model model, RedirectAttributes redirectAttributes){

        try {
            MedicineEntity medicine = pharmService.getSingleMedicine(id);
            if(medicine == null){
                throw new Exception();
            }
            model.addAttribute("medicine", medicine);
            model.addAttribute("user", pharmService.getCurrentUser());
            model.addAttribute("org", pharmService.getOrganization());

            return "single";
        }catch(Exception e){
            redirectAttributes.addFlashAttribute("error", "Such Medicine Not Found In This Organization");
            redirectAttributes.addFlashAttribute("path", "/pharmacist");
            redirectAttributes.addFlashAttribute("status", "500");
            return "redirect:/error";
        }
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
        model.addAttribute("sold", pharmService.getSoldMedicines(0));

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

    @GetMapping("/edit/{medId}")
    public String editMedicine(@PathVariable int medId, Model model){
        MedicineEntity med = pharmService.getMedicine(medId);

        model.addAttribute("org", pharmService.getOrganization());
        model.addAttribute("user", pharmService.getCurrentUser());
        model.addAttribute("medicine", med);

        return "edit";
    }

    @PostMapping("/editPost")
    public String editMedicinePost(@ModelAttribute MedicineInfoDTO medInfo){

        pharmService.editMedicine(medInfo);


        return "redirect:/pharmacist/single/" + medInfo.getId();
    }

    @GetMapping("/delete/{medId}")
    public String deleteMedicine(@PathVariable int medId){
        pharmService.deleteMedicine(medId);
        return "redirect:/pharmacist";
    }

    @GetMapping("/earning")
    public String getEarning(Model model){
        model.addAttribute("org", pharmService.getOrganization());
        model.addAttribute("user", pharmService.getCurrentUser());

        if(model.asMap().get("result") == null) {
            model.addAttribute("cash", pharmService.getEarningPerMonth());
        }else{
            model.addAttribute("cash", model.asMap().get("result"));
        }
        return "earningP";
    }

    @PostMapping("/perYear")
    public String getPerYear(@RequestParam int year, RedirectAttributes redirectAttributes){
        redirectAttributes.addFlashAttribute("result", pharmService.getEarningPerYear(year));
        return "redirect:/pharmacist/earning";
    }

    @GetMapping("/topSellers")
    public String getTopSeller(Model model){
        model.addAttribute("org", pharmService.getOrganization());
        model.addAttribute("user", pharmService.getCurrentUser());
        model.addAttribute("postPath", "/pharmacist/topSellersPerYear");
        model.addAttribute("pageInfo", "statistics of top Sellers");

        if(model.asMap().get("result") == null) {
            model.addAttribute("data", pharmService.getTopSellerPerMonth());
        }else{
            model.addAttribute("data", model.asMap().get("result"));
        }
        return "statistics";
    }

    @PostMapping("/topSellersPerYear")
    public String getTopSellerPerYear(@RequestParam int year, RedirectAttributes redirectAttributes){
        redirectAttributes.addFlashAttribute("result", pharmService.getTopSellerPerYear(year));
        return "redirect:/pharmacist/topSellers";
    }

    @GetMapping("/topMedicines")
    public String getTopMedicines(Model model){
        model.addAttribute("org", pharmService.getOrganization());
        model.addAttribute("user", pharmService.getCurrentUser());
        model.addAttribute("postPath", "/pharmacist/topMedicinesPerYear");
        model.addAttribute("pageInfo", "statistics of top Medicines");

        if(model.asMap().get("result") == null) {
            model.addAttribute("data", pharmService.getTopMedicinesPerMonth());
        }else{
            model.addAttribute("data", model.asMap().get("result"));
        }
        return "statistics";
    }

    @PostMapping("/topMedicinesPerYear")
    public String getMedicinesPerYear(@RequestParam int year, RedirectAttributes redirectAttributes){
        redirectAttributes.addFlashAttribute("result", pharmService.getTopMedicinesPerYear(year));
        return "redirect:/pharmacist/topMedicines";
    }


}
