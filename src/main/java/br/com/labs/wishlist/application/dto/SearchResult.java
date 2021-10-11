package br.com.labs.wishlist.application.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class SearchResult {

  private String searchProperty;
  private String propertyValue;
  private boolean isFoundInQuery;
}
