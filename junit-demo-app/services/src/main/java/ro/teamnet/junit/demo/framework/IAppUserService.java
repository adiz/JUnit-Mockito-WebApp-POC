package ro.teamnet.junit.demo.framework;

import ro.teamnet.junit.demo.exception.ServiceException;
import ro.teamnet.junit.demo.model.AppUser;
import ro.teamnet.junit.demo.model.UserInfo;

/**
 * @author adrian.zamfirescu
 * @since 31/07/2014
 */
public interface IAppUserService {

    UserInfo saveUpdatedUserInfo(int appUserId, int noQuestionsAnswered) throws ServiceException;

    void updateUserInfo(AppUser appUser, int pointsGained);

}
