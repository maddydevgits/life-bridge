package com.makeskilled.LifeBridge;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpSession;

import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/blood-requests")
public class BloodRequestController {

    @Autowired
    private BloodRequestRepository bloodRequestRepo;

    @Autowired
    private LifeBridgeRepository userRepo;

    @Autowired
    private BloodRequestHistoryRepository bloodRequestHistoryRepo;

    @Autowired
    private EmailService emailService;


    // Create a new blood request
    @PostMapping("/create")
    public String createBloodRequest(HttpSession session, 
                                     @RequestParam("bloodType") String bloodType,
                                     @RequestParam("requestedQuantity") int requestedQuantity, 
                                     Model model) {
        String username = (String) session.getAttribute("username");
        // Fetch the user (requester) based on the logged-in username
        LifeBridgeModel requester = userRepo.findByUsername(username);  // Assuming you have this method in UserRepository
    
        if (requester == null) {
            model.addAttribute("error", "User not found.");
            return "error";  // Redirect to an error page if the user is not found
        }
        
        BloodRequestModel request = new BloodRequestModel(username, bloodType, requestedQuantity, new Date());
        request.setRequester(requester); 
    
        bloodRequestRepo.save(request);

        String subject = "Blood Request Created";
        String body = "<div><b>Your blood request for " + requestedQuantity + " units of " + bloodType + " has been successfully created.</b></div>";
        emailService.sendEmail(requester.getEmail(), requester.getName(), subject, body);
        
        model.addAttribute("message", "Blood request successfully created.");
        model.addAttribute("username", username);
        return "request_blood";  // Redirect to list of blood requests
    }

    // List all blood requests that haven't been accepted
    @GetMapping("/list")
    public String listBloodRequests(Model model) {
        List<BloodRequestModel> requests = bloodRequestRepo.findByAcceptedFalse();
        model.addAttribute("requests", requests);
        return "blood_requests_list";  // Corresponding Thymeleaf template for the list of requests
    }

    @GetMapping("/list1")
    public String listBloodRequests1(HttpSession session,Model model) {
        String username = (String) session.getAttribute("username");
        List<BloodRequestModel> requests = bloodRequestRepo.findByAcceptedFalse();
        model.addAttribute("requests", requests);
        model.addAttribute("username", username);
        return "blood_requests_list";  // Corresponding Thymeleaf template for the list of requests
    }

    // Accept a blood request
    @PostMapping("/accept1/{id}")
    public String acceptBloodRequest(@PathVariable("id") Long id, HttpSession session, Model model) {
        BloodRequestModel request = bloodRequestRepo.findById(id).orElse(null);
        if (request == null) {
            model.addAttribute("error", "Blood request not found.");
            return "error";  // Error page
        }
        
        String acceptedBy = (String) session.getAttribute("username");
        LifeBridgeModel user = userRepo.findByUsername(acceptedBy);  // Assuming `findByUsername` method exists in UserRepository
        String mobileNumber = user.getMobile();  // Assuming `mobileNumber` field exists in User entity

       // Mark the request as accepted and store who accepted it along with the mobile number
        request.setAccepted(true);
        request.setAcceptedBy(acceptedBy);
        request.setAcceptedByMobile(mobileNumber);
        bloodRequestRepo.save(request);  // Save the updated request

        // Mark the request as accepted and store who accepted it
        request.setAccepted(true);
        request.setAcceptedBy(acceptedBy);
        bloodRequestRepo.save(request);  // Save the updated request

        // Save the request history
        BloodRequestHistory history = new BloodRequestHistory(request, acceptedBy, mobileNumber, new Date());
        bloodRequestHistoryRepo.save(history);  // Save history record

        // Send an email to the requester
        String subjectRequester = "Your Blood Request Has Been Accepted";
        String bodyRequester = "<div><b>Your blood request for " + request.getRequestedQuantity() + " units of " + request.getBloodType() + " has been accepted by " + acceptedBy + ".</b></div>";
        emailService.sendEmail(request.getRequester().getEmail(), request.getRequester().getName(), subjectRequester, bodyRequester);

        // Send an email to the user/hospital who accepted the request
        String subjectAccepter = "You Have Accepted a Blood Request";
        String bodyAccepter = "<div><b>You have successfully accepted the blood request for " + request.getRequestedQuantity() + " units of " + request.getBloodType() + ".</b></div>";
        emailService.sendEmail(user.getEmail(), user.getName(), subjectAccepter, bodyAccepter);

        model.addAttribute("success", "Blood request accepted by " + acceptedBy);
        return "redirect:/blood-requests/list1";  // Redirect to list of blood requests
    }

    @GetMapping("/myrequests")
    public String viewMyRequests(HttpSession session, Model model) {
        String username = (String) session.getAttribute("username");

        // Fetch the current user's blood requests
        List<BloodRequestModel> myRequests = bloodRequestRepo.findByUsername(username);
        model.addAttribute("myRequests", myRequests);
        model.addAttribute("username",username);
        return "my_blood_requests";  // Corresponding Thymeleaf template to display the user's requests
    }

    @GetMapping("/others")
    public String listRequestsByOthers(HttpSession session, Model model) {
        String username = (String) session.getAttribute("username");

        // Fetch requests made by other users
        List<BloodRequestModel> requests = bloodRequestRepo.findByUsernameNotAndAcceptedFalse(username);
        model.addAttribute("requests", requests);
        model.addAttribute("username",username);

        return "others_blood_requests";  // Corresponding Thymeleaf template
    }

    @PostMapping("/accept/{id}")
    public String acceptBloodRequest1(@PathVariable("id") Long id, HttpSession session, Model model) {
        BloodRequestModel request = bloodRequestRepo.findById(id).orElse(null);
        if (request == null) {
            model.addAttribute("error", "Blood request not found.");
            return "error";  // Error page
        }

        String acceptedBy = (String) session.getAttribute("username");

        LifeBridgeModel user = userRepo.findByUsername(acceptedBy);  // Assuming `findByUsername` method exists in UserRepository
        String mobileNumber = user.getMobile();  // Assuming `mobileNumber` field exists in User entity

        // Mark the request as accepted and store who accepted it along with the mobile number
        request.setAccepted(true);
        request.setAcceptedBy(acceptedBy);
        request.setAcceptedByMobile(mobileNumber);
        bloodRequestRepo.save(request);  // Save the updated request

        // Mark the request as accepted and store who accepted it
        request.setAccepted(true);
        request.setAcceptedBy(acceptedBy);
        bloodRequestRepo.save(request);  // Save the updated request

        // Save the request history
        BloodRequestHistory history = new BloodRequestHistory(request, acceptedBy, mobileNumber, new Date());
        bloodRequestHistoryRepo.save(history);  // Save history record

        // Send an email to the requester
        String subjectRequester = "Your Blood Request Has Been Accepted";
        String bodyRequester = "<div><b>Your blood request for " + request.getRequestedQuantity() + " units of " + request.getBloodType() + " has been accepted by " + acceptedBy + ".</b></div>";
        emailService.sendEmail(request.getRequester().getEmail(), request.getRequester().getName(), subjectRequester, bodyRequester);

        // Send an email to the user/hospital who accepted the request
        String subjectAccepter = "You Have Accepted a Blood Request";
        String bodyAccepter = "<div><b>You have successfully accepted the blood request for " + request.getRequestedQuantity() + " units of " + request.getBloodType() + ".</b></div>";
        emailService.sendEmail(user.getEmail(), user.getName(), subjectAccepter, bodyAccepter);

        model.addAttribute("success", "Blood request accepted by " + acceptedBy);
        return "redirect:/blood-requests/others";  // Redirect back to the list of blood requests
    }
    
    @GetMapping("/history")
    public String viewAcceptedRequestsHistory(HttpSession session, Model model) {
        String acceptedBy = (String) session.getAttribute("username");

        // Fetch the history of accepted requests for the current user
        List<BloodRequestHistory> history = bloodRequestHistoryRepo.findByAcceptedBy(acceptedBy);

        model.addAttribute("history", history);
        model.addAttribute("username", acceptedBy);

        return "accepted_requests_history";  // Corresponding Thymeleaf template
    }

    @GetMapping("/history1")
    public String viewAcceptedRequestsHistory1(HttpSession session, Model model) {
        String acceptedBy = (String) session.getAttribute("username");

        // Fetch the history of accepted requests for the current user
        List<BloodRequestHistory> history = bloodRequestHistoryRepo.findByAcceptedBy(acceptedBy);

        model.addAttribute("history", history);
        model.addAttribute("username", acceptedBy);

        return "accepted_requests_history1";  // Corresponding Thymeleaf template
    }
}
