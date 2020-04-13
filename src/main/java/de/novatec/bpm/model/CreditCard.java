package de.novatec.bpm.model;

import java.io.Serializable;
import java.time.Duration;
import java.time.Instant;
import java.time.ZoneId;
import java.util.UUID;

public class CreditCard implements Serializable {

    private String holder;
    private String number;
    private String code;
    private Instant expirationDate;

    public CreditCard() {
        this.holder = UUID.randomUUID().toString();
    }

    public String getNumber() {
        return "**** **** **** " + number.substring(12);
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Instant getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Instant expirationDate) {
        this.expirationDate = expirationDate;
    }

    public long getDaysUntilExpiration() {
        Duration duration = Duration.between(Instant.now().atZone(ZoneId.systemDefault()), expirationDate.atZone(ZoneId.systemDefault()));
        return duration.toDays();
    }

    public String getHolder() {
        return holder;
    }
}
