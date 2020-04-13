package de.novatec.bpm.listener;

import de.novatec.bpm.helper.ProcessVariable;
import de.novatec.bpm.model.CreditCard;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.ExecutionListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EndEventListener implements ExecutionListener {

    Logger logger = LoggerFactory.getLogger(EndEventListener.class);

    @Override
    public void notify(DelegateExecution execution) throws Exception {
        CreditCard card = (CreditCard) execution.getVariable(ProcessVariable.CARD.getName());
        String status = (String) execution.getVariable(ProcessVariable.CARD_STATUS.getName());
        logger.info("Card {} evaluated. Status: {}", card.getNumber(), status);
    }
}