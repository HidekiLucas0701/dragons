package br.com.fiap.dragons.services;

import br.com.fiap.dragons.models.Dragon;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class DragonService {

    private List<Dragon> repository = new ArrayList<>();

    public List<Dragon> listDragons(){
        return repository;
    }

    public Optional<Dragon> getDragonById(UUID id){
        return findDragonById(id);
    }

    public Dragon createDragon(Dragon dragon){
        dragon.setId(generateId());
        repository.add(dragon);
        return dragon;
    }

    public void deleteDragonById(UUID id){
        repository.remove(findDragonById(id).get());
    }

    public Dragon updateDragon(UUID id, Dragon dragon){
        deleteDragonById(id);
        dragon.setId(id);
        repository.add(dragon);

        return dragon;
    }

    private UUID generateId(){
        return UUID.randomUUID();
    }

    private Optional<Dragon> findDragonById(UUID id){
        var optionalDragon = repository.stream()
            .filter(d -> d.getId().equals(id))
            .findFirst();

        if(optionalDragon.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Dragão com id " + id + " não encontrado.");
        }
        return optionalDragon;
    }
}
