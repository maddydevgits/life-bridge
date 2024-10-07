package com.makeskilled.LifeBridge;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.servlet.http.HttpSession;

import java.util.Optional;

@Controller
public class LifeBridgeController {

    @Autowired
    LifeBridgeRepository repo;
    
    @GetMapping("/")
    public String homePage() {
        return "index";
    }

    @GetMapping("/register")
    public String registerPage(){
        return "register";
    }

    @GetMapping("/login")
    public String loginPage(){
        return "login";
    }

    @PostMapping("/registerForm")
    public String registerForm(LifeBridgeModel model,Model form) {
        
        System.out.println("Username: " + model.getUsername());
        System.out.println("Password: " + model.getPassword());
        System.out.println("Email: " + model.getEmail());
        System.out.println("Role: "+ model.getRole());

        if (repo.existsByUsername(model.getUsername())) {
            form.addAttribute("message", "Username already exists. Please choose another username.");
            return "register"; // Return to the registration page with the message
        }
        
        if (repo.existsByEmail(model.getEmail())) {
            form.addAttribute("message", "Email already exists. Please use another email.");
            return "register"; // Return to the registration page with the message
        }

        repo.save(model);
        form.addAttribute("message", "Registration successful! You can now log in.");
        return "redirect:/login";
    }

    @PostMapping("/loginForm")
    public String loginForm(LifeBridgeModel model,Model form,HttpSession session) {
        
        System.out.println("Username: " + model.getUsername());
        System.out.println("Password: " + model.getPassword());

        if(repo.existsByUsername(model.getUsername())==false){
            form.addAttribute("message", "Username doesn't exist, register first");
            return "register";
        }

        Optional<LifeBridgeModel> user = repo.findByUsername(model.getUsername());
        if (user.isPresent() && user.get().getPassword().equals(model.getPassword())) {
            // Store username in the session
            session.setAttribute("username", user.get().getUsername());
            session.setAttribute("role",user.get().getRole());

            form.addAttribute("message", "Login successful! Welcome " + user.get().getUsername());
            return "redirect:/dashboard";  
        } else {
            form.addAttribute("message", "Invalid username or password. Please try again.");
            return "login";  // Return to login page with an error message
        }
    }

    @GetMapping("/dashboard")
    public String dashboard(HttpSession session, Model model) {
        // Get the username from the session
        String username = (String) session.getAttribute("username");
        String role = (String) session.getAttribute("role");

        // If the user is not logged in, redirect to login
        if (username == null) {
            return "redirect:/login";
        }

        // Add username to the model to display on the dashboard
        model.addAttribute("username", username);

        if(role=="HOSPITAL"){
            return "hdashboard";
        }

        return "dashboard";

    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();  // Invalidate the session to log out the user
        return "redirect:/login";  // Redirect to the login page
    }
}
