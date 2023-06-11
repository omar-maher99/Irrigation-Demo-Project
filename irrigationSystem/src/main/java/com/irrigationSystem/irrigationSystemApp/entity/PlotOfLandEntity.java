package com.irrigationSystem.irrigationSystemApp.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "plot_of_land", schema = "irrigation_system")
public class PlotOfLandEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "location")
    private String location;

    @Column(name = "width")
    private Integer width;

    @Column(name = "length")
    private Integer length;

    @Column(name = "soil_type")
    private String soil_type;

    @CreationTimestamp
    @Column(name = "created_date")
    private Instant createdDate;

    @UpdateTimestamp
    @Column(name = "updated_date")
    private Instant updatedDate;

    @Column(name = "irrigated")
    private String irrigated;

    @Column(name = "irrigate_from")
    private Integer irrigate_from;

    @Column(name = "irrigate_to")
    private Integer irrigate_to;
}
