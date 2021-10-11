package br.com.labs.wishlist.service.usecases;

import br.com.labs.wishlist.application.dto.SearchResult;
import br.com.labs.wishlist.domain.entities.WishList;
import br.com.labs.wishlist.domain.entities.impl.CommonWishList;
import br.com.labs.wishlist.domain.repository.WishListRepository;
import br.com.labs.wishlist.service.exception.WishListNotFoundException;
import java.util.Optional;
import java.util.Set;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class FIndProductInWishListTest {

  private static final String PROPERTY_NAME = "productId";

  @Mock
  WishListRepository wishListRepository;
  FindProductInWishList findProductInWishList;

  @Test
  void testFindProductInWishListWithMatch() {
    when(wishListRepository.findByUserId(anyString())).thenReturn(Optional.of(createWishList()));
    findProductInWishList = new FindProductInWishList(wishListRepository);

    SearchResult searchResult = findProductInWishList.handle("1", "2");

    assertEquals(PROPERTY_NAME, searchResult.getSearchProperty());
    assertTrue(searchResult.isFoundInQuery());
  }

  @Test
  void testFindProductInWishListWithoutMatch() {
    when(wishListRepository.findByUserId(anyString())).thenReturn(Optional.of(createWishList()));
    findProductInWishList = new FindProductInWishList(wishListRepository);

    SearchResult searchResult = findProductInWishList.handle("1", "3");

    assertEquals(PROPERTY_NAME, searchResult.getSearchProperty());
    assertFalse(searchResult.isFoundInQuery());
  }

  @Test
  void testFindProductInWishListThrowsException() {
    when(wishListRepository.findByUserId(anyString())).thenReturn(Optional.empty());
    findProductInWishList = new FindProductInWishList(wishListRepository);

    assertThrows(WishListNotFoundException.class, () -> findProductInWishList.handle("1", "2"));
  }

  WishList createWishList() {
    return new CommonWishList(
        "1",
        Set.of("1","2")
    );
  }
}
