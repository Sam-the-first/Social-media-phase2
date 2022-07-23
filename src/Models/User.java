package Models;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;

public class User {

    public final static ArrayList<User> users = new ArrayList<>();
    public static final String SECURITY_QUESTION = "What was your best friend's name in high school?";
    public static Comparator<? super User> searchComprator;


    private String username;
    private String password;
    private String firstname;
    private String lastname;
    private String bio;
    private LocalDate birthDate;
    private String securityAnswer;
    private String type;

    private ArrayList<User> followings=new ArrayList<>();
    private ArrayList<User> followers=new ArrayList<>();

    public User(String firstname, String lastname, String username, String password,String bio, LocalDate birthDate, String securityAnswer, String type) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.username = username;
        this.password = password;
        this.bio = bio;
        this.birthDate = birthDate;
        this.securityAnswer = securityAnswer;
        this.type = type;

        User.users.add(this);
    }

    public static User getUserByUsername (String username) {
        for (User user : User.users) {
            if (user.username.equals(username))
                return user;
        }
        return null;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getSecurityAnswer() {
        return securityAnswer;
    }

    public void setSecurityAnswer(String securityAnswer) {
        this.securityAnswer = securityAnswer;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public ArrayList<User> getFollowings() {
        return followings;
    }

    public ArrayList<User> getFollowers() {
        return followers;
    }
    public void removeFollowing(User user)
    {
        followings.remove(user);
        user.getFollowers().remove(this);
    }
    public void addFollowing(User user)
    {
        followings.add(user);
        user.getFollowers().add(this);
    }
    public void removeFollowers(User user)
    {

    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", bio='" + bio + '\'' +
                ", birthDate=" + birthDate +
                ", securityAnswer='" + securityAnswer + '\'' +
                '}';
    }
    public String toString2()
    {
        return "User{" +
                "username='" + username + '\'' +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", bio='" + bio + '\'' +
                ", birthDate=" + birthDate +
                '}';
    }
}
