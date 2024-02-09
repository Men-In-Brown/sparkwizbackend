// package com.nighthawk.spring_portfolio.mvc.test;

// import org.springframework.data.jpa.repository.JpaRepository;
// import org.springframework.data.jpa.repository.Query;

// import java.util.List;

// public class DataTestJpaRepository {
    
// public interface DataTestJpaRepository extends JpaRepository<Test, Long> {
//     Test findByEmail(String state);

//     List<Test> findAllByOrderByNameAsc();

//     List<Test> findByNameContainingIgnoreCaseOrEmailContainingIgnoreCase(String name, String email);

//     Test findByEmailAndPassword(String state, String password);

//     @Query(
//             value = "SELECT * FROM Test p WHERE p.name LIKE ?1 or p.email LIKE ?1",
//             nativeQuery = true)
//     List<Test> findByLikeTermNative(String term);
//     }
// }