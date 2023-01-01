package ru.itis.prytkovd.webapp.util;

import lombok.AllArgsConstructor;

public class StringValidator {
    private final String string;
    private boolean isValid;

    private StringValidator(String string) {
        this.string = string;
        this.isValid = true;
    }

    public static StringValidator of(String string) {
        System.out.println();
        return new StringValidator(string);
    }

    public StringValidator satisfiesRule(Rule rule) {
        if (isValid) {
            isValid &= rule.validate(string);
        }
        return this;
    }

    public StringValidator matchesRegex(String regex) {
        return satisfiesRule(new RegexRule(regex));
    }

    public StringValidator isAlphabetic() {
        return matchesRegex("^[a-zA-Z]+$");
    }

    public StringValidator isNumeric() {
        return matchesRegex("^\\d+$");
    }

    public StringValidator isAlphanumeric() {
        return matchesRegex("^[a-zA-Z0-9]+$");
    }

    public StringValidator isWord() {
        return matchesRegex("^\\w+$");
    }

    public StringValidator containsLowercase() {
        return matchesRegex(".*[a-z].*");
    }

    public StringValidator containsUppercase() {
        return matchesRegex(".*[A-Z].*");
    }

    public StringValidator containsDigit() {
        return matchesRegex(".*\\d.*");
    }

    public StringValidator minLength(int length) {
        if (length < 0) {
            throw new IllegalArgumentException("length must be positive");
        }
        return satisfiesRule(s -> s.length() >= length);
    }

    public StringValidator maxLength(int length) {
        if (length < 0) {
            throw new IllegalArgumentException("length must be positive");
        }
        return satisfiesRule(s -> s.length() <= length);
    }

    public boolean validate() {
        return isValid;
    }

    public interface Rule {
        boolean validate(String string);
    }

    @AllArgsConstructor
    public static class RegexRule implements Rule {
        private final String regex;

        @Override
        public boolean validate(String string) {
            return string.matches(regex);
        }
    }
}
