package br.com.labs.wishlist.service.usecases;

import br.com.labs.wishlist.domain.entities.WishList;
import br.com.labs.wishlist.domain.repository.WishListRepository;
import br.com.labs.wishlist.service.exception.WishListNotFoundException;
import java.util.Set;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class FindAllProductsFromWishList {

  private WishListRepository wishListRepository;

  public Set<String> handle(String userId) {
    WishList wishList = wishListRepository.findByUserId(userId)
        .orElseThrow(WishListNotFoundException::new);

    return wishList.getProducts();
  }
}
