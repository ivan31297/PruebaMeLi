package com.mutante.app.dtos.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Ivan
 * <p>Esta clase se encarga de modelar la respuesta del /stats.
 */
@Getter
@Setter
@Builder
public class StatDto {
    @JsonProperty("count_mutant_dna")
    private long countMutantDna;

    @JsonProperty("count_human_dna")
    private long countHumanDna;
    private double ratio;
}
