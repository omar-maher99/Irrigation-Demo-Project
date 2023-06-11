package com.irrigationSystem.irrigationSystemApp.dto;
import lombok.Data;

import java.time.Instant;

@Data
public class PlotOFLandDto {
    private long id;

    private String name;

    private String location;

    private Integer width;

    private Integer length;

    private String soil_type;

    private Instant createdDate;

    private Instant updatedDate;

    private String irrigated = "Y";

    private Integer irrigate_from;

    private Integer irrigate_to;
}
