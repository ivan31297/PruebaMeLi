/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mutante.app.controllers;

import com.mutante.app.dtos.request.DNA;
import com.mutante.app.dtos.response.StatDto;
import com.mutante.app.services.DNAService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author Ivan
 * <p>Esta clase se encarga de la logíca de negocio y administra las solicitudes del servicio rest.
 */

@RestController
@Slf4j
@RequestMapping()
public class DNAController {

    DNAService dnaService;

    // Se inyecta DNAService a través del constructor.
    @Autowired
    public DNAController(DNAService dnaService) {
        this.dnaService = dnaService;
    }

    /*
     * Se mapea a través de un petición GET para acceder a la función del metodo asignado.
     * Devuelve un Objeto de datos para visualizarlos a través de un formato JSON.
     */
    @GetMapping("/stats")
    public ResponseEntity<StatDto> statsDNA() {
        StatDto statDto = dnaService.getMutantRatio();
        return new ResponseEntity<StatDto>(statDto, HttpStatus.OK);
    }

    /*
     * Se mapea a través de un petición POST para acceder a la función del metodo asignado.
     * Se encarga de recibir el DNA y devuelve un respuesta HTTP 200 o 403.
     */
    @PostMapping("/mutant/")
    public ResponseEntity<DNA> checkMutantDNA(@Valid @RequestBody DNA dna) {
        HttpStatus response;

        if (dnaService.checkSequenceDNA(dna.getDna())) {
            if (dnaService.checkMutantDNA(dna.getDna())) {
                response = HttpStatus.OK;
            }else{
                response= HttpStatus.FORBIDDEN;
            }
        }else{
            response = HttpStatus.BAD_REQUEST;
        }

        return new ResponseEntity<>(response);
    }
}
