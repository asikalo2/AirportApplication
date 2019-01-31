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

        public static boolean isStringValid(String n) {
            if (n.length() < 1)
                return false;
            return true;
        }

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

        private static boolean cifraCheck(String number) {
            boolean validno = false;

            char[] chars = number.toCharArray();
            for (int i = 0; i < chars.length; i++) {
                validno = false;
                if (((chars[i] >= '0') && (chars[i] <= '9'))) {
                    validno = true;
                }
            }
            return validno;
        }


        private static boolean charCheck(char c) {
            // A, E, O, T, K, J, M, 0-9
            if (c == 'A' || c == 'E' || c == 'O' || c == 'T' || c == 'K' || c == 'J' || c == 'M' ||
                    (c > '0' && c < '9'))
                return true;
            return false;
        }

}
