package ro.teamnet.junit.demo.utils;

/**
 * @author adrian.zamfirescu
 * @since 04/08/2014
 */
public enum UserStatus {

    BEGINNER("beginner",0),
    INTERMEDIATE("intermediate",500),
    PROFESSIONAL("professional",1000);

    private String userStatus;
    private int minPoints;

    UserStatus(String userStatus, int minPoints){
        this.userStatus = userStatus;
        this.minPoints = minPoints;
    }

    public String getUserStatus(){
        return userStatus;
    }

    public int getMinPoints() {
        return minPoints;
    }
}
