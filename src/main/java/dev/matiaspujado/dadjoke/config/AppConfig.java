package dev.matiaspujado.dadjoke.config;

import dev.matiaspujado.dadjoke.model.CustomClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@Configuration
public class AppConfig {

  private static final Logger log = LoggerFactory.getLogger(AppConfig.class);

  @Bean
  CustomClient dadJokeClient() {
    WebClient webClient = WebClient.builder()
                            .baseUrl(CustomClient.BASE_URL)
                            .defaultHeader("Accept", MediaType.APPLICATION_JSON_VALUE)
                            .defaultHeader("User-Agent", "Practice project to learn about Spring Shell.")
                            .defaultHeader("Host", "icanhazdadjoke.com")
                            .defaultHeader("Connection", "keep-alive")
                            .build();

    if (log.isDebugEnabled()) {
      log.debug("WebClient created: {}", webClient.toString());
    }

    HttpServiceProxyFactory factory = HttpServiceProxyFactory.builderFor(WebClientAdapter.create(webClient)).build();

    if (log.isDebugEnabled()) {
      log.debug("HttpServiceProxyFactory created: {}", factory.toString());
    }

    return factory.createClient(CustomClient.class);
  }
}
