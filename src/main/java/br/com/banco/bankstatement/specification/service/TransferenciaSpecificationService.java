package br.com.banco.bankstatement.specification.service;

import java.io.Serializable;
import java.util.List;

import br.com.banco.bankstatement.models.Transferencia;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public interface TransferenciaSpecificationService {
	List<Transferencia> filterTransferencia(FilterTransferencia filter);
	
	@Builder
	@Getter @Setter @NoArgsConstructor @AllArgsConstructor
	public class FilterTransferencia implements Serializable{
		
		private static final long serialVersionUID = 1251982398523266286L;
		private Long accountNumber;
		private String initialPeriod;
		private String finalPeriod;
		private String responsableOperatorName;
		
		public Long getAccountNumber() {
			return accountNumber;
		}
		public void setAccountNumber(Long accountNumber) {
			this.accountNumber = accountNumber;
		}
		public String getInitialPeriod() {
			return initialPeriod;
		}
		public void setInitialPeriod(String initialPeriod) {
			this.initialPeriod = initialPeriod;
		}
		public String getFinalPeriod() {
			return finalPeriod;
		}
		public void setFinalPeriod(String finalPeriod) {
			this.finalPeriod = finalPeriod;
		}
		public String getResponsableOperatorName() {
			return responsableOperatorName;
		}
		public void setResponsableOperatorName(String responsableOperatorName) {
			this.responsableOperatorName = responsableOperatorName;
		}
	}
}
