package ru.ntiteam.antufievsemen.web.contoller;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import ru.ntiteam.antufievsemen.entity.Lord;
import ru.ntiteam.antufievsemen.entity.Planet;
import ru.ntiteam.antufievsemen.repository.LordRepository;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureTestDatabase
@AutoConfigureMockMvc
public class LordRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private LordRepository lordRepository;

    @BeforeEach
    public void resetMocks() {
        Mockito.reset(lordRepository);
    }

    @Test
    public void getLordsApiTest() throws Exception {
        Lord lord1 = new Lord("Mix", 11L);
        lord1.setId(1L);
        Lord lord2 = new Lord("Kok", 12312L);
        lord2.setId(2L);
        List<Lord> lordList = new ArrayList<>();
        lordList.add(lord1);
        lordList.add(lord2);
        Mockito.when(lordRepository.findAll()).thenReturn(lordList);
        String jsonRequest = "[{\"id\":1,\"name\":\"Mix\",\"years\":11}," +
                              "{\"id\":2,\"name\":\"Kok\",\"years\":12312}]";
        mockMvc.perform(get("/lord"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(jsonRequest));
    }

    @Test
    public void getLordsNullApiTest() throws Exception{
        Mockito.when(lordRepository.findAll()).thenReturn(null);
        mockMvc.perform(get("/lord"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(""));
    }

    @Test
    public void getLordApiTest() throws Exception {
        Lord lord = new Lord("Max", 11L);
        lord.setId(1L);
        Optional<Lord> lordOptional = Optional.of(lord);
        String jsonResponse = "";
        Mockito.when(lordRepository.findById(Mockito.eq(lord.getId()))).thenReturn(lordOptional);
        mockMvc.perform(get(String.format("/lord/%s", lord.getId())))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.name", is("Max")))
                .andExpect(jsonPath("$.years", is(11)));
    }

    @Test
    public void getLordNullApiTest() throws Exception {
        Long planetId = 1L;
        mockMvc.perform(get(String.format("/lord/%s", planetId)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(""));
    }

    @Test
    public void addLordApiTest() throws Exception {
        Lord lord = new Lord("Mix", 11L);
        String jsonRequest = "{\"name\":\"Mix\",\"years\":11}";
        Mockito.when(lordRepository.saveAndFlush(Mockito.eq(lord))).thenReturn(lord);
        mockMvc.perform(post("/lord")
                .contentType(MediaType.APPLICATION_JSON_VALUE).content(jsonRequest).characterEncoding(StandardCharsets.UTF_8.toString()))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is("Mix")))
                .andExpect(jsonPath("$.years", is(11)));
    }

    @Test
    public void deleteLordApiTest() throws Exception {
        Lord lord = new Lord("Max", 11L);
        lord.setId(1L);
        Optional<Lord> lordOptional = Optional.of(lord);
        Mockito.when(lordRepository.findById(Mockito.eq(lord.getId()))).thenReturn(lordOptional);
        mockMvc.perform(delete(String.format("/lord/%s", lord.getId())))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string("true"));

    }

    @Test
    public void deleteLordNullApiTest() throws Exception {
        Mockito.when(lordRepository.findById(Mockito.eq(1L))).thenReturn(Optional.empty());
        mockMvc.perform(delete(String.format("/lord/%s", 1L)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string("false"));
    }

    @Test
    public void updateLordApiTest() throws Exception {
        Lord lord = new Lord("Mix", 11L);
        lord.setId(1L);
        String jsonRequest = "{\"id\":1,\"name\":\"Mix\",\"years\":11}";
        Mockito.when(lordRepository.saveAndFlush(Mockito.eq(lord))).thenReturn(lord);
        mockMvc.perform(put(String.format("/lord/%s", 1L))
                .contentType(MediaType.APPLICATION_JSON_VALUE).content(jsonRequest).characterEncoding(StandardCharsets.UTF_8.toString()))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(jsonRequest));
    }

    @Test
    public void getTopTenYoungestLords() throws Exception {
        Lord lord1 = new Lord("Mix", 11L);
        lord1.setId(1L);
        Lord lord2 = new Lord("Kok", 12312L);
        lord2.setId(2L);
        Lord lord3 = new Lord("Lan", 500L);
        lord3.setId(3L);
        List<Lord> lordList = new ArrayList<>();
        lordList.add(lord1);
        lordList.add(lord2);
        lordList.add(lord3);
        String jsonResponse = "[{\"id\":1,\"name\":\"Mix\",\"years\":11}," +
                "{\"id\":3,\"name\":\"Lan\",\"years\":500}," +
                "{\"id\":2,\"name\":\"Kok\",\"years\":12312}]";
        Mockito.when(lordRepository.findAll()).thenReturn(lordList);
        mockMvc.perform(get("/lord/topTenYoungestLords"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(jsonResponse));
    }

    @Test
    public void getTopTenYoungestNullAPiTest() throws Exception {
        List<Lord> lordList = new ArrayList<>();
        Mockito.when(lordRepository.findAll()).thenReturn(lordList);
        mockMvc.perform(get("/lord/topTenYoungestLords"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string("[]"));
    }

    @Test
    public void getUselessLordsApiTest() throws Exception {
        Lord lord1 = new Lord("Mix", 11L);
        lord1.setId(1L);
        Lord lord2 = new Lord("Kok", 12312L);
        lord2.setId(2L);
        Lord lord3 = new Lord("Lan", 500L);
        lord3.setId(3L);
        Planet planet = new Planet("Mars", lord2);
        List<Lord> lordList = new ArrayList<>();
        lordList.add(lord1);
        lordList.add(lord3);
        String jsonResponse = "[{\"id\":1,\"name\":\"Mix\",\"years\":11}," +
                "{\"id\":3,\"name\":\"Lan\",\"years\":500}]";
        Mockito.when(lordRepository.findAll()).thenReturn(lordList);
        mockMvc.perform(get("/lord/topTenYoungestLords"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(jsonResponse));
    }
}
