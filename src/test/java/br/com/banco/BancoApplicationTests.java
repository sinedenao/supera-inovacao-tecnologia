package br.com.banco;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.banco.bankstatement.services.BankstatementService;
import br.com.banco.bankstatement.services.implementations.BankstatementServiceImpl;
import br.com.banco.bankstatement.specification.service.TransferenciaSpecificationService.FilterTransferencia;

//TODO Verificar mocks necessarios para teste

@SpringBootTest
class BancoApplicationTests {

	//@Autowired
	private BankstatementService bankstatementService = new BankstatementServiceImpl();
	
	@Disabled ("This test is disabled as the feature is WIP")
	@Test
    void  whenListAllBankStatementsIsNotNullTest(){
		FilterTransferencia filter = new FilterTransferencia();
		filter.setAccountNumber(Long.valueOf("2"));
		filter.setInitialPeriod("02-02-2019");
		filter.setFinalPeriod("04-02-2019");
		filter.setResponsableOperatorName("Sicrano");
    	assertNotNull(bankstatementService.filterTransferencia(filter));
    }
	@Disabled ("This test is disabled as the feature is WIP")
	@Test
    void  whenListAllBankStatementsIsNullTest(){
		FilterTransferencia filter = new FilterTransferencia();
		filter.setAccountNumber(Long.valueOf("1"));
		filter.setInitialPeriod("02-02-2019");
		filter.setFinalPeriod("04-02-2019");
		filter.setResponsableOperatorName("Sicrano");
    	assertNull(bankstatementService.filterTransferencia(filter));
    }
}