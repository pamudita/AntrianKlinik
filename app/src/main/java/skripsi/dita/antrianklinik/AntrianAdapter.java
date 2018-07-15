package skripsi.dita.antrianklinik;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import skripsi.dita.antrianklinik.model.Antrian;

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


        public MyViewHolder(View view){
            super(view);
            noAntrian       = (TextView) view.findViewById(R.id.txtNoAntrian);
            namaKlinik      = (TextView) view.findViewById(R.id.txtNamaKlinik);
            statusAntrian   = (TextView) view.findViewById(R.id.txtStatus);
            namaDokter      = (TextView) view.findViewById(R.id.txtNamaDokter);
            tglPeriksa      = (TextView) view.findViewById(R.id.txtTglperiksa);
            antrianSkrg     = (TextView) view.findViewById(R.id.txtAntrianSkrg);
            estimasiWaktu   = (TextView) view.findViewById(R.id.txtEstimasi);
        }
    }

    public AntrianAdapter(List<Antrian> antrianList){
        this.antrianList = antrianList;
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
        holder.namaKlinik.setText(antrian.getRuang());
        holder.statusAntrian.setText(antrian.getStatus());
        holder.namaDokter.setText(antrian.getNama());
        holder.tglPeriksa.setText("Tgl.Periksa : "+antrian.getTglPeriksa());
        //holder.sisaAntrian.setText(antrian.());
        holder.estimasiWaktu.setText(antrian.getJamPeriksa());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, DetailAntrian.class);
                context.startActivity(i);

            }
        });
    }

    @Override
    public int getItemCount(){
        return antrianList.size();
    }
}
