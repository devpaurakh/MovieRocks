package np.com.paurakh.movierocks.features.helper.roomdatabase;

import androidx.room.Database;
import androidx.room.RoomDatabase;

/**
 * This class represents the local Room database that stores the UserTable.
 */
@Database(entities = {UserTable.class},version = 1,exportSchema = false)
public abstract class UserDatabase extends RoomDatabase {

    /**
     * Get the DAO (Data Access Object) for the UserTable.
     *
     * @return The UserDao object that can be used to access and manipulate the UserTable.
     */
    public abstract UserDao getDao();
}
