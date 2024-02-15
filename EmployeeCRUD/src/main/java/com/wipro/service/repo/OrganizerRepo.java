package com.wipro.service.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wipro.entity.Organization;


public interface OrganizerRepo extends JpaRepository<Organization, Long> {

	public Organization findByEmail(String email);
}
