package at.avox.calorietracker.be.service.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private UUID id;
    private String subject;
    private String name;
}
