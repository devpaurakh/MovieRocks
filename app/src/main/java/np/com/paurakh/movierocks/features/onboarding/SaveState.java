package np.com.paurakh.movierocks.features.onboarding;

import android.content.Context;
import android.content.SharedPreferences;

public class SaveState {
    public Context context;
    public String SaveName;
    private final SharedPreferences sharedPreferences;

    public SaveState(Context context, String saveName) {
        this.context = context;
        this.SaveName = saveName;
        sharedPreferences = context.getSharedPreferences(saveName, Context.MODE_PRIVATE);
    }
    public void setSate(int key){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("key",key);
        editor.apply();
    }

    public int getState(){
        return sharedPreferences.getInt("key",0);
    }
}
