package br.com.labs.wishlist.domain.entities;

import java.util.Set;

public interface WishList {

  String getId();
  String getUserId();
  Set<String> getProducts();
  boolean isReachedMaxNumberOfProducts(int newProductsSize);
  void addProductsToList(Set<String> productsIds);
  void removeProductsFromWishList(String productsId);
}
