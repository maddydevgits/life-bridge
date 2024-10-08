package com.makeskilled.LifeBridge;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/inventory")
public class BloodInventoryController {

    @Autowired
    private BloodInventoryRepository inventoryRepo;

    @Autowired
    private EmailService emailService;

    // List all blood inventory
    @GetMapping("/list")
    public String listInventory(HttpSession session,Model model) {
        String username = (String) session.getAttribute("username");
        List<BloodInventoryModel>inventoryList = inventoryRepo.findAll();
        model.addAttribute("inventoryList", inventoryList);
        model.addAttribute("username", username);
        return "inventory_list";  // Corresponding Thymeleaf template to display the inventory
    }

    @GetMapping("/ulist")
    public String ulistInventory(HttpSession session,Model model) {
        String username = (String) session.getAttribute("username");
        List<BloodInventoryModel>inventoryList = inventoryRepo.findAll();
        model.addAttribute("inventoryList", inventoryList);
        model.addAttribute("username", username);
        return "uinventory_list";  // Corresponding Thymeleaf template to display the inventory
    }

    // Show form to add new blood inventory
    @GetMapping("/add")
    public String addInventoryForm(HttpSession session,Model model) {
        String username = (String) session.getAttribute("username");
        model.addAttribute("bloodInventory", new BloodInventoryModel());
        model.addAttribute("username", username);
        return "add_inventory";  // Corresponding Thymeleaf template for the form
    }

    // Save new blood inventory
    @PostMapping("/save")
    public String saveInventory(HttpSession session,@ModelAttribute("bloodInventory") BloodInventoryModel bloodInventory) {
        String name = (String) session.getAttribute("name");
        String contact = (String) session.getAttribute("contact");

        bloodInventory.setHospitalName(name);
        bloodInventory.setHospitalNo(contact);
        inventoryRepo.save(bloodInventory);

        // Send an email notification about the new inventory
        String subject = "New Blood Inventory Added";
        String body = "<div><b>New blood inventory has been added by " + name + " (" + contact + ")</b><br>" +
                    "Blood Type: " + bloodInventory.getBloodType() + "<br>" +
                    "Available Units: " + bloodInventory.getQuantity() + "</div>";
        emailService.sendEmail(contact, name, subject, body);  // Send the email

        return "redirect:/inventory/list";
    }

    // Delete blood inventory
    @GetMapping("/delete/{id}")
    public String deleteInventory(@PathVariable("id") Long id) {
        BloodInventoryModel inventory = inventoryRepo.findById(id).orElse(null);
        if (inventory != null) {
            inventoryRepo.deleteById(id);

            // Send an email notification about the deleted inventory
            String subject = "Blood Inventory Deleted";
            String body = "<div><b>The following blood inventory has been deleted:</b><br>" +
                        "Blood Type: " + inventory.getBloodType() + "<br>" +
                        "Deleted Units: " + inventory.getQuantity() + "</div>";
            emailService.sendEmail(inventory.getHospitalNo(), inventory.getHospitalName(), subject, body);  // Send the email
        }
        return "redirect:/inventory/list";
    }

    // Show form to update existing blood inventory
    @GetMapping("/update/{id}")
    public String updateInventoryForm(HttpSession session,@PathVariable("id") Long id, Model model) {
        String username = (String) session.getAttribute("username");
        BloodInventoryModel inventory = inventoryRepo.findById(id).orElse(null);
        model.addAttribute("bloodInventory", inventory);
        model.addAttribute("username", username);
        return "update_inventory";  // Corresponding Thymeleaf template for the update form
    }

    // Save updated blood inventory
    @PostMapping("/update")
    public String updateInventory(@ModelAttribute("bloodInventory") BloodInventoryModel bloodInventory) {
        inventoryRepo.save(bloodInventory);

        // Send an email notification about the updated inventory
        String subject = "Blood Inventory Updated";
        String body = "<div><b>The blood inventory has been updated.</b><br>" +
                    "Blood Type: " + bloodInventory.getBloodType() + "<br>" +
                    "Updated Units: " + bloodInventory.getQuantity() + "</div>";
        emailService.sendEmail(bloodInventory.getHospitalNo(), bloodInventory.getHospitalName(), subject, body);
        return "redirect:/inventory/list";
    }
}