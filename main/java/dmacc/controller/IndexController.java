package dmacc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    @GetMapping("/index")
    public String showIndexPage() {
        return "index";
    }

    @GetMapping("/Catalog")
    public String showCatalogPage() {
        return "Catalog"; 
    }
    @GetMapping("/Register")
    public String showRegisterPage() {
        return "Register"; 
    }
    @GetMapping("/Review")
    public String showReviewPage() {
        return "Review"; 
    }
    @GetMapping("/Contact")
    public String showContactPage() {
        return "Contact"; 
    }
    @GetMapping("/PcGames")
    public String showPcGames() {
        return "PcGames";
    }
}
