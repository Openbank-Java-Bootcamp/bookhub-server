package com.ironhack.bookhubserver.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateRatingDTO {
    @NotNull
    private String comment;
    @NotNull
    private int points;
}
