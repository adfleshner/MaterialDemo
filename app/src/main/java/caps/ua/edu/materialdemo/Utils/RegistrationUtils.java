package caps.ua.edu.materialdemo.Utils;

import android.content.SharedPreferences;

import caps.ua.edu.materialdemo.Models.User;

/**
 * Created by afleshner on 7/8/2014.
 */
public class RegistrationUtils {
    /**
     * Checks to see if the user is registered in the local shared preferences in the application.
     * @param preferences
     * @param username
     * @return
     */
    public static Boolean isRegistered(SharedPreferences preferences, String username){
       return  preferences.contains(mat_constants.USER_PREF_KEY + username);
    }

    /**
     * Registers user in the shared preferences and returns true if the user is successfully registered.
     * @param preferences
     * @param user
     * @return
     */
    public static Boolean registerUser(SharedPreferences preferences, User user){
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(mat_constants.USER_PREF_KEY + user.getUsername(), User.toJson(user));
        editor.commit();
        if(preferences.contains(mat_constants.USER_PREF_KEY + user.getUsername())){
            return true;
        }else{
            return false;
        }
    }
}
