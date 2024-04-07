package ru.raspad.marketspring.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.raspad.marketspring.dto.Client;
import ru.raspad.marketspring.services.ClientService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MainController {
    private final ClientService service;
    @GetMapping("/clients")
    public List<Client> getAllStudents(Model model){
        return service.getAllClients();
    }

    @GetMapping("/client/change_score")
    public void changeScore(@RequestParam Long clientId,@RequestParam Integer delta){
        service.changeScore(clientId, delta);
    }

    @PostMapping("/client/add")
    public void postClient(@RequestBody Client client){
        service.addClient(client);
    }

}
