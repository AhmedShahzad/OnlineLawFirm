package myapp.admin.example.com.onlinelawfirm;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

public class SessionManager {
    private static String TAG = SessionManager.class.getSimpleName();

    // Shared Preferences
    SharedPreferences pref;

    SharedPreferences.Editor editor;
    Context _context;

    // Shared pref mode
    int PRIVATE_MODE = 0;

    // Shared preferences file name
    private static final String PREF_NAME = "lawyer";

    private static final String KEY_IS_LOGGEDIN = "isLoggedIn";
    private static final String KEY_USERNAME = "keyusername";
    private static final String KEY_EMAIL = "keyemail";
    private static final String KEY_CONTACT = "keycontact";
    private static final String KEY_USERTYPE= "keyusertype";
    private static final String KEY_ID = "keyid";
    private static final String KEY_PASS = "keypass";
    private static final String pdf = "pdf";


    public SessionManager(Context context) {
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    public void setLogin(boolean isLoggedIn) {

        editor.putBoolean(KEY_IS_LOGGEDIN, isLoggedIn);


        // commit changes
        editor.commit();

        Log.d(TAG, "User login session modified!");
    }

    public boolean isLoggedIn(){
        return pref.getBoolean(KEY_IS_LOGGEDIN, false);
    }

    public void setKeyUsername(String name) {
        editor.putString(KEY_USERNAME, name);
        // commit changes
        editor.commit();
        Log.d(TAG, "User name");
    }

    public String getKeyUsername()
    {
        return pref.getString(KEY_USERNAME, "");
    }

    public  String getPdf() {
       return pref.getString(pdf, "");
    }
    public void setPdf(String pdf)
    {
        editor.putString(pdf, pdf);
        // commit changes
        editor.commit();
        Log.d(TAG, "pdf");

    }

    public  String getKeyEmail() {
        return pref.getString(KEY_EMAIL , "");
    }
    public void setKeyEmail(String email)
    {
        editor.putString(KEY_EMAIL, email);
        // commit changes
        editor.commit();
        Log.d(TAG, "User email");

    }

    public  String getKeyContact() {
        return pref.getString(KEY_CONTACT , "");
    }
    public void setKeyContact(String contact)
    {
        editor.putString(KEY_CONTACT, contact);
        // commit changes
        editor.commit();
        Log.d(TAG, "User contact");

    }
    public  String getKeyPass() {
        return pref.getString(KEY_PASS , "");
    }
    public void setKeyPass(String pass)
    {
        editor.putString(KEY_PASS, pass);
        // commit changes
        editor.commit();
        Log.d(TAG, "User pass");

    }
    public  String getKeyId() {
        return pref.getString(KEY_ID ,"");
    }
    public void setKeyId(String key)
    {
        editor.putString(KEY_ID, key);
        // commit changes
        editor.commit();
        Log.d(TAG, "User id");

    }
    public  String getKeyUsertype()
    {
        return pref.getString(KEY_USERTYPE , "");
    }
    public void setKeyUsertype(String type)
    {
        editor.putString(KEY_USERTYPE, type);
        // commit changes
        editor.commit();
        Log.d(TAG, "User email");

    }

}
