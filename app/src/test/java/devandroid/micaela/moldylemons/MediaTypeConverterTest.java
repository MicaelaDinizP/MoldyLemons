package devandroid.micaela.moldylemons;

import devandroid.micaela.moldylemons.data.local.MediaTypeConverter;
import devandroid.micaela.moldylemons.data.model.enums.MediaType;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class MediaTypeConverterTest {

    @ParameterizedTest
    @EnumSource(MediaType.class)
    void givenValidMediaType_whenConvertedToString_thenReturnsCorrectString(MediaType type) {
        String result = MediaTypeConverter.toString(type);

        assertEquals(type.getMediaTypeString(), result);
    }

    @Test
    void givenNullMediaType_whenConvertedToString_thenReturnsDefaultString() {
        MediaType type = null;
        String result = MediaTypeConverter.toString(type);

        assertEquals(MediaType.ALL.getMediaTypeString(), result);
    }

    @Test
    void givenValidMediaTypeString_whenConvertedFromString_thenReturnsCorrectMediaType() {
        String value = MediaType.ALL.getMediaTypeString();
        MediaType result = MediaTypeConverter.fromString(value);

        assertEquals(MediaType.ALL, result);
    }

    @Test
    void givenNullString_whenConvertedFromString_thenReturnsDefaultMediaType() {
        String value = null;
        MediaType result = MediaTypeConverter.fromString(value);

        assertEquals(MediaType.ALL, result);
    }

    @Test
    void givenEmptyString_whenConvertedFromString_thenReturnsDefaultMediaType() {
        String value = "";
        MediaType result = MediaTypeConverter.fromString(value);

        assertEquals(MediaType.ALL, result);
    }

    @Test
    void givenInvalidString_whenConvertedFromString_thenReturnsDefaultMediaType() {
        String value = "invalid-type";
        MediaType result = MediaTypeConverter.fromString(value);

        assertEquals(MediaType.ALL, result);
    }

    @Test
    void givenMediaType_whenConvertedToStringAndBack_thenReturnsSameMediaType() {
        MediaType originalType = MediaType.ANIME;
        String string = MediaTypeConverter.toString(originalType);
        MediaType converted = MediaTypeConverter.fromString(string);

        assertEquals(originalType, converted);
    }

    @Test
    void givenMediaTypeString_whenConvertedFromStringAndBack_thenReturnsSameString() {
        String original = MediaType.SERIES.getMediaTypeString();
        MediaType mediaType = MediaTypeConverter.fromString(original);
        String converted = MediaTypeConverter.toString(mediaType);

        assertEquals(original, converted);
    }
}
