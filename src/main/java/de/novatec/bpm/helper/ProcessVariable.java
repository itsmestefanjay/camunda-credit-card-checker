package de.novatec.bpm.helper;

public enum ProcessVariable {

    CARD("card"),
    CARD_STATUS("cardStatus"),
    CREDIT_CARD_LIST("creditCardList");

    private String name;

    ProcessVariable(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
