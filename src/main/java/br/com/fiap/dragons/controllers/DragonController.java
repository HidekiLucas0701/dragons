package br.com.fiap.dragons.controllers;

import br.com.fiap.dragons.models.Dragon;
import br.com.fiap.dragons.services.DragonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/dragons")
public class DragonController {

    private final Logger log = (Logger) LoggerFactory.getLogger(getClass());

    @Autowired
    private DragonService service;

    @GetMapping
    public ResponseEntity<List<Dragon>> listDragons() {
        log.info("Listando todos os dragões");
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(service.listDragons());
    }

    @PostMapping
    public ResponseEntity<Dragon> createDragon(@RequestBody Dragon dragon) {
        log.info("Criando dragão...");
        var dragons = service.createDragon(dragon);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(dragons);

    }

    @GetMapping("/{id}")
    public ResponseEntity<Dragon> getDragonById(@PathVariable Integer id){
        log.info("Buscando dragão com id {}", id);
        var optionalDragon = service.getDragonById(id);
        if(optionalDragon.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(optionalDragon.get());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDragonById(@PathVariable Integer id){
        service.deleteDragonById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Dragon> updateDragon(@PathVariable Integer id, @RequestBody Dragon dragon){
        Dragon newDragon = service.updateDragon(id, dragon);
        return ResponseEntity.ok(newDragon);
    }



}
