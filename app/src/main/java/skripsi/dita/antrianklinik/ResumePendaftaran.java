package skripsi.dita.antrianklinik;

import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import skripsi.dita.antrianklinik.service.ApiService;

public class ResumePendaftaran extends AppCompatActivity {
    TextView no_daftar, no_rm, nama_pasien, jk, tgl_periksa, klinik, dokter, no_antrian;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resume_pendaftaran);
        this.setTitle("Pendaftaran Online");

        no_daftar   = (TextView) findViewById(R.id.txtNoDaftar);
        no_rm       = (TextView) findViewById(R.id.txtNoRM);
        nama_pasien = (TextView) findViewById(R.id.textView46);
        jk          = (TextView) findViewById(R.id.txtJK);
        tgl_periksa = (TextView) findViewById(R.id.txtTglPeriksa);
        klinik      = (TextView) findViewById(R.id.txtNmKlinik);
        dokter      = (TextView) findViewById(R.id.txtDokter);
        no_antrian  = (TextView) findViewById(R.id.txtNoantrian);

        ResumeDaftar();
    }

    public void ResumeDaftar(){
        PrefManager prefManager = new PrefManager(ResumePendaftaran.this);
        ApiService.newInstance().getPendaftaranService()
                .resdaf(prefManager.getSpNorm()).enqueue(new Callback<List<skripsi.dita.antrianklinik.model.Pendaftaran>>() {
            @Override
            public void onResponse(Call<List<skripsi.dita.antrianklinik.model.Pendaftaran>> call, final Response<List<skripsi.dita.antrianklinik.model.Pendaftaran>> response) {
                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    @Override
                    public void run() {
                        if(response.isSuccessful()){
                            if(!response.body().isEmpty()){
                                skripsi.dita.antrianklinik.model.Pendaftaran resume = response.body().get(0);
                                no_daftar.setText(resume.getId_pendaftaran());
                                no_rm.setText(resume.getNomorRM());
                                nama_pasien.setText(resume.getNamaPasien());
                                jk.setText(resume.getJk());
                                tgl_periksa.setText(resume.getTglPeriksa());
                                klinik.setText(resume.getRuang());
                                dokter.setText(resume.getNamaDokter());
                                no_antrian.setText(resume.getNo_antrian());
                            }
                        }
                    }
                });
            }

            @Override
            public void onFailure(Call<List<skripsi.dita.antrianklinik.model.Pendaftaran>> call, Throwable t) {
                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(),"data tidak ada", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

    }
}
