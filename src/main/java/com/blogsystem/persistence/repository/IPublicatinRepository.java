package com.blogsystem.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blogsystem.persistence.entity.Publication;

public interface IPublicatinRepository extends JpaRepository< Publication,Long> {
	
}
