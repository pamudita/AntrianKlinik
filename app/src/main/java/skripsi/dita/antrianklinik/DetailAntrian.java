package skripsi.dita.antrianklinik;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import org.w3c.dom.Text;

public class DetailAntrian extends AppCompatActivity {
    TextView noAntrian, namaKlinik, noRm, namaPasien, jk, tglPeriksa, poli, dokter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_antrian);
        this.setTitle("Detail Antrian");

        noAntrian   = (TextView) findViewById(R.id.txtNoAntrian);
        namaKlinik  = (TextView) findViewById(R.id.txtNamaKlinik);
        noRm        = (TextView) findViewById(R.id.txtNoRM);
        namaPasien  = (TextView) findViewById(R.id.txtNamaPasien);
        jk          = (TextView) findViewById(R.id.txtJK);
        tglPeriksa  = (TextView) findViewById(R.id.txtTglperiksa);
        poli        = (TextView) findViewById(R.id.txtKlinik);
        dokter      = (TextView) findViewById(R.id.txtDokter);

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
}
