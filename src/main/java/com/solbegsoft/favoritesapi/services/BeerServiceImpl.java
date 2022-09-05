package com.solbegsoft.favoritesapi.services;


import com.solbegsoft.favoritesapi.models.dtos.FavoritesBeerDto;
import com.solbegsoft.favoritesapi.models.dtos.GetRequestDto;
import com.solbegsoft.favoritesapi.models.dtos.SaveRequestDto;
import com.solbegsoft.favoritesapi.models.dtos.UpdateRequestDto;
import com.solbegsoft.favoritesapi.models.entities.FavoritesBeer;
import com.solbegsoft.favoritesapi.repositories.BeerRepository;
import com.solbegsoft.favoritesapi.utils.ExceptionMessagesConstant;
import com.solbegsoft.favoritesapi.utils.FavoritesBeerConverter;
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

    @Override
    public FavoritesBeerDto getBeerById(UUID userId, UUID id) {

        return beerRepository.findOneBeerById(userId, id)
                .map(FavoritesBeerConverter.INSTANCE::toDtoFromFavoritesBeer)
                .orElseThrow(() -> new EntityNotFoundException(String.format(ExceptionMessagesConstant.ENTITY_NOT_FOUND, id)));
    }

    @Override
    public Page<FavoritesBeerDto> getAllBeersByRate(GetRequestDto getRequestDto) {

        if (Objects.isNull(getRequestDto.getRate()) || getRequestDto.getRate().isEmpty()) {

            return beerRepository.getAllWithPagination(getRequestDto, getRequestDto.getPageable())
                    .map(FavoritesBeerConverter.INSTANCE::toDtoFromFavoritesBeer);
        }
        return beerRepository.getAllBySetRatesWithPagination(getRequestDto, getRequestDto.getPageable())
                .map(FavoritesBeerConverter.INSTANCE::toDtoFromFavoritesBeer);
    }

    @Override
    public void deleteFavoriteBeer(UUID userId, UUID id) {

        FavoritesBeer result = beerRepository.findOneBeerById(userId, id)
                .orElseThrow(() -> new EntityNotFoundException(
                        String.format(ExceptionMessagesConstant.ENTITY_NOT_FOUND, id)));
        beerRepository.deleteOne(result.getUserId(), result.getId());
    }

    @Override
    public FavoritesBeerDto updateRateFavoritesBeer(UpdateRequestDto requestDto) {
        UUID id = requestDto.getRequestFavoritesBeer().getId();
        UUID userId = requestDto.getUserId();
        beerRepository.updateRateFavoriteBeer(requestDto);

        return beerRepository.findOneBeerById(userId, id)
                .map(FavoritesBeerConverter.INSTANCE::toDtoFromFavoritesBeer)
                .orElseThrow(() -> new EntityNotFoundException(String.format(ExceptionMessagesConstant.ENTITY_NOT_FOUND, id)));
    }

    @Override
    public FavoritesBeerDto updateFavoriteBeer(UpdateRequestDto requestDto) {

        UUID id = requestDto.getRequestFavoritesBeer().getId();

        FavoritesBeer favoritesBeer = beerRepository.findOneBeerById(requestDto.getUserId(), id)
                .orElseThrow(() -> new EntityNotFoundException(String.format(ExceptionMessagesConstant.ENTITY_NOT_FOUND, id)));

        updateFavoritesBeerFromRequest(requestDto, favoritesBeer);
        beerRepository.updateFavoriteBeer(favoritesBeer);
        return FavoritesBeerConverter.INSTANCE.toDtoFromFavoritesBeer(favoritesBeer);
    }


    @Override
    public FavoritesBeerDto saveFavoriteBeer(SaveRequestDto requestDto) {

        UUID userId = requestDto.getUserId();
        Long beerId = requestDto.getRequestFavoritesBeer().getBeerId();

        if (beerRepository.findByUserAndBeer(userId, beerId).isPresent()) {
            throw new EntityExistsException(String.format(ExceptionMessagesConstant.ENTITY_ALREADY_EXIST, beerId));
        }
        beerRepository.saveFavoritesBeer(requestDto);

        return beerRepository.findByUserAndBeer(userId, beerId)
                .map(FavoritesBeerConverter.INSTANCE::toDtoFromFavoritesBeer)
                .orElseThrow(() -> new EntityNotFoundException(String.format(ExceptionMessagesConstant.ENTITY_NOT_FOUND, beerId)));
    }

    // TODO: 05.09.2022 конвертер?
    private static void updateFavoritesBeerFromRequest(UpdateRequestDto request, FavoritesBeer favoritesBeer) {

        FavoritesBeerDto dto = FavoritesBeerDto.builder()
                .id(request.getRequestFavoritesBeer().getId())
                .userId(request.getUserId())
                .beerId(request.getRequestFavoritesBeer().getBeerId())
                .rate(request.getRequestFavoritesBeer().getRate())
                .build();

        FavoritesBeerConverter.INSTANCE.updateFavoritesBeerFromDto(dto, favoritesBeer); // TODO: 05.09.2022 ??????
    }
}