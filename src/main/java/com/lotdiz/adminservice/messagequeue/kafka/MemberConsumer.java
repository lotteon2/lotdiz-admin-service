package com.lotdiz.adminservice.messagequeue.kafka;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lotdiz.adminservice.dto.request.CreateMemberRequestDto;
import com.lotdiz.adminservice.mapper.MemberInfoMapper;
import com.lotdiz.adminservice.repository.MemberInfoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemberConsumer {

  private final MemberInfoMapper memberInfoMapper;
  private final ObjectMapper mapper;
  private final MemberInfoRepository memberInfoRepository;

  @KafkaListener(topics = "create-member")
  public void receiveCreateMaker(String message, Acknowledgment ack) {
    log.info("Get create maker msg");
    try {
      CreateMemberRequestDto createMemberRequestDto =
          mapper.readValue(message, CreateMemberRequestDto.class);
      memberInfoRepository.save(
          memberInfoMapper.createMemberRequestDtoToMemberInfo(createMemberRequestDto));

      ack.acknowledge();
    } catch (JsonProcessingException e) {
      throw new RuntimeException(e);
    }
  }
}
