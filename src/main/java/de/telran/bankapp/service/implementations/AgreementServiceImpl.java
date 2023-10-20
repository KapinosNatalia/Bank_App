package de.telran.bankapp.service.implementations;

import de.telran.bankapp.dto.AgreementDto;
import de.telran.bankapp.mapper.AgreementMapper;
import de.telran.bankapp.repository.AgreementRepository;
import de.telran.bankapp.service.interfaces.AgreementService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AgreementServiceImpl implements AgreementService {
    private final AgreementRepository agreementRepository;
    private final AgreementMapper agreementMapper;

    @Override
    public List<AgreementDto> getAllAgreements() {
        return agreementMapper.toDtoList(agreementRepository.findAll());
    }
}
