package com.dmg.spring.cloud.aws.lambda;

import java.util.function.Consumer;
import org.springframework.stereotype.Component;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class MyConsumer implements Consumer<String>{

  @Override
  public void accept(String input) {
    log.info("Received input::{}",input);
  }

}
