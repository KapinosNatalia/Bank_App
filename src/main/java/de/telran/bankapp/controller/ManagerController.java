package de.telran.bankapp.controller;

import de.telran.bankapp.dto.ManagerDto;
import de.telran.bankapp.entity.enums.ClientStatus;
import de.telran.bankapp.service.interfaces.ManagerService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/managers")
@RequiredArgsConstructor
@Tag(name = "Manager controller API")
public class ManagerController {
    private final ManagerService managerService;

    @GetMapping
    public ResponseEntity<List<ManagerDto>> getAllManagers() {
        return ResponseEntity.ok(managerService.getAllManagers());
    }

    @GetMapping("/{managerID}")
    public ResponseEntity<ManagerDto> getManagerByID(@PathVariable("managerID") UUID managerID) {
        return ResponseEntity.ok(managerService.getManagerByID(managerID));
    }

    @DeleteMapping ("/{managerID}")
    public ResponseEntity<Void> deleteManagerByID(@PathVariable("managerID") UUID managerID) {
        managerService.deleteManagerByID(managerID);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping()
    public ResponseEntity<Void> saveManager(@RequestBody ManagerDto managerDto) {
        managerService.saveManager(managerDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping()
    public ResponseEntity<Void> updateManager(@RequestBody ManagerDto managerDto) {
        managerService.updateManager(managerDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/get-all-by-clients-status/{status}")
    public ResponseEntity<List<ManagerDto>> getAllManagersByClientStatus(@PathVariable("status") ClientStatus status) {
        return ResponseEntity.ok(managerService.getAllManagersByClientStatus(status));
    }
}
