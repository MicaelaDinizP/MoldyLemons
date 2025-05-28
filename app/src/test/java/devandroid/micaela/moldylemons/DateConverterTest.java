package devandroid.micaela.moldylemons;

import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

import devandroid.micaela.moldylemons.data.local.DateConverter;

class DateConverterTest {

    @Test
    void givenValidDateObject_whenConvertingFromDate_thenReturnsCorrectLong() {
        Calendar cal = Calendar.getInstance();
        cal.set(2025, Calendar.MAY, 21);
        Date validDate = cal.getTime();

        Long result = DateConverter.fromDate(validDate);

        assertEquals(validDate.getTime(), result);
    }

    @Test
    void givenNullDateObject_whenConvertingFromDate_thenReturnsNull() {
        Date nullDate = null;

        Long result = DateConverter.fromDate(nullDate);

        assertNull(result);
    }

    @Test
    void givenValidLongValue_whenConvertingFromLong_thenReturnsCorrectDate() {
        Calendar cal = Calendar.getInstance();
        cal.set(2025, Calendar.MAY, 21);
        Long validTimestamp = cal.getTimeInMillis();

        Date result = DateConverter.fromLong(validTimestamp);

        assertNotNull(result);
        assertEquals(validTimestamp, result.getTime());
    }

    @Test
    void givenNullLongValue_whenConvertingFromLong_thenReturnsNull() {
        Long nullTimestamp = null;

        Date result = DateConverter.fromLong(nullTimestamp);

        assertNull(result);
    }

    @Test
    void givenEpochLongValue_whenConvertingFromLong_thenReturnsEpochDate() {
        Long epochTimestamp = 0L;

        Date result = DateConverter.fromLong(epochTimestamp);

        assertNotNull(result);
        assertEquals(epochTimestamp, result.getTime());
    }

    @Test
    void givenDateObject_whenConvertedToLongAndBack_thenReturnsSameDate() {
        Calendar cal = Calendar.getInstance();
        cal.set(2022, Calendar.DECEMBER, 15);
        Date originalDate = cal.getTime();

        Long longValue = DateConverter.fromDate(originalDate);
        Date convertedDate = DateConverter.fromLong(longValue);

        assertNotNull(convertedDate);
        assertEquals(originalDate.getTime(), convertedDate.getTime());
    }

    @Test
    void givenLongValue_whenConvertedToDateAndBack_thenReturnsSameLong() {
        Calendar cal = Calendar.getInstance();
        cal.set(2018, Calendar.APRIL, 10);
        Long originalLong = cal.getTimeInMillis();

        Date date = DateConverter.fromLong(originalLong);
        Long convertedLong = DateConverter.fromDate(date);

        assertNotNull(convertedLong);
        assertEquals(originalLong, convertedLong);
    }


}