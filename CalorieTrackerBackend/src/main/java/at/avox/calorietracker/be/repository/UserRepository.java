package at.avox.calorietracker.be.repository;

import at.avox.calorietracker.be.repository.entity.UserEntity;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.Optional;
import java.util.UUID;

@ApplicationScoped
public class UserRepository implements PanacheRepositoryBase<UserEntity, UUID> {
    public Optional<UserEntity> findBySubject(String subject) {
        return find("subject", subject).firstResultOptional();
    }
}
