package org.example.registromagos.service;

import org.example.registromagos.domain.entity.Wizard;

import java.util.List;
import java.util.UUID;

public interface WizardService {
    void createwizard(Wizard wizard);
    List<Wizard> getAllWizards();
    List<Wizard> getDeatheaters();
    void updateWizard(UUID id, Wizard wizard);
    List<Wizard> getByPatronus(String patronus);
    Wizard deleteWizard(UUID id);
}
