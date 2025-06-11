package com.testing.ground.misc;

import org.apache.commons.lang3.StringEscapeUtils;

public class SpecialCharacterHandler {
    public static void main(String[] args) {
        String input = "Special characters: <, >, &, \", '";

//        input = "";
        // Encode to HTML
        String encodedHtml = StringEscapeUtils.escapeHtml4(input);
        System.out.println("Encoded HTML: " + encodedHtml);

        // Decode from HTML
        String decodedHtml = StringEscapeUtils.unescapeHtml4(encodedHtml);
        System.out.println("Decoded HTML: " + decodedHtml);

        // Encode to XML
        String encodedXml = StringEscapeUtils.escapeXml10(input);
        System.out.println("Encoded XML: " + encodedXml);

        // Decode from XML
        String decodedXml = StringEscapeUtils.unescapeXml(encodedXml);
        System.out.println("Decoded XML: " + decodedXml);


        String vas = "Walmart US Data Center &amp; Campus";
        System.out.println("Original: " + vas);
        System.out.println("Replaced: " + replaceSpecialChars(vas));

    }

    public static String replaceSpecialChars(String input) {
        if (input == null) return null;
        return input.replaceAll("&amp;", "&");
    }
}


