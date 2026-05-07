package org.example.registromagos.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Table(name = "wizard")
@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class Wizard {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private UUID id;

    @Column(name = "name")
    private String nombre;

    @Column(name = "house")
    private String casa;

    @Column(name = "patronus")
    private String patronus;

    @Column(name = "isDeatheater")
    private Boolean esMortifago;
}
