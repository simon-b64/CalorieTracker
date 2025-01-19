package at.avox.calorietracker.be.util;

import java.util.List;
import java.util.stream.Collectors;

public interface BasicMapper<BASE, MODEL> {
    MODEL toModel(BASE model);
    BASE fromModel(MODEL model);

    default List<BASE> fromModel(List<MODEL> models) {
        return models.stream().map(this::fromModel).collect(Collectors.toList());
    }

    default List<MODEL> toModel(List<BASE> bases) {
        return bases.stream().map(this::toModel).collect(Collectors.toList());
    }
}
