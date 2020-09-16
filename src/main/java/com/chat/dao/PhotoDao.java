package com.chat.dao;

import com.chat.model.Photo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PhotoDao extends JpaRepository<Photo,Long> {
}
