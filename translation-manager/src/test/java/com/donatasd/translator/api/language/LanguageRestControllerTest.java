package com.donatasd.translator.api.language;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.donatasd.translator.api.language.dto.LanguageCreateDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.web.servlet.MockMvc;

/**
 * Test for {@link LanguageRestController}
 *
 * @author Donatas Daubaras
 */
@SpringBootTest
@AutoConfigureMockMvc
@Rollback
public class LanguageRestControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @Test
  public void findAll() throws Exception {
    mockMvc.perform(get("/api/languages"))
        .andDo(print())
        .andExpect(status().isOk());
  }

  @Test
  public void findById() throws Exception {
    mockMvc.perform(get("/api/languages/1"))
        .andDo(print())
        .andExpect(status().isOk());
  }

  @Test
  public void deleteById() throws Exception {
    mockMvc.perform(delete("/api/languages/1"))
        .andDo(print())
        .andExpect(status().isOk());
  }

  @Test
  public void create() throws Exception {
    var createDTO = new LanguageCreateDTO();
    createDTO.setCode("en");
    ObjectMapper objectMapper = new ObjectMapper();
    var content = objectMapper.writeValueAsString(createDTO);
    mockMvc.perform(post("/api/languages").contentType(MediaType.APPLICATION_JSON).content(content))
        .andDo(print())
        .andExpect(status().isOk());
  }
}
