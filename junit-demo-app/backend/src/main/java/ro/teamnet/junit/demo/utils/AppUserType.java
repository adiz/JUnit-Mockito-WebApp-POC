package ro.teamnet.junit.demo.utils;

/**
 * @author adrian.zamfirescu
 * @since 01/09/2014
 */
public enum AppUserType {

    ADMINISTRATOR("admin"),
    PLAYER("player");

    private String typeName;

    AppUserType(String typeName){
        this.typeName = typeName;
    }

    public String getTypeName(){
        return typeName;
    }

}
