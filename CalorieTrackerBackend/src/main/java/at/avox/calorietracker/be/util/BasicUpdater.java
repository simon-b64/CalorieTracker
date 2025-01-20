package at.avox.calorietracker.be.util;

import org.mapstruct.MappingTarget;

public interface BasicUpdater<ENTITY, MODEL> {
    void updateFromModel(@MappingTarget ENTITY entity, MODEL serviceModel);
}