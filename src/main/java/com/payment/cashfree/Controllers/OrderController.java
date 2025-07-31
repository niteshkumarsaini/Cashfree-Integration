package com.payment.cashfree.Controllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.payment.cashfree.Models.Order;
import com.payment.cashfree.Models.OrderResponse;
import com.payment.cashfree.Service.OrderService;

import org.springframework.ui.Model;

@Controller
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/create")
    public ResponseEntity<?> generateOrder(@ModelAttribute Order order, Model model) {
        OrderResponse orderResponse = orderService.createOrder(order);

        // Redirecting to hosted checkout
        return ResponseEntity.ok(orderResponse);
    }
    
    
  

    @GetMapping("/checkout")
    public String checkoutPage(Model model) {
        model.addAttribute("order", new Order());
        return "checkout"; // Thymeleaf page
    }
}
