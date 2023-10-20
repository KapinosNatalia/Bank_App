package de.telran.bankapp.service.interfaces;

import de.telran.bankapp.dto.ManagerDto;
import de.telran.bankapp.entity.enums.ClientStatus;

import java.util.List;
import java.util.UUID;

public interface ManagerService {
    List<ManagerDto> getAllManagers();

    ManagerDto getManagerByID(UUID id);

    void deleteManagerByID(UUID id);

    void createManager(ManagerDto managerDto);

    void updateManager(ManagerDto managerDto);

    List<ManagerDto> getAllManagersByClientStatus(ClientStatus status);
}
