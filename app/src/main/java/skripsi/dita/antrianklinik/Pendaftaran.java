package skripsi.dita.antrianklinik;

import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import skripsi.dita.antrianklinik.model.CaraBayar;
import skripsi.dita.antrianklinik.model.Dokter;
import skripsi.dita.antrianklinik.model.Klinik;
import skripsi.dita.antrianklinik.service.ApiService;

public class Pendaftaran extends AppCompatActivity implements DatePickerDialog.OnDateSetListener{
    EditText NoKartu, TglPeriksa, Keluhan;
    TextView NomorRM, NamaPasien, TglLahir;
    String Tgl_periksa;
    Spinner ListKlinik, ListCaraBayar, ListDokter;
    Button btnSimpan;

    private KlinikListAdapter adapter;
    private List<Klinik> klinikList = new ArrayList<>();

    private CaraBayarListAdapter adaptercarabayar;
    private List<CaraBayar> caraBayarList = new ArrayList<>();

    private DokterListAdapter adapterdokter;
    private List<Dokter> dokterList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pendaftaran);
        this.setTitle("Pendaftaran Online");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        NomorRM     = (TextView) findViewById(R.id.txtNomorRM);
        NamaPasien  = (TextView) findViewById(R.id.txtNama);
        TglLahir    = (TextView) findViewById(R.id.txtTglLahir);
        ListCaraBayar = (Spinner) findViewById(R.id.spinnerCaraBayar);
        NoKartu     = (EditText) findViewById(R.id.txtNoKartu);
        TglPeriksa  = (EditText) findViewById(R.id.txtTglPeriksa);
        ListKlinik  = (Spinner) findViewById(R.id.spinner2);
        ListDokter  = (Spinner) findViewById(R.id.spinner3);
        Keluhan     = (EditText) findViewById(R.id.txtKeluhan);
        btnSimpan   = (Button) findViewById(R.id.btnSimpan);

        //adapter data klinik
        adapter = new KlinikListAdapter(this, android.R.layout.simple_spinner_item, klinikList);
        ListKlinik.setAdapter(adapter);
        ListKlinik.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Klinik klinik = adapter.getItem(position);
                // Here you can do the action you want to...
                Toast.makeText(Pendaftaran.this, "ID: " + klinik.getKode_ruang() + "\nName: " + klinik.getRuang(),
                        Toast.LENGTH_SHORT).show();

                DataDokter(klinik.getKode_ruang());

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //adapter cara bayar
        adaptercarabayar = new CaraBayarListAdapter(this, android.R.layout.simple_spinner_item, caraBayarList);
        ListCaraBayar.setAdapter(adaptercarabayar);
        ListCaraBayar.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                CaraBayar caraBayar = adaptercarabayar.getItem(position);
                Toast.makeText(Pendaftaran.this, "ID: " + caraBayar.getKode() + "\nName: " + caraBayar.getKeterangan(),
                        Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //adapter data dokter
        adapterdokter = new DokterListAdapter(this, android.R.layout.simple_spinner_item,dokterList);
        ListDokter.setAdapter(adapterdokter);
        ListDokter.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Dokter dokter = adapterdokter.getItem(position);
                Toast.makeText(Pendaftaran.this, "ID: " + dokter.getKodedokter() + "\nName: " + dokter.getNama(),
                        Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        DataCaraBayar();
        DataPasien();
        DataKlinik();

        btnSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //get data spinner
                CaraBayar cara_Bayar = caraBayarList.get(ListCaraBayar.getSelectedItemPosition());
                Dokter dokter = dokterList.get(ListDokter.getSelectedItemPosition());
                Klinik kliniklist = klinikList.get(ListKlinik.getSelectedItemPosition());

                //tgl daftar
                Calendar c = Calendar.getInstance();
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");

                String norm      = NomorRM.getText().toString();
                String nama      = NamaPasien.getText().toString();
                String tgllahir  = TglLahir.getText().toString();
                String carabayar = cara_Bayar.getKode().toString();
                String nokartu   = NoKartu.getText().toString();
                String tgldaftar = df.format(c.getTime());
                String tglperiksa = TglPeriksa.getText().toString();
                String kd_ruang  = kliniklist.getKode_ruang().toString();
                String kd_dokter = dokter.getKodedokter().toString();
                String keluhan   = Keluhan.getText().toString();

                DaftarPeriksa(norm,carabayar,tgldaftar,tglperiksa,nokartu,kd_ruang,kd_dokter,keluhan);

            }
        });
    }

    public void DataPasien(){
        PrefManager prefManager = new PrefManager(Pendaftaran.this);
        ApiService.newInstance().getPendaftaranService()
                .daftarperiksa(prefManager.getSpNorm()).enqueue(new Callback<List<skripsi.dita.antrianklinik.model.Pendaftaran>>() {
            @Override
            public void onResponse(Call<List<skripsi.dita.antrianklinik.model.Pendaftaran>> call, final Response<List<skripsi.dita.antrianklinik.model.Pendaftaran>> response) {
                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    @Override
                    public void run() {
                        if (response.isSuccessful()){
                            if (!response.body().isEmpty()){
                                skripsi.dita.antrianklinik.model.Pendaftaran pendaftaran = response.body().get(0);
                                NomorRM.setText(pendaftaran.getNorm());
                                NamaPasien.setText(pendaftaran.getNama());
                                TglLahir.setText(pendaftaran.getTgl_lahir());
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

    public void DataCaraBayar(){
        caraBayarList.clear();
        caraBayarList.add(new CaraBayar("- Pilih -"));
        ApiService.newInstance().getPendaftaranService().carabayar()
                .enqueue(new Callback<List<CaraBayar>>() {
                    @Override
                    public void onResponse(Call<List<CaraBayar>> call, final Response<List<CaraBayar>> response) {
                        new Handler(Looper.getMainLooper()).post(new Runnable() {
                            @Override
                            public void run() {
                                if (response.isSuccessful()){
                                    for (CaraBayar caraBayar : response.body()){
                                        caraBayarList.add(caraBayar);
                                    }
                                }

                                adaptercarabayar.notifyDataSetChanged();
                            }
                        });
                    }

                    @Override
                    public void onFailure(Call<List<CaraBayar>> call, Throwable t) {
                        new Handler(Looper.getMainLooper()).post(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(getApplicationContext(),"data tidak ada", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                });

    }

    public void DataKlinik() {
        klinikList.clear();
        klinikList.add(new Klinik("- Pilih -"));
        ApiService.newInstance().getJadwalService().jadwal()
                .enqueue(new Callback<List<Klinik>>() {
                    @Override
                    public void onResponse(Call<List<Klinik>> call, final Response<List<Klinik>> response) {
                        new Handler(Looper.getMainLooper()).post(new Runnable() {
                            @Override
                            public void run() {
                                if (response.isSuccessful()){
                                    for (Klinik klinik : response.body()){
                                        klinikList.add(klinik);
                                    }
                                }

                                adapter.notifyDataSetChanged();
                            }
                        });
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

    public void DataDokter(String kode_ruang){
        dokterList.clear();
        dokterList.add(new Dokter("- Pilih -"));
        ApiService.newInstance().getPendaftaranService().dokter(kode_ruang)
                .enqueue(new Callback<List<Dokter>>() {
                    @Override
                    public void onResponse(Call<List<Dokter>> call, final Response<List<Dokter>> response) {
                        new Handler(Looper.getMainLooper()).post(new Runnable() {
                            @Override
                            public void run() {
                                if (response.isSuccessful()){
                                    for (Dokter dokter : response.body()){
                                        dokterList.add(dokter);
                                    }
                                }
                                adapterdokter.notifyDataSetChanged();
                            }
                        });
                    }

                    @Override
                    public void onFailure(Call<List<Dokter>> call, Throwable t) {

                    }
                });
    }

    public void DaftarPeriksa(String norm, String kd_carabayar, String tgl_daftar, String tgl_periksa, String nokartu, String kode_ruang, String kode_dokter, String keluhan){
        ApiService.newInstance().getPendaftaranService().daftarp(norm, kd_carabayar, tgl_daftar, tgl_periksa, nokartu, kode_ruang, kode_dokter, keluhan)
                .enqueue(new Callback<skripsi.dita.antrianklinik.model.Pendaftaran>() {
                    @Override
                    public void onResponse(Call<skripsi.dita.antrianklinik.model.Pendaftaran> call, final Response<skripsi.dita.antrianklinik.model.Pendaftaran> response) {
                        if (response.isSuccessful()){
                            new Handler(Looper.getMainLooper()).post(new Runnable() {
                                @Override
                                public void run() {
                                    if(!response.body().getAlert().equals("Form Belum Lengkap")){
                                        Toast.makeText(getApplicationContext(), response.body().getAlert(), Toast.LENGTH_SHORT).show();
                                        Intent i = new Intent(Pendaftaran.this, ResumePendaftaran.class);
                                        startActivity(i);
                                    }else {
                                        Toast.makeText(getApplicationContext(), response.body().getAlert(), Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                        }
                    }

                    @Override
                    public void onFailure(Call<skripsi.dita.antrianklinik.model.Pendaftaran> call, Throwable t) {
                        new Handler(Looper.getMainLooper()).post(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(getApplicationContext(),"gagal daftar", Toast.LENGTH_SHORT).show();
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

    public void Calendar(View view){
         Calendar saturday;
        Calendar sunday;
        List<Calendar> weekends = new ArrayList<>();
        int weeks = 5;

        Calendar cal = Calendar.getInstance();
        DatePickerDialog dpd = DatePickerDialog.newInstance(
                Pendaftaran.this,
                cal.get(Calendar.YEAR),
                cal.get(Calendar.MONTH),
                cal.get(Calendar.DAY_OF_MONTH)
        );

        for (int i = 0; i < (weeks * 7) ; i = i + 7) {
            sunday = Calendar.getInstance();
            sunday.add(Calendar.DAY_OF_YEAR, (Calendar.SUNDAY - sunday.get(Calendar.DAY_OF_WEEK) + 7 + i));
             saturday = Calendar.getInstance();
             saturday.add(Calendar.DAY_OF_YEAR, (Calendar.SATURDAY - saturday.get(Calendar.DAY_OF_WEEK) + i));
             weekends.add(saturday);
            weekends.add(sunday);
        }
        Calendar[] disabledDays = weekends.toArray(new Calendar[weekends.size()]);
        dpd.setMinDate(cal);

        cal.add(Calendar.DATE, 8);
        dpd.setMaxDate(cal);
        dpd.setDisabledDays(disabledDays);
        dpd.show(getFragmentManager(), "dialogmaterial");

       /* Calendar cal = Calendar.getInstance();
        DatePickerDialog dpd = DatePickerDialog.newInstance(
                Pendaftaran.this,
                cal.get(Calendar.YEAR),
                cal.get(Calendar.MONTH),
                cal.get(Calendar.DAY_OF_MONTH)
        );

        dpd.setMinDate(cal);

        cal.add(Calendar.DATE, 7);
        dpd.setMaxDate(cal);
        dpd.show(getFragmentManager(), "dialogmaterial");*/
    }

    @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
        TglPeriksa.setText(year+"-"+(monthOfYear + 1)+"-"+dayOfMonth);
        //TglPeriksa.setText(dayOfMonth+"-"+(monthOfYear + 1)+"-"+year);
        //Tgl_periksa = year+"-"+(monthOfYear + 1)+"-"+dayOfMonth;
    }
}
