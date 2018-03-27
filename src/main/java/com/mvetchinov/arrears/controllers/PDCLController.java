package com.mvetchinov.arrears.controllers;

import com.mvetchinov.arrears.repositories.PDCLRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/pdcl")
public class PDCLController {

    private PDCLRepository pdclRepository;

    public PDCLController(PDCLRepository pdclRepository) {
        this.pdclRepository = pdclRepository;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String getIndex(Model model){
        model.addAttribute("pdcl", pdclRepository.findAll());
        return "pdcl";
    }
}
