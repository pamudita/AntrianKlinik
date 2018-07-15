package skripsi.dita.antrianklinik;

import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import skripsi.dita.antrianklinik.model.User;
import skripsi.dita.antrianklinik.service.ApiService;

public class UbahPassword extends AppCompatActivity {

    private EditText editTextPasswordLama;
    private EditText editTextPasswordBaru;
    private EditText editTextPasswordKonfirmasiBaru;
    private Button buttonSave;
    private PrefManager prefManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ubah_password);
        this.setTitle("Ubah Password");

        prefManager = new PrefManager(getApplicationContext());
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        editTextPasswordLama = findViewById(R.id.editTextPasswornLama);
        editTextPasswordBaru = findViewById(R.id.editTextPassswordBaru);
        editTextPasswordKonfirmasiBaru = findViewById(R.id.editTextPasswordKonfirmasiBaru);
        buttonSave = findViewById(R.id.button);
        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TextUtils.equals(editTextPasswordBaru.getText(), editTextPasswordKonfirmasiBaru.getText())) {
                    updatePassword();
                } else {
                    Toast.makeText(getApplicationContext(), "Konfirmasi password tidak sama!", Toast.LENGTH_LONG).show();
                }
            }
        });
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

    private void updatePassword() {
        ApiService.newInstance().getUserService().updatePassword(prefManager.getSpNorm(), editTextPasswordBaru.getText().toString(), editTextPasswordLama.getText().toString())
                .enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call, final Response<User> response) {
                        new Handler(Looper.getMainLooper()).post(new Runnable() {
                            @Override
                            public void run() {
                                if (response.isSuccessful()) {
                                    if (response.body().getStatus() == 1) {
                                        Toast.makeText(getApplicationContext(), response.body().getAlert(), Toast.LENGTH_SHORT).show();
                                    } else {
                                        Toast.makeText(getApplicationContext(), response.body().getAlert(), Toast.LENGTH_SHORT).show();
                                    }
                                } else {
                                    Toast.makeText(getApplicationContext(), "Koneksi terputus!", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    }

                    @Override
                    public void onFailure(Call<User> call, Throwable t) {
                        new Handler(Looper.getMainLooper()).post(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(getApplicationContext(), "Koneksi terputus!", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                });
    }
}
