package com.mutante.app.dtos.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Ivan
 * <p>Esta clase se encarga de modelar la respuesta del /stats.
 */
@Getter
@Setter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)      // mostrar solo los no nulos
public class StatDto {
    @JsonProperty("count_mutant_dna")
    private long countMutantDna;

    @JsonProperty("count_human_dna")
    private long countHumanDna;
    private double ratio;

    @JsonProperty("error_message")
    private String errorMessage;
}
