package de.telran.bankapp.mapper;

import de.telran.bankapp.dto.AccountDto;
import de.telran.bankapp.dto.AgreementDto;
import de.telran.bankapp.entity.Account;
import de.telran.bankapp.entity.Agreement;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AgreementMapper {
    AgreementDto toDto(Agreement agreement);
    List<AgreementDto> toDtoList(List<Agreement> agreements);
}
