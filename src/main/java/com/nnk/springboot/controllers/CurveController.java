package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.service.CurvePointService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
public class CurveController {

    private final CurvePointService curvePointService;

    public CurveController(CurvePointService curvePointService) {
        this.curvePointService = curvePointService;
    }

    @RequestMapping("/curvePoint/list")
    public String home(Model model) {
        // HTML attend: ${curvePoints}
        model.addAttribute("curvePoints", curvePointService.findAll());
        return "curvePoint/list";
    }

    @GetMapping("/curvePoint/add")
    public String addBidForm(CurvePoint curvePoint, Model model) {
        // HTML attend: th:object="${curvePoint}"
        model.addAttribute("curvePoint", curvePoint);
        return "curvePoint/add";
    }

    @PostMapping("/curvePoint/validate")
    public String validate(@Valid CurvePoint curvePoint, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("curvePoint", curvePoint);
            return "curvePoint/add";
        }

        // curveId n'est pas dans le formulaire, donc on met une valeur par défaut
        if (curvePoint.getCurveId() == null) {
            curvePoint.setCurveId(1);
        }

        curvePointService.create(curvePoint);
        return "redirect:/curvePoint/list";
    }

    @GetMapping("/curvePoint/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        CurvePoint curvePoint = curvePointService.findById(id);
        model.addAttribute("curvePoint", curvePoint);
        return "curvePoint/update";
    }

    @PostMapping("/curvePoint/update/{id}")
    public String updateBid(@PathVariable("id") Integer id,
                            @Valid CurvePoint curvePoint,
                            BindingResult result,
                            Model model) {
        if (result.hasErrors()) {
            model.addAttribute("curvePoint", curvePoint);
            return "curvePoint/update";
        }

        // ✅ Solution 1 aussi en update (sécurité)
        if (curvePoint.getCurveId() == null) {
            curvePoint.setCurveId(1);
        }

        curvePointService.update(id, curvePoint);
        return "redirect:/curvePoint/list";
    }

    @GetMapping("/curvePoint/delete/{id}")
    public String deleteBid(@PathVariable("id") Integer id, Model model) {
        curvePointService.delete(id);
        return "redirect:/curvePoint/list";
    }
}
