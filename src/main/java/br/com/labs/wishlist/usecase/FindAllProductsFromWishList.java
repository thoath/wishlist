package br.com.labs.wishlist.usecase;

import br.com.labs.wishlist.domain.entities.WishList;
import br.com.labs.wishlist.infrastructure.database.repository.WishListMongoRepository;
import br.com.labs.wishlist.domain.exception.WishListNotFoundException;
import java.util.Set;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class FindAllProductsFromWishList {

  private WishListMongoRepository wishListRepository;

  public Set<String> handle(String userId) {
    WishList wishList = wishListRepository
      .findByUserId(userId)
      .orElseThrow(WishListNotFoundException::new);

    return wishList.getProducts();
  }
}
