package com.codewithmosh.store.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "blacklisted_tokens")
public class BlacklistedToken {
    @Id
    @Size(max = 36)
    @Column(name = "token_id", nullable = false, length = 36)
    private String tokenId;

    @NotNull
    @Column(name = "expires_at", nullable = false)
    private Instant expiresAt;


}