package com.mvetchinov.arrears.controllers;

import com.mvetchinov.arrears.model.Deal;
import com.mvetchinov.arrears.repositories.DealRepository;
import com.mvetchinov.arrears.repositories.PDCLRepository;
import com.mvetchinov.arrears.services.Arrears;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Optional;

@Controller
@RequestMapping("/deals")
public class DealController {
    private final DealRepository dealRepository;
    private final PDCLRepository pdclRepository;
    private final Arrears arrears;

    public DealController(DealRepository dealRepository, PDCLRepository pdclRepository, Arrears arrears) {
        this.dealRepository = dealRepository;
        this.pdclRepository = pdclRepository;
        this.arrears = arrears;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String getDeals(Model model) {
        model.addAttribute("deals", dealRepository.findAll());
        return "deals";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id:[\\d]+}")
    public String getDeal(Model model, @PathVariable Long id) {
        Optional<Deal> deal = dealRepository.findById(id);
        if (!deal.isPresent()) throw new IllegalArgumentException("No deals was found by id " + id);
        model.addAttribute("deal", deal.get());
        model.addAttribute("indebtedness", arrears.getIndebtedness(deal.get()));

        return "deal";
    }
}
