package com.payment.cashfree.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RedirectController {

    @GetMapping("/")
    public String redirectToCheckout() {
        return "redirect:/checkout";
    }
}
