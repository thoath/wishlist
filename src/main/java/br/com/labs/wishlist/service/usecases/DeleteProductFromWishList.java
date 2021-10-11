package br.com.labs.wishlist.service.usecases;

import br.com.labs.wishlist.domain.entities.WishList;
import br.com.labs.wishlist.domain.repository.WishListRepository;
import br.com.labs.wishlist.service.exception.WishListNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DeleteProductFromWishList {

  private WishListRepository wishListRepository;

  public void handle(String userId, String productId) {
    WishList wishList = wishListRepository.findByUserId(userId)
        .orElseThrow(WishListNotFoundException::new);
    wishList.removeProductsFromWishList(productId);
    wishListRepository.save(wishList);
  }
}
