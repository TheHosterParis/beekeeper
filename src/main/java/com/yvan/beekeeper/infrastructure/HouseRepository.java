package com.yvan.beekeeper.infrastructure;

import com.yvan.beekeeper.domain.House;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HouseRepository extends JpaRepository<House, Long> {
}