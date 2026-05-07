package org.example.registromagos.repository;

import org.example.registromagos.domain.entity.Wizard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface WizardRepository extends JpaRepository<Wizard, UUID> {
    Wizard getWizardById(UUID id);
    List<Wizard> findByEsMortifagoTrue();
    List<Wizard> findByPatronus(String patronus);
}
