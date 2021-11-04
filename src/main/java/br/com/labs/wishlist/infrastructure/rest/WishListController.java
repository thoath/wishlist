package br.com.labs.wishlist.infrastructure.rest;

import br.com.labs.wishlist.domain.entities.WishList;
import br.com.labs.wishlist.infrastructure.rest.forms.WishListForm;
import br.com.labs.wishlist.usecase.CreateWishList;
import br.com.labs.wishlist.usecase.DeleteProductFromWishList;
import br.com.labs.wishlist.usecase.FindAllProductsFromWishList;
import br.com.labs.wishlist.usecase.FindProductInWishList;
import br.com.labs.wishlist.usecase.FindProductInWishList.SearchResult;
import br.com.labs.wishlist.usecase.UpdateWishList;
import io.swagger.annotations.ApiParam;
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

  private static final String USER_ID_INFO = "Id do usuário que essa lista pertence";
  private static final String WISHLIST_FORM_INFO = "Lista de desejos, contendo os produtos a serem adicionados";
  private CreateWishList createWishList;
  private UpdateWishList updateWishList;
  private DeleteProductFromWishList deleteWishList;
  private FindAllProductsFromWishList findAllProductsFromWishList;
  private FindProductInWishList findProductInWishList;

  @PostMapping("/{userId}")
  public ResponseEntity<WishList> createWishList(
    @PathVariable @ApiParam(USER_ID_INFO) String userId,
    @RequestBody @ApiParam(WISHLIST_FORM_INFO) WishListForm wishListForm
  ) {
    return ResponseEntity
      .status(HttpStatus.CREATED)
      .body(createWishList.handle(userId, wishListForm));
  }

  @PutMapping("/{userId}")
  public ResponseEntity<WishList> updateWishList(
    @PathVariable @ApiParam(USER_ID_INFO) String userId,
    @RequestBody @ApiParam(WISHLIST_FORM_INFO) WishListForm wishListForm
  ) {
    return ResponseEntity
      .status(HttpStatus.RESET_CONTENT)
      .body(updateWishList.handle(userId, wishListForm));
  }

  @DeleteMapping("/{userId}/{productId}")
  public ResponseEntity<WishList> deleteWishList(
    @PathVariable @ApiParam(USER_ID_INFO) String userId,
    @PathVariable @ApiParam("Produto a ser excluido da lista de desejos") String productId
  ) {
    deleteWishList.handle(userId, productId);
    return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
  }

  @GetMapping("/{userId}")
  public ResponseEntity<Set<String>> getWishList(
    @PathVariable @ApiParam(USER_ID_INFO) String userId
  ) {
    return ResponseEntity.ok().body(findAllProductsFromWishList.handle(userId));
  }

  @GetMapping("/{userId}/{productId}")
  public ResponseEntity<SearchResult> getProductIsPartOfWishList(
    @PathVariable @ApiParam(USER_ID_INFO) String userId,
    @PathVariable @ApiParam("Produto a ser buscado da lista de desejos") String productId
  ) {
    return ResponseEntity.ok().body(findProductInWishList.handle(userId,productId));
  }
}