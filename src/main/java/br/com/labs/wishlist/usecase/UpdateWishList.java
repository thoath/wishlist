package br.com.labs.wishlist.usecase;

import br.com.labs.wishlist.infrastructure.rest.forms.WishListForm;
import br.com.labs.wishlist.domain.entities.WishList;
import br.com.labs.wishlist.infrastructure.database.repository.WishListMongoRepository;
import br.com.labs.wishlist.domain.exception.WishListNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UpdateWishList {

  private WishListMongoRepository wishListRepository;

  public WishList handle(String userId, WishListForm wishListDto) {
    WishList userWishList = wishListRepository
      .findByUserId(userId)
      .orElseThrow(WishListNotFoundException::new);

    userWishList.addProductsToList(wishListDto.getProducts());
    return wishListRepository.save(userWishList);
  }
}
