package com.example.demo.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class MessageRecord {
    @Id @GeneratedValue
    public Long id;
    public String name;
    public String email;
    public String phone;
    public String message;
    public String mode;
    public LocalDateTime sentAt;
}