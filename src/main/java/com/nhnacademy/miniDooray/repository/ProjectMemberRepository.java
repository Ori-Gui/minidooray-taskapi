package com.nhnacademy.miniDooray.repository;

import com.nhnacademy.miniDooray.entity.ProjectMember;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectMemberRepository extends JpaRepository<ProjectMember, Long> {
}
