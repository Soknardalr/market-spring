package ru.raspad.marketspring.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.raspad.marketspring.dto.Client;
import ru.raspad.marketspring.services.ClientService;

@Controller
@RequiredArgsConstructor
public class MainController {
    private final ClientService service;

    @GetMapping("/page")
    public String page(Model model, @RequestParam Long id) {
        model.addAttribute("client", service.getClient(id));
        return "index.html";
    }


    @GetMapping("/clients")
    public String getAllStudents(Model model){
        model.addAttribute("clients", service.getAllClients());
        return "clients.html";
    }

    @GetMapping("/client/add")
    @ResponseBody
    public void addClient(Long id, String name){
        service.addClient(id, name);
    }
    @GetMapping("/show_form")
    public String form(){
        return "simpleForm.html";
    }

    @PostMapping("/client/add")
    public void postClient(@RequestBody Client client){
        service.addClient(client);
    }

}
