package com.example.Cadastro_Aluno;

import com.example.Cadastro_Aluno.dto.cadastroAlunodto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;

@SpringBootTest
@AutoConfigurationMockMvc
public class AlunontegrationTest {
 @Autowired
 private MockMvc mockMvc;

 @Autowired
    private ObjectMapper objectMapper;

 @Test
    void deveCadastraroAlunovalido() throws  Exception{
     var dto = new cadastroAlunodto(
             null,
             "Cadastro de Aluno no curso tecnico de mectronica",

     )
 }
}
