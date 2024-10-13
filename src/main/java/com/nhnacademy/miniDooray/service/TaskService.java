package com.nhnacademy.miniDooray.service;

import com.nhnacademy.miniDooray.dto.TaskDto;
import com.nhnacademy.miniDooray.dto.TaskTagDto;
import org.springframework.data.domain.Page;

public interface TaskService {
    TaskDto registerTask(TaskDto taskDto);
    TaskDto updateTask(Long taskId, TaskDto taskDto);
    void deleteTask(Long taskId);
    Page<TaskDto> getTasks(int page, int size);
    TaskDto getTask(Long taskId);
    TaskDto updateMilestone(Long taskId, Long milestoneId);
    TaskTagDto updateTag(Long taskTagId);
}