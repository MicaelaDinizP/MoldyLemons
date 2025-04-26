package devandroid.micaela.moldylemons.util;

import android.content.Context;
import android.content.SharedPreferences;

public class SessionManager {

    private static final String PREF_NAME = "UserSession";
    private static final String KEY_ID = "user_id";
    private static final String KEY_USERNAME = "username";
    private static final String KEY_IS_LOGGED_IN = "is_logged_in";

    private final SharedPreferences prefs;
    private final SharedPreferences.Editor editor;

    public SessionManager(Context context) {
        this.prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        this.editor = this.prefs.edit();
    }

    public void saveSession(int id, String username) {
        this.editor.putInt(KEY_ID, id);
        this.editor.putString(KEY_USERNAME, username);
        this.editor.putBoolean(KEY_IS_LOGGED_IN, true);
        this.editor.apply();
    }

    public boolean isLoggedIn() {
        return this.prefs.getBoolean(KEY_IS_LOGGED_IN, false);
    }

    public int getUserId() {
        return this.prefs.getInt(KEY_ID, -1);
    }
    public String getUsername() {
        return this.prefs.getString(KEY_USERNAME, null);
    }
    public void clearSession() {
        this.editor.clear();
        this.editor.apply();
    }
}