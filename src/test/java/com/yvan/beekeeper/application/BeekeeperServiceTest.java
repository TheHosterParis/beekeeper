package com.yvan.beekeeper.application;

import com.yvan.beekeeper.domain.House;
import com.yvan.beekeeper.infrastructure.HouseRepositoryJPA;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.mockito.Mockito.*;

class BeekeeperServiceTest {

    @Mock
    HouseRepositoryJPA houseRepository;

    @InjectMocks
    BeekeeperService beekeeperService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSaveHouse() {
        String name = "Bee House";
        
        House house = new House(1L, name, "date", "Yvan");
        when(houseRepository.save(any(House.class))).thenReturn(house);

        beekeeperService.saveHouse(house);

        verify(houseRepository, times(1)).save(any(House.class));
    }

    @Test
    void testGetHouse() {
        Long id = 1L;
        String name = "Bee House";
        
        House house = new House(1L, name, "date", "Yvan");
        when(houseRepository.findById(id)).thenReturn(Optional.of(house));

        Optional<House> result = beekeeperService.getHouse(id);

        Assertions.assertTrue(result.isPresent());
        Assertions.assertEquals(id, result.get().getId());
        Assertions.assertEquals(name, result.get().getName());
    }

    @Test
    void testDeleteHouse() {
        Long id = 1L;

        beekeeperService.deleteHouse(id);

        verify(houseRepository, times(1)).deleteById(id);
    }

    @Test
    void testUpdateHouse() {
        Long id = 1L;
        String name = "New Bee House";
        
        House house = new House(id, "Bee House", "date", "Yvan");
        when(houseRepository.findById(id)).thenReturn(Optional.of(house));

        beekeeperService.updateHouse(id, house);

        verify(houseRepository, times(1)).findById(id);
        verify(houseRepository, times(1)).save(house);
        Assertions.assertEquals(name, house.getName());
    }
}
