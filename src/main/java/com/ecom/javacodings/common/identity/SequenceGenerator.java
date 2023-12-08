package com.ecom.javacodings.common.identity;

import java.util.Random;

public class SequenceGenerator {

    public String generateUnique(DuplicateChecker isDuplicate, int length) {
        Boolean isDuplicated = false;
        String generatedSequence = "";

        do {
            generatedSequence = generate(length);
            isDuplicated = isDuplicate.check(generatedSequence);
        } while ( isDuplicated );
        return generatedSequence;
    }

    public String generate(int length) {
        Random random = new Random();

        int leftLimit = 48;   // numeral '0'
        int rightLimit = 122; // letter 'z'

        return random.ints(leftLimit,rightLimit + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(length)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }
}
