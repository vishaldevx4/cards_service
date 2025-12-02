package com.nov.cards.controller;

import com.nov.cards.constants.CardsConstants;
import com.nov.cards.dto.CardsDto;
import com.nov.cards.dto.ResponseDto;
import com.nov.cards.service.ICardsService;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;

@RestController
@AllArgsConstructor
@RequestMapping("/api/cards")
public class CardsController {

    private ICardsService iCardsService;

    @Operation(summary = "Create a new card for a customer using their mobile number")
    @Schema(description = "Endpoint to create a new card associated with the provided mobile number")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Card created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid mobile number provided")
    })
    @PostMapping("/createCard")
    public ResponseEntity<ResponseDto> createCard(
            @Pattern(regexp = "^\\d{10}$", message = "Invalid mobile number format")
            @RequestParam String mobileNumber) {

        iCardsService.createCard(mobileNumber);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDto(CardsConstants.STATUS_201, CardsConstants.MESSAGE_201));
    }

    //fetch cards
    @Schema(description = "Endpoint to fetch card details associated with the provided mobile number")
    @Operation(summary = "Fetch card details for a customer using their mobile number")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Card details fetched successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid mobile number provided")
    })
    @GetMapping("/getCards")
    public ResponseEntity<CardsDto> fetchCards(
            @Pattern(regexp = "^\\d{10}$", message = "Invalid mobile number format")
            @RequestParam String mobileNumber) {
        var fetchCardsDto = iCardsService.fetchCardDetails(mobileNumber);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(fetchCardsDto);
    }

    //update card
    @Schema(description = "Endpoint to update card details")
    @Operation(summary = "Update card details for a customer")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Card details updated successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid card details provided")
    })
    @PutMapping("/updateCard")
    public ResponseEntity<ResponseDto> updateCard(
            @RequestBody(required = true) CardsDto cardsDto) {
        boolean isUpdated = iCardsService.updateCardDetails(cardsDto);

        if(!isUpdated) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(new ResponseDto(CardsConstants.STATUS_400, CardsConstants.MESSAGE_400));
        }else {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDto(CardsConstants.STATUS_200, "Card details updated for card number: " + cardsDto.getCardNumber()));
        }

    }


    @Schema(description = "Endpoint to delete a card associated with the provided mobile number")
    @Operation(summary = "Delete a card for a customer using their mobile number")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Card deleted successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid mobile number provided")
    })
    @DeleteMapping("/deleteCard")
    public ResponseEntity<ResponseDto> deleteCard(
            @Pattern(regexp = "^\\d{10}$", message = "Invalid mobile number format")
            @RequestParam String mobileNumber) {
        boolean isCardDeleted = iCardsService.deleteCard(mobileNumber);
        if(!isCardDeleted) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(new ResponseDto(CardsConstants.STATUS_400, CardsConstants.MESSAGE_400));
        }

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ResponseDto(CardsConstants.STATUS_201, CardsConstants.MESSAGE_201));

    }

}
