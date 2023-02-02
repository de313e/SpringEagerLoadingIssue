package com.kion.springeagerloadingissue.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.kion.springeagerloadingissue.model.UserSettings;

@Repository
public interface UserSettingsRepository
    extends JpaRepository<UserSettings, String> { //, QuerydslPredicateExecutor<User> {

}
