package de.telran.bankapp.mapper;

import de.telran.bankapp.dto.ManagerDto;
import de.telran.bankapp.entity.Manager;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ManagerMapper {
    ManagerDto toDto(Manager manager);
    List<ManagerDto> toDtoList(List<Manager> managerList);
}

