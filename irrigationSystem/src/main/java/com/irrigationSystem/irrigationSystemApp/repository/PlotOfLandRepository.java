package com.irrigationSystem.irrigationSystemApp.repository;

import com.irrigationSystem.irrigationSystemApp.entity.PlotOfLandEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PlotOfLandRepository extends JpaRepository<PlotOfLandEntity, Long> {

    @Query(nativeQuery = true, value = "SELECT * FROM plot_of_land WHERE irrigated = 'N'")
    List<PlotOfLandEntity> findPlotsNeedIrrigation();

}
