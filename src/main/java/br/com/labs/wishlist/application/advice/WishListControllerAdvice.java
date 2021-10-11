package br.com.labs.wishlist.application.advice;

import br.com.labs.wishlist.application.dto.ExceptionResponse;
import br.com.labs.wishlist.service.exception.MaxNumberOfProductsException;
import br.com.labs.wishlist.service.exception.WishListAlreadyExistException;
import br.com.labs.wishlist.service.exception.WishListNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class WishListControllerAdvice {

  private static final String WISH_LIST_ALREADY_EXIST = "Lista de desejos já existe.";
  private static final String WISH_LIST_NOT_FOUND = "Lista de desejos não existe.";
  private static final String MAX_NUMBER_OF_PRODUCTS = "Número de produtos atingiu seu limite máximo de 20.";

  @ExceptionHandler(WishListAlreadyExistException.class)
  public ResponseEntity<ExceptionResponse> handleWishListAlreadyExistException(){
    var exceptionResponse = new ExceptionResponse(WISH_LIST_ALREADY_EXIST, HttpStatus.BAD_REQUEST.value());
    return ResponseEntity.badRequest().body(exceptionResponse);
  }

  @ExceptionHandler(WishListNotFoundException.class)
  public ResponseEntity<ExceptionResponse> handleWishListNotFoundException(){
    var exceptionResponse = new ExceptionResponse(WISH_LIST_NOT_FOUND, HttpStatus.NOT_FOUND.value());
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exceptionResponse);
  }

  @ExceptionHandler(MaxNumberOfProductsException.class)
  public ResponseEntity<ExceptionResponse> handleMaxNumberOfProductsException(){
    var exceptionResponse = new ExceptionResponse(MAX_NUMBER_OF_PRODUCTS, HttpStatus.BAD_REQUEST.value());
    return ResponseEntity.badRequest().body(exceptionResponse);
  }
}
