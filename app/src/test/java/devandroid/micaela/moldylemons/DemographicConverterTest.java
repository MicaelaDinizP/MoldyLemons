package devandroid.micaela.moldylemons;

import devandroid.micaela.moldylemons.data.local.DemographicConverter;
import devandroid.micaela.moldylemons.data.model.enums.Demographic;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DemographicConverterTest {

    @Test
    void givenValidDemographic_whenConvertedToId_thenReturnsCorrectId() {
        Demographic demographic = Demographic.SEINEN;
        Integer result = DemographicConverter.toId(demographic);

        assertEquals(demographic.getId(), result);
    }

    @Test
    void givenNullDemographic_whenConvertedToId_thenReturnsNull() {
        Demographic demographic = null;
        Integer result = DemographicConverter.toId(demographic);

        assertNull(result);
    }

    @Test
    void givenValidId_whenConvertedFromId_thenReturnsCorrectDemographic() {
        int validId = Demographic.JOSEI.getId();
        Demographic result = DemographicConverter.fromId(validId);

        assertEquals(Demographic.JOSEI, result);
    }

    @Test
    void givenNullId_whenConvertedFromId_thenReturnsNull() {
        Integer id = null;
        Demographic result = DemographicConverter.fromId(id);

        assertNull(result);
    }

    @Test
    void givenInvalidNegativeId_whenConvertedFromId_thenReturnsNull() {
        int invalidId = -1;
        Demographic result = DemographicConverter.fromId(invalidId);

        assertNull(result);
    }

    @Test
    void givenNonexistentId_whenConvertedFromId_thenReturnsNull() {
        int nonexistentId = 999;
        Demographic result = DemographicConverter.fromId(nonexistentId);

        assertNull(result);
    }

    @Test
    void givenDemographic_whenConvertedToIdAndBack_thenReturnsSameDemographic() {
        Demographic original = Demographic.SHOUJO;
        Integer id = DemographicConverter.toId(original);
        Demographic converted = DemographicConverter.fromId(id);

        assertNotNull(converted);
        assertEquals(original, converted);
    }

    @Test
    void givenId_whenConvertedToDemographicAndBack_thenReturnsSameId() {
        Integer originalId = Demographic.SHOUNEN.getId();
        Demographic demographic = DemographicConverter.fromId(originalId);
        Integer convertedId = DemographicConverter.toId(demographic);

        assertNotNull(convertedId);
        assertEquals(originalId, convertedId);
    }
}
