package br.com.labs.wishlist.infrastructure.rest;

import br.com.labs.wishlist.domain.entities.WishList;
import br.com.labs.wishlist.infrastructure.rest.forms.WishListForm;
import br.com.labs.wishlist.usecase.CreateWishList;
import br.com.labs.wishlist.usecase.DeleteProductFromWishList;
import br.com.labs.wishlist.usecase.FindAllProductsFromWishList;
import br.com.labs.wishlist.usecase.FindProductInWishList;
import br.com.labs.wishlist.usecase.FindProductInWishList.SearchResult;
import br.com.labs.wishlist.usecase.UpdateWishList;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
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
  @ApiOperation("Cria uma lista de desejos para um usuário")
  @ApiResponses(value = {
    @ApiResponse(code = 201, message = "Lista de desejos criada com sucesso"),
    @ApiResponse(code = 500, message = "Erro interno"),
    @ApiResponse(code = 400, message = "Request incompleto ou lista de desejos já existe para o usuário"),
    @ApiResponse(code = 422, message = "Lista de desejos atingiu o número máximo de produtos")}
  )
  public ResponseEntity<WishList> createWishList(
    @PathVariable @ApiParam(USER_ID_INFO) String userId,
    @RequestBody @ApiParam(WISHLIST_FORM_INFO) WishListForm wishListForm
  ) {
    return ResponseEntity
      .status(HttpStatus.CREATED)
      .body(createWishList.handle(userId, wishListForm));
  }

  @PutMapping("/{userId}")
  @ApiOperation("Atualiza a lista de desejos de um usuário")
  @ApiResponses(value = {
      @ApiResponse(code = 205, message = "Lista de desejos atualizada com sucesso"),
      @ApiResponse(code = 500, message = "Erro interno"),
      @ApiResponse(code = 404, message = "Lista não encontrada para o usuário"),
      @ApiResponse(code = 422, message = "Lista de desejos atingiu o número máximo de produtos")}
  )
  public ResponseEntity<WishList> updateWishList(
    @PathVariable @ApiParam(USER_ID_INFO) String userId,
    @RequestBody @ApiParam(WISHLIST_FORM_INFO) WishListForm wishListForm
  ) {
    return ResponseEntity
      .status(HttpStatus.RESET_CONTENT)
      .body(updateWishList.handle(userId, wishListForm));
  }

  @DeleteMapping("/{userId}/{productId}")
  @ApiOperation("Deleta a lista de desejos do usuário")
  @ApiResponses(value = {
      @ApiResponse(code = 204, message = "Lista de desejos removida com sucesso"),
      @ApiResponse(code = 500, message = "Erro interno"),
      @ApiResponse(code = 404, message = "Lista não encontrada para o usuário")}
  )
  public ResponseEntity<WishList> deleteWishList(
    @PathVariable @ApiParam(USER_ID_INFO) String userId,
    @PathVariable @ApiParam("Produto a ser excluido da lista de desejos, id do tipo ObjectId") String productId
  ) {
    deleteWishList.handle(userId, productId);
    return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
  }

  @GetMapping("/{userId}")
  @ApiOperation("Busca todos os produtos da lista de desejos do usuário")
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "Produtos encontrados na lista"),
      @ApiResponse(code = 500, message = "Erro interno"),
      @ApiResponse(code = 404, message = "Lista não encontrada para o usuário")}
  )
  public ResponseEntity<Set<String>> getProductsFromWishList(
    @PathVariable @ApiParam(USER_ID_INFO) String userId
  ) {
    return ResponseEntity.ok().body(findAllProductsFromWishList.handle(userId));
  }

  @GetMapping("/{userId}/{productId}")
  @ApiOperation("Verifica se um produto especifico faz parte da lista do usuário")
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "Produto encontrado na lista"),
      @ApiResponse(code = 500, message = "Erro interno"),
      @ApiResponse(code = 404, message = "Lista não encontrada para o usuário")}
  )
  public ResponseEntity<SearchResult> getProductIsPartOfWishList(
    @PathVariable @ApiParam(USER_ID_INFO) String userId,
    @PathVariable @ApiParam("Produto a ser buscado da lista de desejos, id do tipo ObjectId") String productId
  ) {
    return ResponseEntity.ok().body(findProductInWishList.handle(userId,productId));
  }
}