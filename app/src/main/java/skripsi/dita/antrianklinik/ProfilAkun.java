package skripsi.dita.antrianklinik;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.messaging.FirebaseMessaging;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import skripsi.dita.antrianklinik.model.Pendaftaran;
import skripsi.dita.antrianklinik.service.ApiService;

public class ProfilAkun extends AppCompatActivity {

    PrefManager prefManager;
    private TextView textViewNama;
    private TextView textViewNorm;
    private TextView textViewAlamat;
    private TextView textViewNoHp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil_akun);
        this.setTitle("Profil Akun");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        textViewNama = (TextView) findViewById(R.id.textView);
        textViewNorm = (TextView) findViewById(R.id.textView13);
        textViewAlamat = (TextView) findViewById(R.id.textView26);
        textViewNoHp = (TextView) findViewById(R.id.textView27);

        prefManager = new PrefManager(ProfilAkun.this);
        profile();
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

    public void UbahPassword(View view) {
        Intent i = new Intent(ProfilAkun.this, UbahPassword.class);
        startActivity(i);
    }

    public void Tentang(View view) {
        Intent i = new Intent(ProfilAkun.this, Tentang.class);
        startActivity(i);
    }

    public void Logout(View view) {
        prefManager.spEditor.clear();
        prefManager.spEditor.commit();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    FirebaseMessaging.getInstance().setAutoInitEnabled(false);
                    FirebaseInstanceId.getInstance().deleteInstanceId();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        Intent i = new Intent(ProfilAkun.this, Login.class);
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(i);
        Toast.makeText(getApplicationContext(), "Logout Sukses", Toast.LENGTH_SHORT).show();
        finish();
    }

    private void profile() {
        ApiService.newInstance().getPendaftaranService().daftarperiksa(prefManager.getSpNorm())
                .enqueue(new Callback<List<Pendaftaran>>() {
                    @Override
                    public void onResponse(Call<List<Pendaftaran>> call, final Response<List<Pendaftaran>> response) {
                        new Handler(Looper.getMainLooper()).post(new Runnable() {
                            @Override
                            public void run() {
                                if (response.isSuccessful()) {
                                    Pendaftaran pendaftaran = response.body().get(0);

                                    textViewNama.setText(pendaftaran.getNama());
                                    textViewNorm.setText(pendaftaran.getNorm());
                                    textViewNoHp.setText(pendaftaran.getNoHp());
                                    textViewAlamat.setText(pendaftaran.getAlamat());
                                }
                            }
                        });
                    }

                    @Override
                    public void onFailure(Call<List<Pendaftaran>> call, Throwable t) {
                        new Handler(Looper.getMainLooper()).post(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(ProfilAkun.this, "Koneksi terputus", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                });
    }
}
