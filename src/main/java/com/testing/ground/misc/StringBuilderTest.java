package com.testing.ground.misc;

import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;

public class StringBuilderTest {

    public static void main(String[] args) {
        StringBuilder invalidVAs = new StringBuilder();
        invalidVAs.append("Walmart US Data Center & Campus").append(",");

        String input = "Walmart US Data Center &AMP; Campus";
        String decoded = StringEscapeUtils.unescapeHtml4(input);
        System.out.println("Decoded: "+decoded);

        if (invalidVAs.length() > 0)
            invalidVAs.replace(invalidVAs.length() - 1, invalidVAs.length(), "");

        // Convert to String
//        String result = invalidVAs.toString();
        if (StringUtils.isNotBlank(invalidVAs.toString())) {
            System.out.println("I am in");
            System.out.println("Invalid VAs: " + invalidVAs);
        }
    }
}
