package com.nov.cards.dto;

import jakarta.annotation.Nonnull;
import lombok.Data;
//import jakarta.validation.constraints.Pattern;

@Data
public class CardsDto {

//    @Pattern(regexp="^\\d{10}$", message="Mobile number must be 10 digits")
    private String mobileNumber;


    private String cardNumber;
    private String cardType;
    private Integer totalLimit;
    private Integer amountUsed;
    private Integer availableAmount;

}
