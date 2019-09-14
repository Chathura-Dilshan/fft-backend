package com.fft.farm.repository;

import com.fft.farm.entity.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface LocationRepository extends JpaRepository<Location, Integer> {
    Optional<Location> findByLocationName(String locationName);

    List<Location> findByStatusAndUserSeq(Integer statusSeq, Integer userSeq);
}
