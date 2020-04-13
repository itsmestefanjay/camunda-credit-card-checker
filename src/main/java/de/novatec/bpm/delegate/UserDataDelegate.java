package de.novatec.bpm.delegate;

import de.novatec.bpm.helper.ProcessVariable;
import de.novatec.bpm.model.CreditCard;
import de.novatec.bpm.service.CreditCardService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class UserDataDelegate implements JavaDelegate {

    Logger logger = LoggerFactory.getLogger(UserDataDelegate.class);

    @Autowired
    CreditCardService creditCardService;

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        Set<CreditCard> creditCards = creditCardService.getCreditCards(10000);
        logger.info("Received {} credit cards to check", creditCards.size());
        execution.setVariable(ProcessVariable.CREDIT_CARD_LIST.getName(), creditCards);
    }
}
