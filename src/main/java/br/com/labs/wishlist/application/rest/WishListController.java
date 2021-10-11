package br.com.labs.wishlist.application.rest;

import br.com.labs.wishlist.application.dto.SearchResult;
import br.com.labs.wishlist.application.dto.WishListDto;
import br.com.labs.wishlist.domain.entities.WishList;
import br.com.labs.wishlist.service.usecases.CreateWishList;
import br.com.labs.wishlist.service.usecases.DeleteProductFromWishList;
import br.com.labs.wishlist.service.usecases.FindAllProductsFromWishList;
import br.com.labs.wishlist.service.usecases.FindProductInWishList;
import br.com.labs.wishlist.service.usecases.UpdateWishList;
import java.util.Set;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/wishlist")
@AllArgsConstructor
public class WishListController {

  private CreateWishList createWishList;
  private UpdateWishList updateWishList;
  private DeleteProductFromWishList deleteWishList;
  private FindAllProductsFromWishList findAllProductsFromWishList;
  private FindProductInWishList findProductInWishList;

  @PostMapping("/{userId}")
  public ResponseEntity<WishList> createWishList(
    @PathVariable String userId,
    @RequestBody WishListDto wishListDto) {

    return ResponseEntity
      .status(HttpStatus.CREATED)
      .body(createWishList.handle(userId, wishListDto));
  }

  @PutMapping("/{userId}")
  public ResponseEntity<WishList> updateWishList(
      @PathVariable String userId,
      @RequestBody WishListDto wishListDto) {

    return ResponseEntity
      .status(HttpStatus.RESET_CONTENT)
      .body(updateWishList.handle(userId, wishListDto));
  }

  @DeleteMapping("/{userId}/{productId}")
  public ResponseEntity<WishList> deleteWishList(
    @PathVariable String userId,
    @PathVariable String productId)
  {
    deleteWishList.handle(userId, productId);
    return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
  }

  @GetMapping("/{userId}")
  public ResponseEntity<Set<String>> getWishList(@PathVariable String userId) {
    return ResponseEntity.ok().body(findAllProductsFromWishList.handle(userId));
  }

  @GetMapping("/{userId}/{productId}")
  public ResponseEntity<SearchResult> getProductIsPartOfWishList(
    @PathVariable String userId,
    @PathVariable String productId) {
    return ResponseEntity.ok().body(findProductInWishList.handle(userId,productId));
  }
}