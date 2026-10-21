package com.kaiserraf.ponto8app.database.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "order_labor")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class orderLaborEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(nullable = false)
    private float amount;
    @Column(name = "total_price", nullable = false)
    private float totalPrice;
    @ManyToOne
    @JoinColumn(name = "service_order_id")
    private serviceOrderEntity serviceOrder;
    @ManyToOne
    @JoinColumn(name = "labor_id")
    private laborEntity labor;
}
