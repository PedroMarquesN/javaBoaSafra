package br.com.boasafra.usermanagement.service;


import br.com.boasafra.usermanagement.model.Contract;
import br.com.boasafra.usermanagement.model.User;
import br.com.boasafra.usermanagement.enums.Role;
import br.com.boasafra.usermanagement.repository.ContractRepository;
import br.com.boasafra.usermanagement.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class ContractService {

    private final UserRepository userRepository;
    private final ContractRepository contractRepository;

    public ContractService(UserRepository userRepository, ContractRepository contractRepository) {
        this.userRepository = userRepository;
        this.contractRepository = contractRepository;
    }

    public List<Contract> getAllContracts() {
        return contractRepository.findAll();
    }
    @Transactional
    public Contract createContract(Long clientId, double quantity) {
        User client = userRepository.findById(clientId)
                .orElseThrow(() -> new IllegalArgumentException("Client not found"));

        Contract contract = new Contract();
        contract.setClient(client);
        contract.setQuantity(quantity);
        contract.setStatus("stored");

        return contractRepository.save(contract);
    }

    @Transactional
    public Contract splitContract(Long clientId, UUID contractId, double splitQuantity) {
        User user = userRepository.findById(clientId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        if (user.getRole() != Role.Admin && user.getRole() != Role.Client) {
            throw new IllegalArgumentException("Only Admin and Client can split contracts");
        }

        Contract originalContract = contractRepository.findById(contractId)
                .orElseThrow(() -> new IllegalArgumentException("Contract not found"));

        if (originalContract.getQuantity() < splitQuantity) {
            throw new IllegalArgumentException("Split quantity is greater than the original contract quantity");
        }

        originalContract.setQuantity(originalContract.getQuantity() - splitQuantity);
        contractRepository.save(originalContract);

        Contract newContract = new Contract();
        newContract.setClient(user);
        newContract.setQuantity(splitQuantity);
        newContract.setStatus("to be delivered");

        return contractRepository.save(newContract);
    }

    public List<Contract> getContractsByClientId(Long clientId) {
        return contractRepository.findByClientId(clientId);
    }
}