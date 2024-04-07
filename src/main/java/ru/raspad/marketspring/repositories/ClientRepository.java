package ru.raspad.marketspring.repositories;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Repository;
import ru.raspad.marketspring.dto.Client;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Repository
public class ClientRepository {
    private List<Client> clients;

    @PostConstruct
    public void init(){
        clients = new ArrayList<>(Arrays.asList(
                new Client(1L, "gashgash"),
                new Client(2L, "quincy"),
                new Client(3L, "cursed")
        ));
    }

    public Client findById(Long id){
        return clients.stream().filter(p -> p.getId().equals(id)).findFirst().orElseThrow(() -> new RuntimeException("student not found"));
    }

    public List<Client> getAllClients() {
        return clients;
    }

    public void addClient(Long id, String name){
        clients.add(new Client(id, name));
    }

    public void addClient(Client client) {
        clients.add(client);
    }
}
