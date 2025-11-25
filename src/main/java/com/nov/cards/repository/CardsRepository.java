package com.nov.cards.repository;

import com.nov.cards.entity.Cards;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CardsRepository extends CrudRepository<Cards, Long> {

     Optional<Cards> findCardByMobileNumber(String mobileNumber);
     Optional<Cards> findCardByCardNumber(String cardNumber);
}
