package com.kaiserraf.ponto8app.database.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "parts")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class partsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private float amount;
    @Column(name = "buy_value", nullable = false)
    private float buyValue;
    @Column(name = "sale_value", nullable = false)
    private float saleValue;
}
