package br.com.labs.wishlist.domain.factory;

import br.com.labs.wishlist.domain.entities.WishList;
import java.util.Set;

public interface WishListFactory {
  WishList create(String userId, Set<String> products);
}
