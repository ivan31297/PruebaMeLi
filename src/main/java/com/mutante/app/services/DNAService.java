package com.mutante.app.services;

import com.mutante.app.dtos.response.StatDto;

import java.util.List;

/**
 * @author Ivan
 * <p>Interface que implementa 2 metodos de tipo StatDto y boolean.
 */
public interface DNAService {
    StatDto getMutantRatio();

    boolean checkMutantDNA(List<String> HumanDna);

    boolean checkSequenceDNA(List<String> HumanDna);
}
