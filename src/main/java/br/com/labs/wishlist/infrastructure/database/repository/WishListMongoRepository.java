package br.com.labs.wishlist.infrastructure.database.repository;

import br.com.labs.wishlist.domain.entities.WishList;
import java.util.Optional;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface WishListMongoRepository extends MongoRepository<WishList, String> {

  @Query("{'userId': ObjectId(?0)}")
  Optional<WishList> findByUserId(String userId);
}
