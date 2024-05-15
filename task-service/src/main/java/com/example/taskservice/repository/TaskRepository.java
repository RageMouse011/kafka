package com.example.taskservice.repository;

import com.example.taskservice.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    @Query("SELECT t FROM task t WHERE :userName MEMBER OF t.usersNames")
    List<Task> findTasksByUserName(@Param("userName") String userName);
}
