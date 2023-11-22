package de.telran.bankapp.controller;

import de.telran.bankapp.dto.ErrorDto;
import de.telran.bankapp.dto.ManagerDto;
import de.telran.bankapp.entity.enums.ClientStatus;
import de.telran.bankapp.exceptions.EntityNotFoundException;
import de.telran.bankapp.exceptions.ManagerCreationException;
import de.telran.bankapp.service.interfaces.ManagerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.security.RolesAllowed;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@RestController
@RequestMapping("/managers")
@RequiredArgsConstructor
@Tag(name = "Manager controller API", description = "With this controller you can get list of all managers," +
        " get only one manager by ID, create, update and delete manager by ID," +
        " and get a list of managers who accompany clients with a certain status")
public class ManagerController {
    private final ManagerService managerService;

    @GetMapping
    @Operation(summary = "Get list of all managers")
    @SecurityRequirement(name = "Bearer Authentication")
    @Secured("ROLE_VIP")
    public ResponseEntity<List<ManagerDto>> getAllManagers() {
        return ResponseEntity.ok(managerService.getAllManagers());
    }

    @GetMapping("/{managerID}")
    @Operation(summary = "Get manager by ID")
    @SecurityRequirement(name = "Bearer Authentication")
    public ResponseEntity<ManagerDto> getManagerByID(@PathVariable("managerID") UUID managerID) {
        return ResponseEntity.ok(managerService.getManagerByID(managerID));
    }

    @PostMapping()
    @Operation(summary = "Create new manager", description = "The update data is sent as the Request body. " +
            "For example, {\n" +
            "        \"id\": \"523e4567-e89b-12d3-a456-010000000001\",\n" +
            "        \"firstName\": \"Johan\",\n" +
            "        \"lastName\": Rider\n" +
            "        \"status\": \"FIRED\",\n" +
            "    }")
    @SecurityRequirement(name = "Bearer Authentication")
    public ResponseEntity<Void> createManager(@RequestBody ManagerDto managerDto) {
        managerService.createManager(managerDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping()
    @Operation(summary = "Update existing manager", description = "The update data is sent as the Request body. " +
            "For example, {\n" +
            "        \"id\": \"523e4567-e89b-12d3-a456-010000000001\",\n" +
            "        \"firstName\": \"Johan\",\n" +
            "        \"lastName\": Rider\n" +
            "        \"status\": \"FIRED\",\n" +
            "    }")
    @SecurityRequirement(name = "Bearer Authentication")
    public ResponseEntity<Void> updateManager(@RequestBody ManagerDto managerDto) {
        managerService.updateManager(managerDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping ("/{managerID}")
    @Operation(summary = "Delete manager by ID")
    @SecurityRequirement(name = "Bearer Authentication")
    public ResponseEntity<Void> deleteManagerByID(@PathVariable("managerID") UUID managerID) {
        managerService.deleteManagerByID(managerID);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/get-all-by-clients-status/{status}")
    @Operation(summary = "Get a list of managers who accompany clients with a certain status")
    @SecurityRequirement(name = "Bearer Authentication")
    public ResponseEntity<List<ManagerDto>> getAllManagersByClientStatus(@PathVariable("status") ClientStatus status) {
        return ResponseEntity.ok(managerService.getAllManagersByClientStatus(status));
    }

}
