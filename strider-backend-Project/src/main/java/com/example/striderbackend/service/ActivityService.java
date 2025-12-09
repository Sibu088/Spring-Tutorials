package com.example.striderbackend.service;
import com.example.striderbackend.dto.ActivityDto;
import com.example.striderbackend.model.Activity;
import com.example.striderbackend.repository.ActivityRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
public class ActivityService {
    private final ActivityRepository repo;

    public ActivityService(ActivityRepository repo) {
        this.repo = repo;
    }

    public ActivityDto toDto(Activity a) {
        ActivityDto d = new ActivityDto();
        d.setId(a.getId());
        d.setTitle(a.getTitle());
        d.setDescription(a.getDescription());
        d.setDistanceKm(a.getDistanceKm());
        d.setDurationMinutes(a.getDurationMinutes());
        d.setDate(a.getDate());
        return d;
    }

    public Activity fromDto(ActivityDto d) {
        Activity a = new Activity();
        a.setId(d.getId());
        a.setTitle(d.getTitle());
        a.setDescription(d.getDescription());
        a.setDistanceKm(d.getDistanceKm());
        a.setDurationMinutes(d.getDurationMinutes());
        a.setDate(d.getDate());
        return a;
    }

    public ActivityDto create(ActivityDto dto) {
        Activity saved = repo.save(fromDto(dto));
        return toDto(saved);
    }

    public List<ActivityDto> listAll() {
        return repo.findAll().stream().map(this::toDto).collect(Collectors.toList());
    }

    public Optional<ActivityDto> get(UUID id) {
        return repo.findById(id).map(this::toDto);
    }

    public Optional<ActivityDto> update(UUID id, ActivityDto dto) {
        return repo.findById(id).map(existing -> {
            existing.setTitle(dto.getTitle());
            existing.setDescription(dto.getDescription());
            existing.setDistanceKm(dto.getDistanceKm());
            existing.setDurationMinutes(dto.getDurationMinutes());
            existing.setDate(dto.getDate());
            return toDto(repo.save(existing));
        });
    }

    public void delete(UUID id) {
        repo.deleteById(id);
    }
}
