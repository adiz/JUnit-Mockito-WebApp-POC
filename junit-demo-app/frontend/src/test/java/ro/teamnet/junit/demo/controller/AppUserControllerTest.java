package ro.teamnet.junit.demo.controller;

import org.fest.assertions.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import ro.teamnet.junit.demo.exception.ControllerException;
import ro.teamnet.junit.demo.exception.ServiceException;
import ro.teamnet.junit.demo.framework.IAppUserService;
import ro.teamnet.junit.demo.model.UserInfo;

@RunWith(MockitoJUnitRunner.class)
public class AppUserControllerTest {

    @Mock
    private IAppUserService mockedAppUserService;

    @InjectMocks
    private AppUserController appUserController;

    @Test(expected = ControllerException.class)
    public void should_throw_exception_if_player_data_is_corrupted() throws ControllerException {

        // given
        Integer testPlayerId = null;
        Integer numberOfPointsGained = 100;

        // when
        appUserController.updatePlayerStatus(testPlayerId,numberOfPointsGained);

        // then - should throw exception

    }

    @Test(expected = ControllerException.class)
    public void should_throw_controller_exception_if_business_exception_occured() throws ServiceException, ControllerException {

        // given
        Integer testPlayerId = 5;
        Integer numberOfPointsGained = 100;

        Mockito.when(mockedAppUserService.saveUpdatedUserInfo(Mockito.anyInt(),Mockito.anyInt())).thenThrow(ServiceException.class);

        // when
        appUserController.updatePlayerStatus(testPlayerId,numberOfPointsGained);

        // then - should throw exception

    }

    @Test
    public void should_update_player_status() throws ControllerException, ServiceException {

        // given
        Integer testPlayerId = 5;
        Integer numberOfPointsGained = 100;

        UserInfo testUserInfo = new UserInfo();
        Mockito.when(mockedAppUserService.saveUpdatedUserInfo(Mockito.anyInt(),Mockito.anyInt())).thenReturn(testUserInfo);

        // when
        UserInfo userInfo = appUserController.updatePlayerStatus(testPlayerId,numberOfPointsGained);

        // then
        Mockito.verify(mockedAppUserService).saveUpdatedUserInfo(5,100);
        Assertions.assertThat(userInfo).isEqualTo(testUserInfo);

    }

}
