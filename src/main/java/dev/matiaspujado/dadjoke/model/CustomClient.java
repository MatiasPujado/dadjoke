package dev.matiaspujado.dadjoke.model;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.service.annotation.GetExchange;

public interface CustomClient {

  String BASE_URL = "https://icanhazdadjoke.com";
  String RANDOM_JOKES_ENDPOINT = "/";
  String SPECIFIC_JOKES_ENDPOINT = "/j";
  String SEARCH_JOKES_ENDPOINT = "/search";

  @GetExchange(BASE_URL + RANDOM_JOKES_ENDPOINT)
  CustomResponse random();

  @GetExchange(BASE_URL + SPECIFIC_JOKES_ENDPOINT + "/{id}")
  CustomResponse specificJoke(@PathVariable(name = "id") String id);

  @GetExchange(BASE_URL + SEARCH_JOKES_ENDPOINT)
  SearchResponse search(
    @RequestParam(name = "page", required = false) String page,
    @RequestParam(name = "limit", required = false) String limit,
    @RequestParam(name = "term", required = false) String term
    );

}
