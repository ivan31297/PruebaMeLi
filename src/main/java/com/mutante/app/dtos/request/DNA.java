/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mutante.app.dtos.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;

/**
 *
 * @author Ivan
 * <p>Esta clase se encarga de modelar el DNA.
 */
@Getter
@Setter
@NoArgsConstructor
public class DNA
{
    @NotNull
    private ArrayList<String> dna;
}
