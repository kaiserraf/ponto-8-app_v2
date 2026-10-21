package com.kaiserraf.ponto8app.database.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "service_order")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class serviceOrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(nullable = true)
    private String description;
    @Column(name = "total_price", nullable = false)
    private float totalPrice;
    @Column(name = "pdf_path", nullable = true)
    private String pdfPath;
    @Column(name = "created_at", nullable = false)
    private Date createdAt;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private clientsEntity client;
    @ManyToOne
    @JoinColumn(name = "vehicle_id")
    private vehicleEntity vehicle;
    @ManyToOne
    @JoinColumn(name = "mechanic")
    private usersEntity mechanic;
}
