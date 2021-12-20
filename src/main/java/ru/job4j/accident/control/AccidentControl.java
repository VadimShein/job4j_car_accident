package ru.job4j.accident.control;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;
import ru.job4j.accident.repository.AccidentJdbcTemplate;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;

@Controller
public class AccidentControl {
    private final AccidentJdbcTemplate service;

    public AccidentControl(AccidentJdbcTemplate service) {
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
        Collection<AccidentType> types = service.getAllTypes();
        Collection<Rule> rules = service.getAllRules();
        model.addAttribute("types", types);
        model.addAttribute("rules", rules);
        return "create";
    }

    @GetMapping(value = "/edit")
    public String edit(Model model, @RequestParam("itemId") String itemId) {
        Accident accident = service.get(Integer.parseInt(itemId));
        model.addAttribute(accident);
        Collection<AccidentType> types = service.getAllTypes();
        Collection<Rule> rules = service.getAllRules();
        model.addAttribute("types", types);
        model.addAttribute("rules", rules);
        return "edit";
    }
}