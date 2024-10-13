package com.nhnacademy.miniDooray.controller;

import com.nhnacademy.miniDooray.dto.MilestoneDto;
import com.nhnacademy.miniDooray.dto.TagDto;
import com.nhnacademy.miniDooray.dto.TaskDto;
import com.nhnacademy.miniDooray.dto.TaskTagDto;
import com.nhnacademy.miniDooray.service.TaskService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/projects/{projectId}/tasks")
@RequiredArgsConstructor
@RestController
public class TaskController {

    private final TaskService taskService;

    @Operation(summary = "프로젝트 내 태스크 추가")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Task successfully created"),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "403", description = "Forbidden"),
            @ApiResponse(responseCode = "404", description = "Project not found")
    })
    @PostMapping
    public ResponseEntity<TaskDto> registerTask(
            @RequestHeader("X-USER-ID") String userId,
            @Validated @RequestBody TaskDto taskDto) {
        TaskDto createdTask = taskService.registerTask(taskDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdTask);
    }

    @Operation(summary = "프로젝트 내 태스크 수정")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Task successfully updated"),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "403", description = "Forbidden"),
            @ApiResponse(responseCode = "404", description = "Task not found")
    })
    @PutMapping("/{taskId}")
    public ResponseEntity<TaskDto> updateTask(
            @RequestHeader("X-USER-ID") String userId,
            @PathVariable Long taskId, @Validated @RequestBody TaskDto taskDto) {
        TaskDto updatedTask = taskService.updateTask(taskId, taskDto);
        return ResponseEntity.ok(updatedTask);
    }

    @Operation(summary = "프로젝트 내 태스크 삭제")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Task successfully deleted"),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "403", description = "Forbidden"),
            @ApiResponse(responseCode = "404", description = "Task not found")
    })
    @DeleteMapping("/{taskId}")
    public ResponseEntity<Void> deleteTask(
            @RequestHeader("X-USER-ID") String userId,
            @PathVariable Long taskId) {
        taskService.deleteTask(taskId);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "프로젝트 내 태스크 전체 조회")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Tasks retrieved"),
            @ApiResponse(responseCode = "400", description = "Invalid pagination parameters"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "403", description = "Forbidden"),
            @ApiResponse(responseCode = "404", description = "Project not found")
    })
    @GetMapping
    public ResponseEntity<Page<TaskDto>> getTasks(
            @RequestHeader("X-USER-ID") String userId,
            Pageable pageable) {
        Page<TaskDto> taskDtoList = taskService.getTasks(pageable.getPageNumber(), pageable.getPageSize());
        return ResponseEntity.ok(taskDtoList);
    }

    @Operation(summary = "프로젝트 내 태스크 단일 조회")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Task found"),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "403", description = "Forbidden"),
            @ApiResponse(responseCode = "404", description = "Task not found")
    })
    @GetMapping("/{taskId}")
    public ResponseEntity<TaskDto> getTask(
            @RequestHeader("X-USER-ID") String userId,
            @PathVariable Long taskId) {
        TaskDto taskDto = taskService.getTask(taskId);
        return ResponseEntity.ok(taskDto);
    }

    @Operation(summary = "Update a milestone to a task")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Milestone successfully updated"),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "403", description = "Forbidden"),
            @ApiResponse(responseCode = "404", description = "Task not found")
    })
    @PutMapping("/{taskId}/milestone")
    public ResponseEntity<TaskDto> updateMilestone(
            @RequestHeader("X-USER-ID") String userId,
            @PathVariable Long taskId,
            @RequestParam Long milestoneId,
            @Validated @RequestBody MilestoneDto milestoneDto) {
        TaskDto updatedTask = taskService.updateMilestone(taskId, milestoneId, milestoneDto);
        return ResponseEntity.ok(updatedTask);
    }

    @Operation(summary = "Update a tag to a task")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Tag successfully updated"),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "403", description = "Forbidden"),
            @ApiResponse(responseCode = "404", description = "Task not found")
    })
    @PutMapping("/{taskTagId}")
    public ResponseEntity<TaskTagDto> updateTag(
            @RequestHeader("X-USER-ID") String userId,
            @PathVariable Long taskTagId,
            @Validated @RequestBody TaskDto taskDto,
            @Validated @RequestBody TagDto tagDto) {
        TaskTagDto updatedTaskTag = taskService.updateTag(taskTagId, taskDto, tagDto);
        return ResponseEntity.ok(updatedTaskTag);
    }
}