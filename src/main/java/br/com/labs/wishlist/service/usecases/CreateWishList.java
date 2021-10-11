package br.com.labs.wishlist.service.usecases;

import br.com.labs.wishlist.application.dto.WishListDto;
import br.com.labs.wishlist.domain.entities.WishList;
import br.com.labs.wishlist.domain.factory.WishListFactory;
import br.com.labs.wishlist.domain.repository.WishListRepository;
import br.com.labs.wishlist.service.exception.MaxNumberOfProductsException;
import br.com.labs.wishlist.service.exception.WishListAlreadyExistException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CreateWishList {

  private WishListFactory wishListFactory;
  private WishListRepository wishListRepository;

  public WishList handle(String userId, WishListDto wishListDto) {
    if (wishListRepository.findByUserId(userId).isPresent()) {
      throw new WishListAlreadyExistException();
    }
    WishList wishList = wishListFactory.create(userId, wishListDto.getProducts());
    return wishListRepository.save(wishList);
  }
}
