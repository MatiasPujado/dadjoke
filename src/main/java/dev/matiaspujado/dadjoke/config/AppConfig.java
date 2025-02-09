package dev.matiaspujado.dadjoke.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@Configuration
public class AppConfig {

  @Bean
  dev.matiaspujado.dadjoke.model.CustomClient dadJokeClient() {
    WebClient webClient = WebClient.builder()
                            .baseUrl(dev.matiaspujado.dadjoke.model.CustomClient.BASE_URL)
                            .defaultHeader("Accept", MediaType.APPLICATION_JSON_VALUE)
                            .defaultHeader("User-Agent", "Practice project to learn about Spring Shell.")
                            .defaultHeader("Host", "icanhazdadjoke.com")
                            .defaultHeader("Connection", "keep-alive")
                            .build();

    HttpServiceProxyFactory factory = HttpServiceProxyFactory.builderFor(WebClientAdapter.create(webClient)).build();
    return factory.createClient(dev.matiaspujado.dadjoke.model.CustomClient.class);
  }
}
