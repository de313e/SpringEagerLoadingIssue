package com.kion.springeagerloadingissue;

import com.kion.springeagerloadingissue.model.User;
import com.kion.springeagerloadingissue.model.UserSettings;
import com.kion.springeagerloadingissue.model.UserTermsOfUse;
import com.kion.springeagerloadingissue.repository.UserRepository;
import com.kion.springeagerloadingissue.repository.UserSettingsRepository;
import com.kion.springeagerloadingissue.repository.UserTermsOfUseRepository;
import jakarta.persistence.EntityManager;
import org.aspectj.lang.annotation.After;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional // <-- works fine with this annotation. Without second and third test fail in the same way
class SpringEagerLoadingIssueApplicationTests {

	@Autowired
	UserRepository userRepository;

	@Autowired
	UserTermsOfUseRepository userTermsOfUseRepository;

	@Autowired
	UserSettingsRepository userSettingsRepository;

	@Autowired
	EntityManager em;

	User user = new User();

	@BeforeEach
	void beforeEach() {

		UserSettings userSettings = new UserSettings();
		userSettings.setUser(user);
		user.setUserSettings(userSettings);

		UserTermsOfUse userTermsOfUse = new UserTermsOfUse();
		userTermsOfUse.setUser(user);
		user.setUserTermsOfUse(userTermsOfUse);

		//when
		userRepository.save(user);

	}

	@AfterEach
	void afterEach(){
		userRepository.delete(user);
	}

	@Test
	void firstTest() {

		//given
		System.out.println("- 1st TEST ");
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
	@Test
	void thirdTest() {
		System.out.println("- 3rd TEST ");

		User user = em.createQuery("select u from User u", User.class).getSingleResult();
		System.out.println("------------------------");
		System.out.println("User.ID: " + user.getId());
		System.out.println("User.userSettings.ID: " + user.getUserSettings().getId()); // Null Pointer da user settings nicht da
		System.out.println("User.userTermsOfUse.ID: " + user.getUserTermsOfUse().getId());
		System.out.println("------------------------");

	}
}
