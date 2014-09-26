package ro.teamnet.junit.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ro.teamnet.junit.demo.model.AppUser;

/**
 * @author adrian.zamfirescu
 * @since 31/07/2014
 */
@Repository
public interface AppUserRepository extends JpaRepository<AppUser,Integer>{

    @Query(value = "SELECT appUser.active FROM AppUser appUser WHERE appUser.username=?")
    boolean getUserActiveByUsername(String username);

}

