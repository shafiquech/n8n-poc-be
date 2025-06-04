package com.example.demo.repository;

import com.example.demo.model.MessageRecord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<MessageRecord, Long> {}