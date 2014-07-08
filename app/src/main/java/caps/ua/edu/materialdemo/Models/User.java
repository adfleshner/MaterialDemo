package caps.ua.edu.materialdemo.Models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * Created by afleshner on 7/7/2014.
 */
public class User implements Parcelable {

    private String Username;
    //never ever ever save a password only doing it this way for ease of use
    private String Email;

    public User(){};

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public User(String username, String email) {
        Username = username;
        Email = email;
    }

    public static String toJson(User user) {
        String temp = "{}";
        try {
            temp = new Gson().toJson(user, User.class);
        } catch (Exception e) {

        }
        return temp;
    }

    public static String toJsonArray(ArrayList<User> user) {
        String temp = "[{}]";
        try {
            Type listOfUserObject = new TypeToken<ArrayList<User>>() {
            }.getType();
            temp = new Gson().toJson(user, listOfUserObject);
        } catch (Exception e) {

        }
        return temp;
    }

    public static User fromJson(String user) {
        User temp = new User();
        try {
            temp = new Gson().fromJson(user, User.class);
        } catch (Exception e) {

        }
        return temp;
    }

    public static ArrayList<User> fromJsonArray(String user) {
        ArrayList<User> temp = new ArrayList<User>();
        try {
            Type listOfUserObject = new TypeToken<ArrayList<User>>() {
            }.getType();
            temp = new Gson().fromJson(user, listOfUserObject);
        } catch (Exception e) {

        }
        return temp;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.Username);
        dest.writeString(this.Email);
    }



    private User(Parcel in) {
        this.Username = in.readString();
        this.Email = in.readString();
    }

    public static final Parcelable.Creator<User> CREATOR = new Parcelable.Creator<User>() {
        public User createFromParcel(Parcel source) {
            return new User(source);
        }

        public User[] newArray(int size) {
            return new User[size];
        }
    };
}
