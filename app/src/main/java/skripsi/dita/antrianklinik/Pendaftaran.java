package skripsi.dita.antrianklinik;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import java.util.Calendar;

public class Pendaftaran extends AppCompatActivity implements DatePickerDialog.OnDateSetListener{
    EditText NoKartu, TglPeriksa;
    TextView NomorRM, NamaPasien, TglLahir;
    String Tgl_periksa;
    Button btnSimpan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pendaftaran);
        this.setTitle("Pendaftaran Online");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        NomorRM     = (TextView) findViewById(R.id.txtNomorRM);
        NamaPasien  = (TextView) findViewById(R.id.txtNama);
        TglLahir    = (TextView) findViewById(R.id.txtTglLahir);
        TglPeriksa  = (EditText) findViewById(R.id.txtTglPeriksa);

        btnSimpan   = (Button) findViewById(R.id.btnSimpan);

        btnSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Pendaftaran.this, ResumePendaftaran.class);
                startActivity(i);
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

    public void Calendar(View view){
        Calendar now = Calendar.getInstance();
        DatePickerDialog dpd = DatePickerDialog.newInstance(
                Pendaftaran.this,
                now.get(Calendar.YEAR),
                now.get(Calendar.MONTH),
                now.get(Calendar.DAY_OF_MONTH)
        );
        dpd.show(getFragmentManager(), "dialogmaterial");
    }

    @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
        TglPeriksa.setText(dayOfMonth+"-"+(monthOfYear + 1)+"-"+year);
        Tgl_periksa = year+"-"+(monthOfYear + 1)+"-"+dayOfMonth;
    }
}
