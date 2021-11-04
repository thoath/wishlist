package br.com.labs.wishlist.domain.entities.impl;

import br.com.labs.wishlist.domain.entities.WishList;
import br.com.labs.wishlist.domain.exception.MaxNumberOfProductsException;
import java.util.HashSet;
import java.util.Set;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;

@Document
@Getter
@Setter
public class CommonWishList implements WishList {

  public CommonWishList(String userId, Set<String> products) {
    this.userId = userId;
    addProductsToList(products);
  }

  @Id
  private String id;
  @Field(targetType = FieldType.OBJECT_ID)
  private String userId;
  @Field(targetType = FieldType.OBJECT_ID)
  private Set<String> products = new HashSet<>();

  @Override
  public boolean isReachedMaxNumberOfProducts(int newProductsSize) {
    return this.products.size() + newProductsSize > 20;
  }

  @Override
  public void addProductsToList(Set<String> productsIds) {
    if (isReachedMaxNumberOfProducts(productsIds.size())) {
      throw new MaxNumberOfProductsException();
    }
    this.products.addAll(productsIds);
  }

  @Override
  public void removeProductsFromWishList(String productsId) {
    this.products.remove(productsId);
  }
}
