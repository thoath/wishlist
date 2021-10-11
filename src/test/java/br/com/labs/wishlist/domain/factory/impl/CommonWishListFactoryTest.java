package br.com.labs.wishlist.domain.factory.impl;

import br.com.labs.wishlist.domain.entities.impl.CommonWishList;
import java.util.Set;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class CommonWishListFactoryTest {

  CommonWishListFactory wishList = new CommonWishListFactory();

  @Test
  public void testIfCommonWishListIsCreated(){
    assertTrue(wishList.create("1", Set.of("1")) instanceof CommonWishList);
  }

}
