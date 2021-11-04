package br.com.labs.wishlist.usecase;

import br.com.labs.wishlist.domain.entities.WishList;
import br.com.labs.wishlist.infrastructure.database.repository.WishListMongoRepository;
import br.com.labs.wishlist.domain.exception.WishListNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DeleteProductFromWishList {

  private WishListMongoRepository wishListRepository;

  public void handle(String userId, String productId) {
    WishList wishList = wishListRepository
      .findByUserId(userId)
      .orElseThrow(WishListNotFoundException::new);

    wishList.removeProductsFromWishList(productId);
    wishListRepository.save(wishList);
  }
}
