package de.novatec.bpm.service;

import de.novatec.bpm.model.CreditCard;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.ZoneId;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

@Service
public class CreditCardService {

    private static final int SEC_CODE_LENGTH = 3;
    private static final int CC_NO_LENGTH = 16;
    private static final Instant IN_FOUR_YEARS = Instant.now().atZone(ZoneId.systemDefault()).plusYears(4).toInstant();
    private static final Instant ONE_YEAR_AGO = Instant.now().atZone(ZoneId.systemDefault()).minusYears(1).toInstant();

    /**
     * get all credit cards
     * @param limit the maximum amount to fetch
     */
    public Set<CreditCard> getCreditCards(int limit) {
        Stream<CreditCard> userDataStream = IntStream.iterate(1, i -> i + 1).limit(limit).parallel().mapToObj(i -> generateCard());
        return userDataStream.collect(Collectors.toSet());
    }

    private CreditCard generateCard() {
        CreditCard data = new CreditCard();
        data.setCode(generateRandomNumber(SEC_CODE_LENGTH));
        data.setNumber(generateRandomNumber(CC_NO_LENGTH));
        data.setExpirationDate(generateRandomExpirationDate(ONE_YEAR_AGO, IN_FOUR_YEARS));
        return data;
    }

    public Instant generateRandomExpirationDate(Instant start, Instant end) {
        long startSeconds = start.getEpochSecond();
        long endSeconds = end.getEpochSecond();
        long random = ThreadLocalRandom.current().nextLong(startSeconds, endSeconds);
        return Instant.ofEpochSecond(random);
    }

    private String generateRandomNumber(int length) {
        return RandomStringUtils.random(length, false, true);
    }

}
