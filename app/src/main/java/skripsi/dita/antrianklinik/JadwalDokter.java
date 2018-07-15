package skripsi.dita.antrianklinik;

import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import skripsi.dita.antrianklinik.model.Klinik;
import skripsi.dita.antrianklinik.model.User;
import skripsi.dita.antrianklinik.service.ApiService;

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

        EditText editTextSearch = (EditText)findViewById(R.id.editText4);

        editTextSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                mAdapter.getFilter().filter(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        KlinikData();
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

    private void KlinikData() {
        ApiService.newInstance().getJadwalService().jadwal()
                .enqueue(new Callback<List<Klinik>>() {
                    @Override
                    public void onResponse(Call<List<Klinik>> call, final Response<List<Klinik>> response) {
                        if (response.isSuccessful()){
                            new Handler(Looper.getMainLooper()).post(new Runnable() {
                                @Override
                                public void run() {
                                    klinikList.addAll(response.body());
                                    mAdapter.notifyDataSetChanged();
                                    //Toast.makeText(getApplicationContext(),response.body().getRuang(), Toast.LENGTH_SHORT).show();
                                }
                            });
                        }

                    }

                    @Override
                    public void onFailure(Call<List<Klinik>> call, Throwable t) {
                        new Handler(Looper.getMainLooper()).post(new Runnable() {
                            @Override
                            public void run() {

                            }
                        });
                    }
                });

    }
}
