package org.example.registromagos.service;

import org.example.registromagos.domain.entity.Wizard;
import org.example.registromagos.repository.WizardRepository;
import org.example.registromagos.service.Impl.WizardServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class WizardServiceImplTest {

    @Mock
    private WizardRepository wizardRepository;

    @InjectMocks
    private WizardServiceImpl wizardService;

    private Wizard wizard;
    private UUID wizardId;

    @BeforeEach
    void setUp() {
        wizardId = UUID.randomUUID();
        wizard = new Wizard();
        wizard.setId(wizardId);
        wizard.setNombre("Severus Snape");
        wizard.setCasa("Slytherin");
        wizard.setPatronus("Doe");
        wizard.setEsMortifago(true);
    }

    // Test de guardar un mago con el repositorio
    @Test
    void createWizard_shouldCallRepositorySave() {
        wizardService.createwizard(wizard);

        verify(wizardRepository, times(1)).save(wizard);
    }

    // Test de obtener solo los mortífagos
    @Test
    void getDeatheaters_shouldReturnOnlyDeatheaters() {
        Wizard nonDeatheater = new Wizard();
        nonDeatheater.setEsMortifago(false);

        when(wizardRepository.findByEsMortifagoTrue()).thenReturn(List.of(wizard));

        List<Wizard> result = wizardService.getDeatheaters();

        assertThat(result).hasSize(1);
        assertThat(result.get(0).getEsMortifago()).isTrue();
        verify(wizardRepository).findByEsMortifagoTrue();
    }

    // Test de actualizar un mago
    @Test
    void updateWizard_shouldUpdateFieldsAndSave() {
        Wizard updatedData = new Wizard();
        updatedData.setNombre("Tom Riddle");
        updatedData.setCasa("Slytherin");
        updatedData.setPatronus(null);
        updatedData.setEsMortifago(true);

        when(wizardRepository.getWizardById(wizardId)).thenReturn(wizard);

        wizardService.updateWizard(wizardId, updatedData);

        assertThat(wizard.getNombre()).isEqualTo("Tom Riddle");
        assertThat(wizard.getPatronus()).isNull();
        verify(wizardRepository).save(wizard);
    }
}