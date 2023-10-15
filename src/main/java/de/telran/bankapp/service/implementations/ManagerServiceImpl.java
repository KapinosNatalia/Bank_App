package de.telran.bankapp.service.implementations;

import de.telran.bankapp.dto.ManagerDto;
import de.telran.bankapp.entity.Manager;
import de.telran.bankapp.entity.enums.ClientStatus;
import de.telran.bankapp.entity.enums.ManagerStatus;
import de.telran.bankapp.exceptions.ManagerCreationException;
import de.telran.bankapp.exceptions.ManagerNotFoundException;
import de.telran.bankapp.mapper.ManagerMapper;
import de.telran.bankapp.repository.ManagerRepository;
import de.telran.bankapp.service.interfaces.ManagerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ManagerServiceImpl implements ManagerService {
    final ManagerRepository managerRepository;
    final ManagerMapper managerMapper;

    @Override
    public List<ManagerDto> getAllManagers() {
        List<Manager> list = managerRepository.findAll();
        return managerMapper.toDtoList(list);
    }

    @Override
    public ManagerDto getManagerByID(UUID id) {
        Manager manager = managerRepository.getReferenceById(id);//.getById(id);
        return managerMapper.toDto(manager);
    }

    @Override
    public void deleteManagerByID(UUID id) {
        if (managerRepository.existsById(id)) {
            managerRepository.deleteById(id);
        } else {
            throw new ManagerNotFoundException("with ID " + id);
        }
    }

    @Override
    public void saveManager(ManagerDto managerDto) {
        if (managerDto.getId().isEmpty()) {
            throw new ManagerCreationException("ID can´t be empty.");
        }
        UUID currentID = UUID.fromString(managerDto.getId());
        if (managerRepository.existsById(currentID)) {

            String currentFirstName = managerDto.getFirstName();
            String currentLastName = managerDto.getLastName();
            ManagerStatus currentStatus = ManagerStatus.valueOf(managerDto.getStatus());
            Manager newManager = new Manager(currentID, currentFirstName, currentLastName, currentStatus);
            managerRepository.save(newManager);
        } else {
            throw new ManagerNotFoundException("with ID " + currentID);
        }
    }

    @Override
    public void updateManager(ManagerDto managerDto) {
        if (managerDto.getId().isEmpty()) {
            throw new ManagerCreationException("ID can´t be empty.");
        }
        Manager manager = managerRepository.getReferenceById(UUID.fromString(managerDto.getId()));
        if (managerDto.getFirstName() != null &&
                !managerDto.getFirstName().equals(manager.getFirstName())) {
            manager.setFirstName(managerDto.getFirstName());
        }
        if (managerDto.getLastName() != null &&
                !managerDto.getLastName().equals(manager.getLastName())) {
            manager.setLastName(managerDto.getLastName());
        }
        if (managerDto.getStatus() != null &&
                !ManagerStatus.valueOf(managerDto.getStatus()).equals(manager.getStatus())) {
            manager.setStatus(ManagerStatus.valueOf(managerDto.getStatus()));
        }
        managerRepository.save(manager);
    }

    @Override
    public List<ManagerDto> getAllManagersByClientStatus(ClientStatus status) {
        return managerMapper.toDtoList(managerRepository.getAllManagersByClientStatus(status));
    }
}
