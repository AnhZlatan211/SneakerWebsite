package project01.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import project01.ecommerce.model.User;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);

    @Query("SELECT u FROM User u WHERE u.fullName LIKE %:keyword%")
    List<User> findByFullNameContaining(@Param("keyword") String keyword);

    @Query("SELECT COUNT(u) FROM User u")
    long countTotalUsers();
}
