package skripsi.dita.antrianklinik;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import skripsi.dita.antrianklinik.model.User;
import skripsi.dita.antrianklinik.service.ApiService;

public class Login extends AppCompatActivity {

    EditText nomorrm;
    Button btnLogin;
    TextInputEditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        nomorrm = (EditText) findViewById(R.id.txtNomorrm);
        password = (TextInputEditText) findViewById(R.id.password);
        btnLogin = (Button) findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String usernameKey = nomorrm.getText().toString();
                String passwordKey = password.getText().toString();

                Login(usernameKey,passwordKey);
            }

        });

    }

    public void Daftar(View view){
        Intent i = new Intent(Login.this, DaftarAkun.class);
        startActivity(i);
    }

    public void Login(String norm, String password){
        ApiService.newInstance().getUserService().login(norm, password)
                .enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call, final Response<User> response) {
                        if (response.isSuccessful()){
                            new Handler(Looper.getMainLooper()).post(new Runnable() {
                                @Override
                                public void run() {
                                    /*Toast.makeText(getApplicationContext(),response.body().getNorm(), Toast.LENGTH_SHORT).show();*/
                                    if (response.body().getAlert() == null){
                                        PrefManager prefManager = new PrefManager(Login.this);
                                        prefManager.setSpSudahLogin(true);
                                        prefManager.setSpNorm(response.body().getNorm());

                                        Toast.makeText(getApplicationContext(),"Login Sukses", Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(Login.this, BottomNavMenu.class);
                                        startActivity(intent);
                                        finish();
                                    }else {
                                        Toast.makeText(getApplicationContext(),response.body().getAlert(), Toast.LENGTH_SHORT).show();
                                    }

                                }
                            });
                        }

                    }

                    @Override
                    public void onFailure(Call<User> call, Throwable t) {
                        new Handler(Looper.getMainLooper()).post(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(getApplicationContext(),"gagal login", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                });
    }
}
