package at.avox.calorietracker.be.service;

import at.avox.calorietracker.be.repository.UserRepository;
import at.avox.calorietracker.be.repository.entity.UserEntity;
import at.avox.calorietracker.be.service.mapper.UserMapper;
import at.avox.calorietracker.be.service.model.User;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.eclipse.microprofile.jwt.JsonWebToken;

import java.util.Objects;
import java.util.UUID;

@ApplicationScoped
@RequiredArgsConstructor
public class SecurityService {
    private final JsonWebToken jwt;
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public String getSubject() {
        String subject = jwt.getClaim("sub");

        if (Objects.isNull(subject)) {
            throw new SecurityException("Subject not found!");
        }

        return subject;
    }

    @Transactional
    public UUID getCurrentUserId() {
        return getOrCreateUser(getSubject()).getId();
    }

    @Transactional
    public User getCurrentUser() {
        var subject = getSubject();
        return userMapper.toModel(
            getOrCreateUser(subject)
        );
    }

    @Transactional
    public UserEntity getCurrentUserEntity() {
        return getOrCreateUser(getSubject());
    }

    private UserEntity getOrCreateUser(String subject) {
        return userRepository.findBySubject(subject).orElseGet(() -> {
            var entity = new UserEntity();
            entity.setSubject(subject);
            entity.setName(jwt.getClaim("name"));
            userRepository.persistAndFlush(entity);
            return entity;
        });
    }
}
