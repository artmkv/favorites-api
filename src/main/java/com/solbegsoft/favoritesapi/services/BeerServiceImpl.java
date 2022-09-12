package com.solbegsoft.favoritesapi.services;


import com.solbegsoft.favoritesapi.exceptions.ExceptionMessagesConstant;
import com.solbegsoft.favoritesapi.models.dtos.FavoritesBeerDto;
import com.solbegsoft.favoritesapi.models.entities.FavoritesBeer;
import com.solbegsoft.favoritesapi.models.requests.dtos.GetRequestDto;
import com.solbegsoft.favoritesapi.models.requests.dtos.SaveRequestDto;
import com.solbegsoft.favoritesapi.models.requests.dtos.UpdateRequestDto;
import com.solbegsoft.favoritesapi.repositories.BeerRepository;
import com.solbegsoft.favoritesapi.utils.FavoritesBeerConverter;
import com.solbegsoft.favoritesapi.utils.MessageUtils;
import com.solbegsoft.favoritesapi.utils.UpdateRequestEntityConverter;
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
    public Page<FavoritesBeerDto> getAllBeersByRate(GetRequestDto getRequestDto) {

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
    public FavoritesBeerDto updateRateFavoritesBeer(UpdateRequestDto requestDto) {
        UUID id = requestDto.getId();
        UUID userId = requestDto.getUserId();
        beerRepository.updateRateFavoritesBeer(requestDto);

        return beerRepository.findOneBeerById(userId, id)
                .map(FavoritesBeerConverter.INSTANCE::toDtoFromFavoritesBeer)
                .orElseThrow(() -> new EntityNotFoundException(String.format(messages.getMessage(ExceptionMessagesConstant.ENTITY_NOT_FOUND), id)));
    }

    @Override
    public FavoritesBeerDto updateFavoriteBeer(UpdateRequestDto requestDto) {

        UUID id = requestDto.getId();
        UUID userId = requestDto.getUserId();
        beerRepository.findOneBeerById(userId, id)
                .orElseThrow(() -> new EntityNotFoundException(String.format(messages.getMessage(ExceptionMessagesConstant.ENTITY_NOT_FOUND), id)));

        FavoritesBeer entity = UpdateRequestEntityConverter.INSTANCE.convertRequestToFavoritesBeer(requestDto);
        beerRepository.updateFavoritesBeer(entity);

        return beerRepository.findOneBeerById(userId, id)
                .map(FavoritesBeerConverter.INSTANCE::toDtoFromFavoritesBeer)
                .orElseThrow(() -> new EntityNotFoundException(String.format(messages.getMessage(ExceptionMessagesConstant.ENTITY_NOT_FOUND), id)));
    }

    @Override
    public FavoritesBeerDto saveFavoriteBeer(SaveRequestDto requestDto) {

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