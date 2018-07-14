package skripsi.dita.antrianklinik;

import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import skripsi.dita.antrianklinik.model.User;
import skripsi.dita.antrianklinik.service.ApiService;

public class DaftarAkun extends AppCompatActivity implements DatePickerDialog.OnDateSetListener{
     EditText NomorRm, TglLahir;
     TextInputEditText Password;
     Button btnDaftar;
     String Tgl_lahir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daftar_akun);
        this.setTitle("Daftar Akun");
        //this.requestWindowFeature(Window.FEATURE_NO_TITLE);

        NomorRm = (EditText) findViewById(R.id.txtNomorRM);
        TglLahir = (EditText) findViewById(R.id.txtTglLahir);
        Password = (TextInputEditText) findViewById(R.id.password);
        btnDaftar = (Button) findViewById(R.id.btnDaftar);

        btnDaftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String NoRm = NomorRm.getText().toString();
                String Tgl_lahir = TglLahir.getText().toString();
                String password = Password.getText().toString();

                Daftar(NoRm,password,Tgl_lahir);
            }

        });
    }

    public void Daftar(String norm, String password, String tgl_lahir){
        ApiService.newInstance().getUserService().daftar(norm, tgl_lahir, password)
                .enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call, final Response<User> response) {
                        if (response.isSuccessful()){
                            new Handler(Looper.getMainLooper()).post(new Runnable() {
                                @Override
                                public void run() {
                                    if (response.body().getAlert() == null){
                                        Toast.makeText(getApplicationContext(),response.body().getAlert(), Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(DaftarAkun.this, Login.class);
                                        startActivity(intent);
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
                                Toast.makeText(getApplicationContext(),"gagal daftar", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                });
    }

    public void Calendar(View view){
        Calendar now = Calendar.getInstance();
        DatePickerDialog dpd = DatePickerDialog.newInstance(
                DaftarAkun.this,
                now.get(Calendar.YEAR),
                now.get(Calendar.MONTH),
                now.get(Calendar.DAY_OF_MONTH)
        );
        dpd.show(getFragmentManager(), "dialogmaterial");
    }

    @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
        TglLahir.setText(year+"-"+(monthOfYear + 1)+"-"+dayOfMonth);
        //TglLahir.setText(dayOfMonth+"-"+(monthOfYear + 1)+"-"+year);
        //Tgl_lahir = year+"-"+(monthOfYear + 1)+"-"+dayOfMonth;
    }
}
