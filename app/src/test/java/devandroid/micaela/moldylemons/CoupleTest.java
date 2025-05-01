package devandroid.micaela.moldylemons;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

import devandroid.micaela.moldylemons.data.model.Couple;
import devandroid.micaela.moldylemons.data.model.Media;
import devandroid.micaela.moldylemons.data.model.enums.Genre;
import devandroid.micaela.moldylemons.data.model.enums.MediaType;

public class CoupleTest {
    private Couple couple;
    private int id;
    private String partnerOne;
    private String partnerTwo;
    private Date validDate;
    private String login;
    private String password;

    @BeforeEach
    void setUp() {
        Calendar cal = Calendar.getInstance();
        cal.set(2020, Calendar.JANUARY, 1);
        this.validDate = cal.getTime();
        this.partnerOne = "Alice";
        this.partnerTwo = "Bob";
        this.login = "user_123";
        this.password = "pass1234";
        this.id = 1;
        this.couple = new Couple(this.partnerOne, this.partnerTwo, this.validDate, this.login, this.password);
        this.couple.setId(this.id);
    }

    @Test
    void givenAllValidData_whenCreatingCouple_thenCreateSucessfully(){
        Couple validCouple = new Couple(this.partnerOne, this.partnerTwo, this.validDate, this.login, this.password);
        validCouple.setId(this.id);

        assertEquals(this.partnerOne, validCouple.getPartnerOneName());
        assertEquals(this.partnerTwo, validCouple.getPartnerTwoName());
        assertEquals(this.login, validCouple.getLogin());
        assertEquals(this.password, validCouple.getPassword());
        assertEquals(this.validDate, validCouple.getAnniversaryDate());
        assertEquals(this.id, validCouple.getId());
    }

    @ParameterizedTest
    @ValueSource(strings = {"", " ", "   ", "\t", "\n"})
    void givenEmptyPartnerOne_whenCreatingCouple_thenCreateSucessfully(String invalidPartnerOne){
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Couple(invalidPartnerOne, this.partnerTwo, this.validDate, this.login, this.password);
        });
        assertEquals("Partner one's name cannot be null or empty.", exception.getMessage());
    }

    @ParameterizedTest
    @ValueSource(strings = {"", " ", "   ", "\t", "\n"})
    void givenEmptyPartnerTwo_whenCreatingCouple_thenCreateSucessfully(String invalidPartnerTwo){
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Couple(this.partnerOne, invalidPartnerTwo, this.validDate, this.login, this.password);
        });
        assertEquals("Partner two's name cannot be null or empty.", exception.getMessage());
    }

    @Test
    void givenInvalidValidDate_whenCreatingCouple_thenCreateSucessfully(){
        Calendar cal = Calendar.getInstance();
        cal.set(4000, Calendar.JANUARY, 3);
        Date invalidDate = cal.getTime();
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Couple(this.partnerOne, this.partnerTwo, invalidDate, this.login, this.password);
        });
        assertEquals("Anniversary date cannot be null or in the future.", exception.getMessage());
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "1user", "_user", ".user", "us", "ab",
            "user@123", "a!bcd", "a bc", "a12345678901234567890", "-user", "a123456789012345678901", "",
            " ", "   ", "\t", "\n"
    })
    void givenInvalidLogin_whenCreatingCouple_thenThrowsIllegalArgumentException(String invalidLogin) {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Couple(partnerOne, partnerTwo, validDate, invalidLogin, password);
        });
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "12345678", "abcdefgh", "abc123", "abc@ 1234", "abc123456789012345678901234567890",
            "abc123!{}", "", "        ", " ", "   ", "\t", "\n"
    })
    void givenInvalidPassword_whenCreatingCouple_thenThrowsIllegalArgumentException(String invalidPassword) {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Couple(partnerOne, partnerTwo, validDate, login, invalidPassword);
        });
    }

    @Test
    void givenNullFields_whenCreatingCouple_thenCreateSucessfully(){
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Couple(null, null, null, null, null);
        });
    }

    @ParameterizedTest
    @ValueSource( ints = { 2, 30, 300, 3000 } )
    void givenValidPassword_whenCreatingCouple_thenUpdateSuccessfully(int validId) {
        this.couple.setId(validId);
        assertEquals(validId, this.couple.getId());
    }

    @ParameterizedTest
    @ValueSource( ints = { 0, -1 } )
    void givenInvalidId_whenSettingId_thenThrowException(int invalidId) {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            this.couple.setId(invalidId);
        });
        assertEquals("ID must be greater than zero.", exception.getMessage());
    }

    @ParameterizedTest
    @ValueSource(strings = {"", " ", "   ", "\t", "\n"})
    void givenInvalidPartnerOneName_whenSettingPartnerOneName_thenThrowsIllegalArgumentException(String invalidName) {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            couple.setPartnerOneName(invalidName);
        });
        assertEquals("Partner one's name cannot be null or empty.", exception.getMessage());
    }

    @ParameterizedTest
    @ValueSource(strings = {"", " ", "   ", "\t", "\n"})
    void givenInvalidPartnerTwoName_whenSettingPartnerTwoName_thenThrowsIllegalArgumentException(String invalidName) {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            couple.setPartnerTwoName(invalidName);
        });
        assertEquals("Partner two's name cannot be null or empty.", exception.getMessage());
    }

    @Test
    void givenValidPartnerOneName_whenSettingPartnerOneName_thenUpdateSuccessfully() {
        String newName = "Charlie";
        couple.setPartnerOneName(newName);
        assertEquals(newName, couple.getPartnerOneName());
    }

    @Test
    void givenValidPartnerTwoName_whenSettingPartnerTwoName_thenUpdateSuccessfully() {
        String newName = "Diana";
        couple.setPartnerTwoName(newName);
        assertEquals(newName, couple.getPartnerTwoName());
    }

    @Test
    void givenNullAnniversaryDate_whenSettingAnniversaryDate_thenThrowsIllegalArgumentException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            couple.setAnniversaryDate(null);
        });
        assertEquals("Anniversary date cannot be null or in the future.", exception.getMessage());
    }

    @Test
    void givenFutureAnniversaryDate_whenSettingAnniversaryDate_thenThrowsIllegalArgumentException() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_YEAR, 1);
        Date futureDate = cal.getTime();

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            couple.setAnniversaryDate(futureDate);
        });
        assertEquals("Anniversary date cannot be null or in the future.", exception.getMessage());
    }

    @Test
    void givenValidAnniversaryDate_whenSettingAnniversaryDate_thenUpdateSuccessfully() {
        Calendar cal = Calendar.getInstance();
        cal.set(2022, Calendar.MARCH, 10);
        Date validDate = cal.getTime();

        couple.setAnniversaryDate(validDate);
        assertEquals(validDate, couple.getAnniversaryDate());
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "1user", "_user", ".user", "us", "ab",
            "user@123", "a!bcd", "a bc", "a12345678901234567890", "-user", "a123456789012345678901", "",
            " ", "   ", "\t", "\n"
    })
    void givenInvalidLogin_whenSettingLogin_thenThrowsIllegalArgumentException(String invalidLogin) {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            couple.setLogin(invalidLogin);
        });
    }

    @ParameterizedTest
    @ValueSource(strings = {"user_123", "abcd", "abc_123", "userName"})
    void givenValidLogin_whenSettingLogin_thenUpdateSuccessfully(String validLogin) {
        couple.setLogin(validLogin);
        assertEquals(validLogin, couple.getLogin());
    }
    @Test
    void givenNullLogin_whenSettingLogin_thenThrowException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            couple.setLogin(null);
        });
        assertEquals("Login cannot be null or empty.", exception.getMessage());
    }


    @ParameterizedTest
    @ValueSource(strings = {
            "12345678", "abcdefgh", "abc123", "abc@ 1234", "abc123456789012345678901234567890",
            "abc123!{}", "", "        ", " ", "   ", "\t", "\n"
    })
    void givenInvalidPassword_whenSettingPassword_thenThrowsIllegalArgumentException(String invalidPassword) {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            couple.setPassword(invalidPassword);
        });
    }

    @ParameterizedTest
    @ValueSource(strings = {"abc12345", "user1234", "pass2024", "java2023"})
    void givenValidPassword_whenSettingPassword_thenUpdateSuccessfully(String validPassword) {
        this.couple.setPassword(validPassword);
        assertEquals(validPassword, couple.getPassword());
    }

    @Test
    void givenNullPassword_whenSettingPassword_thenThrowException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            couple.setPassword(null);
        });
        assertEquals("Password cannot be null or empty.", exception.getMessage());
    }

}
