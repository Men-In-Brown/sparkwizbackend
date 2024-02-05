package com.nighthawk.spring_portfolio.mvc.many;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ManyJpaRepository extends JpaRepository<Many, Long> {
    // Custom queries can be added here based on your requirements
}

