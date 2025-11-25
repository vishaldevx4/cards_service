package com.nov.cards.service;

import com.nov.cards.dto.CardsDto;
import com.nov.cards.entity.Cards;

public interface ICardsService {
    void createCard(String mobileNumber);

    CardsDto fetchCardDetails(String mobileNumber);

    void updateCardDetails(CardsDto cardsDto);

    boolean deleteCard(String mobileNumber);
}
