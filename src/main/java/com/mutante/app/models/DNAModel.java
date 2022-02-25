/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mutante.app.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 *
 * @author Ivan
 * <p>Persitencia de datos
 */

@Entity
@Table(name = "dna_records")
@Getter
@Setter
public class DNAModel
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private int id;

    @Column(length=100)
    private String adn;

    @Column(length=3)
    private String isHuman;
}
