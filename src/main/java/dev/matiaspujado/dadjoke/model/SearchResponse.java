package dev.matiaspujado.dadjoke.model;

import java.util.List;

public record SearchResponse(
  int current_page,
  int limit,
  int next_page,
  int previous_page,
  List<Result> results,
  String search_term,
  int status,
  int total_jokes,
  int total_pages
) {}
