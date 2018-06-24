package skripsi.dita.antrianklinik;

import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.KeyListener;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import skripsi.dita.antrianklinik.model.Dokter;
import skripsi.dita.antrianklinik.model.Klinik;
import skripsi.dita.antrianklinik.service.ApiService;

public class DetailJadwal extends AppCompatActivity {
    TextView namaKlinik;
    TextView hariSenin, hariSelasa, hariRabu, hariKamis, hariJumat;
    TextView namaDokter1, namaDokter2, namaDokter3, namaDokter4, namaDokter5;

    String dokter = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_jadwal);
        this.setTitle("Jadwal Dokter");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        namaKlinik  = (TextView) findViewById(R.id.NamaKlinik);

        namaDokter1 = (TextView) findViewById(R.id.txtDokter1);
        namaDokter2 = (TextView) findViewById(R.id.txtDokter2);
        namaDokter3 = (TextView) findViewById(R.id.txtDokter3);
        namaDokter4 = (TextView) findViewById(R.id.txtDokter4);
        namaDokter5 = (TextView) findViewById(R.id.txtDokter5);

        Bundle b = getIntent().getExtras();
        DataDokter(b.getString("kode_ruang"));

        namaKlinik.setText(b.getString("nama_ruang"));

    }

    public void DataDokter(String kode_ruang){
        ApiService.newInstance().getJadwalService().detailjadwal(kode_ruang)
                .enqueue(new Callback<List<Klinik>>() {
                    @Override
                    public void onResponse(Call<List<Klinik>> call, final Response<List<Klinik>> response) {
                        if (response.isSuccessful()){
                            new Handler(Looper.getMainLooper()).post(new Runnable() {
                                @Override
                                public void run() {
                                for(Klinik klinik : response.body()){
                                    switch (klinik.getHari()){
                                        case "1" :
                                            dokter = dokter +", "+ klinik.getNama_dokter();
                                            namaDokter1.setText(namaDokter1.getText()+ " \n"+klinik.getNama_dokter());
                                            break;
                                        case "2" :
                                            dokter = dokter +", "+ klinik.getNama_dokter();
                                            namaDokter2.setText(namaDokter2.getText()+ " \n"+klinik.getNama_dokter());
                                            break;
                                        case "3" :
                                            dokter = dokter +", "+ klinik.getNama_dokter();
                                            namaDokter3.setText(namaDokter3.getText()+ " \n"+klinik.getNama_dokter());
                                            break;
                                        case "4" :
                                            dokter = dokter +", "+ klinik.getNama_dokter();
                                            namaDokter4.setText(namaDokter4.getText()+ " \n"+klinik.getNama_dokter());
                                            break;
                                        case "5" :
                                            dokter = dokter +", "+ klinik.getNama_dokter();
                                            namaDokter5.setText(namaDokter5.getText()+ " \n"+klinik.getNama_dokter());
                                            break;
                                    }
                                }
                                }
                            });
                        }
                    }

                    @Override
                    public void onFailure(Call<List<Klinik>> call, Throwable t) {
                        new Handler(Looper.getMainLooper()).post(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(getApplicationContext(),"data tidak ada", Toast.LENGTH_SHORT).show();
                            }
                        });
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
}
