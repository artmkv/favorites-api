package com.solbegsoft.favoritesapi.services;


import com.solbegsoft.favoritesapi.models.dtos.FavoritesBeerDto;
import com.solbegsoft.favoritesapi.models.dtos.GetRequestDto;
import com.solbegsoft.favoritesapi.models.dtos.SaveRequestDto;
import com.solbegsoft.favoritesapi.models.dtos.UpdateRequestDto;
import com.solbegsoft.favoritesapi.models.entities.FavoritesBeer;
import com.solbegsoft.favoritesapi.repositories.BeerRepository;
import com.solbegsoft.favoritesapi.utils.FavoritesBeerConverter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import java.util.Objects;
import java.util.Optional;
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
    public FavoritesBeerDto getBeerById(Long userId, UUID id) {

        return beerRepository.findOneBeerById(userId, id)
                .map(FavoritesBeerConverter.INSTANCE::toDto)
                .orElseThrow(() -> new EntityNotFoundException(String.format("Not Found with id %s", id)));
    }

    @Override
    public Page<FavoritesBeerDto> getAllBeersByRate(GetRequestDto getRequestDto) {

        if (Objects.isNull(getRequestDto.getRate()) || getRequestDto.getRate().isEmpty()) {

            return beerRepository.getAllWithPagination(getRequestDto, getRequestDto.getPageable())
                    .map(FavoritesBeerConverter.INSTANCE::toDto);
        }
        return beerRepository.getAllBySetRatesWithPagination(getRequestDto, getRequestDto.getPageable())
                .map(FavoritesBeerConverter.INSTANCE::toDto);
    }

    @Override
    public void deleteFavoriteBeer(Long userId, UUID beerId) {
        beerRepository.deleteOne(userId, beerId);
    }

    @Override
    public FavoritesBeerDto updateRateFavoritesBeer(UpdateRequestDto requestDto) {
        UUID id = requestDto.getRequestFavoritesBeer().getId();
        beerRepository.updateRateFavoriteBeer(requestDto);

        return beerRepository.findById(id)
                .map(FavoritesBeerConverter.INSTANCE::toDto)
                .orElseThrow(() -> new EntityNotFoundException(String.format("Not Found with id %s", id)));
    }

    @Override
    public FavoritesBeerDto updateFavoriteBeer(UpdateRequestDto requestDto) {
        UUID id = requestDto.getRequestFavoritesBeer().getId();

        FavoritesBeer favoritesBeer = beerRepository.findOneBeerById(requestDto.getUserId(), id)
                .orElseThrow(() -> new EntityNotFoundException(String.format("Not Found with id %s", id)));

        updateFavoritesBeerFromRequest(requestDto, favoritesBeer);
        beerRepository.updateFavoriteBeer(favoritesBeer);
        return FavoritesBeerConverter.INSTANCE.toDto(favoritesBeer);
    }


    @Override
    public FavoritesBeerDto saveFavoriteBeer(SaveRequestDto requestDto) {

        Long userId = requestDto.getUserId();
        Long beerId = requestDto.getRequestFavoritesBeer().getBeerId();

        if (beerRepository.findByUserAndBeer(userId, beerId).isPresent()) {
            throw new EntityExistsException(String.format("Entity with beerId %s is exist yet", beerId));
        }
        beerRepository.saveFavoritesBeer(requestDto);

        return beerRepository.findByUserAndBeer(userId, beerId)
                .map(FavoritesBeerConverter.INSTANCE::toDto)
                .orElseThrow(() -> new EntityNotFoundException(String.format("Not Found with beerId %s", beerId)));

    }

    private static void updateFavoritesBeerFromRequest(UpdateRequestDto request, FavoritesBeer favoritesBeer) {

        FavoritesBeerDto dto = FavoritesBeerDto.builder()
                .id(request.getRequestFavoritesBeer().getId())
                .userId(request.getUserId())
                .rate(request.getRequestFavoritesBeer().getRate())
                .build();

        FavoritesBeerConverter.INSTANCE.updateFavoritesBeerFromDto(dto, favoritesBeer);
    }
}