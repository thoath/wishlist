package br.com.labs.wishlist.service.usecases;

import br.com.labs.wishlist.application.dto.WishListDto;
import br.com.labs.wishlist.domain.entities.WishList;
import br.com.labs.wishlist.domain.repository.WishListRepository;
import br.com.labs.wishlist.service.exception.WishListNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UpdateWishList {

  private WishListRepository wishListRepository;

  public WishList handle(String userId, WishListDto wishListDto) {
    WishList userWishList = wishListRepository.findByUserId(userId)
      .orElseThrow(WishListNotFoundException::new);
    userWishList.addProductsToList(wishListDto.getProducts());
    return wishListRepository.save(userWishList);
  }
}
