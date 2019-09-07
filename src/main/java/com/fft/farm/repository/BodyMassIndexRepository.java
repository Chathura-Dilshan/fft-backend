package com.fft.farm.repository;

import com.fft.farm.entity.BodyMassIndex;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface BodyMassIndexRepository extends JpaRepository<BodyMassIndex, Integer> {

    List<BodyMassIndex> findByStatus(Integer statusSeq);

    Optional<BodyMassIndex> findByUserSeqAndStatus(Integer userSeq, Integer statusSeq);
}
