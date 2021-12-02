package ru.job4j.accident.control;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.service.AccidentService;

import java.util.Date;

@Controller
public class AccidentControl {
    private final AccidentService service;

    public AccidentControl(AccidentService service) {
        this.service = service;
    }

    @PostMapping("/save")
    public String create(@ModelAttribute Accident accident) {
        accident.setCreated(new Date(System.currentTimeMillis()));
        service.add(accident);
        return "redirect:/";
    }

    @GetMapping("/create")
    public String create() {
        return "create";
    }

    @GetMapping(value = "/edit")
    public String edit(Model model, @RequestParam("itemId") String itemId) {
        Accident accident = service.get(Integer.parseInt(itemId));
        model.addAttribute(accident);
        return "edit";
    }
}