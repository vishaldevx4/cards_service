package com.nov.cards.controller;

import com.nov.cards.service.ICardsService;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.v3.oas.annotations.Operation;

@RestController
@NoArgsConstructor
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
    public ResponseEntity<Object> createCard(
            @Pattern(regexp = "^\\d{10}$", message = "Invalid mobile number format")
            @RequestParam String mobileNumber) {

        iCardsService.createCard(mobileNumber);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body("Card created for mobile number: " + mobileNumber);
    }
}
