package ba.unsa.etf.rpr.projekat;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Validation {

        public static boolean isDateValid(String s) {
            DateFormat format = new SimpleDateFormat("M/d/yyyy");
            format.setLenient(false);

            try {
                Date t = format.parse(s);
                //System.out.println(t);
                if (t.compareTo(Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant())) != -1)
                    return false;
            } catch (ParseException e) {
                return false;
            }

            return true;
        }

    public static boolean numberCheck(String number) {
            boolean valid = false;

            char[] chars = number.toCharArray();
            for (int i = 0; i < chars.length; i++) {
                valid = false;
                if (((chars[i] >= '0') && (chars[i] <= '9'))) {
                    valid = true;
                }
            }
            return valid;
        }



    public static boolean charCheck(char charx) {
            if ((charx >= '0') && (charx <= '9'))
                return true;
        return false;
        }

    public static boolean upperLetter(char c){
            if(c >= 'A' && c <='Z') return true;
            return false;
        }
    public static boolean lowerLetter(char c){
            if(c >= 'a' && c <='z') return true;
        return false;
        }
    public static boolean justLetter(char c){
            if((c >= 'A' && c <='Z') || (c >= 'a' && c <='z')) return true;
        return false;
        }
    public static boolean isValidString(String s){
            if(s.length()<1) return false;
            int i=0;
                for (i=0; i<s.length(); i++)
                    if(!charCheck(s.charAt(i)) || !justLetter(s.charAt(i)) || s.charAt(i) != ' ') return false;
        return true;
        }

    public static boolean name(String s){
            if (s.length() > 15) return false;
            int i=0;
            for(i=0; i<s.length(); i++){
                if(!justLetter(s.charAt(i))) return false;
            }
            return true;
        }


    public static boolean flightNumberLength(String string){
            int i = 0;
            int counter = 0;

            for(i=0; i<string.length(); i++) {
                if (charCheck(string.charAt(i)) == true)
                    counter++;
            }
            if (counter>4 || counter<1) return false;
            return true;
        }

        public static boolean flightCode(String string){
            if (string.length()!=6) return false;
            if(!upperLetter(string.charAt(0))) return false;
            if(lowerLetter(string.charAt(1))) return false;

            int i = 0;
            for (i = 2; i<string.length(); i++){
                if(justLetter(string.charAt(i))) return false;
            }
            return true;
        }

    public static boolean isStringTooLong(String s){
            int i=0, brojac=0;
            for(i=0; i<s.length(); i++){
                brojac++;
            }
            if(brojac>15) return false;
            return true;
    }

    public static boolean isStringTooLong2(String text) {
        int i=0, brojac=0;
        for(i=0; i<text.length(); i++){
            brojac++;
        }
        if(brojac>6) return false;
        return true;
    }
}
