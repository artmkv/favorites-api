package com.solbegsoft.favoritesapi.utils;


import com.solbegsoft.favoritesapi.models.dtos.FavoritesBeerDto;
import com.solbegsoft.favoritesapi.models.entities.FavoritesBeer;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

/**
 * Test for {@link FavoritesBeerConverter}
 */
class FavoritesBeerConverterTest {

    /**
     * Test method toDtoFromFavoritesBeer
     */
    @Test
    void toDtoFromFavoritesBeer() {
        FavoritesBeer actual = createFavoritesBeer();
        FavoritesBeerDto expected = FavoritesBeerConverter.INSTANCE.toDtoFromFavoritesBeer(actual);

        assertEquals(expected.getId(), actual.getId());
        assertEquals(expected.getUserId(), actual.getUserId());
        assertEquals(expected.getForeignBeerApiId(), actual.getForeignBeerApiId());
        assertEquals(expected.getRate(), actual.getRate());
        assertEquals(expected.getName(), actual.getName());
        assertEquals(expected.getAbv(), actual.getAbv());
        assertEquals(expected.getEbc(), actual.getEbc());
        assertEquals(expected.getIbu(), actual.getIbu());

    }

    /**
     * Test method getFavoritesFoodFromDto
     */
    @Test
    void toFavoritesBeerFromDto() {
        FavoritesBeerDto actual = createFavoritesBeerDto();
        FavoritesBeer expected = FavoritesBeerConverter.INSTANCE.toFavoritesBeerFromDto(actual);

        assertEquals(expected.getId(), actual.getId());
        assertEquals(expected.getUserId(), actual.getUserId());
        assertEquals(expected.getForeignBeerApiId(), actual.getForeignBeerApiId());
        assertEquals(expected.getRate(), actual.getRate());
        assertEquals(expected.getName(), actual.getName());
        assertEquals(expected.getAbv(), actual.getAbv());
        assertEquals(expected.getEbc(), actual.getEbc());
        assertEquals(expected.getIbu(), actual.getIbu());

    }

    /**
     * Test method updateModel
     */
    @Test
    void testUpdateModel() {
        FavoritesBeer actual = createFavoritesBeer();
        FavoritesBeerDto expected = createFavoritesBeerDto();

        assertNotEquals(expected.getId(), actual.getId());
        assertNotEquals(expected.getUserId(), actual.getUserId());
        assertNotEquals(expected.getForeignBeerApiId(), actual.getForeignBeerApiId());
        assertNotEquals(expected.getRate(), actual.getRate());
        assertNotEquals(expected.getName(), actual.getName());
        assertNotEquals(expected.getAbv(), actual.getAbv());
        assertNotEquals(expected.getEbc(), actual.getEbc());
        assertNotEquals(expected.getIbu(), actual.getIbu());

        FavoritesBeerConverter.INSTANCE.updateModel(expected, actual);

        assertEquals(expected.getId(), actual.getId());
        assertEquals(expected.getUserId(), actual.getUserId());
        assertEquals(expected.getForeignBeerApiId(), actual.getForeignBeerApiId());
        assertEquals(expected.getRate(), actual.getRate());
        assertEquals(expected.getName(), actual.getName());
        assertEquals(expected.getAbv(), actual.getAbv());
        assertEquals(expected.getEbc(), actual.getEbc());
        assertEquals(expected.getIbu(), actual.getIbu());

    }

    /**
     * Create {@link FavoritesBeerDto}
     *
     * @return {@link FavoritesBeerDto}
     */
    private FavoritesBeerDto createFavoritesBeerDto() {
        FavoritesBeerDto actual = new FavoritesBeerDto();
        actual.setId(UUID.randomUUID());
        actual.setUserId(UUID.randomUUID());
        actual.setForeignBeerApiId(128L);
        actual.setRate(3);
        actual.setName("ABC");
        actual.setAbv(14.4);
        actual.setEbc(1.1);
        actual.setIbu(5.4);
        return actual;
    }

    /**
     * Create {@link FavoritesBeer}
     *
     * @return {@link FavoritesBeer}
     */
    private FavoritesBeer createFavoritesBeer() {
        FavoritesBeer actual = new FavoritesBeer();
        actual.setId(UUID.randomUUID());
        actual.setUserId(UUID.randomUUID());
        actual.setForeignBeerApiId(55L);
        actual.setRate(1);
        actual.setName("EF");
        actual.setAbv(5.5);
        actual.setEbc(2.3);
        actual.setIbu(3.7);
        return actual;
    }
}
