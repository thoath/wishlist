package br.com.labs.wishlist.domain.factory.impl;

import br.com.labs.wishlist.domain.entities.WishList;
import br.com.labs.wishlist.domain.entities.impl.CommonWishList;
import br.com.labs.wishlist.domain.factory.WishListFactory;
import java.util.Set;
import org.springframework.stereotype.Component;

@Component
public class CommonWishListFactory implements WishListFactory {

  @Override
  public WishList create(String userId, Set<String> products) {
    return new CommonWishList(userId, products);
  }
}
