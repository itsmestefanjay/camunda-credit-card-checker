package de.novatec.bpm.delegate;

import de.novatec.bpm.model.CreditCard;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class LockDelegate implements JavaDelegate {

    public static final String CARD_VAR = "card";
    Logger logger = LoggerFactory.getLogger(LockDelegate.class);

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        CreditCard card = (CreditCard) execution.getVariable(CARD_VAR);
        String expirationString = DateTimeFormatter.ofPattern("MM/yy").format(card.getExpirationDate().atZone(ZoneId.systemDefault()));
        logger.warn("Card {} evaluated: Expired at {}", card.getNumber(), expirationString);
        logger.info("Account {} locked for further activities", card.getHolder());
    }
}
