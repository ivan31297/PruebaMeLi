package com.mutante.app.controllers;

import com.mutante.app.MutantApplication;
import com.mutante.app.dtos.response.StatDto;
import com.mutante.app.models.DNAModel;
import com.mutante.app.services.DNAService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = MutantApplication.class)
@AutoConfigureMockMvc
@TestPropertySource(
        locations = "classpath:application.properties")
public class DNAControllerTest {

    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    @MockBean
    private DNAService dnaService;

    @Before
    public void setUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    @Test
    public void checkMutantDNATest() throws Exception {

        Mockito.when(dnaService.checkSequenceDNA(ArgumentMatchers.anyList())).thenReturn(true);
        Mockito.when(dnaService.checkMutantDNA(ArgumentMatchers.anyList())).thenReturn(true);
        mockMvc.perform(MockMvcRequestBuilders.post("/mutant/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\n" +
                                "    \"dna\":[\"ATGCGA\",\n" +
                                "            \"CAGTGC\",\n" +
                                "            \"TTATGT\",\n" +
                                "            \"AGAAGG\",\n" +
                                "            \"CCCCTA\",\n" +
                                "            \"TCACTG\"]\n" +
                                "}"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void checkMutantDNANotMutantTest() throws Exception {

        Mockito.when(dnaService.checkSequenceDNA(ArgumentMatchers.anyList())).thenReturn(true);
        Mockito.when(dnaService.checkMutantDNA(ArgumentMatchers.anyList())).thenReturn(false);
        mockMvc.perform(MockMvcRequestBuilders.post("/mutant/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\n" +
                                "    \"dna\":[\"ATGCGA\",\"CAGTGC\",\"TTATGT\",\"AGAAGG\",\"CCCCTA\",\"TCACTG\"]\n" +
                                "}"))
                .andExpect(MockMvcResultMatchers.status().isForbidden());
    }

    @Test
    public void checkMutantDNAWithoutRequestTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/mutant/")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    public void statsDNATest() throws Exception {
        Mockito.when(dnaService.getMutantRatio()).thenReturn(buildStatDto());
        mockMvc.perform(MockMvcRequestBuilders.get("/stats")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());

    }

    private StatDto buildStatDto() {
        return StatDto.builder()
                .countHumanDna(2)
                .countMutantDna(10)
                .ratio(5)
                .build();
    }
}
