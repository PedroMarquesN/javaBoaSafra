package br.com.boasafra.usermanagement.model;

import jakarta.persistence.*;
import java.util.Set;

@Entity
public class Cnpj {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String cnpj;

    @ManyToMany(mappedBy = "cnpjs")
    private Set<User> users;


}
