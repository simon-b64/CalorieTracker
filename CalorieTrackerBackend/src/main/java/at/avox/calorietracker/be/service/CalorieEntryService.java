package at.avox.calorietracker.be.service;

import at.avox.calorietracker.be.exceptions.InternalServerException;
import at.avox.calorietracker.be.exceptions.NotFoundException;
import at.avox.calorietracker.be.repository.CalorieEntryRepository;
import at.avox.calorietracker.be.service.mapper.CalorieEntryMapper;
import at.avox.calorietracker.be.web.dto.CalorieEntryDto;
import io.quarkus.logging.Log;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.UUID;

@ApplicationScoped
@RequiredArgsConstructor
public class CalorieEntryService {
    private final CalorieEntryRepository calorieEntryRepository;
    private final CalorieEntryMapper calorieEntryMapper;
    private final SecurityService securityService;

    public List<CalorieEntryDto> findAll() {
        return calorieEntryMapper.toModel(
            calorieEntryRepository.listAllForUser(securityService.getCurrentUserId())
        );
    }

    @Transactional
    public CalorieEntryDto createOrUpdate(CalorieEntryDto calorieEntryDto) {
        if (calorieEntryDto.id() == null) {
            return createInternal(calorieEntryDto);
        } else {
            return updateInternal(calorieEntryDto);
        }
    }

    @Transactional
    public CalorieEntryDto delete(UUID id) {
        var entry = calorieEntryRepository.findByIdForUser(id, securityService.getCurrentUserId())
            .orElseThrow(() -> new NotFoundException("Calorie entry with id " + id + " for user " + securityService.getCurrentUserId() + " not found!"));

        calorieEntryRepository.delete(entry);

        return calorieEntryMapper.toModel(entry);
    }

    private CalorieEntryDto createInternal(CalorieEntryDto calorieEntryDto) {
        if (calorieEntryDto.id() != null) {
            Log.error("Cannot create a new calorie entry with a given id!");
            throw new InternalServerException();
        }

        var entry = calorieEntryMapper.fromModel(calorieEntryDto);
        entry.setUser(securityService.getCurrentUserEntity());
        calorieEntryRepository.persistAndFlush(entry);
        return calorieEntryMapper.toModel(entry);
    }

    private CalorieEntryDto updateInternal(CalorieEntryDto calorieEntryDto) {
        if (calorieEntryDto.id() == null) {
            Log.error("Cannot update a calorie entry without a given id!");
            throw new InternalServerException();
        }

        var entry = calorieEntryRepository.findByIdForUser(calorieEntryDto.id(), securityService.getCurrentUserId())
            .orElseThrow(() -> new NotFoundException("Calorie entry with id " + calorieEntryDto.id() + " not found!"));

        calorieEntryMapper.updateFromModel(entry, calorieEntryDto);
        calorieEntryRepository.persistAndFlush(entry);

        return calorieEntryMapper.toModel(entry);
    }
}
