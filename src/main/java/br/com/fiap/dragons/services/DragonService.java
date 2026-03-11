package br.com.fiap.dragons.services;

import br.com.fiap.dragons.models.Dragon;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;

@Service
public class DragonService {

    private List<Dragon> repository = new ArrayList<>();

    public List<Dragon> listDragons(){
        return repository;
    }

    public Optional<Dragon> getDragonById(Integer id){
        return findDragonById(id);
    }

    public Dragon createDragon(Dragon dragon){
        dragon.setId(generateId());
        repository.add(dragon);
        return dragon;
    }

    public void deleteDragonById(Integer id){
        repository.remove(findDragonById(id).get());
    }

    public Dragon updateDragon(Integer id, Dragon dragon){
        deleteDragonById(id);
        dragon.setId(id);
        repository.add(dragon);

        return dragon;
    }

    private Integer generateId(){
        return Math.abs(new Random().nextInt());
    }

    private Optional<Dragon> findDragonById(Integer id){
        var optionalDragon = repository.stream()
            .filter(d -> d.getId().equals(id))
            .findFirst();

        if(optionalDragon.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Dragão com id " + id + " não encontrado.");
        }
        return optionalDragon;
    }
}
