package br.com.boasafra.usermanagement.service;

import br.com.boasafra.usermanagement.model.Cnpj;
import br.com.boasafra.usermanagement.repository.CnpjRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CnpjService {

    @Autowired
    private CnpjRepository cnpjRepository;

    public List<Cnpj> getAllCnpjs() {
        return cnpjRepository.findAll();
    }
}
