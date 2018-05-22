package skripsi.dita.antrianklinik;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

import skripsi.dita.antrianklinik.model.Antrian;

public class DaftarAntrian extends AppCompatActivity {
    private List<Antrian> antrianList = new ArrayList<>();
    private RecyclerView recyclerView;
    private AntrianAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daftar_antrian);
        this.setTitle("Daftar Antrian");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        mAdapter = new AntrianAdapter(antrianList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);

        prepareAntrianData();
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

    private void prepareAntrianData() {
        Antrian antrian = new Antrian();
        antrian.setAntrian("T001");
        antrian.setNamaklinik("Klinik THT");
        antrian.setStatusantrian("Tunggu");
        antrianList.add(antrian);

        antrian = new Antrian();
        antrian.setAntrian("A002");
        antrian.setNamaklinik("Klinik Anak");
        antrian.setStatusantrian("Batal");
        antrianList.add(antrian);

        mAdapter.notifyDataSetChanged();
    }
}
