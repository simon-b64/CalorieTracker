package at.avox.calorietracker.be.service.mapper;

import at.avox.calorietracker.be.config.MapstructConfig;
import at.avox.calorietracker.be.repository.entity.UserEntity;
import at.avox.calorietracker.be.service.model.User;
import at.avox.calorietracker.be.util.BasicMapper;
import at.avox.calorietracker.be.util.LazyLoadingAwareMapper;
import org.mapstruct.Mapper;

@Mapper(config = MapstructConfig.class, uses = {LazyLoadingAwareMapper.class})
public interface UserMapper extends BasicMapper<UserEntity, User> {
}
