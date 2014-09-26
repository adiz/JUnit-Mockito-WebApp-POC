package ro.teamnet.junit.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import ro.teamnet.junit.demo.dao.AppUserRepository;
import ro.teamnet.junit.demo.framework.ISecurityService;
import ro.teamnet.junit.demo.model.AppUser;
import ro.teamnet.junit.demo.utils.AppUserType;

/**
 * @author adrian.zamfirescu
 * @since 31/07/2014
 */
public class SecurityService implements ISecurityService{

    @Autowired
    private AppUserRepository appUserRepository;

    public AppUser getCurrentUser() {
        /* some framework-dependent implementation here */
        return null;
    }

    public boolean isAdminAuthenticatedUser(){

        AppUser authenticatedUser = getCurrentUser();

        boolean isActiveUser = appUserRepository.getUserActiveByUsername(authenticatedUser.getUsername());
        if (!isActiveUser)
            return false;

        if (authenticatedUser.getType().equals(AppUserType.ADMINISTRATOR.getTypeName()))
            return true;

        return false;
    }

}
