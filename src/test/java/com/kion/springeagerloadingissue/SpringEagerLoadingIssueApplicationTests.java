package com.kion.springeagerloadingissue;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.List;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.kion.springeagerloadingissue.model.User;
import com.kion.springeagerloadingissue.model.UserSettings;
import com.kion.springeagerloadingissue.model.UserTermsOfUse;
import com.kion.springeagerloadingissue.repository.UserSettingsRepository;
import com.kion.springeagerloadingissue.repository.UserTermsOfUseRepository;
import com.kion.springeagerloadingissue.repository.UserRepository;

@SpringBootTest
class SpringEagerLoadingIssueApplicationTests {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserTermsOfUseRepository userTermsOfUseRepository;

    @Autowired
    UserSettingsRepository userSettingsRepository;

    @Test
    @Order(1)
    void firstTest() {
        //given
        System.out.println("- 1st TEST ");
        User user = new User();

        UserSettings userSettings = new UserSettings();
        userSettings.setUser(user);
        user.setUserSettings(userSettings);

        UserTermsOfUse userTermsOfUse = new UserTermsOfUse();
        userTermsOfUse.setUser(user);
        user.setUserTermsOfUse(userTermsOfUse);

        //when
        userRepository.save(user);
        // then
        assertEquals(1, userRepository.findAll().size());
        assertEquals(1, userTermsOfUseRepository.findAll().size());
        assertEquals(1, userSettingsRepository.findAll().size());

        System.out.println("------------------------");
        System.out.println("User.ID: " + user.getId());
        System.out.println("User.userSettings.ID: " + user.getUserSettings().getId());
        System.out.println("User.userTermsOfUse.ID: " + user.getUserTermsOfUse().getId());
        System.out.println("------------------------");
    }

    @Test
    @Order(2)
    void secondTest() {
        System.out.println("- 2nd TEST ");
        List<User> all = userRepository.findAll();

        assertEquals(1, all.size());
        User user = all.get(0);
        System.out.println("------------------------");
        System.out.println("User.ID: " + user.getId());
        System.out.println("User.userSettings.ID: " + user.getUserSettings().getId()); // Null Pointer da user settings nicht da
        System.out.println("User.userTermsOfUse.ID: " + user.getUserTermsOfUse().getId());
        System.out.println("------------------------");

    }

}
