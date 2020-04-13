package de.novatec.bpm.delegate;

import de.novatec.bpm.helper.ProcessVariable;
import de.novatec.bpm.model.CreditCard;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class InformDelegate implements JavaDelegate {

    Logger logger = LoggerFactory.getLogger(InformDelegate.class);

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        CreditCard card = (CreditCard) execution.getVariable(ProcessVariable.CARD.getName());
        String expirationString = DateTimeFormatter.ofPattern("MM/yy").format(card.getExpirationDate().atZone(ZoneId.systemDefault()));
        logger.warn("Card {} evaluated: Expires at {}", card.getNumber(), expirationString);
        logger.info("Dear {}, please update your info for your credit card {}, expires at {}", card.getHolder(), card.getNumber(), expirationString);
    }
}
