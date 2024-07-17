package br.com.boasafra.usermanagement.repository;

import br.com.boasafra.usermanagement.model.Cnpj;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CnpjRepository extends JpaRepository<Cnpj, Long> {
}
