package ab.servercall;

/**
 * Created by arlen on 8/25/17.
 */

public class User {
    private String firstName;
    private String lastName;
    private String userEmail;
    private String userId;
    private String userStatus;

    public User(){}

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public String getUserId() {
        return userId;
    }

    public String getUserStatus() {
        return userStatus;
    }

    public User(String firstName, String lastName, String userEmail, String userId, String userStatus){
        this.firstName = firstName;
        this.lastName = lastName;
        this.userEmail = userEmail;
        this.userId = userId;
        this.userStatus = userStatus;
    }
}
