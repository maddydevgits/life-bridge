package com.makeskilled.LifeBridge;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import org.springframework.security.access.prepost.PreAuthorize;

import java.util.List;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/inventory")
public class LifeBridgeBloodInventoryController {

    @Autowired
    private LifeBridgeBloodInventoryRepository inventoryRepo;

    // List all blood inventory
    @PreAuthorize("hasRole('ADMIN') or hasRole('BLOODBANK')")
    @GetMapping("/list")
    public String listInventory(HttpSession session,Model model) {
        String username = (String) session.getAttribute("username");
        List<LifeBridgeBloodInventoryModel>inventoryList = inventoryRepo.findAll();
        model.addAttribute("inventoryList", inventoryList);
        model.addAttribute("username", username);
        return "inventory_list";  // Corresponding Thymeleaf template to display the inventory
    }

    // Show form to add new blood inventory
    @GetMapping("/add")
    public String addInventoryForm(HttpSession session,Model model) {
        String username = (String) session.getAttribute("username");
        model.addAttribute("bloodInventory", new LifeBridgeBloodInventoryModel());
        model.addAttribute("username", username);
        return "add_inventory";  // Corresponding Thymeleaf template for the form
    }

    // Save new blood inventory
    @PostMapping("/save")
    public String saveInventory(@ModelAttribute("bloodInventory") LifeBridgeBloodInventoryModel bloodInventory) {
        inventoryRepo.save(bloodInventory);
        return "redirect:/inventory/list";
    }

    // Delete blood inventory
    @GetMapping("/delete/{id}")
    public String deleteInventory(@PathVariable("id") Long id) {
        inventoryRepo.deleteById(id);
        return "redirect:/inventory/list";
    }

    // Show form to update existing blood inventory
    @GetMapping("/update/{id}")
    public String updateInventoryForm(HttpSession session,@PathVariable("id") Long id, Model model) {
        String username = (String) session.getAttribute("username");
        LifeBridgeBloodInventoryModel inventory = inventoryRepo.findById(id).orElse(null);
        model.addAttribute("bloodInventory", inventory);
        model.addAttribute("username", username);
        return "update_inventory";  // Corresponding Thymeleaf template for the update form
    }

    // Save updated blood inventory
    @PostMapping("/update")
    public String updateInventory(@ModelAttribute("bloodInventory") LifeBridgeBloodInventoryModel bloodInventory) {
        inventoryRepo.save(bloodInventory);
        return "redirect:/inventory/list";
    }
}