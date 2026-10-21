package com.kaiserraf.ponto8app.database.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "refresh_tokens")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class refreshToken {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(nullable = false)
    private String token;
    @Column(name = "expires_at", nullable = false)
    private Date expiresAt;
    @Column(name = "created_at", nullable = false)
    private Date createdAt;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private usersEntity user;
}
