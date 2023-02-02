package com.kion.springeagerloadingissue.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.kion.springeagerloadingissue.model.User;

@Repository
public interface UserRepository
    extends JpaRepository<User, String> { //, QuerydslPredicateExecutor<User> {

}
