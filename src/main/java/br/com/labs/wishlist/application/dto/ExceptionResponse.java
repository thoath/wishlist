package br.com.labs.wishlist.application.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ExceptionResponse {

  private String ErrorMessage;
  private int status;
}
