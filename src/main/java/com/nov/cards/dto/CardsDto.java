package com.nov.cards.dto;

import jakarta.annotation.Nonnull;
import lombok.Data;
//import jakarta.validation.constraints.Pattern;

@Data
public class CardsDto {

//    @Pattern(regexp="^\\d{10}$", message="Mobile number must be 10 digits")
    private String mobileNumber;


    private Long cardNumber;
    private String cardType;
    private String totalLimit;
    private String amountUsed;
    private String availableAmount;

}
