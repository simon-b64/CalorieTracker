package at.avox.calorietracker.be.service;

import at.avox.calorietracker.be.repository.UserRepository;
import at.avox.calorietracker.be.repository.entity.UserEntity;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.eclipse.microprofile.jwt.JsonWebToken;

import java.util.Objects;

@ApplicationScoped
@RequiredArgsConstructor
public class SecurityService {
    private final JsonWebToken jwt;
    private final UserRepository userRepository;

    public String getSubject() {
        String subject = jwt.getClaim("sub");

        if (Objects.isNull(subject)) {
            throw new SecurityException("Subject not found!");
        }

        return subject;
    }

    @Transactional
    public UserEntity getOrCreateUserEntity() {
        var subject = getSubject();
        return userRepository.findBySubject(subject).orElseGet(() -> {
            var entity = new UserEntity();
            entity.setSubject(subject);
            entity.setName(jwt.getClaim("name"));
            userRepository.persistAndFlush(entity);
            return entity;
        });
    }
}
