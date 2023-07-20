package br.com.banco.bankstatement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import br.com.banco.bankstatement.models.Transferencia;

@Repository
public interface TransferenciaRepository extends JpaRepository<Transferencia, Long> , JpaSpecificationExecutor<Transferencia> {
}
