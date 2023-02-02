package com.kion.springeagerloadingissue.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.kion.springeagerloadingissue.model.UserTermsOfUse;

@Repository
public interface UserTermsOfUseRepository
    extends JpaRepository<UserTermsOfUse, String> { //, QuerydslPredicateExecutor<User> {

}
