package ro.teamnet.junit.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import ro.teamnet.junit.demo.exception.ControllerException;
import ro.teamnet.junit.demo.exception.ServiceException;
import ro.teamnet.junit.demo.framework.IAppUserService;
import ro.teamnet.junit.demo.model.UserInfo;

import java.util.ResourceBundle;

/**
 * @author adrian.zamfirescu
 * @since 31/07/2014
 */
@Controller
/* some mapping for ajax requests */
public class AppUserController {

    private static ResourceBundle controllerErrorsBundle = ResourceBundle.getBundle("controllerErrorMessages");

    @Autowired
    private IAppUserService appUserService;

    @ResponseBody
    /* more mapping here */
    public UserInfo updatePlayerStatus(@RequestParam Integer playerUserId, @RequestParam Integer pointsGained) throws ControllerException {

        if (playerUserId==null || pointsGained==null)
            throw new ControllerException(HttpStatus.BAD_REQUEST.value(),controllerErrorsBundle.getString("bad.request.parameters"));

        UserInfo updateUserInfo = null;
        try {
            updateUserInfo = appUserService.saveUpdatedUserInfo(playerUserId, pointsGained);
        } catch (ServiceException e) {
            throw new ControllerException(HttpStatus.BAD_REQUEST.value(),e.getMessage());
        }

        return updateUserInfo;

    }

}
