package skripsi.dita.antrianklinik;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import skripsi.dita.antrianklinik.model.Klinik;

/**
 * Created by Dita on 24/06/2018.
 */

public class KlinikListAdapter extends ArrayAdapter{
    private Context context;
    private boolean useList = true;

    public KlinikListAdapter (Context context, List items){
        super(context, android.R.layout.simple_list_item_1, items);
        this.context = context;
    }


    private class ViewHolder{
        TextView titleText;
    }


    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        Klinik item = (Klinik) getItem(position);
        View viewToUse = null;

        // This block exists to inflate the settings list item conditionally based on whether
        // we want to support a grid or list view.
        LayoutInflater mInflater = (LayoutInflater) context
                .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        if (convertView == null) {
            if(useList){
                viewToUse = mInflater.inflate(R.layout.klinik_list_item, null);
            } else {
                viewToUse = mInflater.inflate(R.layout.klinik_grid_item, null);
            }

            holder = new ViewHolder();
            holder.titleText = (TextView)viewToUse.findViewById(R.id.titleTextView);
            viewToUse.setTag(holder);
        } else {
            viewToUse = convertView;
            holder = (ViewHolder) viewToUse.getTag();
        }

        holder.titleText.setText(item.getRuang());
        return viewToUse;
    }
}
