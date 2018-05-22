package skripsi.dita.antrianklinik;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;

public class DaftarAkun extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daftar_akun);
        this.setTitle("Daftar Akun");
        //this.requestWindowFeature(Window.FEATURE_NO_TITLE);
    }
}
