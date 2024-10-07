package com.makeskilled.LifeBridge;

import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequestMapping("/donations")
public class DonationController {

    @Autowired
    private DonationRepository donationRepo;

    // List all donations
    @GetMapping("/list")
    public String listDonations(HttpSession session,Model model) {
        String username = (String) session.getAttribute("username");
        model.addAttribute("username", username);
        List<DonationModel> donationList = donationRepo.findAll();
        model.addAttribute("donationList", donationList);
        return "donations_list";  // Corresponding Thymeleaf template to display the donations
    }
}

