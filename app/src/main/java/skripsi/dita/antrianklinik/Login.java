package skripsi.dita.antrianklinik;

import android.app.AlertDialog;
import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity {

    EditText username;
    Button btnLogin;
    TextInputEditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username = (EditText) findViewById(R.id.txtUsername);
        password = (TextInputEditText) findViewById(R.id.password);
        btnLogin = (Button) findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String usernameKey = username.getText().toString();
                String passwordKey = password.getText().toString();

                if (usernameKey.equals("123") && passwordKey.equals("123")){
                    Toast.makeText(getApplicationContext(),"Login Sukses", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(Login.this, BottomNavMenu.class);
                    startActivity(intent);
                }else {
                    //jika login gagal
                    AlertDialog.Builder builder = new AlertDialog.Builder(Login.this);
                    builder.setMessage("Username atau Password Anda Salah!").setNegativeButton("Retry", null).create().show();
                }
            }

        });
    }

    public void Daftar(View view){
        Intent i = new Intent(Login.this, DaftarAkun.class);
        startActivity(i);
    }
}
