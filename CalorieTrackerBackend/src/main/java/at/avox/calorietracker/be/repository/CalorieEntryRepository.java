package at.avox.calorietracker.be.repository;

import at.avox.calorietracker.be.repository.entity.CalorieEntryEntity;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@ApplicationScoped
public class CalorieEntryRepository implements PanacheRepositoryBase<CalorieEntryEntity, UUID> {
    public List<CalorieEntryEntity> listAllForUser(UUID userId) {
        return find("user.id", userId).list();
    }

    public Optional<CalorieEntryEntity> findByIdForUser(UUID id, UUID userId) {
        return find("user.id = ?1 and id = ?2", userId, id).firstResultOptional();
    }
}
