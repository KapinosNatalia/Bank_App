package de.telran.bankapp.repository;

import de.telran.bankapp.entity.Manager;
import de.telran.bankapp.entity.enums.ClientStatus;
import lombok.Value;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface ManagerRepository extends JpaRepository<Manager, UUID> {
    @Query("SELECT m FROM Manager m JOIN Client c ON m = c.manager AND c.status = :status")
    List<Manager> getAllManagersByClientStatus(ClientStatus status);
}
