package ro.teamnet.junit.demo.model;

import javax.persistence.*;

/**
 * @author adrian.zamfirescu
 * @since 31/07/2014
 */
@Entity
@Table(name = "appuser")
public class AppUser extends BaseEntity{

    private String username;
    private String password;
    private String type;
    private boolean active;
    private UserInfo userInfo;

    @Column(name = "username")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Column(name = "password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Column(name = "type")
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Column(name = "active")
    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "userinfo_id")
    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }
}
