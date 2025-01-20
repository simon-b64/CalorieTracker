package at.avox.calorietracker.be.web.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.With;

import java.time.LocalDate;
import java.util.UUID;

@Builder
@With
public record CalorieEntryDto(
    UUID id,

    @NotNull
    LocalDate date,

    @NotNull
    Integer amount,

    String note
) {
}
