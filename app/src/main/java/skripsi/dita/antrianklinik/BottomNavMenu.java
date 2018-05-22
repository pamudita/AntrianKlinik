package skripsi.dita.antrianklinik;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

public class BottomNavMenu extends AppCompatActivity {

    private TextView mTextMessage;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.nav_beranda:
                    Beranda fragment = new Beranda();
                    break;
                case R.id.nav_jadwal:
                    Intent jadwal = new Intent(BottomNavMenu.this, JadwalDokter.class);
                    startActivity(jadwal);
                    break;
                case R.id.nav_pendaftaran:
                    Intent daftar = new Intent(BottomNavMenu.this, Pendaftaran.class);
                    startActivity(daftar);
                    break;
                case R.id.nav_antrian:
                    Intent antrian = new Intent(BottomNavMenu.this, DaftarAntrian.class);
                    startActivity(antrian);
                    break;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_nav_menu);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

    }

}
