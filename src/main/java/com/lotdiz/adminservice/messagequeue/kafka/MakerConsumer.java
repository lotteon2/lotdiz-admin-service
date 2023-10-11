package com.lotdiz.adminservice.messagequeue.kafka;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lotdiz.adminservice.dto.request.CreateMakerRequestDto;
import com.lotdiz.adminservice.mapper.MakerInfoMapper;
import com.lotdiz.adminservice.repository.MakerInfoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class MakerConsumer {

  private final MakerInfoMapper makerInfoMapper;
  private final ObjectMapper mapper;
  private final MakerInfoRepository makerInfoRepository;

  @KafkaListener(topics = "create-maker")
  public void receiveCreateMaker(String message, Acknowledgment ack) {
    log.info("Get create maker msg");
    try {
      CreateMakerRequestDto createMakerRequestDto =
          mapper.readValue(message, CreateMakerRequestDto.class);
      makerInfoRepository.save(
          makerInfoMapper.createMakerRequestDtoToMakerInfo(createMakerRequestDto));

      ack.acknowledge();
    } catch (JsonProcessingException e) {
      throw new RuntimeException(e);
    }
  }
}
