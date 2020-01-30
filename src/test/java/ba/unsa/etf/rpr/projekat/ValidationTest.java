package ba.unsa.etf.rpr.projekat;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ValidationTest {

    @Test
    public void charCheck1() {
        Validation unit = new Validation();
        Boolean result = unit.charCheck('S');
        assertEquals(false, result);
    }


    @Test
    public void validateNumberOfSeats() {
        Validation unit = new Validation();
        Boolean result = unit.validateNumberOfSeats("301");
        assertEquals(false, result);
    }

    @Test
    public void validateNumberOfSeats2() {
        Validation unit = new Validation();
        Boolean result = unit.validateNumberOfSeats("300");
        assertEquals(true, result);
    }

    @Test
    public void charCheck2() {
        Validation unit = new Validation();
        Boolean result = unit.charCheck('9');
        assertEquals(true, result);
    }

    @Test
    public void validateChar1(){
        Validation unit = new Validation();
        Boolean result = unit.justLetter('S');
        assertEquals(true, result);
    }
    @Test
    public void validateChar2(){
        Validation unit = new Validation();
        Boolean result = unit.justLetter('9');
        assertEquals(false, result);
    }

    @Test
    public void validateString1(){
        Validation unit = new Validation();
        Boolean result = unit.isValidString("");
        assertEquals(false, result);
    }
    @Test
    public void validateString2(){
        Validation unit = new Validation();
        Boolean result = unit.isValidString("String");
        assertEquals(true, result);
    }
    @Test
    public void validateNumber1(){
        Validation unit = new Validation();
        Boolean result = unit.validateNumber("");
        assertEquals(false, result);
    }
    @Test
    public void validateNumber2(){
        Validation unit = new Validation();
        Boolean result = unit.validateNumber("9876");
        assertEquals(true, result);
    }

    @Test
    public void tooLongString1(){
        Validation unit = new Validation();
        Boolean result = unit.isValidString2("");
        assertEquals(false, result);
    }
    @Test
    public void tooLongString2(){
        Validation unit = new Validation();
        Boolean result = unit.isValidString2("LongString");
        assertEquals(false, result);
    }
    @Test
    public void tooLongString3(){
        Validation unit = new Validation();
        Boolean result = unit.isStringTooLong("JHGTGH67HF2");
        assertEquals(false, result);
    }
    @Test
    public void tooLongString4(){
        Validation unit = new Validation();
        Boolean result = unit.isStringTooLong("HTBH2");
        assertEquals(true, result);
    }
    @Test
    public void tooLongString5(){
        Validation unit = new Validation();
        Boolean result = unit.isStringTooLong("hzgt7");
        assertEquals(false, result);
    }

    @Test
    public void flightNumberLength(){
        Validation unit = new Validation();
        Boolean result = unit.flightNumberLength("HTBH2");
        assertEquals(true, result);
    }
    @Test
    public void flightNumberLength2(){
        Validation unit = new Validation();
        Boolean result = unit.flightNumberLength("");
        assertEquals(false, result);
    }

    @Test
    public void flightNumberLength3(){
        Validation unit = new Validation();
        Boolean result = unit.flightNumberLength("768");
        assertEquals(true, result);
    }
    @Test
    public void flightNumberLength4(){
        Validation unit = new Validation();
        Boolean result = unit.flightNumberLength("HZK");
        assertEquals(false, result);
    }

    @Test
    public void isValidNameTest(){
        Validation unit = new Validation();
        Boolean result = unit.isValidName("HZdsadasdasdasdasdasdasdasdaskd");
        assertEquals(false, result);
    }
    @Test
    public void isValidNameTest2(){
        Validation unit = new Validation();
        Boolean result = unit.isValidName("HZdsadasdasdasdadasdasdasdaisd");
        assertEquals(true, result);
    }
    @Test
    public void isValidNameTest3(){
        Validation unit = new Validation();
        Boolean result = unit.isValidName("454");
        assertEquals(false, result);
    }
}
