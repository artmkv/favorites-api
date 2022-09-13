package com.solbegsoft.favoritesapi.services.impl;


import com.solbegsoft.favoritesapi.exceptions.ExceptionMessagesConstant;
import com.solbegsoft.favoritesapi.models.dtos.FavoritesBeerDto;
import com.solbegsoft.favoritesapi.models.entities.FavoritesBeer;
import com.solbegsoft.favoritesapi.models.requests.dtos.GetBeerRequestDto;
import com.solbegsoft.favoritesapi.models.requests.dtos.SaveBeerRequestDto;
import com.solbegsoft.favoritesapi.models.requests.dtos.UpdateBeerRequestDto;
import com.solbegsoft.favoritesapi.repositories.BeerRepository;
import com.solbegsoft.favoritesapi.services.BeerService;
import com.solbegsoft.favoritesapi.utils.FavoritesBeerConverter;
import com.solbegsoft.favoritesapi.utils.MessageUtils;
import com.solbegsoft.favoritesapi.utils.UpdateBeerRequestEntityConverter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
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

    /**
     * @see MessageUtils
     */
    private final MessageUtils messages;

    @Override
    public FavoritesBeerDto getBeerById(UUID userId, UUID id) {

        return beerRepository.findOneBeerById(userId, id)
                .map(FavoritesBeerConverter.INSTANCE::toDtoFromFavoritesBeer)
                .orElseThrow(() -> new EntityNotFoundException(String.format(messages.getMessage(ExceptionMessagesConstant.ENTITY_NOT_FOUND), id)));
    }

    @Override
    public Page<FavoritesBeerDto> getAllBeersByRate(GetBeerRequestDto getRequestDto) {

        if (Objects.isNull(getRequestDto.getRate()) || getRequestDto.getRate().isEmpty()) {

            return beerRepository.findAllWithPagination(getRequestDto, getRequestDto.getPageable())
                    .map(FavoritesBeerConverter.INSTANCE::toDtoFromFavoritesBeer);
        }
        return beerRepository.findAllBySetRatesWithPagination(getRequestDto, getRequestDto.getPageable())
                .map(FavoritesBeerConverter.INSTANCE::toDtoFromFavoritesBeer);
    }

    @Override
    public void deleteFavoriteBeer(UUID userId, UUID id) {

        FavoritesBeer result = beerRepository.findOneBeerById(userId, id)
                .orElseThrow(() -> new EntityNotFoundException(String.format(messages.getMessage(ExceptionMessagesConstant.ENTITY_NOT_FOUND), id)));

        beerRepository.deleteOne(result.getUserId(), result.getId());
    }

    @Override
    public FavoritesBeerDto updateRateFavoritesBeer(UpdateBeerRequestDto requestDto) {
        UUID id = requestDto.getId();
        UUID userId = requestDto.getUserId();
        beerRepository.updateRateFavoritesBeer(requestDto);

        return beerRepository.findOneBeerById(userId, id)
                .map(FavoritesBeerConverter.INSTANCE::toDtoFromFavoritesBeer)
                .orElseThrow(() -> new EntityNotFoundException(String.format(messages.getMessage(ExceptionMessagesConstant.ENTITY_NOT_FOUND), id)));
    }

    @Override
    public FavoritesBeerDto updateFavoriteBeer(UpdateBeerRequestDto requestDto) {

        UUID id = requestDto.getId();
        UUID userId = requestDto.getUserId();
        FavoritesBeer favoritesBeer = beerRepository.findOneBeerById(userId, id)
                .orElseThrow(() -> new EntityNotFoundException(String.format(messages.getMessage(ExceptionMessagesConstant.ENTITY_NOT_FOUND), id)));

        FavoritesBeerDto favoritesBeerDto = UpdateBeerRequestEntityConverter.INSTANCE.convertRequestToFavoritesBeer(requestDto);
        FavoritesBeerConverter.INSTANCE.updateModel(favoritesBeerDto, favoritesBeer);

        beerRepository.updateFavoritesBeer(favoritesBeer);

        return beerRepository.findOneBeerById(userId, id)
                .map(FavoritesBeerConverter.INSTANCE::toDtoFromFavoritesBeer)
                .orElseThrow(() -> new EntityNotFoundException(String.format(messages.getMessage(ExceptionMessagesConstant.ENTITY_NOT_FOUND), id)));
    }

    @Override
    public FavoritesBeerDto saveFavoriteBeer(SaveBeerRequestDto requestDto) {

        UUID userId = requestDto.getUserId();
        Long beerId = requestDto.getForeignBeerApiId();

        if (beerRepository.findByUserAndBeer(userId, beerId).isPresent()) {
            throw new EntityExistsException(String.format(messages.getMessage(ExceptionMessagesConstant.ENTITY_ALREADY_EXIST), beerId));
        }
        beerRepository.saveFavoritesBeer(requestDto);

        return beerRepository.findByUserAndBeer(userId, beerId)
                .map(FavoritesBeerConverter.INSTANCE::toDtoFromFavoritesBeer)
                .orElseThrow(() -> new EntityNotFoundException(String.format(messages.getMessage(ExceptionMessagesConstant.ENTITY_NOT_FOUND), beerId)));
    }

}