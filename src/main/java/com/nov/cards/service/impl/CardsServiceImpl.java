package com.nov.cards.service.impl;

import com.nov.cards.constants.CardsConstants;
import com.nov.cards.dto.CardsDto;
import com.nov.cards.entity.Cards;
import com.nov.cards.exception.ResourceAlredyExistsException;
import com.nov.cards.exception.ResourceNotFoundException;
import com.nov.cards.mapper.CardsMapper;
import com.nov.cards.repository.CardsRepository;
import com.nov.cards.service.ICardsService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static org.springframework.util.ClassUtils.isPresent;

@Service
@AllArgsConstructor
public class CardsServiceImpl implements ICardsService {

    private CardsRepository cardsRepository;


    @Override
    public void createCard(String mobileNumber) {
        Optional<Cards> fetchCards = cardsRepository.findCardByMobileNumber(mobileNumber);

        if(fetchCards != null) {
            throw new ResourceAlredyExistsException("Card already exists for mobile number: " + mobileNumber);
        }

        Cards newCard = createNewCard(mobileNumber);
        cardsRepository.save(newCard);
    }

    @Override
    public CardsDto fetchCardDetails(String mobileNumber) {

        Cards fetchedCards = cardsRepository.findCardByMobileNumber(mobileNumber).orElseThrow(
                ()-> new ResourceNotFoundException("Card", "MobileNumber", mobileNumber));

        CardsDto fetchCardsDto = CardsMapper.mapToCardsDto(fetchedCards, new CardsDto());

        return fetchCardsDto;
    }

    @Override
    public void updateCardDetails(CardsDto cardsDto) {

        Cards existingCard = cardsRepository.findCardByCardNumber(cardsDto.getCardNumber()).orElseThrow(
                ()-> new ResourceNotFoundException("Card", "MobileNumber", cardsDto.getMobileNumber()));

        Cards updatedCard = CardsMapper.mapCardsDtoToCards(cardsDto, existingCard);
        cardsRepository.save(updatedCard);

    }

    /**
     * Delete card by mobile number
     * @param mobileNumber
     */
    @Override
    public boolean deleteCard(String mobileNumber) {
        Cards existingCard = cardsRepository.findCardByMobileNumber(mobileNumber).orElseThrow(
                ()-> new ResourceNotFoundException("Card", "MobileNumber", mobileNumber));

        cardsRepository.deleteById(existingCard.getCardId());
        return true;
    }


    private Cards createNewCard(String mobileNumber) {
        Cards newCard = new Cards();
        newCard.setMobileNumber(mobileNumber);
        Long cardNumber = (long) (1_000_000_000L + Math.random() * 9_000_000_0000L);
        newCard.setCardNumber(String.valueOf(cardNumber));
        newCard.setCardType(CardsConstants.CARD_TYPE);
        newCard.setTotalLimit(100_000);
        newCard.setAmountUsed(0);
        newCard.setAvailableAmount(newCard.getTotalLimit() - newCard.getAmountUsed());

        return newCard;

    }


}
