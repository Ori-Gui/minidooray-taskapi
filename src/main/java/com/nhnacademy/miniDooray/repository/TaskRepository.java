package com.nhnacademy.miniDooray.repository;

import com.nhnacademy.miniDooray.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {
}
