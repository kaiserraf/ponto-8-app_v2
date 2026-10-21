package com.kaiserraf.ponto8app.database.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "vehicle")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class vehicleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(name = "vehicle_model", nullable = false)
    private String vehicleModel;
    @Column(name = "vehicle_brand", nullable = false)
    private String vehicleBrand;
    @Column(nullable = false)
    private Integer year;
    @Column(nullable = false, unique = true)
    private String chassi;
    @Column(nullable = false, unique = true)
    private String plate;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private clientsEntity client;
    @OneToMany(mappedBy = "vehicle")
    private Set<serviceOrderEntity> serviceOrder = new HashSet<>();
}
