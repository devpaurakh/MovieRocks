package np.com.paurakh.movierocks.features.helper.roomdatabase;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {UserTable.class},version = 1,exportSchema = false)
public abstract class UserDatabase extends RoomDatabase {
    public abstract UserDao getDao();
}
