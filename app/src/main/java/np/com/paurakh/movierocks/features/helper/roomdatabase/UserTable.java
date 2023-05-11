package np.com.paurakh.movierocks.features.helper.roomdatabase;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import np.com.paurakh.movierocks.features.loginandsignupscreen.fragments.halper.LoginAndSignupContract;

@Entity(tableName = "userTable")
public class UserTable implements LoginAndSignupContract {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String userName;
    private String email;
    private String phoneNumber;
    private String password;

    public UserTable(int id, String userName, String email, String phoneNumber, String password) {
        this.id = id;
        this.userName = userName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


}
