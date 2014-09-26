package ro.teamnet.junit.demo.framework;

import ro.teamnet.junit.demo.model.AppUser;

/**
 * @author adrian.zamfirescu
 * @since 05/08/2014
 */
public interface ISecurityService {

    AppUser getCurrentUser();

    boolean isAdminAuthenticatedUser();

}
