package com.ganie.Train.repo;

import com.ganie.Train.entity.Train;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository

public interface TrainRepository extends JpaRepository<Train,Long> {
}
