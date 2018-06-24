package skripsi.dita.antrianklinik;

import android.annotation.SuppressLint;
import android.support.v4.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.internal.BottomNavigationItemView;
import android.support.design.internal.BottomNavigationMenuView;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Field;

public class BottomNavMenu extends AppCompatActivity {

    private TextView mTextMessage;
    private TextView textViewTitle;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment fragment = null;

            switch (item.getItemId()) {
               /* case R.id.nav_beranda:
                    fragment = new Beranda();
                    textViewTitle.setText(getString(R.string.app_name));
                    break;*/
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
                case R.id.nav_account:
                    Intent account = new Intent(BottomNavMenu.this, ProfilAkun.class);
                    startActivity(account);
                    break;
            }

            if (fragment !=null){
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.frame_home, fragment);
                fragmentTransaction.commit();
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
        BottomNavigationViewHelper.disableShiftMode(navigation);
        getSupportFragmentManager().beginTransaction().add(R.id.frame_home, new Beranda()).commit();

    }

    /**
     *0 Navigation Helper
     */
    private static class BottomNavigationViewHelper {
        @SuppressLint("RestrictedApi")
        static void disableShiftMode(BottomNavigationView view) {
            BottomNavigationMenuView menuView = (BottomNavigationMenuView) view.getChildAt(0);
            try {
                Field shiftingMode = menuView.getClass().getDeclaredField("mShiftingMode");
                shiftingMode.setAccessible(true);
                shiftingMode.setBoolean(menuView, false);
                shiftingMode.setAccessible(false);
                for (int i = 0; i < menuView.getChildCount(); i++) {
                    BottomNavigationItemView item = (BottomNavigationItemView) menuView.getChildAt(i);
                    //noinspection RestrictedApi
                    item.setShiftingMode(false);
                    // set once again checked value, so view will be updated
                    //noinspection RestrictedApi
                    item.setChecked(item.getItemData().isChecked());
                }
            } catch (NoSuchFieldException e) {
                Log.e("BottomNavigation : ", "Unable to get shift mode field", e);
            } catch (IllegalAccessException e) {
                Log.e("BottomNavigation : ", "Unable to change value of shift mode", e);
            }
        }
    }

}
