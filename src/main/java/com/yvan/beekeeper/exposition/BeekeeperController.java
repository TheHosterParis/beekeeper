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
        logger.info("start of service setHouse");
        // Code pour enregistrer l'objet House dans la base de données ou un autre emplacement
        beekeeperService.saveHouse(house.getOwner());
    }
}