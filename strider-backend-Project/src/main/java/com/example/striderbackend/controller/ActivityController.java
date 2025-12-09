// src/main/java/com/example/striderbackend/controller/ActivityController.java

package com.example.striderbackend.controller;

import com.example.striderbackend.dto.ActivityDto;
import com.example.striderbackend.service.ActivityService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/activities")
// Swagger tag for grouping all Activity-related endpoints
@Tag(name = "Activities", description = "CRUD operations for user activities (running, cycling, etc.)")
public class ActivityController {

    // Injected service that handles business logic
    private final ActivityService activityService;

    // Constructor injection
    public ActivityController(ActivityService activityService) {
        this.activityService = activityService;
    }

    /**
     * GET /api/v1/activities
     * Returns a list of all activities
     */
    @GetMapping
    @Operation(summary = "List all activities", description = "Returns all activities in the system")
    public ResponseEntity<List<ActivityDto>> listAll() {
        return ResponseEntity.ok(activityService.listAll());
    }

    /**
     * POST /api/v1/activities
     * Creates a new activity and returns it with a generated UUID.
     * Responds with 201 Created + Location header.
     */
    @PostMapping
    @Operation(summary = "Create a new activity", description = "Creates and returns a new activity with generated UUID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Activity created successfully",
                    content = @Content(schema = @Schema(implementation = ActivityDto.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input data")
    })
    public ResponseEntity<ActivityDto> create(@Valid @RequestBody ActivityDto dto) {
        // Create the activity using the service layer
        ActivityDto created = activityService.create(dto);

        // Build the URI for the new resource (/api/v1/activities/{id})
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(created.getId())
                .toUri();

        // Return 201 Created with the created object in the body
        return ResponseEntity.created(location).body(created);
    }

    /**
     * GET /api/v1/activities/{id}
     * Fetch a single activity by its UUID.
     * Uses Optional to handle 404 Not Found.
     */
    @GetMapping("/{id}")
    @Operation(summary = "Get activity by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Activity found"),
            @ApiResponse(responseCode = "404", description = "Activity not found")
    })
    public ResponseEntity<ActivityDto> getById(
            @Parameter(description = "UUID of the activity") @PathVariable UUID id) {

        return activityService.get(id)
                .map(ResponseEntity::ok)               // Return 200 OK if found
                .orElseGet(() -> ResponseEntity.notFound().build()); // Otherwise 404
    }

    /**
     * PUT /api/v1/activities/{id}
     * Updates an existing activity.
     * Returns 200 if successful, 404 if the activity does not exist.
     */
    @PutMapping("/{id}")
    @Operation(summary = "Update an existing activity")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Activity updated"),
            @ApiResponse(responseCode = "404", description = "Activity not found"),
            @ApiResponse(responseCode = "400", description = "Invalid data")
    })
    public ResponseEntity<ActivityDto> update(
            @PathVariable UUID id,
            @Valid @RequestBody ActivityDto dto) {

        return activityService.update(id, dto)
                .map(ResponseEntity::ok)                // Return updated activity
                .orElseGet(() -> ResponseEntity.notFound().build()); // Return 404 if not found
    }

    /**
     * DELETE /api/v1/activities/{id}
     * Deletes the activity.
     * Always returns HTTP 204 No Content (successful delete).
     */
    @DeleteMapping("/{id}")
    @Operation(summary = "Delete an activity")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Activity deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Activity not found")
    })
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        activityService.delete(id);   // Perform the delete operation
        return ResponseEntity.noContent().build(); // Respond with 204 No Content
    }
}
