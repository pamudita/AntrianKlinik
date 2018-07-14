package skripsi.dita.antrianklinik;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import skripsi.dita.antrianklinik.model.Klinik;

/**
 * Created by Dita on 13/05/2018.
 */

public class KlinikAdapter extends RecyclerView.Adapter<KlinikAdapter.MyViewHolder> implements Filterable {

    private List<Klinik> klinikList;
    private List<Klinik> filteredList;
    private CustomFilter filter;
    private Context context;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView namaKlinik;

        public MyViewHolder(View view) {
            super(view);
            namaKlinik = (TextView) view.findViewById(R.id.txtKlinik);
        }
    }

    public KlinikAdapter(List<Klinik> poliklinikList) {
        this.klinikList = poliklinikList;
        filteredList = new ArrayList<>();
        filteredList.addAll(klinikList);
        filter = new CustomFilter(this);
    }

    public KlinikAdapter(List<Klinik> poliklinikList, Context context) {
        this.klinikList = poliklinikList;
        this.context = context;

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.itemview_poliklinik, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        Klinik klinik = klinikList.get(position);
        holder.namaKlinik.setText(klinik.getRuang());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(context, DetailJadwal.class);
                i.putExtra("kode_ruang", klinikList.get(position).getKode_ruang());
                i.putExtra("nama_ruang", klinikList.get(position).getRuang());
                context.startActivity(i);

            }
        });
    }

    @Override
    public Filter getFilter() {
        return filter;
    }

    @Override
    public int getItemCount() {
        return klinikList.size();
    }

    public class CustomFilter extends Filter {
        private KlinikAdapter mAdapter;

        private CustomFilter(KlinikAdapter mAdapter) {
            super();
            this.mAdapter = mAdapter;
        }

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            System.out.println("filtered");
            filteredList.clear();
            final FilterResults results = new FilterResults();
            if (constraint.length() == 0) {
                filteredList.addAll(klinikList);
                mAdapter.notifyDataSetChanged();
            } else {
                final String filterPattern = constraint.toString().toLowerCase().trim();
                for (final Klinik klinik : klinikList) {
                    if (klinik.getRuang().toLowerCase().startsWith(filterPattern)) {
                        filteredList.add(klinik);
                    }

                    System.out.println("fill");
                }
                mAdapter.notifyDataSetChanged();
            }

            results.values = filteredList;
            results.count = filteredList.size();
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            this.mAdapter.notifyDataSetChanged();
        }
    }
}
