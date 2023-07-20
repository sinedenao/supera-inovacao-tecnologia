package br.com.banco.bankstatement.services.implementations;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import br.com.banco.bankstatement.models.Transferencia;
import br.com.banco.bankstatement.repository.TransferenciaRepository;
import br.com.banco.bankstatement.services.BankstatementService;
import br.com.banco.bankstatement.specification.TransferenciaSpecification;
import br.com.banco.bankstatement.specification.service.TransferenciaSpecificationService;
import lombok.AllArgsConstructor;

@AllArgsConstructor @Service
public class BankstatementServiceImpl implements BankstatementService, TransferenciaSpecificationService{

	@Autowired 
	private final TransferenciaRepository bankstatementRepository = null;

	@Override
	public List<Transferencia> filterTransferencia(FilterTransferencia filter) {
		Specification<Transferencia> specs = Specification.where((root, query, cb) -> cb.conjunction());
		
		if(StringUtils.hasText(filter.getResponsableOperatorName())) {
			specs = specs.and(TransferenciaSpecification.responsableOperatorNameLike(filter)).or(TransferenciaSpecification.transactionOperatorNameLike(filter));
		}
		
		if(filter.getAccountNumber()!=null) {
			specs = specs.and(TransferenciaSpecification.idEqual(filter));
		}
		
	    LocalDate initialPeriod = parseLocalDate(filter.getInitialPeriod());
	    LocalDate finalPeriod = parseLocalDate(filter.getFinalPeriod());
		specs = specs.and(TransferenciaSpecification.dateBetween(initialPeriod, finalPeriod));
		
		List<Transferencia> transferencia = bankstatementRepository.findAll(specs);
		
        return transferencia;
	}
	
	private LocalDate parseLocalDate(String dateStr) {
	    if (dateStr == null || dateStr.isEmpty()) {
	        return null;
	    }

	    try {
	        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
	        return LocalDate.parse(dateStr.replace("/", "-"), formatter);
	    } catch (DateTimeParseException e) {
	        e.printStackTrace();
	        return null;
	    }
	}
}
