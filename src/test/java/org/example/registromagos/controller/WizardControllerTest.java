package org.example.registromagos.controller;

import org.example.registromagos.domain.entity.Wizard;
import org.example.registromagos.service.Impl.WizardServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.UUID;

import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import tools.jackson.databind.ObjectMapper;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest(WizardController.class)
class WizardControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private WizardServiceImpl wizardService;

    @Autowired
    private ObjectMapper objectMapper;

    private Wizard wizard;
    private UUID wizardId;

    @BeforeEach
    void setUp() {
        wizardId = UUID.randomUUID();
        wizard = new Wizard();
        wizard.setId(wizardId);
        wizard.setNombre("Harry Potter");
        wizard.setCasa("Gryffindor");
        wizard.setPatronus("Stag");
        wizard.setEsMortifago(false);
    }

    // Crear un nuevo mago y recibirlo como respuesta
    @Test
    void createWizard_shouldReturn201WithBody() throws Exception {
        doNothing().when(wizardService).createwizard(any(Wizard.class));

        mockMvc.perform(post("/api/wizards")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(wizard)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.nombre").value("Harry Potter"))
                .andExpect(jsonPath("$.casa").value("Gryffindor"));
    }

    // Obtener la lista de mortífagos
    @Test
    void getDeatheaters_shouldReturn200WithList() throws Exception {
        Wizard deatheater = new Wizard();
        deatheater.setNombre("Bellatrix Lestrange");
        deatheater.setEsMortifago(true);

        when(wizardService.getDeatheaters()).thenReturn(List.of(deatheater));

        mockMvc.perform(get("/api/wizards/deatheaters"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nombre").value("Bellatrix Lestrange"))
                .andExpect(jsonPath("$[0].esMortifago").value(true));
    }

    // Eliminar un mago y obtenerlo como respuesta
    @Test
    void deleteWizard_shouldReturn200WithDeletedWizard() throws Exception {
        when(wizardService.deleteWizard(eq(wizardId))).thenReturn(wizard);

        mockMvc.perform(delete("/api/wizards/delete/{id}", wizardId)
                        .param("id", wizardId.toString()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre").value("Harry Potter"));
    }
}