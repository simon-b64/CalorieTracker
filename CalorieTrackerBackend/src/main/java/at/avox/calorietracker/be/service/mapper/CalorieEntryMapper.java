package at.avox.calorietracker.be.service.mapper;

import at.avox.calorietracker.be.config.MapstructConfig;
import at.avox.calorietracker.be.repository.entity.CalorieEntryEntity;
import at.avox.calorietracker.be.util.BasicMapper;
import at.avox.calorietracker.be.util.BasicUpdater;
import at.avox.calorietracker.be.util.LazyLoadingAwareMapper;
import at.avox.calorietracker.be.web.dto.CalorieEntryDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(config = MapstructConfig.class, uses = {LazyLoadingAwareMapper.class})
public interface CalorieEntryMapper extends BasicMapper<CalorieEntryEntity, CalorieEntryDto>, BasicUpdater<CalorieEntryEntity, CalorieEntryDto> {

    @Override
    @Mapping(target = "user", ignore = true)
    CalorieEntryEntity fromModel(CalorieEntryDto model);

    @Override
    @Mapping(target = "user", ignore = true)
    void updateFromModel(@MappingTarget CalorieEntryEntity entity, CalorieEntryDto serviceModel);

}
