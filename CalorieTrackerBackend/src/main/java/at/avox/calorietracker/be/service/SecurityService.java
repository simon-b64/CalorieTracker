package at.avox.calorietracker.be.service;

import jakarta.enterprise.context.ApplicationScoped;
import lombok.RequiredArgsConstructor;
import org.eclipse.microprofile.jwt.JsonWebToken;

import java.util.Objects;

@ApplicationScoped
@RequiredArgsConstructor
public class SecurityService {
    private final JsonWebToken jwt;

    public String getSubject() {
        String subject = jwt.getClaim("sub");

        if (Objects.isNull(subject)) {
            throw new SecurityException("Subject not found!");
        }

        return subject;
    }
}
