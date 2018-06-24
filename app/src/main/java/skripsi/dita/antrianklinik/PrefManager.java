package skripsi.dita.antrianklinik;

import android.content.Context;
import android.content.SharedPreferences;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by Dita on 06/06/2018.
 */

public class PrefManager {

    public static final String SP_ANTRIAN_APP = "spAntrianApp";

    public static final String SP_NORM = "spNorm";


    public static final String SP_SUDAH_LOGIN = "spSudahLogin";

    SharedPreferences sp;
    SharedPreferences.Editor spEditor;

    public PrefManager(Context context){
        sp = context.getSharedPreferences(SP_ANTRIAN_APP, Context.MODE_PRIVATE);
        spEditor = sp.edit();
    }


    public String getSpNorm(){
        return sp.getString(SP_NORM, "");
    }

    public void setSpNorm(String value){
        spEditor.putString(SP_NORM, value);
        spEditor.commit();
    }

    public void setSpSudahLogin(Boolean value){
        spEditor.putBoolean(SP_SUDAH_LOGIN, value);
        spEditor.commit();
    }

    public Boolean getSPSudahLogin(){
        return sp.getBoolean(SP_SUDAH_LOGIN, false);
    }
}
