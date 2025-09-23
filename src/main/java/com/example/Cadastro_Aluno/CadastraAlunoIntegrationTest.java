package com.example.Cadastro_Aluno;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static java.io.IO.print;
import static org.springframework.http.RequestEntity.post;
import static org.springframework.web.servlet.function.ServerResponse.status;

class CadastraAlunoIntegrationTest{
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;
    @Test
    void deveCadastrarAlunoValido() throws Exception{
        var dto = new CadastroAlunodto( null, "Jakson Santos",20, "Analise Desenvolvimento");

        mockMvc.perform(post("/cadastrosAluno"))
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dto))
                .andDo(print());
        .andExpect(status().isOk())
                .andExpect(jsonPanth("$.nome").value("João Silva"));

    }
    @Test
   public void deveRetornarErroSeIdadeForInvalida() throws Exception{
        var dto = new C

    }
}