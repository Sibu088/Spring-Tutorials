package com.example.striderbackend.repository;
import com.example.striderbackend.model.Activity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface ActivityRepository extends JpaRepository<Activity, UUID> {
}
