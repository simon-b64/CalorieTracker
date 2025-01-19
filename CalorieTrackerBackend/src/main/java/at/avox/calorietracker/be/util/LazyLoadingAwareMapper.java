package at.avox.calorietracker.be.util;

import at.avox.calorietracker.be.config.MapstructConfig;
import org.hibernate.Hibernate;
import org.mapstruct.Condition;
import org.mapstruct.Mapper;

@Mapper(config = MapstructConfig.class)
public interface LazyLoadingAwareMapper {
    @Condition
    default <T> boolean isFieldEagerLoaded(T source) {
        return Hibernate.isInitialized(source);
    }
}
