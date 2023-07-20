package br.com.banco.bankstatement.specification;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;

import org.springframework.data.jpa.domain.Specification;

import br.com.banco.bankstatement.models.Conta;
import br.com.banco.bankstatement.models.Transferencia;
import br.com.banco.bankstatement.specification.service.TransferenciaSpecificationService.FilterTransferencia;

public class TransferenciaSpecification {

	public static Specification<Transferencia> idEqual(FilterTransferencia filter) {
	    return (root, query, cb) -> {
	        Join<Transferencia, Conta> contaJoin = root.join("conta");
	        return cb.equal(contaJoin.get("id"), filter.getAccountNumber().toString());
	    };
	}	
	
	public static Specification<Transferencia> responsableOperatorNameLike(FilterTransferencia filter) {
	    return (root, query, cb) -> {
	        Join<Transferencia, Conta> contaJoin = root.join("conta");
	        return cb.like(cb.upper(contaJoin.get("responsableOperatorName")), "%" + filter.getResponsableOperatorName().toUpperCase() + "%");
	    };
	}
	
	public static Specification<Transferencia> transactionOperatorNameLike(FilterTransferencia filter) {
	    return (root, query, cb) -> {
	        return cb.like(cb.upper(root.get("transactionOperatorName")), "%" + filter.getResponsableOperatorName().toUpperCase() + "%");
	    };
	}	
	
	public static Specification<Transferencia> dateBetween(LocalDate initialPeriod, LocalDate finalPeriod) {
	    return (root, query, cb) -> {
	        List<Predicate> predicates = new ArrayList<>();
	        
	        if (initialPeriod != null && finalPeriod != null) {
	            predicates.add(cb.between(root.get("transferenciaDate"), initialPeriod, finalPeriod));
	        }
	        else if (initialPeriod != null) {
	            predicates.add(cb.greaterThan(root.get("transferenciaDate"), initialPeriod));
	        }
	        else if (finalPeriod != null) {
	            predicates.add(cb.lessThan(root.get("transferenciaDate"), finalPeriod));
	        }

	        return cb.and(predicates.toArray(new Predicate[0]));
	    };
	}
}
