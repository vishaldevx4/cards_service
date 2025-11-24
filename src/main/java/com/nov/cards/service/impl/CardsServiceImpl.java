package com.nov.cards.service.impl;

import com.nov.cards.constants.CardsConstants;
import com.nov.cards.entity.Cards;
import com.nov.cards.exception.ResourceAlredyExistsException;
import com.nov.cards.repository.CardsRepository;
import com.nov.cards.service.ICardsService;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import static org.springframework.util.ClassUtils.isPresent;

@Service
@NoArgsConstructor
public class CardsServiceImpl implements ICardsService {

    private CardsRepository cardsRepository;


    @Override
    public void createCard(String mobileNumber) {
        Cards fetchCards = cardsRepository.findCardByMobileNumber(mobileNumber);

        if(fetchCards != null) {
            throw new ResourceAlredyExistsException("Card already exists for mobile number: " + mobileNumber);
        }

        Cards newCard = createNewCard(mobileNumber);
        cardsRepository.save(newCard);
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
