package com.nighthawk.spring_portfolio.mvc.user;

import org.springframework.data.jpa.repository.JpaRepository;

public interface  UserRoleJpaRepository extends JpaRepository<UserRole, Long> {
    UserRole findByName(String name);
}
