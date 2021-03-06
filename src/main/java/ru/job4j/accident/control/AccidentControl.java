package ru.job4j.accident.control;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;
import ru.job4j.accident.service.AccidentService;

import javax.servlet.http.HttpServletRequest;

@Controller
public class AccidentControl {
    private final AccidentService service;

    public AccidentControl(AccidentService service) {
        this.service = service;
    }

    @PostMapping("/save")
    public String create(@ModelAttribute Accident accident, HttpServletRequest req) {
        String[] ids = req.getParameterValues("rIds");
        service.add(accident, ids);
        return "redirect:/";
    }

    @GetMapping("/create")
    public String create(Model model) {
        Iterable<AccidentType> types = service.getAllTypes();
        Iterable<Rule> rules = service.getAllRules();
        model.addAttribute("types", types);
        model.addAttribute("rules", rules);
        model.addAttribute("userName", SecurityContextHolder.getContext().getAuthentication().getName());
        return "create";
    }

    @GetMapping(value = "/edit")
    public String edit(Model model, @RequestParam("itemId") String itemId) {
        Accident accident = service.get(Integer.parseInt(itemId));
        model.addAttribute(accident);
        Iterable<AccidentType> types = service.getAllTypes();
        Iterable<Rule> rules = service.getAllRules();
        model.addAttribute("types", types);
        model.addAttribute("rules", rules);
        model.addAttribute("userName", SecurityContextHolder.getContext().getAuthentication().getName());
        return "edit";
    }
}