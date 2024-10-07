package com.makeskilled.LifeBridge;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpSession;

import java.util.List;

@Controller
@RequestMapping("/equipment")
public class EquipmentInventoryController {

    @Autowired
    private EquipmentInventoryRepository equipmentRepo;

    // List all equipment inventory
    @GetMapping("/list")
    public String listEquipment(HttpSession session,Model model) {
        String username = (String) session.getAttribute("username");
        List<EquipmentInventoryModel> equipmentList = equipmentRepo.findAll();
        model.addAttribute("equipmentList", equipmentList);
        model.addAttribute("username", username);
        return "equipment_list";  // Corresponding Thymeleaf template to display the equipment
    }

    // Show form to add new equipment
    @GetMapping("/add")
    public String addEquipmentForm(HttpSession session,Model model) {
        String username = (String) session.getAttribute("username");
        model.addAttribute("username", username);
        model.addAttribute("equipmentInventory", new EquipmentInventoryModel());
        return "add_equipment";  // Corresponding Thymeleaf template for the form
    }

    // Save new equipment
    @PostMapping("/save")
    public String saveEquipment(@ModelAttribute("equipmentInventory") EquipmentInventoryModel equipmentInventory) {
        equipmentRepo.save(equipmentInventory);
        return "redirect:/equipment/list";
    }

    // Delete equipment
    @GetMapping("/delete/{id}")
    public String deleteEquipment(@PathVariable("id") Long id) {
        equipmentRepo.deleteById(id);
        return "redirect:/equipment/list";
    }

    // Show form to update existing equipment
    @GetMapping("/update/{id}")
    public String updateEquipmentForm(HttpSession session,@PathVariable("id") Long id, Model model) {
        String username = (String) session.getAttribute("username");
        model.addAttribute("username", username);
        EquipmentInventoryModel equipment = equipmentRepo.findById(id).orElse(null);
        model.addAttribute("equipmentInventory", equipment);
        return "update_equipment";  // Corresponding Thymeleaf template for the update form
    }

    // Save updated equipment
    @PostMapping("/update")
    public String updateEquipment(@ModelAttribute("equipmentInventory") EquipmentInventoryModel equipmentInventory) {
        equipmentRepo.save(equipmentInventory);
        return "redirect:/equipment/list";
    }
}
