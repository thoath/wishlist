package br.com.labs.wishlist.infrastructure.rest.advice;

import br.com.labs.wishlist.domain.exception.MaxNumberOfProductsException;
import br.com.labs.wishlist.domain.exception.WishListAlreadyExistException;
import br.com.labs.wishlist.domain.exception.WishListNotFoundException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class WishListControllerAdvice {

  private static final String WISH_LIST_ALREADY_EXIST = "Lista de desejos já existe.";
  private static final String WISH_LIST_NOT_FOUND = "Lista de desejos não existe.";
  private static final String MAX_NUMBER_OF_PRODUCTS = "Número de produtos atingiu seu limite máximo de 20.";
  private static final String GENERIC_EXCEPTION = "Erro ao tentar processar sua requisição, favor tentar novamente mais tarde";
  private static final String INVALID_USER_ID = "Id do usuário inválido, precisa ser uma string no formato de um ObjectId válido.";

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
    var exceptionResponse = new ExceptionResponse(MAX_NUMBER_OF_PRODUCTS, HttpStatus.UNPROCESSABLE_ENTITY.value());
    return ResponseEntity.badRequest().body(exceptionResponse);
  }

  @ExceptionHandler(IllegalArgumentException.class)
  public ResponseEntity<ExceptionResponse> handleInvalidUserIdException(){
    var exceptionResponse =
        new ExceptionResponse(INVALID_USER_ID, HttpStatus.BAD_REQUEST.value());

    return ResponseEntity.badRequest().body(exceptionResponse);
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<ExceptionResponse> handleGenericException(){
    var exceptionResponse =
      new ExceptionResponse(GENERIC_EXCEPTION, HttpStatus.INTERNAL_SERVER_ERROR.value());

    return ResponseEntity.internalServerError().body(exceptionResponse);
  }

  @AllArgsConstructor
  @Getter
  @Setter
  public static class ExceptionResponse {
    private final String ErrorMessage;
    private final int status;
  }
}
