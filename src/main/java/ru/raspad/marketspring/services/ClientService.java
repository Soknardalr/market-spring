package ru.raspad.marketspring.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.raspad.marketspring.dto.Client;
import ru.raspad.marketspring.repositories.ClientRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClientService {
    private final ClientRepository repository;

    public Client getClient(Long id){
        return repository.findById(id);
    }

    public List<Client> getAllClients(){
        return repository.getAllClients();
    }

    public void addClient(Long id, String name){
        repository.addClient(id, name);
    }

    public void addClient(Client client) {
        repository.addClient(client);
    }
}
