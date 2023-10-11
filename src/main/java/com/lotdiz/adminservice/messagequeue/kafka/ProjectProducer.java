package com.lotdiz.adminservice.messagequeue.kafka;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lotdiz.adminservice.dto.request.AuthorizedProjectRequestDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProjectProducer {

    private final ObjectMapper mapper;
    private final KafkaTemplate<String, String> kafkaTemplate;

    public void sendAuthorizedProject(AuthorizedProjectRequestDto authorizedProjectRequestDto) {
        log.info("Send authorize project msg");
        try {
            String jsonString = mapper.writeValueAsString(authorizedProjectRequestDto);
            kafkaTemplate.send("auth-project", jsonString);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
