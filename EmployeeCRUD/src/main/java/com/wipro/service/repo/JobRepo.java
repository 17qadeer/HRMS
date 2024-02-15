package com.wipro.service.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wipro.entity.Job;

@Repository
public interface JobRepo extends JpaRepository<Job, Long> {

}
