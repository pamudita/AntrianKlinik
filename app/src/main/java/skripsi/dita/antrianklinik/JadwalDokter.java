package skripsi.dita.antrianklinik;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

import skripsi.dita.antrianklinik.model.Klinik;

public class JadwalDokter extends AppCompatActivity {
    private List<Klinik> klinikList = new ArrayList<>();
    private RecyclerView recyclerView;
    private KlinikAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jadwal_dokter);
        this.setTitle("Jadwal Dokter");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        mAdapter = new KlinikAdapter(klinikList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);

        prepareKlinikData();
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

    private void prepareKlinikData() {
        Klinik klinik = new Klinik();
        klinik.setKlinik("Klinik THT");
        klinikList.add(klinik);

        klinik = new Klinik();
        klinik.setKlinik("Klinik Saraf");
        klinikList.add(klinik);

        klinik = new Klinik();
        klinik.setKlinik("Klinik Mata");
        klinikList.add(klinik);

        klinik = new Klinik();
        klinik.setKlinik("Klinik Paru");
        klinikList.add(klinik);

        klinik = new Klinik();
        klinik.setKlinik("Klinik Bedah Anak");
        klinikList.add(klinik);

        klinik = new Klinik();
        klinik.setKlinik("Klinik Penyakit Dalam");
        klinikList.add(klinik);

        klinik = new Klinik();
        klinik.setKlinik("Klinik Bedah Ortopedi");
        klinikList.add(klinik);

        klinik = new Klinik();
        klinik.setKlinik("Klinik Kesehatan Anak");
        klinikList.add(klinik);

        klinik = new Klinik();
        klinik.setKlinik("Klinik Kulit dan Kelamin");
        klinikList.add(klinik);

        klinik = new Klinik();
        klinik.setKlinik("Klinik Jantung");
        klinikList.add(klinik);

        klinik = new Klinik();
        klinik.setKlinik("Klinik Psikologi");
        klinikList.add(klinik);

        klinik = new Klinik();
        klinik.setKlinik("Klinik Bedah Urologi");
        klinikList.add(klinik);

        mAdapter.notifyDataSetChanged();
    }
}
