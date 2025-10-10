package org;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;

public class AccountService {

    private Set<String> registeredEmails =new HashSet<>();
    private  Set<String> registeredUsernames = new HashSet<>();
    private static final Pattern EMAIL_PATTERN =
            Pattern.compile("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$");

    public boolean registerAccount(String username, String password, String email) {
        if ((username == null || username.isBlank())) return false;
        if (!isValidPassword(password)) return false;
        if (!isValidEmail(email)) return false;
        if (!registeredUsernames.add(username.trim())) return false;
        return registeredEmails.add(email.trim());

    }
    public boolean isValidEmail(String email) {
        if (email == null) return false;
        return  EMAIL_PATTERN.matcher(email).matches();
    }
    private boolean isValidPassword(String password) {
        if (password == null) return false;
        String p = password.trim();
        if (p.length() < 6) return false;

        boolean hasLetterUp = p.chars().anyMatch(Character::isUpperCase);
        boolean hasLetterLow = p.chars().anyMatch(Character::isLowerCase);
        boolean hasDigit  = p.chars().anyMatch(Character::isDigit);
        boolean hasSpecial = password.matches(".*[^A-Za-z0-9].*");
        return hasLetterLow && hasDigit&&hasLetterUp&&hasSpecial;
    }
}
