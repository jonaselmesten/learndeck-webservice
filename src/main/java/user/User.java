package user;

import javax.persistence.*;

@Entity
public class User {

    enum UserType {
        STUDENT, TEACHER;
    }

    @Column(name="user_id")
    private @Id @GeneratedValue(strategy = GenerationType.AUTO) Long userId;
    @Column(name="user_type")
    private String userType;

    public User() {}

    public User(long userId, String userType) {
        this.userId = userId;
        this.userType = userType;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public Long getUserId() {
        return userId;
    }


    public void setUserId(Long userId) {
        this.userId = userId;
    }




}
