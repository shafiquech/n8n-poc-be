package com.example.demo.model;

import java.util.List;
import lombok.Data;

@Data
public class Profile {
    private String id;
    private String name;
    private String profilePic;
    private List<String> aiInsights;
    private String email;
    private String phone;
    private String title;
    private String company;
    private String linkedin;
    private String location;
}
