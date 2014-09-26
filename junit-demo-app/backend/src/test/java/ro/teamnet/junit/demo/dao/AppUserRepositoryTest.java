package ro.teamnet.junit.demo.dao;

import org.fest.assertions.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ro.teamnet.junit.demo.model.AppUser;

@ContextConfiguration(locations = {"classpath:spring-repository.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class AppUserRepositoryTest {

    @Autowired
    private AppUserRepository appUserRepository;

    @Test
    public void should_check_if_user_is_active_by_username_lookup(){

        // given
        AppUser testUser = new AppUser();
        testUser.setUsername("test_user");
        testUser.setPassword("testPass");
        testUser.setType("someType");
        testUser.setActive(false);

        AppUser savedTestUser = appUserRepository.save(testUser);

        // when
        boolean isActive = appUserRepository.getUserActiveByUsername("test_user");

        // then
        Assertions.assertThat(isActive).isFalse();

        appUserRepository.delete(savedTestUser);

    }

}
