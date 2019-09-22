package ua.pl.alex.aliexpress.codes.Entity;

/**
 * Created by Aleksandr on 18.09.2019.
 */
public class User {
    private int id;
    private String login;
    private String password;
    private String permissions;

    public User( String login, String password, String permissions) {
        this.login = login;
        this.password = password;
        this.permissions = permissions;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPermissions() {
        return permissions;
    }

    public void setPermissions(String permissions) {
        this.permissions = permissions;
    }
}
