package com.example.demo.mapper;

import com.example.demo.model.Profile;
import com.example.demo.model.SearchResponse;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

class ResponseMapperTest {

    private final ResponseMapper responseMapper = new ResponseMapper();

    @Test
    void testMapResponse() {
        // Mock input data
        Map<String, Object> inputData = new HashMap<>();
        inputData.put("_id", "6650e3000000000000000005");
        inputData.put("name", "Emily Chen");
        inputData.put("profilePic", "https://example.com/images/emily.jpg");
        inputData.put("aiInsights", List.of("Front-end enthusiast", "Speaks at React meetups", "Designs UI kits"));
        inputData.put("email", "emily.chen@example.com");
        inputData.put("phone", "+1-555-7890");
        inputData.put("title", "UI/UX Designer");
        inputData.put("company", "PixelPerfect Designs");
        inputData.put("linkedin", "https://linkedin.com/in/emilychen");
        inputData.put("location", "New York, NY");

        // Call the method under test
        SearchResponse response = responseMapper.mapResponse(inputData);

        // Assertions
        assertNotNull(response);
        assertTrue(response.isSuccess());
        assertNotNull(response.getData());

        Profile profile = response.getData();
        assertEquals("Emily Chen", profile.getName());
        assertEquals("https://example.com/images/emily.jpg", profile.getProfilePic());
        assertEquals(List.of("Front-end enthusiast", "Speaks at React meetups", "Designs UI kits"), profile.getAiInsights());
        assertEquals("emily.chen@example.com", profile.getEmail());
        assertEquals("+1-555-7890", profile.getPhone());
        assertEquals("UI/UX Designer", profile.getTitle());
        assertEquals("PixelPerfect Designs", profile.getCompany());
        assertEquals("https://linkedin.com/in/emilychen", profile.getLinkedin());
        assertEquals("New York, NY", profile.getLocation());
    }
}