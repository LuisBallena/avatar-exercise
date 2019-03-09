package com.scrap.avatar.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.junit.WireMockRule;
import com.scrap.avatar.entity.GenderEntity;
import com.scrap.avatar.entity.PersonEntity;
import java.util.Arrays;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * PersonApiTest.
 *
 * @author Luis Alonso Ballena Garcia
 */

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles(profiles = "test")
@AutoConfigureMockMvc
public class PersonApiTest {

    public static WireMockRule wireMockRule = new WireMockRule(8089);

    @BeforeClass
    public static void setUp() throws Exception {
        wireMockRule.start();
    }

    @AfterClass
    public static void down() throws Exception {
        wireMockRule.stop();
    }


    @Autowired
    private MockMvc mvc;

    @Test
    public void shouldGetList()
            throws Exception {
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get("/persona")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        String body = mvcResult.getResponse().getContentAsString();
        ObjectMapper objectMapper = new ObjectMapper();
        PersonEntity[] personEntities = objectMapper.readValue(body, PersonEntity[].class);
        long count = Arrays.asList(personEntities).stream().filter(p->p.getName().equalsIgnoreCase("WHEREVER1"))
                .count();
        Assert.assertEquals(1, count);
    }

    @Test
    public void shouldCreatePerson() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        PersonEntity personEntity = new PersonEntity();
        personEntity.setName("Quetal");
        personEntity.setGender(GenderEntity.MALE);
        personEntity.setPlanet("Alderaan");
        // por motivos practicos, se pega el recurso como cadena
        wireMockRule.stubFor(WireMock.get(WireMock.urlPathEqualTo("/planets"))
                .willReturn(WireMock.aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody("{\n" +
                                "  \"next\": null,\n" +
                                "  \"previous\": null,\n" +
                                "  \"results\": [\n" +
                                "    {\n" +
                                "      \"name\": \"Alderaan\"\n" +
                                "    }\n" +
                                "  ]\n" +
                                "}")));

        mvc.perform(MockMvcRequestBuilders.post("/persona")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsBytes(personEntity)))
                .andExpect(status().isCreated());
    }


    @Test
    public void shouldUpdatePerson() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        PersonEntity personEntity = new PersonEntity();
        personEntity.setName("Se ha cambiado");
        personEntity.setGender(GenderEntity.MALE);
        personEntity.setPlanet("Alderaan");
        // por motivos practicos, se pega el recurso como cadena
        wireMockRule.stubFor(WireMock.get(WireMock.urlPathEqualTo("/planets"))
                .willReturn(WireMock.aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody("{\n" +
                                "  \"next\": null,\n" +
                                "  \"previous\": null,\n" +
                                "  \"results\": [\n" +
                                "    {\n" +
                                "      \"name\": \"Alderaan\"\n" +
                                "    }\n" +
                                "  ]\n" +
                                "}")));

        mvc.perform(MockMvcRequestBuilders.put("/persona/2")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsBytes(personEntity)))
                .andExpect(status().isAccepted());
    }

    @Test
    public void shouldDeletePerson() throws Exception {
        // por motivos practicos, se pega el recurso como cadena
        mvc.perform(MockMvcRequestBuilders.delete("/persona/3")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }



}
