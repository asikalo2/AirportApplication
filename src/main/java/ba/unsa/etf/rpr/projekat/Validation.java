package ba.unsa.etf.rpr.projekat;

public class Validation {

    public static boolean charCheck(char charx) {
        if ((charx >= '0') && (charx <= '9'))
            return true;
        return false;
    }

    public static boolean justLetter(char c) {
        if ((c >= 'A' && c <= 'Z') || (c >= 'a' && c <= 'z')) return true;
        return false;
    }

    public static boolean isValidString(String s) {
        if (s.length() < 1) return false;
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (Character.isLetter(ch) || ch == ' ') {
                continue;
            }
            return false;
        }
        return true;
    }

    public static boolean validateNumber(String s) {
        if (s.length() < 1) return false;
        int i = 0;
        for (i = 0; i < s.length(); i++)
            if (!charCheck(s.charAt(i)) || s.charAt(i) == ' ') return false;
        return true;
    }

    public static boolean isValidName(String s) {
        if (s.length() > 30) return false;
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (Character.isLetter(ch) || ch == ' ') {
                continue;
            }
            return false;
        }
        return true;
    }

    public static boolean flightNumberLength(String string) {
        int i = 0;
        int counter = 0;

        for (i = 0; i < string.length(); i++) {
            if (charCheck(string.charAt(i)) == true)
                counter++;
        }
        if (counter > 4 || counter < 1) return false;
        return true;
    }

    public static boolean isStringTooLong(String s) {
        int i = 0, counter = 0;
        for (i = 0; i < s.length(); i++) {
            counter++;
            if (!s.equals(s.toUpperCase())) return false;
        }
        if (counter > 5) return false;

        return true;
    }

    public static boolean isValidString2(String s) {
        if (s.length() < 1) return false;
        int i = 0;
        for (i = 0; i < s.length(); i++)
            if (!justLetter(s.charAt(i)) || s.charAt(i) != ' ') return false;
        return true;
    }

    public static boolean validateNumberOfSeats(String n) {
        int result = Integer.parseInt(n);
        if(result>300) return false;
        return true;
    }
}