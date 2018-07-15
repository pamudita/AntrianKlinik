package skripsi.dita.antrianklinik;

import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import skripsi.dita.antrianklinik.model.Antrian;
import skripsi.dita.antrianklinik.service.ApiService;

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

        DaftarAntrian();
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

    private void DaftarAntrian(){
        PrefManager prefManager = new PrefManager(DaftarAntrian.this);
        ApiService.newInstance().getAntrianService()
                .daftarantrian(prefManager.getSpNorm()).enqueue(new Callback<List<Antrian>>() {
                    @Override
                    public void onResponse(Call<List<Antrian>> call, final Response<List<Antrian>> response) {
                        if (response.isSuccessful()){
                            new Handler(Looper.getMainLooper()).post(new Runnable() {
                                @Override
                                public void run() {
                                    antrianList.addAll(response.body());
                                    mAdapter.notifyDataSetChanged();
                                }
                            });
                        }
                    }

                    @Override
                    public void onFailure(Call<List<Antrian>> call, Throwable t) {
                        new Handler(Looper.getMainLooper()).post(new Runnable() {
                            @Override
                            public void run() {

                            }
                        });
                    }
                });
    }

}
