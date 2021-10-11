package br.com.labs.wishlist.service.usecases;

import br.com.labs.wishlist.application.dto.WishListDto;
import br.com.labs.wishlist.domain.entities.WishList;
import br.com.labs.wishlist.domain.entities.impl.CommonWishList;
import br.com.labs.wishlist.domain.factory.WishListFactory;
import br.com.labs.wishlist.domain.factory.impl.CommonWishListFactory;
import br.com.labs.wishlist.domain.repository.WishListRepository;
import br.com.labs.wishlist.service.exception.MaxNumberOfProductsException;
import br.com.labs.wishlist.service.exception.WishListAlreadyExistException;
import java.util.Optional;
import java.util.Set;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class CreateWishListTest {

  @Mock
  WishListRepository wishListRepository;
  WishListFactory wishListFactory;
  CreateWishList createWishList;

  @BeforeEach
  void setup() {
    wishListFactory = new CommonWishListFactory();
  }

  @Test
  void testIfWishListIsCreated() {
    when(wishListRepository.findByUserId(anyString())).thenReturn(Optional.empty());
    when(wishListRepository.save(any())).thenReturn(createWishList());
    createWishList = new CreateWishList(wishListFactory, wishListRepository);

    assertEquals("1", createWishList.handle("1", new WishListDto(Set.of("1", "2"))).getUserId());
    assertEquals(2, createWishList.handle("1", new WishListDto(Set.of("1", "2"))).getProducts().size());
  }

  @Test
  void testIfProductReachMax() {
    when(wishListRepository.findByUserId(anyString())).thenReturn(Optional.empty());
    createWishList = new CreateWishList(wishListFactory, wishListRepository);

    assertThrows(MaxNumberOfProductsException.class, () -> createWishList.handle(
        "1",
        new WishListDto(Set.of("1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20","21"))));
  }

  @Test
  void testIfWishListAlreadyExist() {
    when(wishListRepository.findByUserId(anyString())).thenReturn(Optional.of(createWishList()));
    createWishList = new CreateWishList(wishListFactory, wishListRepository);

    assertThrows(WishListAlreadyExistException.class, () -> createWishList.handle("1", new WishListDto()));
  }

  WishList createWishList() {
    return new CommonWishList(
     "1",
     Set.of("1","2")
    );
  }
}
