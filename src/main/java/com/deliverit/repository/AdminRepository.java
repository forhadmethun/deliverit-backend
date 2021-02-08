package com.deliverit.repository;

import com.deliverit.entity.user.User;
import com.deliverit.entity.user.UserType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdminRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
    List<User> findByUserType(UserType userType);
}
