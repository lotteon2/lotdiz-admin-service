package com.lotdiz.adminservice.messagequeue.kafka;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lotdiz.adminservice.dto.request.CreateProjectRequestDto;
import com.lotdiz.adminservice.mapper.ProjectInfoMapper;
import com.lotdiz.adminservice.repository.ProjectInfoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProjectConsumer {

  private final ProjectInfoMapper projectInfoMapper;
  private final ObjectMapper mapper;
  private final ProjectInfoRepository projectInfoRepository;

  @KafkaListener(topics = "create-project")
  public void receiveCreateProject(String message, Acknowledgment ack) {
    log.info("Get create project msg");
    try {
      CreateProjectRequestDto createProjectRequestDto =
          mapper.readValue(message, CreateProjectRequestDto.class);
      projectInfoRepository.save(
          projectInfoMapper.createProjectRequestDtoToProjectInfo(createProjectRequestDto));

      ack.acknowledge();
    } catch (JsonProcessingException e) {
      throw new RuntimeException(e);
    }
  }
}
