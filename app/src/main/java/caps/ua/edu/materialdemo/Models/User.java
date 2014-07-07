package caps.ua.edu.materialdemo.Models;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * Created by afleshner on 7/7/2014.
 */
public class User {

    String Username;
    //never ever ever save a password only doing it this way for ease of use
    String Password;

    public static String toJson(User user) {
        String temp = "{}";
        try {
            temp = new Gson().toJson(user, User.class);
        } catch (Exception e) {

        }
        return temp;
    }

    public static String toJsonArray(ArrayList<User>user) {
        String temp = "[{}]";
        try {
            Type listOfUserObject = new TypeToken<ArrayList<User>>(){}.getType();
            temp = new Gson().toJson(user,listOfUserObject);
        } catch (Exception e) {

        }
        return temp;
    }

    public static User fromJson(String user) {
        User temp = new User();
        try {
            temp = new Gson().fromJson(user,User.class);
        } catch (Exception e) {

        }
        return temp;
    }

    public static ArrayList<User> fromJsonArray(String user) {
        ArrayList<User> temp = new ArrayList<User>();
        try {
            Type listOfUserObject = new TypeToken<ArrayList<User>>(){}.getType();
            temp = new Gson().fromJson(user, listOfUserObject);
        } catch (Exception e) {

        }
        return temp;
    }

}
