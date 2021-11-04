package br.com.labs.wishlist.usecase;

import br.com.labs.wishlist.domain.entities.WishList;
import br.com.labs.wishlist.domain.entities.impl.CommonWishList;
import br.com.labs.wishlist.infrastructure.database.repository.WishListMongoRepository;
import br.com.labs.wishlist.domain.exception.WishListNotFoundException;
import java.util.Optional;
import java.util.Set;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class DeleteProductFromWishListTest {

  @Mock
  WishListMongoRepository wishListRepository;
  DeleteProductFromWishList deleteProductFromWishList;

  @Test
  void testIfDeleteProductFromWishList() {
    WishList wishList = createWishList();
    when(wishListRepository.findByUserId(anyString())).thenReturn(Optional.of(wishList));
    when(wishListRepository.save(any())).thenReturn(wishList);
    deleteProductFromWishList = new DeleteProductFromWishList(wishListRepository);
    deleteProductFromWishList.handle("1", "2");

    assertEquals(1, wishList.getProducts().size());
  }

  @Test
  void testIfDeleteProductThrowsException() {
    when(wishListRepository.findByUserId(anyString())).thenReturn(Optional.empty());
    deleteProductFromWishList = new DeleteProductFromWishList(wishListRepository);

    assertThrows(WishListNotFoundException.class, () -> deleteProductFromWishList.handle("1", "2"));
  }

  WishList createWishList() {
    return new CommonWishList(
        "1",
        Set.of("1","2")
    );
  }
}
