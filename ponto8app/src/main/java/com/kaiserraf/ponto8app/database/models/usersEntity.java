package com.kaiserraf.ponto8app.database.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.*;

@Entity
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class usersEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false, unique = true)
    private String email;
    @Column(nullable = false)
    private String passwordHash;
    @Column(nullable = false)
    private Date createdDate;
    @OneToMany(mappedBy = "user")
    private Set<refreshToken> refreshTokens = new HashSet<>();
    @OneToMany(mappedBy = "mechanic")
    private Set<serviceOrderEntity> serviceOrder = new HashSet<>();
}
