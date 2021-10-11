package br.com.labs.wishlist.service.usecases;

import br.com.labs.wishlist.application.dto.SearchResult;
import br.com.labs.wishlist.domain.entities.WishList;
import br.com.labs.wishlist.domain.repository.WishListRepository;
import br.com.labs.wishlist.service.exception.WishListNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class FindProductInWishList {

  private WishListRepository wishListRepository;

  public SearchResult handle(String userId, String productId) {
    WishList wishList = wishListRepository.findByUserId(userId)
      .orElseThrow(WishListNotFoundException::new);
    return new SearchResult(
      "productId",
      productId,
      wishList.getProducts().contains(productId)
    );
  }
}
