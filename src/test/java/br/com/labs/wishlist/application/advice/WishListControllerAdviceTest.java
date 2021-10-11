package br.com.labs.wishlist.application.advice;

import br.com.labs.wishlist.application.dto.ExceptionResponse;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class WishListControllerAdviceTest {

  WishListControllerAdvice controllerAdvice = new WishListControllerAdvice();

  @Test
  public void testIfWishListNotFoundIsHandled() {
    ResponseEntity<ExceptionResponse> response = controllerAdvice.handleWishListNotFoundException();

    assertEquals(404, response.getStatusCodeValue());
  }

  @Test
  public void testIfWishListAlreadyExistIsHandled() {
    ResponseEntity<ExceptionResponse> response = controllerAdvice.handleWishListAlreadyExistException();

    assertEquals(400, response.getStatusCodeValue());
  }

  @Test
  public void testIfMaxNumberOfProductsIsHandled() {
    ResponseEntity<ExceptionResponse> response = controllerAdvice.handleMaxNumberOfProductsException();

    assertEquals(400, response.getStatusCodeValue());
  }
}
