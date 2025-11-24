package com.nov.cards.repository;

import com.nov.cards.entity.Cards;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CardsRepository extends CrudRepository<Cards, Long> {

     Cards findCardByMobileNumber(String mobileNumber);
}
