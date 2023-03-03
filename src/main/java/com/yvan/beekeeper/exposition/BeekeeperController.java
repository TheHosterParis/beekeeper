package com.yvan.beekeeper.exposition;

import com.yvan.beekeeper.application.BeekeeperService;
import com.yvan.beekeeper.domain.House;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/beekeeper")
public class BeekeeperController {

    @Autowired
    BeekeeperService beekeeperService;

    Logger logger = LoggerFactory.getLogger(BeekeeperController.class);

    /**
     * @param id
     * @return
     */
    @GetMapping("/house")
    public Optional<House> getHouse(@RequestParam Long id) {
        logger.info("start of service getHouse");
        //mock final House house = new House(1L,"test");
        // Code pour récupérer l'objet House à partir de la base de données ou d'un autre emplacement
        final Optional<House> house = beekeeperService.getHouse(id);
        logger.info("getHouse service return {}", house);
        return house;
    }

    @PostMapping("/house")
    public void setHouse(@RequestBody House house) {
        logger.info("start of service setHouse", house);
        // Code pour enregistrer l'objet House dans la base de données ou un autre emplacement
        beekeeperService.saveHouse(house);
    }

    @DeleteMapping("/house")
    public void deleteHouse(@RequestParam Long id) {
        logger.info("start of service deleteHouse");
        // Code pour supprimer l'objet House dans la base de données ou un autre emplacement
        beekeeperService.deleteHouse(id);
    }

    @PutMapping("/house")
    public void updateHouse(@RequestParam Long id, @RequestBody House house) {
        logger.info("start of service updateHouse");
        // Code pour mettre à jour l'objet House dans la base de données ou un autre emplacement
        beekeeperService.updateHouse(id, house);
    }

    @GetMapping("/houses")
    public Iterable<House> getAllHouses() {
        logger.info("start of service getAllHouses");
        // Code pour récupérer tous les objets House à partir de la base de données ou d'un autre emplacement
        final Iterable<House> houses = beekeeperService.getAllHouses();
        logger.info("getAllHouses service return {}", houses);
        return houses;
    }

    @GetMapping("/houses/since")
    public Iterable<House> getAllHousesSince(@RequestParam String date) {
        logger.info("start of service getAllHousesSince");
        // Code pour récupérer tous les objets House à partir de la base de données ou d'un autre emplacement
        final Iterable<House> houses = beekeeperService.getAllHousesSince(date);
        logger.info("getAllHousesSince service return {}", houses);
        return houses;
    }
}