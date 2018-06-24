package skripsi.dita.antrianklinik;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class ProfilAkun extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil_akun);
        this.setTitle("Profil Akun");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                //do whatever
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void UbahPassword(View view){
        Intent i = new Intent(ProfilAkun.this, UbahPassword.class);
        startActivity(i);
    }

    public void Logout(View view){
        PrefManager prefManager = new PrefManager(ProfilAkun.this);
        prefManager.spEditor.clear();
        prefManager.spEditor.commit();

        Intent i = new Intent(ProfilAkun.this, Login.class);
        startActivity(i);
        Toast.makeText(getApplicationContext(),"Logout Sukses", Toast.LENGTH_SHORT).show();
        finish();
    }
}
