package com.solbegsoft.favoritesapi.services.impl;


import com.solbegsoft.favoritesapi.configurations.exceptions.ExceptionMessageCodes;
import com.solbegsoft.favoritesapi.exceptions.BeerEntityNotFoundException;
import com.solbegsoft.favoritesapi.exceptions.BeerExistsException;
import com.solbegsoft.favoritesapi.models.dtos.FavoritesBeerDto;
import com.solbegsoft.favoritesapi.models.entities.FavoritesBeer;
import com.solbegsoft.favoritesapi.models.requests.dtos.GetBeerRequestDto;
import com.solbegsoft.favoritesapi.models.requests.dtos.SaveBeerRequestDto;
import com.solbegsoft.favoritesapi.models.requests.dtos.UpdateBeerRequestDto;
import com.solbegsoft.favoritesapi.repositories.BeerRepository;
import com.solbegsoft.favoritesapi.services.BeerService;
import com.solbegsoft.favoritesapi.utils.FavoritesBeerConverter;
import com.solbegsoft.favoritesapi.utils.RequestBeerAndEntityBeerConverter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.UUID;

/**
 * Beer service implemented {@link BeerService}
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class BeerServiceImpl implements BeerService {

    /**
     * @see BeerRepository
     */
    private final BeerRepository beerRepository;

    @Override
    public FavoritesBeerDto getBeerById(UUID userId, UUID beerId) {

        return getFavoritesBeerDtoToResponse(userId, beerId);
    }

    @Override
    public Page<FavoritesBeerDto> getAllBeersByRate(GetBeerRequestDto getRequestDto) {
        Page<FavoritesBeer> result;
        if (isExistRate(getRequestDto)) {
            result = beerRepository.findAllWithPagination(getRequestDto, getRequestDto.getPageable());
        } else {
            result = beerRepository.findAllBySetRatesWithPagination(getRequestDto, getRequestDto.getPageable());
        }
        return result.map(FavoritesBeerConverter.INSTANCE::toDtoFromFavoritesBeer);
    }

    @Override
    public void deleteFavoriteBeer(UUID userId, UUID beerId) {

        FavoritesBeer result = beerRepository.findOneBeerById(userId, beerId)
                .orElseThrow(() -> new BeerEntityNotFoundException(ExceptionMessageCodes.ENTITY_NOT_FOUND, beerId));
        beerRepository.deleteOne(result.getUserId(), result.getId());
    }

    @Override
    public FavoritesBeerDto updateRateFavoritesBeer(UpdateBeerRequestDto requestDto) {
        UUID id = requestDto.getId();
        UUID userId = requestDto.getUserId();

        beerRepository.findOneBeerById(userId, id)
                .orElseThrow(() -> new BeerEntityNotFoundException(ExceptionMessageCodes.ENTITY_NOT_FOUND, id));
        beerRepository.updateRateFavoritesBeer(requestDto);

        return getFavoritesBeerDtoToResponse(requestDto.getUserId(), requestDto.getId());
    }

    @Override
    public FavoritesBeerDto updateFavoriteBeer(UpdateBeerRequestDto requestDto) {
        UUID id = requestDto.getId();
        UUID userId = requestDto.getUserId();

        FavoritesBeer beer = beerRepository.findOneBeerById(userId, id)
                .orElseThrow(() -> new BeerEntityNotFoundException(ExceptionMessageCodes.ENTITY_NOT_FOUND, id));
        FavoritesBeerDto beerDto = RequestBeerAndEntityBeerConverter.convertUpdateRequestToFavoritesBeer(requestDto);
        FavoritesBeerConverter.INSTANCE.updateModel(beerDto, beer);
        beerRepository.updateFavoritesBeer(beer);

        return getFavoritesBeerDtoToResponse(userId, id);
    }

    @Override
    public FavoritesBeerDto saveFavoriteBeer(SaveBeerRequestDto requestDto) {
        UUID userId = requestDto.getUserId();
        Long beerId = requestDto.getForeignBeerApiId();

        if (beerRepository.findByUserAndBeer(userId, beerId).isPresent()) {
            throw new BeerExistsException(ExceptionMessageCodes.BEER_ALREADY_EXIST);
        }
        FavoritesBeer beer = RequestBeerAndEntityBeerConverter.convertSaveRequestToFavoritesBeer(requestDto);
        FavoritesBeer saved = beerRepository.save(beer);

        return FavoritesBeerConverter.INSTANCE.toDtoFromFavoritesBeer(saved);
    }

    /**
     * Checks the rate in the RequestDto
     *
     * @param getRequestDto {@link GetBeerRequestDto}
     * @return boolean
     */
    private boolean isExistRate(GetBeerRequestDto getRequestDto) {
        return Objects.isNull(getRequestDto.getRate()) || getRequestDto.getRate().isEmpty();
    }

    /**
     * Return FavoritesBeerDto
     *
     * @param userId User ID
     * @param beerId Beer ID
     * @return {@link FavoritesBeerDto}
     */
    private FavoritesBeerDto getFavoritesBeerDtoToResponse(UUID userId, UUID beerId) {

        return beerRepository.findOneBeerById(userId, beerId)
                .map(FavoritesBeerConverter.INSTANCE::toDtoFromFavoritesBeer)
                .orElseThrow(() -> new BeerEntityNotFoundException(ExceptionMessageCodes.ENTITY_NOT_FOUND, beerId));
    }
}