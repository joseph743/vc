package app.com.project215.activities.warehouse;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import app.com.project215.R;

/**
 * Created by learnovate on 2/17/14.
 */
public class WarehouseAdapter extends ArrayAdapter<Warehouse> {

    Context mContext;
    int mLayoutResourceId;
    Warehouse mData[] = null;

    public WarehouseAdapter(Context context, int resource, Warehouse[] data) {
        super(context, resource, data);

        this.mContext = context;
        this.mLayoutResourceId = resource;
        this.mData = data;

    }

    @Override
    public Warehouse getItem(int position) {
        return super.getItem(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View row = convertView;
        WarehouseHolder holder = null;

        //if we currently don’t have a row View to reuse..
        if (row == null) {
            //Create a new View
            LayoutInflater inflater = LayoutInflater.from(mContext);
            row = inflater.inflate(mLayoutResourceId, parent, false);

            holder = new WarehouseHolder();

            holder.text_name = (TextView) row.findViewById(R.id.tv_name);
            holder.text_city = (TextView) row.findViewById(R.id.tv_city);


            row.setTag(holder);
        } else {
            //Otherwise use an existing one
            holder = (WarehouseHolder) row.getTag();
        }

        //Getting the data form the data array
        Warehouse warehouse = mData[position];


        holder.text_name.setText(warehouse.name);
        holder.text_city.setText(warehouse.city);

        return row;


    }

//    View.OnClickListener PopupListener = new View.OnClickListener() {
//        @Override
//        public void onClick(View view) {
//            Integer viewPosition = (Integer) view.getTag();
//            Warehouse p = mData[viewPosition];
//            Toast.makeText(getContext(), p.mPopup, Toast.LENGTH_SHORT).show();
//        }
//    };


    private static class WarehouseHolder {
        TextView text_name ,text_city ;
    }


}