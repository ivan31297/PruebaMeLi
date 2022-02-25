/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mutante.app.services.impl;

import com.mutante.app.dtos.response.StatDto;
import com.mutante.app.helpers.Mutant;
import com.mutante.app.helpers.Validations;
import com.mutante.app.models.DNAModel;
import com.mutante.app.repositories.DNARepository;
import com.mutante.app.services.DNAService;
import com.mutante.app.utils.enums.IsMutantEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Ivan
 * <p>Esta clase implementa los metodos del DNAService.
 */

@Service
public class DNAServiceImpl implements DNAService {

    DNARepository dnaRepository;

    // Se inyecta DNARepository a través del constructor.
    @Autowired
    public DNAServiceImpl(DNARepository dnaRepository) {
        this.dnaRepository = dnaRepository;
    }

    /**
     * <p> Este metodo se encarga de obtener el promedio entre los humanos y los mutantes.
     * <p> Obtiene todos los registros de la BD que se obtienen a través del metodo findAll y luego se aplica un filtro
     *     para contar el total de humanos y mutantes.
     * <p> Retorna a un objeto StatDto.
     *
     * @return
     */
    public StatDto getMutantRatio()
    {
        ArrayList<DNAModel> resultList = (ArrayList<DNAModel>) dnaRepository.findAll();
        long totalHuman = resultList.stream().filter(s -> s.getIsHuman().equals(IsMutantEnum.NO.name())).count();
        long totalMutant = resultList.stream().filter(s -> s.getIsHuman().equals(IsMutantEnum.YES.name())).count();
        double ratio = totalHuman > 0 ? ((float) totalMutant / (float) totalHuman) : 0;

        return StatDto.builder()
                .countHumanDna(totalHuman)
                .countMutantDna(totalMutant)
                .ratio(ratio)
                .build();
    }

    /**
     * <p> Este metodo se encarga de almacenar el DNA en la base de datos y retorna true sí es mutante.
     *
     * @param humanDna
     * @return
     */
    public boolean checkMutantDNA(List<String> humanDna)
    {
        String isHuman = "";

        if(Mutant.isMutant(humanDna.toArray(new String[humanDna.size()]))) {
            isHuman = IsMutantEnum.YES.name();
        } else {
            isHuman = IsMutantEnum.NO.name();
        }

        // Nivel 3
        DNAModel dnaM = new DNAModel();
        dnaM.setAdn(humanDna.toString());
        dnaM.setIsHuman(isHuman);
        dnaRepository.save(dnaM);
        return isHuman.equals(IsMutantEnum.YES.name()) ? Boolean.TRUE : Boolean.FALSE ;
    }

    /**
     * <p> Este metodo válida la secuencia de los DNA, sean de NxN.
     *
     * @param humanDna
     * @return
     */
    public boolean checkSequenceDNA(List<String> humanDna)
    {
        int contDNA = (int) humanDna
                        .stream()
                        .filter(s -> !Validations.validateMatrixSize(s.length(), humanDna.size()) || !Validations.validateLetter(s))
                        .count();

        return contDNA == 0;
    }
}
