package np.com.paurakh.movierocks.features.helper.roomdatabase;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface UserDao {
    @Insert
    void insertUser(UserTable userTable);

    @Query("SELECT EXISTS(SELECT 1 FROM UserTable WHERE userName = :userName)")
    boolean isUserNameTaken(String userName);


    @Query("SELECT EXISTS(SELECT * FROM UserTable WHERE userName =:userName AND password=:password)")
    boolean login(String userName,String password);
}
