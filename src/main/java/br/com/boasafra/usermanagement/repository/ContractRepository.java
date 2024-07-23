package br.com.boasafra.usermanagement.repository;

import br.com.boasafra.usermanagement.model.Contract;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ContractRepository extends JpaRepository<Contract, UUID> {

    List<Contract> findByClientId(Long clientId);

}
