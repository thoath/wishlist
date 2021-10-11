package br.com.labs.wishlist.service.usecases;

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
public class FindAllProductsFromWishListTest {

  @Mock
  WishListRepository wishListRepository;
  FindAllProductsFromWishList findAllProductsFromWishList;

  @Test
  void testIfFindAllReturnProducts() {
    when(wishListRepository.findByUserId(anyString())).thenReturn(Optional.of(createWishList()));
    findAllProductsFromWishList = new FindAllProductsFromWishList(wishListRepository);

    assertEquals(2, findAllProductsFromWishList.handle("1").size());
  }

  @Test
  void testIfFindAllThrowsException() {
    when(wishListRepository.findByUserId(anyString())).thenReturn(Optional.empty());
    findAllProductsFromWishList = new FindAllProductsFromWishList(wishListRepository);

    assertThrows(WishListNotFoundException.class, () -> findAllProductsFromWishList.handle("1"));
  }

  WishList createWishList() {
    return new CommonWishList(
        "1",
        Set.of("1","2")
    );
  }
}
