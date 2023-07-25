import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;

public class BankCreditTest {

    @ParameterizedTest
    @CsvSource({"100,10,100000,500", "101,20,10000,5000", "102,5,5000,1000"})
    void when_valid_should_return(String clientId, String period, String summ, String initSum) throws BankCreditException {
        BlackListService blackListService = Mockito.mock(BlackListService.class);
        CreditParamRepository creditParamRepository = Mockito.mock(CreditParamRepository.class);
        BankCreditService bankCreditService = new BankCreditService(blackListService, creditParamRepository);

        Mockito.when(blackListService.isInBlackList(anyString())).thenReturn(false);

        Mockito.when(creditParamRepository.getCreditParams(anyString(),anyString(),anyString())).thenReturn(Optional.of(new CreditParam()));

        CreditParam creditParam = bankCreditService.getParams(clientId, period, summ, initSum);
        Assertions.assertNotNull(creditParam);
        Mockito.verify(blackListService,Mockito.times(1)).isInBlackList(anyString());
        Mockito.verify(creditParamRepository,Mockito.times(1)).getCreditParams(anyString(),anyString(),anyString());

    }

    @ParameterizedTest
    @CsvSource({"100,aaa,100000,500", "101,20,aaaa,5000", "102,5,5000,aaaa"})
    void when_notvalid_should_throw(String clientId, String period, String summ, String initSum) throws BankCreditException {
        BlackListService blackListService = Mockito.mock(BlackListService.class);
        CreditParamRepository creditParamRepository = Mockito.mock(CreditParamRepository.class);
        BankCreditService bankCreditService = new BankCreditService(blackListService, creditParamRepository);

        Mockito.when(blackListService.isInBlackList(anyString())).thenReturn(false);

        Mockito.when(creditParamRepository.getCreditParams(anyString(),anyString(),anyString())).thenReturn(Optional.of(new CreditParam()));

        Assertions.assertThrows(BankCreditException.class,
                () -> bankCreditService.getParams(clientId, period, summ, initSum));

        Mockito.verify(blackListService,Mockito.times(1)).isInBlackList(anyString());
        Mockito.verify(creditParamRepository,Mockito.never()).getCreditParams(anyString(),anyString(),anyString());

    }

    @Test
    void when_in_black_list_should_throw() {
        BlackListService blackListService = Mockito.mock(BlackListService.class);
        BankCreditService bankCreditService = new BankCreditService(blackListService, new CreditParamRepository());
        Mockito.when(blackListService.isInBlackList(anyString())).thenReturn(true);
        Assertions.assertThrows(BankCreditException.class,
                () -> bankCreditService.getParams("100","10","100000","500"));
        Mockito.verify(blackListService,Mockito.times(1)).isInBlackList(anyString());
        Mockito.verify(blackListService,Mockito.never()).isInWhiteList(anyString());
    }

    @Test
    void when_not_param_should_throw() {
        BlackListService blackListService = Mockito.mock(BlackListService.class);
        CreditParamRepository creditParamRepository = Mockito.mock(CreditParamRepository.class);
        BankCreditService bankCreditService = new BankCreditService(blackListService, creditParamRepository);
        Mockito.when(blackListService.isInBlackList(anyString())).thenReturn(false);

        Mockito.when(creditParamRepository.getCreditParams(anyString(),anyString(),anyString())).thenReturn(Optional.empty());

        Assertions.assertThrows(BankCreditException.class,
                () -> bankCreditService.getParams("100","10","100000","500"));
        Mockito.verify(blackListService,Mockito.times(1)).isInBlackList(anyString());
        Mockito.verify(creditParamRepository,Mockito.times(1)).getCreditParams(anyString(),anyString(),anyString());

    }
}
