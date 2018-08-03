package skripsi.dita.antrianklinik;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import skripsi.dita.antrianklinik.model.Antrian;
import skripsi.dita.antrianklinik.service.ApiService;

/**
 * Created by Dita on 20/05/2018.
 */

public class AntrianAdapter extends RecyclerView.Adapter<AntrianAdapter.MyViewHolder> {

    private List<Antrian> antrianList;
    private Context context;

    public class MyViewHolder extends RecyclerView.ViewHolder{
        public TextView noAntrian;
        public TextView statusAntrian;
        public TextView namaKlinik, tglPeriksa, namaDokter, antrianSkrg, estimasiWaktu;
        public Button btnBatal;


        public MyViewHolder(View view){
            super(view);
            noAntrian       = (TextView) view.findViewById(R.id.txtNoAntrian);
            namaKlinik      = (TextView) view.findViewById(R.id.txtNamaKlinik);
            statusAntrian   = (TextView) view.findViewById(R.id.txtStatus);
            namaDokter      = (TextView) view.findViewById(R.id.txtNamaDokter);
            tglPeriksa      = (TextView) view.findViewById(R.id.txtTglperiksa);
            antrianSkrg     = (TextView) view.findViewById(R.id.txtAntrianSkrg);
            estimasiWaktu   = (TextView) view.findViewById(R.id.txtEstimasi);
            btnBatal        = (Button) view.findViewById(R.id.btnBatal);

            btnBatal.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view){
                    String NoAntrian = noAntrian.getText().toString();
                    batalAntrian(NoAntrian);
                }
            });
        }
    }

    public AntrianAdapter(List<Antrian> antrianList,Context context){
        this.antrianList = antrianList;
        this.context = context;
    }

    @Override
    public AntrianAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        context = parent.getContext();
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.itemview_antrian, parent, false);

        return new AntrianAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(AntrianAdapter.MyViewHolder holder, int position){
        Antrian antrian = antrianList.get(position);
        holder.noAntrian.setText(antrian.getNo_antrian());
        holder.namaKlinik.setText(antrian.getKlinik());
        holder.statusAntrian.setText(antrian.getStatus());
        holder.namaDokter.setText(antrian.getDokter());
        holder.tglPeriksa.setText("Tgl.Periksa : "+antrian.getTgl_periksa());
        holder.antrianSkrg.setText("Antrian Saat Ini : " + antrian.getAntrian_saat_ini());
        holder.estimasiWaktu.setText("Estimasi Waktu: " +antrian.getEstimasi());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               /* Intent i = new Intent(context, DetailAntrian.class);
                context.startActivity(i);*/

            }
        });
    }

    public void batalAntrian(String noantrian){
        PrefManager prefManager = new PrefManager(context);
        ApiService.newInstance().getAntrianService()
                .batal(prefManager.getSpNorm(), noantrian).enqueue(new Callback<Antrian>() {
                    @Override
                    public void onResponse(Call<Antrian> call, final Response<Antrian> response) {
                        if (response.isSuccessful()){
                            new Handler(Looper.getMainLooper()).post(new Runnable() {
                                @Override
                                public void run() {
                                    if (response.body().getAlert() != null){
                                        Toast.makeText(context, response.body().getAlert(), Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                        }
                    }

                    @Override
                    public void onFailure(Call<Antrian> call, Throwable t) {

                    }
                });
    }

    @Override
    public int getItemCount(){
        return antrianList.size();
    }
}
