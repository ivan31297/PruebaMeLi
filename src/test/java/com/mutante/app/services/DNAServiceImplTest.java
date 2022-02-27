package com.mutante.app.services;

import com.mutante.app.MutantApplication;
import com.mutante.app.dtos.response.StatDto;
import com.mutante.app.models.DNAModel;
import com.mutante.app.repositories.DNARepository;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;


@SpringBootTest(
        classes = MutantApplication.class
)
public class DNAServiceImplTest {

    @MockBean
    private DNARepository dnaRepository;

    @Autowired
    DNAService dnaService;

    @Test
    public void getMutantRatioTest() {
        Mockito.when(dnaRepository.findAll()).thenReturn(buildDNAModelArray());
        StatDto statDto = dnaService.getMutantRatio();

        Assert.assertEquals(statDto.getCountHumanDna(), 2);
        Assert.assertEquals(statDto.getCountMutantDna(), 1);
    }

    @Test
    public void checkMutantDNAIsMutantTest() {
        List<String> mutantAdn = List.of("ATGCGA", "CAGTGC", "TTATGT", "AGAAGG", "CCCCTA", "TCACTG");
        boolean result = dnaService.checkMutantDNA(mutantAdn);
        Assert.assertTrue(result);
    }

    @Test
    public void checkMutantDNAIsHumanTest() {
        List<String> mutantAdn = List.of(
                "ATGCGA",
                "CAGTGC",
                "TTATGT",
                "AGAACG",
                "CCCGTA",
                "TCACTG");
        boolean result = dnaService.checkMutantDNA(mutantAdn);
        Assert.assertFalse(result);
    }

    @Test
    public void checkSequenceDNATrueTest() {
        List<String> mutantAdn = List.of("ATGCGA", "CAGTGC", "TTATGT", "AGAAGG", "CCCCTA", "TCACTG");
        boolean result = dnaService.checkSequenceDNA(mutantAdn);
        Assert.assertTrue(result);
    }

    @Test
    public void checkSequenceDNAFalseTest() {
        List<String> mutantAdn = List.of("ATGCGA", "CAGTG", "TTAUGT", "AGAaGG", "CCuCTA", "TCACTG");
        boolean result = dnaService.checkSequenceDNA(mutantAdn);
        Assert.assertFalse(result);
    }

    private ArrayList<DNAModel> buildDNAModelArray() {
        ArrayList<DNAModel> mutantList = new ArrayList<>();
        DNAModel dnaModel1 = new DNAModel();
        dnaModel1.setAdn("[ATGCGA, CAGTGC, TTATGT, AGAAGG, CCCCTA, TCACTG]");
        dnaModel1.setIsHuman("YES");
        dnaModel1.setId(1);
        mutantList.add(dnaModel1);

        DNAModel dnaModel2 = new DNAModel();
        dnaModel2.setAdn("[ATGCGA, CAGTGC, TTATGT, AGAAGG, CCCCTA, TCACTG]");
        dnaModel2.setIsHuman("YES");
        dnaModel2.setId(1);
        mutantList.add(dnaModel2);

        DNAModel dnaModel3 = new DNAModel();
        dnaModel3.setAdn("[ATGCGA, CAGTGC, TTATGT, AGAAGG, CCCCTA, TCACTG]");
        dnaModel3.setIsHuman("NO");
        dnaModel3.setId(1);
        mutantList.add(dnaModel3);

        return mutantList;
    }
}
