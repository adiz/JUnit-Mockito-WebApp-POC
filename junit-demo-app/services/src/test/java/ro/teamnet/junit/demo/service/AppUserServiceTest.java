package ro.teamnet.junit.demo.service;


import org.fest.assertions.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.runners.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;
import ro.teamnet.junit.demo.dao.AppUserRepository;
import ro.teamnet.junit.demo.exception.ServiceException;
import ro.teamnet.junit.demo.framework.ISecurityService;
import ro.teamnet.junit.demo.model.AppUser;
import ro.teamnet.junit.demo.model.UserInfo;
import ro.teamnet.junit.demo.utils.UserStatus;

@RunWith(MockitoJUnitRunner.class)
public class AppUserServiceTest {

    @Mock
    private ISecurityService mockedSecurityService;
    @Mock
    private AppUserRepository mockedAppUserRepository;

    @InjectMocks
    private AppUserService appUserService;

    private AppUser testUser;

    @Before
    public void setUp(){

        UserInfo testUserInfo = new UserInfo();
        testUserInfo.setPoints(100);
        testUserInfo.setStatus(UserStatus.BEGINNER.getUserStatus());

        testUser = new AppUser();
        testUser.setUserInfo(testUserInfo);

    }

    @Test
    public void should_update_player_points_after_gaining(){

        // given
        int numberOfPointsGained = 350;

        // when
        appUserService.updateUserInfo(testUser,numberOfPointsGained);

        // then
        Assertions.assertThat(testUser.getUserInfo().getPoints()).isEqualTo(450);
        Assertions.assertThat(testUser.getUserInfo().getStatus()).isEqualTo(UserStatus.BEGINNER.getUserStatus());

    }

    @Test
    public void should_update_player_points_after_gaining_and_set_intermediate_level(){

        // given
        int numberOfPointsGained = 400;

        // when
        appUserService.updateUserInfo(testUser,numberOfPointsGained);

        // then
        Assertions.assertThat(testUser.getUserInfo().getPoints()).isEqualTo(500);
        Assertions.assertThat(testUser.getUserInfo().getStatus()).isEqualTo(UserStatus.INTERMEDIATE.getUserStatus());

    }

    @Test
    public void should_update_player_points_after_gaining_and_set_professional_level(){

        // given
        int numberOfPointsGained = 950;

        // when
        appUserService.updateUserInfo(testUser,numberOfPointsGained);

        // then
        Assertions.assertThat(testUser.getUserInfo().getPoints()).isEqualTo(1050);
        Assertions.assertThat(testUser.getUserInfo().getStatus()).isEqualTo(UserStatus.PROFESSIONAL.getUserStatus());

    }

    @Test
    public void should_save_updated_player_user_info_after_gaining_points() throws ServiceException {

        // given
        Mockito.when(mockedSecurityService.isAdminAuthenticatedUser()).thenReturn(true);
        Mockito.when(mockedAppUserRepository.findOne(Mockito.anyInt())).thenReturn(testUser);

        appUserService = Mockito.spy(appUserService);

        Mockito.doAnswer(new Answer() {
            @Override
            public Void answer(InvocationOnMock invocationOnMock) throws Throwable {
                AppUser appUserArg = (AppUser)invocationOnMock.getArguments()[0];
                appUserArg.getUserInfo().setPoints(700);
                appUserArg.getUserInfo().setStatus(UserStatus.INTERMEDIATE.getUserStatus());
                return null;
            }
        }).when(appUserService).updateUserInfo(Mockito.<AppUser>any(),Mockito.anyInt());

        Mockito.when(mockedAppUserRepository.save(Mockito.any(AppUser.class))).thenReturn(testUser);

        int userId = 5;
        int numberOfPointsGained = 600;

        // when
        UserInfo updatedUserInfo = appUserService.saveUpdatedUserInfo(userId,numberOfPointsGained);

        // then
        Mockito.verify(mockedSecurityService).isAdminAuthenticatedUser();
        Mockito.verify(mockedAppUserRepository).findOne(5);
        Mockito.verify(mockedAppUserRepository).save(testUser);
        Assertions.assertThat(updatedUserInfo.getPoints()).isEqualTo(700);
        Assertions.assertThat(updatedUserInfo.getStatus()).isEqualTo(UserStatus.INTERMEDIATE.getUserStatus());

    }

    @Test(expected = ServiceException.class)
    public void should_throw_exception_when_admin_is_not_authenticated() throws ServiceException {

        // given
        Mockito.when(mockedSecurityService.isAdminAuthenticatedUser()).thenReturn(false);

        int userId = 5;
        int numberOfPointsGained = 600;

        // when
        appUserService.saveUpdatedUserInfo(userId,numberOfPointsGained);

        // then - should throw exception

    }

}
