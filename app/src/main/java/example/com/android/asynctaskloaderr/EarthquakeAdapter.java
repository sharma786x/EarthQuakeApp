package example.com.android.asynctaskloaderr;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Sharma786 on 02/05/2017.
 */

public class EarthquakeAdapter extends ArrayAdapter<Earthquake> {




    public EarthquakeAdapter(Context context, List<Earthquake> objects) {
        super(context, 0, objects);

    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Earthquake currentEarthquake=getItem(position);
        if(convertView==null)
        {
            convertView= LayoutInflater.from(getContext()).inflate(R.layout.listlayout,null,false);
        }
        TextView textView1,textView2,textView3;
        textView1=(TextView)convertView.findViewById(R.id.tv1);
        textView2=(TextView)convertView.findViewById(R.id.tv2);
        textView3=(TextView)convertView.findViewById(R.id.tv3);

        textView1.setText(currentEarthquake.getMagnitude());
        textView2.setText(currentEarthquake.getLocation());
        textView3.setText(""+currentEarthquake.getDate());

        return convertView;

    }


}
