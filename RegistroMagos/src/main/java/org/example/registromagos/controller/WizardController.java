package org.example.registromagos.controller;

import lombok.AllArgsConstructor;
import org.example.registromagos.domain.entity.Wizard;
import org.example.registromagos.service.Impl.WizardServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping("/api")
public class WizardController {
    private final WizardServiceImpl wizardService;

    @PostMapping("/wizards")
    public ResponseEntity<Wizard> createWizard(@RequestBody Wizard wizard){
        wizardService.createwizard(wizard);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(wizard);
    }

    @GetMapping("/wizards")
    public ResponseEntity<List<Wizard>> getAllWizards(){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(wizardService.getAllWizards());
    }

    @GetMapping("/wizards/deatheaters")
    public ResponseEntity<List<Wizard>> getWizardsIfDeatheaters(){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(wizardService.getDeatheaters());
    }

    @PutMapping("/wizards/{id}")
    public ResponseEntity<Wizard> updateWizard(@RequestParam UUID id, @RequestBody Wizard wizard){
        wizardService.updateWizard(id, wizard);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(wizard);
    }

    @GetMapping("/wizards/patronus/{patronus}")
    public ResponseEntity<List<Wizard>> getWizardsByPatronus(@RequestParam String patronus){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(wizardService.getByPatronus(patronus));
    }

    @DeleteMapping("/wizards/delete/{id}")
    public ResponseEntity<Wizard> deleteWizard(@RequestParam UUID id){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(wizardService.deleteWizard(id));
    }
}
