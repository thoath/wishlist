package br.com.labs.wishlist.service.usecases;

import br.com.labs.wishlist.application.dto.WishListDto;
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
public class UpdateWishListTest {

  @Mock
  WishListRepository wishListRepository;
  UpdateWishList updateWishList;

  @Test
  void testIfUpdateWishListReturnWithCorrectProductsSize() {
    WishList wishList = createWishList();
    when(wishListRepository.findByUserId(anyString())).thenReturn(Optional.of(wishList));
    when(wishListRepository.save(any())).thenReturn(wishList);
    updateWishList = new UpdateWishList(wishListRepository);

    assertEquals(3, updateWishList.handle("1", createWishListDto("3")).getProducts().size());
  }

  @Test
  void testIfUpdateWishListReturnsSameNumberOfProductsWithAlreadyExistentIdInRequest() {
    WishList wishList = createWishList();
    when(wishListRepository.findByUserId(anyString())).thenReturn(Optional.of(wishList));
    when(wishListRepository.save(any())).thenReturn(wishList);
    updateWishList = new UpdateWishList(wishListRepository);

    assertEquals(2, updateWishList.handle("1", createWishListDto("1")).getProducts().size());
  }

  @Test
  void testIfUpdateWishListThrowsExceptionWhenWishListNotFound() {
    when(wishListRepository.findByUserId(anyString())).thenReturn(Optional.empty());
    updateWishList = new UpdateWishList(wishListRepository);

    assertThrows(WishListNotFoundException.class, () -> updateWishList.handle("1", createWishListDto("1")));
  }

  WishList createWishList() {
    return new CommonWishList(
        "1",
        Set.of("1","2")
    );
  }

  WishListDto createWishListDto(String productId) {
    return new WishListDto(Set.of(productId));
  }
}
