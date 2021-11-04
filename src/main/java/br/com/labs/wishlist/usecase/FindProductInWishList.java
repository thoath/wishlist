package br.com.labs.wishlist.usecase;

import br.com.labs.wishlist.domain.entities.WishList;
import br.com.labs.wishlist.infrastructure.database.repository.WishListMongoRepository;
import br.com.labs.wishlist.domain.exception.WishListNotFoundException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class FindProductInWishList {

  private WishListMongoRepository wishListRepository;

  public SearchResult handle(String userId, String productId) {
    WishList wishList = wishListRepository
      .findByUserId(userId)
      .orElseThrow(WishListNotFoundException::new);

    return new SearchResult(
      "productId",
      productId,
      wishList.getProducts().contains(productId)
    );
  }

  @AllArgsConstructor
  @Getter
  @Setter
  public static class SearchResult {

    private final String searchProperty;
    private final String propertyValue;
    private final boolean isFoundInQuery;
  }
}
