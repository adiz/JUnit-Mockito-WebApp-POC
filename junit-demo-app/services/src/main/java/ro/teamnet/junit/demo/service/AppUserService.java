package ro.teamnet.junit.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.teamnet.junit.demo.dao.AppUserRepository;
import ro.teamnet.junit.demo.exception.ServiceException;
import ro.teamnet.junit.demo.framework.IAppUserService;
import ro.teamnet.junit.demo.framework.ISecurityService;
import ro.teamnet.junit.demo.model.AppUser;
import ro.teamnet.junit.demo.model.UserInfo;
import ro.teamnet.junit.demo.utils.UserStatus;

import java.util.ResourceBundle;

/**
 * @author adrian.zamfirescu
 * @since 31/07/2014
 */
@Service
public class AppUserService implements IAppUserService {

    private static ResourceBundle businessErrorsBundle = ResourceBundle.getBundle("businessErrorMessages");

    private ISecurityService securityService;
    private AppUserRepository appUserRepository;

    @Autowired
    public AppUserService(ISecurityService securityService, AppUserRepository appUserRepository){
        this.securityService = securityService;
        this.appUserRepository = appUserRepository;
    }

    public UserInfo saveUpdatedUserInfo(int appUserId, int pointsGained) throws ServiceException {

        if (!securityService.isAdminAuthenticatedUser())
            throw new ServiceException(businessErrorsBundle.getString("user.not.admin"));

        AppUser appUser = appUserRepository.findOne(appUserId);
        updateUserInfo(appUser,pointsGained);

        AppUser updatedAppUser = appUserRepository.save(appUser);
        return updatedAppUser.getUserInfo();

    }

    public void updateUserInfo(AppUser appUser, int pointsGained){

        UserInfo userInfo = appUser.getUserInfo();

        int initialNoPoints = userInfo.getPoints();
        userInfo.setPoints(initialNoPoints+pointsGained);

        if (userInfo.getPoints()>=UserStatus.PROFESSIONAL.getMinPoints())
            userInfo.setStatus(UserStatus.PROFESSIONAL.getUserStatus());
        else
            if (userInfo.getPoints()>=UserStatus.INTERMEDIATE.getMinPoints())
                userInfo.setStatus(UserStatus.INTERMEDIATE.getUserStatus());

    }

}
