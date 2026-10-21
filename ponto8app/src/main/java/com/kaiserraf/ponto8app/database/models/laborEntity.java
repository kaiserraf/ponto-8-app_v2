package com.kaiserraf.ponto8app.database.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "labor")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class laborEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(nullable = false)
    private String name;
}
