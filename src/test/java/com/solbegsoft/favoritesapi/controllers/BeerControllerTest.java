package com.solbegsoft.favoritesapi.controllers;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.solbegsoft.favoritesapi.configuration.exceptions.ExceptionMessageCodes;
import com.solbegsoft.favoritesapi.exceptions.BeerEntityNotFoundException;
import com.solbegsoft.favoritesapi.models.dtos.FavoritesBeerDto;
import com.solbegsoft.favoritesapi.models.entities.FavoritesBeer;
import com.solbegsoft.favoritesapi.models.requests.SaveFavoritesBeerRequest;
import com.solbegsoft.favoritesapi.models.requests.UpdateFavoritesBeerRequest;
import com.solbegsoft.favoritesapi.models.requests.dtos.SaveBeerRequestDto;
import com.solbegsoft.favoritesapi.models.response.ResponseApi;
import com.solbegsoft.favoritesapi.repositories.BeerRepository;
import com.solbegsoft.favoritesapi.utils.FavoritesBeerConverter;
import com.solbegsoft.favoritesapi.utils.RequestBeerAndEntityBeerConverter;
import com.solbegsoft.favoritesapi.utils.RequestBeerConverter;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Test for {@link BeerController}
 */
class BeerControllerTest extends AbstractControllerTest {

    /**
     * @see BeerRepository
     */
    @MockBean
    private BeerRepository beerRepository;

    @Override
    protected String getEndPoint() {
        return "/favorites-api/v1/beers";
    }

    /**
     * Test method {@link BeerController#getBeerFavoritesById(UUID, UUID)}
     *
     * @throws Exception exception
     */
    @ParameterizedTest
    @ValueSource(strings = {"4234r32", "rgedd", "_! !3$"})
    void testGetBeerFavoritesById_WhenNotCorrectBeerIdInURL_ShouldReturnStatusBadRequestWithMessage(String stringBeerId) throws Exception {

        mockMvc.perform(get(getEndPoint() + "/" + stringBeerId)
                        .header("userId", stringUserId)
                ).andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.data").value("Invalid UUID string: " + stringBeerId));
    }

    /**
     * Test method {@link BeerController#getBeerFavoritesById(UUID, UUID)}
     *
     * @throws Exception exception
     */
    @Test
    void testGetBeerFavoritesById_IfEntityNotExist_ShouldReturnStatusNotFoundWithMessage() throws Exception {
        FavoritesBeer beer = createFavoritesBeer(10L, userIdUUID, 5, "Beer");

        when(beerRepository.findOneBeerById(userIdUUID, beer.getId()))
                .thenThrow(new BeerEntityNotFoundException(ExceptionMessageCodes.ENTITY_NOT_FOUND, beer.getId()));
        mockMvc.perform(get(getEndPoint() + "/" + beer.getId())
                        .header("userId", stringUserId)
                ).andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.data").value("Not found entity with id " + beer.getId() + "."));
    }

    /**
     * Test method {@link BeerController#getBeerFavoritesById(UUID, UUID)}
     *
     * @throws Exception exception
     */
    @Test
    void testGetBeerFavoritesById_WhenCorrectRequest_ShouldReturnStatusOkAndFavoritesBeer() throws Exception {
        FavoritesBeer beer = createFavoritesBeer(10L, userIdUUID, 5, "Beer");
        FavoritesBeerDto expectedBeer = FavoritesBeerConverter.INSTANCE.toDtoFromFavoritesBeer(beer);
        Optional<FavoritesBeer> optional = Optional.of(beer);

        when(beerRepository.findOneBeerById(userIdUUID, beer.getId())).thenReturn(optional);
        String actualAsString = mockMvc.perform(get(getEndPoint() + "/" + beer.getId())
                        .header("userId", stringUserId)
                ).andDo(print())
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        checkEqualsExpectedFavoritesBeerDtoAndActualString(expectedBeer, actualAsString);
    }

    /**
     * Test method {@link BeerController#saveBeerFavorites(SaveFavoritesBeerRequest, UUID)}
     *
     * @throws Exception exception
     */
    @Test
    void testSaveBeerFavorites_WithoutSaveRequest_ShouldReturnStatusBadRequest() throws Exception {
        mockMvc.perform(post(getEndPoint())
                        .header("userId", stringUserId)
                ).andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.data").value(Matchers.stringContainsInOrder("Required request body is missing")));
    }

    /**
     * Test with not correct rate in Request in method {@link BeerController#saveBeerFavorites(SaveFavoritesBeerRequest, UUID)}
     *
     * @throws Exception exception
     */
    @Test
    void testSaveBeerFavorites_WithNotCorrectRateInSaveRequest_ShouldReturnStatusBadRequest() throws Exception {
        SaveFavoritesBeerRequest request = createSaveBeerRequest();
        request.setRate(7);

        mockMvc.perform(post(getEndPoint())
                        .header("userId", stringUserId)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(request))
                ).andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.data").value("Invalid argument: request"));
    }

    /**
     * Test with correct SaveRequest method {@link BeerController#saveBeerFavorites(SaveFavoritesBeerRequest, UUID)}
     *
     * @throws Exception exception
     */
    @Test
    void testSaveBeerFavorites_WithCorrectSaveRequest_ShouldReturnStatusCreatedAndEntity() throws Exception {
        SaveFavoritesBeerRequest request = createSaveBeerRequest();
        SaveBeerRequestDto requestDto = RequestBeerConverter.convertToSaveRequestDto(userIdUUID, request);

        FavoritesBeer beer = RequestBeerAndEntityBeerConverter.convertSaveRequestToFavoritesBeer(requestDto);
        FavoritesBeer savedBeer = RequestBeerAndEntityBeerConverter.convertSaveRequestToFavoritesBeer(requestDto);
        savedBeer.setId(UUID.randomUUID());
        FavoritesBeerDto expected = FavoritesBeerConverter.INSTANCE.toDtoFromFavoritesBeer(savedBeer);

        when(beerRepository.save(beer)).thenReturn(savedBeer);

        String actualAsString = mockMvc.perform(post(getEndPoint())
                        .header("userId", stringUserId)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(request))
                ).andDo(print())
                .andExpect(status().isCreated())
                .andReturn().getResponse().getContentAsString();
        checkEqualsExpectedFavoritesBeerDtoAndActualString(expected, actualAsString);
    }

    /**
     * Test with correct beerId and Request in method {@link BeerController#updateBeerFavorites(UUID, UUID, UpdateFavoritesBeerRequest)}
     *
     * @throws Exception exception
     */
    @Test
    void testUpdateRateOfBeerFavorites_WithCorrectUpdateRequest_ShouldReturnAcceptedAndFavoritesFoodDto() throws Exception {
        FavoritesBeer beer = createFavoritesBeer(10L, userIdUUID, 5, "Beer");
        UpdateFavoritesBeerRequest request = createUpdateFavoritesBeerRequest(beer);
        FavoritesBeerDto expectedBeer = FavoritesBeerConverter.INSTANCE.toDtoFromFavoritesBeer(beer);
        Optional<FavoritesBeer> optional = Optional.of(beer);

        when(beerRepository.findOneBeerById(userIdUUID, beer.getId())).thenReturn(optional);

        String actualAsString = mockMvc.perform(patch(getEndPoint() + "/" + beer.getId())
                        .header("userId", stringUserId)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(request))
                ).andDo(print())
                .andExpect(status().isAccepted())
                .andReturn().getResponse().getContentAsString();
        checkEqualsExpectedFavoritesBeerDtoAndActualString(expectedBeer, actualAsString);
    }

    /**
     * Test with correct beerId and not correct rate in Request in method {@link BeerController#updateBeerFavorites(UUID, UUID, UpdateFavoritesBeerRequest)}
     *
     * @throws Exception exception
     */
    @Test
    void testUpdateRateOfBeerFavorites_WithNotCorrectUpdateRequest_ShouldReturnStatusBadRequest() throws Exception {
        FavoritesBeer beer = createFavoritesBeer(10L, userIdUUID, 5, "Beer");
        UpdateFavoritesBeerRequest request = createUpdateFavoritesBeerRequest(beer);
        request.setRate(7);
        mockMvc.perform(patch(getEndPoint() + "/" + beer.getId())
                        .header("userId", stringUserId)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(request))
                ).andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("data").value("Invalid argument: request"));
    }

    /**
     * Test with correct beerId and Request in method {@link BeerController#updateBeerFavorites(UUID, UUID, UpdateFavoritesBeerRequest)}
     *
     * @throws Exception exception
     */
    @Test
    void testUpdateRateOfBeerFavorites_WithCorrectBeerIdAndRateInURL_ShouldReturnStatusAcceptedAndFavoritesFoodDto() throws Exception {
        FavoritesBeer beer = createFavoritesBeer(10L, userIdUUID, 5, "Beer");
        FavoritesBeerDto expectedBeer = FavoritesBeerConverter.INSTANCE.toDtoFromFavoritesBeer(beer);
        Optional<FavoritesBeer> optional = Optional.of(beer);
        when(beerRepository.findOneBeerById(userIdUUID, beer.getId())).thenReturn(optional);

        String actualAsString = mockMvc.perform(patch(getEndPoint() + "/" + beer.getId() + "/rate/" + beer.getRate())
                        .header("userId", stringUserId)
                ).andDo(print())
                .andExpect(status().isAccepted())
                .andReturn().getResponse().getContentAsString();

        checkEqualsExpectedFavoritesBeerDtoAndActualString(expectedBeer, actualAsString);
    }

    /**
     * Test With not correct beerId and rate in URL in method {@link BeerController#updateRateOfBeerFavorites(UUID, Integer, UUID)}
     *
     * @throws Exception exception
     */
    @ParameterizedTest
    @ValueSource(strings = {"12346", "vsvsv", "__!-"})
    void testUpdateRateOfBeerFavorites_WithNotCorrectBeerIdAndRateInURL_ShouldReturnStatusBadRequestAndMessage(String stringBeerId) throws Exception {
        mockMvc.perform(patch(getEndPoint() + "/" + stringBeerId + "/rate/" + 7)
                        .header("userId", stringUserId)
                ).andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.data").value("Invalid UUID string: " + stringBeerId));
    }

    /**
     * Test With not correct rate in URL in method {@link BeerController#updateRateOfBeerFavorites(UUID, Integer, UUID)}
     *
     * @throws Exception exception
     */
    @Test
    void testUpdateRateOfBeerFavorites_WithNotCorrectRateInURL_ShouldReturnStatusBadRequestAndMessage() throws Exception {
        UUID beerId = UUID.randomUUID();
        mockMvc.perform(patch(getEndPoint() + "/" + beerId + "/rate/" + 7)
                        .header("userId", stringUserId)
                ).andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.data").value("updateRateOfBeerFavorites.rate: must be less than or equal to 5"));
    }

    /**
     * Test method {@link BeerController#deleteBeerFavoritesById(UUID, UUID)}
     *
     * @throws Exception exception
     */
    @Test
    void testDeleteBeerFavoritesById_ShouldReturnStatusAccepted() throws Exception {
        FavoritesBeer beer = createFavoritesBeer(10L, userIdUUID, 5, "Beer");
        Optional<FavoritesBeer> optional = Optional.of(beer);

        when(beerRepository.findOneBeerById(userIdUUID, beer.getId())).thenReturn(optional);

        mockMvc.perform(delete(getEndPoint() + "/" + beer.getId())
                        .header("userId", stringUserId)
                ).andDo(print())
                .andExpect(status().isAccepted())
                .andExpect(jsonPath("$.data").value(Boolean.TRUE));
        verify(beerRepository, times(1)).deleteOne(userIdUUID, beer.getId());
    }

    /**
     * Create {@link FavoritesBeer}
     *
     * @param beerId beerId
     * @param userId userId
     * @param rate   rate
     * @param name   name
     * @return {@link FavoritesBeer}
     */
    private FavoritesBeer createFavoritesBeer(Long beerId, UUID userId, Integer rate, String name) {

        FavoritesBeer beer = new FavoritesBeer();
        beer.setId(UUID.randomUUID());
        beer.setForeignBeerApiId(beerId);
        beer.setUserId(userId);
        beer.setRate(rate);
        beer.setName(name);
        beer.setAbv(1.0);
        beer.setEbc(2.0);
        beer.setIbu(3.0);
        return beer;
    }

    /**
     * Create {@link SaveFavoritesBeerRequest}
     *
     * @return {@link SaveFavoritesBeerRequest}
     */
    private SaveFavoritesBeerRequest createSaveBeerRequest() {
        SaveFavoritesBeerRequest request = new SaveFavoritesBeerRequest();
        request.setForeignBeerApiId(10L);
        request.setName("Beer");
        request.setRate(5);
        request.setAbv(1.1);
        request.setEbc(2.2);
        request.setIbu(3.3);
        return request;
    }

    /**
     * Create {@link UpdateFavoritesBeerRequest}
     *
     * @return {@link UpdateFavoritesBeerRequest}
     */
    private UpdateFavoritesBeerRequest createUpdateFavoritesBeerRequest(FavoritesBeer beer) {
        UpdateFavoritesBeerRequest request = new UpdateFavoritesBeerRequest();
        request.setId(beer.getId());
        request.setForeignBeerApiId(beer.getForeignBeerApiId());
        request.setName(beer.getName());
        request.setRate(beer.getRate());
        request.setAbv(beer.getAbv());
        request.setEbc(beer.getEbc());
        request.setIbu(beer.getIbu());
        return request;
    }

    /**
     * Check all field of Favorites Beer
     *
     * @param expected expected {@link FavoritesBeerDto}
     * @param actual   actual {@link FavoritesBeerDto}
     */
    private void checkAssertionsEqualsFieldsOfFavoritesBeerDto(FavoritesBeerDto expected, FavoritesBeerDto actual) {
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
     * Ckeck all fields {@link FavoritesBeerDto} and actual string
     *
     * @param expectedBeer   expected {@link FavoritesBeerDto}
     * @param actualAsString string as JSON to parsing
     * @throws JsonProcessingException exception
     */
    private void checkEqualsExpectedFavoritesBeerDtoAndActualString(FavoritesBeerDto expectedBeer, String actualAsString) throws JsonProcessingException {
        TypeReference<ResponseApi<FavoritesBeerDto>> typeReference = new TypeReference<>() {
        };
        FavoritesBeerDto actualBeer = objectMapper.readValue(actualAsString, typeReference).getData();
        checkAssertionsEqualsFieldsOfFavoritesBeerDto(expectedBeer, actualBeer);
    }
}
