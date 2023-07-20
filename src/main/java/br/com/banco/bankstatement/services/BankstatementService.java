package br.com.banco.bankstatement.services;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.banco.bankstatement.models.Transferencia;
import br.com.banco.bankstatement.specification.service.TransferenciaSpecificationService.FilterTransferencia;

@Service
public interface BankstatementService {
	List<Transferencia> filterTransferencia(FilterTransferencia filter);
}
