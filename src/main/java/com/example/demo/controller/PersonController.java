package com.example.demo.controller;

import com.example.demo.config.N8NConfig;
import com.example.demo.mapper.ResponseMapper;
import com.example.demo.model.MessageRecord;
import com.example.demo.model.PersonRequest;
import com.example.demo.model.SearchResponse;
import com.example.demo.repository.MessageRepository;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/api")
@Slf4j
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class PersonController {

    private final RestTemplate restTemplate;
    private final MessageRepository messageRepository;
    private final N8NConfig n8NConfig;
    private final ResponseMapper responseMapper;

    @PostMapping("/person-info")
    public ResponseEntity<SearchResponse> getInfo(@RequestBody PersonRequest request) {
        try {
            log.info("Received person request: {}", request);
            log.info(n8NConfig.getPersonInfoUrl());
            ResponseEntity<Map> response = restTemplate.postForEntity(n8NConfig.getPersonInfoUrl(), request, Map.class);
            log.info("Response from person info: {}", response);
            SearchResponse searchResponse = responseMapper.mapResponse(response.getBody());
            return ResponseEntity.ok(searchResponse);
        } catch (Exception e) {
            log.error("Error processing person request. " + e.getMessage(), e);
            return ResponseEntity.badRequest().body(new SearchResponse(false, null, "Invalid request"));
        }

    }

    @PostMapping("/send-message")
    public ResponseEntity<String> sendMessage(@RequestBody MessageRecord record) {
        try {
            log.info("Message record: {}", record);
            messageRepository.save(record);
            log.info("Message saved to in memory repository: {}", record);
            ResponseEntity<String> stringResponseEntity =
                restTemplate.postForEntity(n8NConfig.getSendMessageUrl(), record, String.class);
            log.info("Send Message Res: {}", n8NConfig.getSendMessageUrl());
            return ResponseEntity.ok("Message sent" + stringResponseEntity.getBody());
        } catch (Exception e) {
            log.error("Error processing message record. " + e.getMessage(), e);
            return ResponseEntity.internalServerError().body("Invalid message record");
        }

    }

    @GetMapping("/reports")
    public List<MessageRecord> getReports() {

        List<MessageRecord> records = messageRepository.findAll();
        log.info("Get reports records: {}", records.size());
        return records;
    }
}