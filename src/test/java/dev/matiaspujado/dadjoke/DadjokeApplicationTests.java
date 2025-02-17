package dev.matiaspujado.dadjoke;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class DadjokeApplicationTests {

  @Autowired
  private ApplicationContext applicationContext;

  @BeforeEach
  void setUp() {
  }

  @Test
  void contextLoads() {
    DadjokeApplication.main(new String[] {});
    assertThat(applicationContext).isNotNull();
  }
}
