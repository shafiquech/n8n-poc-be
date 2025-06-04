package com.example.demo.mapper;

import com.example.demo.model.Profile;
import com.example.demo.model.SearchResponse;
import java.util.List;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class ResponseMapper {
    private static final String prefix = "data:image/png;base64,";

    public SearchResponse mapResponse(Map n8NResponse) {
        SearchResponse searchResponse = new SearchResponse();
        Profile profile = new Profile();
        profile.setName(n8NResponse.get("name") != null ? n8NResponse.get("name").toString() : "Unknown");
        profile.setPhone(n8NResponse.get("phone") != null ? n8NResponse.get("phone").toString() : "Unknown");
        profile.setEmail(n8NResponse.get("email") != null ? n8NResponse.get("email").toString() : "Unknown");
        profile.setTitle(n8NResponse.get("title") != null ? n8NResponse.get("title").toString() : "Unknown");
        profile.setCompany(n8NResponse.get("company") != null ? n8NResponse.get("company").toString() : "Unknown");
        profile.setLinkedin(n8NResponse.get("linkedin") != null ? n8NResponse.get("linkedin").toString() : "Unknown");
        profile.setLocation(n8NResponse.get("location") != null ? n8NResponse.get("location").toString() : "Unknown");
        profile.setProfilePic(n8NResponse.get("profilePic") != null ?
            n8NResponse.get("profilePic").toString() : "Unknown");
        //profile.setProfilePic(prefix + "/9j/4AAQSkZJRgABAQEASABIAAD/2wBDAAMCAgMCAgMDAwMEAwMEBQgFBQQEBQoHBwYIDAoMDAsKCwsNDhIQDQ4RDgsLEBYQERMUFRUVDA8XGBYUGBIUFRT/wAALCAABAAEBAREA/8QAFAABAAAAAAAAAAAAAAAAAAAACf/EABQQAQAAAAAAAAAAAAAAAAAAAAD/2gAIAQEAAD8AKp//2Q==");
        List<String> aiInsights = (List<String>) n8NResponse.get("aiInsights");
        profile.setAiInsights(aiInsights);
        searchResponse.setData(profile);
        searchResponse.setSuccess(Boolean.TRUE);
        return searchResponse;
    }
}
