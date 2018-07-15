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
        this.filteredList = poliklinikList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.itemview_poliklinik, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        final Klinik klinik = filteredList.get(position);
        holder.namaKlinik.setText(klinik.getRuang());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(context, DetailJadwal.class);
                i.putExtra("kode_ruang", klinik.getKode_ruang());
                i.putExtra("nama_ruang", klinik.getRuang());
                context.startActivity(i);

            }
        });
    }

    @Override
    public int getItemCount() {
        return filteredList != null ? filteredList.size() : 0;
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                if (charString.isEmpty()) {
                    filteredList = klinikList;
                } else {
                    List<Klinik> filteredKlinikList = new ArrayList<>();
                    for (Klinik row : klinikList) {

                        // name match condition. this might differ depending on your requirement
                        // here we are looking for name or phone number match
//                        if (row.getRuang().toLowerCase().startsWith(charString.toLowerCase())) {
//                            filteredList.add(row);
//                        }

                        for (String s : row.getRuang().toLowerCase().split(" ")) {
                            if (s.startsWith(charString.toLowerCase())) {
                                filteredKlinikList.add(row);
                                break;
                            } else if (row.getRuang().toLowerCase().startsWith(charString.toLowerCase())) {
                                filteredKlinikList.add(row);
                                break;
                            }
                        }
                    }

                    filteredList = filteredKlinikList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = filteredList;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                filteredList = (ArrayList<Klinik>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }
}
