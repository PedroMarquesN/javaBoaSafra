package br.com.boasafra.usermanagement.controller;
import br.com.boasafra.usermanagement.model.Contract;
import br.com.boasafra.usermanagement.service.ContractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/contracts")
public class ContractController {

    @Autowired
    private ContractService contractService;

    @PostMapping("/create")
    public ResponseEntity<Contract> createContract(@RequestParam Long clientId, @RequestParam double quantity) {
        Contract contract = contractService.createContract(clientId, quantity);
        return new ResponseEntity<>(contract, HttpStatus.CREATED);
    }

    @PostMapping("/split/{contractId}")
    public ResponseEntity<String> splitContract(@PathVariable UUID contractId, @RequestParam Long clientId, @RequestParam double splitQuantity) {
        try {
            contractService.splitContract(clientId, contractId, splitQuantity);
            return new ResponseEntity<>("Contract split successfully", HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{clientId}")
    public ResponseEntity<List<Contract>> getContractsByClientId(@PathVariable Long clientId) {
        List<Contract> contracts = contractService.getContractsByClientId(clientId);
        return ResponseEntity.ok(contracts);
    }
}