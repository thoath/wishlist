package br.com.labs.wishlist.usecase;

import br.com.labs.wishlist.domain.entities.WishList;
import br.com.labs.wishlist.domain.factory.WishListFactory;
import br.com.labs.wishlist.infrastructure.database.repository.WishListMongoRepository;
import br.com.labs.wishlist.domain.exception.WishListAlreadyExistException;
import br.com.labs.wishlist.infrastructure.rest.forms.WishListForm;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CreateWishList {

  private WishListFactory wishListFactory;
  private WishListMongoRepository wishListRepository;

  public WishList handle(String userId, WishListForm wishListForm) {
    if (wishListRepository.findByUserId(userId).isPresent()) {
      throw new WishListAlreadyExistException();
    }
    WishList wishList = wishListFactory.create(userId, wishListForm.getProducts());
    return wishListRepository.save(wishList);
  }
}
