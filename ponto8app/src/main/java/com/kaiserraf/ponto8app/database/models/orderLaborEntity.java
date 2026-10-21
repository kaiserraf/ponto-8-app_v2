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
    private float amount;
    private float totalPrice;
    @OneToOne
    @JoinColumn(name = "service_order_id")
    private serviceOrderEntity serviceOrder;
    @OneToOne
    @JoinColumn(name = "id_labor")
    private laborEntity labor;
}
