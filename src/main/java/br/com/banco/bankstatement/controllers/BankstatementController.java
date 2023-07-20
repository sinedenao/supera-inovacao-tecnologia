package br.com.banco.bankstatement.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.banco.bankstatement.models.Transferencia;
import br.com.banco.bankstatement.services.implementations.BankstatementServiceImpl;
import br.com.banco.bankstatement.specification.service.TransferenciaSpecificationService.FilterTransferencia;
import lombok.AllArgsConstructor;

@RestController @AllArgsConstructor
@RequestMapping("/bankstatement")
public class BankstatementController {
	
	@Autowired
	private final BankstatementServiceImpl bankstatementService = new BankstatementServiceImpl();

	@GetMapping
    public ResponseEntity<List<Transferencia>> filterTransferencia(
		@RequestParam(name = "accountNumber", required = false) Long accountNumber,
        @RequestParam(name = "initialPeriod", required = false) String initialPeriod,
        @RequestParam(name = "finalPeriod", required = false) String finalPeriod,
        @RequestParam(name = "operatorName", required = false) String operatorName
	){
		FilterTransferencia filter = new FilterTransferencia();
		filter.setAccountNumber(accountNumber);
		filter.setInitialPeriod(initialPeriod);
		filter.setFinalPeriod(finalPeriod);
		filter.setResponsableOperatorName(operatorName);
            
		List<Transferencia> filterTransferencia = bankstatementService.filterTransferencia(filter);
		return ResponseEntity.ok().body(filterTransferencia);
	};
}