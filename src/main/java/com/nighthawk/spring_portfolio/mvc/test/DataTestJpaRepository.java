package com.nighthawk.spring_portfolio.mvc.test;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

// Rename the class to avoid name conflict
public interface DataTestJpaRepository extends JpaRepository<DataTest, Long> {
    DataTest findByEmail(String state);

    List<DataTest> findAllByOrderByNameAsc();

    List<DataTest> findByNameContainingIgnoreCaseOrStateContainingIgnoreCase(String name, String email);

    DataTest findByEmailAndPassword(String state, String password);

    @Query(
            value = "SELECT * FROM DataTest p WHERE p.name LIKE ?1 or p.email LIKE ?1",
            nativeQuery = true)
    List<DataTest> findByLikeTermNative(String term);
}
