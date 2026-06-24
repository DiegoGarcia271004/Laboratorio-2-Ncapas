package org.example.registromagos.service.Impl;

import lombok.RequiredArgsConstructor;
import org.example.registromagos.domain.entity.Wizard;
import org.example.registromagos.repository.WizardRepository;
import org.example.registromagos.service.WizardService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class WizardServiceImpl implements WizardService {
    private final WizardRepository wizardRepository;

    @Override
    public void createwizard(Wizard wizard) {
        wizardRepository.save(wizard);
    }

    @Override
    public List<Wizard> getAllWizards() {
        return wizardRepository.findAll();
    }

    @Override
    public List<Wizard> getDeatheaters() {
        return wizardRepository.findByEsMortifagoTrue();
    }

    @Override
    public void updateWizard(UUID id, Wizard wizard) {
        Wizard existWizard = wizardRepository.getWizardById(id);
        existWizard.setNombre(wizard.getNombre());
        existWizard.setCasa(wizard.getCasa());
        existWizard.setPatronus(wizard.getPatronus());
        existWizard.setEsMortifago(wizard.getEsMortifago());
        wizardRepository.save(existWizard);
    }

    @Override
    public List<Wizard> getByPatronus(String patronus) {
        return wizardRepository.findByPatronus(patronus);
    }

    @Override
    public Wizard deleteWizard(UUID id) {
        Wizard existWizard = wizardRepository.getWizardById(id);
        wizardRepository.deleteById(id);
        return existWizard;
    }
}
