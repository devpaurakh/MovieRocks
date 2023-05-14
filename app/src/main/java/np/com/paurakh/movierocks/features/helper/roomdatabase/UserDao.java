package np.com.paurakh.movierocks.features.helper.roomdatabase;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

/**
 * This interface defines the methods for accessing and manipulating the UserTable in the local Room database.
 */
@Dao
public interface UserDao {

    /**
     * Insert a new user into the UserTable.
     *
     * @param userTable The UserTable object representing the user to be inserted.
     */
    @Insert
    void insertUser(UserTable userTable);

    /**
     * Check if a username is already taken by another user.
     *
     * @param userName The username to check.
     * @return True if the username is already taken, false otherwise.
     */
    @Query("SELECT EXISTS(SELECT 1 FROM UserTable WHERE userName = :userName)")
    boolean isUserNameTaken(String userName);

    /**
     * Check if a user with the given username and password exists in the UserTable.
     *
     * @param userName The username of the user to check.
     * @param password The password of the user to check.
     * @return True if a user with the given username and password exists, false otherwise.
     */
    @Query("SELECT EXISTS(SELECT * FROM UserTable WHERE userName =:userName AND password=:password)")
    boolean login(String userName,String password);
}
