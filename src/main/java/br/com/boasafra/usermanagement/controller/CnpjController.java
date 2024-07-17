package br.com.boasafra.usermanagement.controller;

import br.com.boasafra.usermanagement.model.Cnpj;
import br.com.boasafra.usermanagement.service.CnpjService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/cnpjs")
public class CnpjController {

    @Autowired
    private CnpjService cnpjService;


    @GetMapping
    public List<Cnpj> getCnpjs() {
        return cnpjService.getAllCnpjs();
    }
}
