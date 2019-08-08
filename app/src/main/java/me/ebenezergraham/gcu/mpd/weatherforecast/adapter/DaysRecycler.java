package me.ebenezergraham.gcu.mpd.weatherforecast.adapter;

import android.content.Context;
import android.content.Intent;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import me.ebenezergraham.gcu.mpd.weatherforecast.R;
import me.ebenezergraham.gcu.mpd.weatherforecast.activities.MoreDetail;
import me.ebenezergraham.gcu.mpd.weatherforecast.model.WeatherDetail;

/**
 * @author Ebenezer Graham
 * Matric Number: S1725987
 */
public class DaysRecycler extends RecyclerView.Adapter<DaysRecycler.ViewHolder> {

    List<WeatherDetail> weatherDetailList;
    Context context;

    public DaysRecycler(List<WeatherDetail> weatherDetailsList) {
        this.weatherDetailList = weatherDetailsList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        context = parent.getContext();
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        final WeatherDetail weatherDetail = weatherDetailList.get(position);
        holder.title.setText(weatherDetail.getTitle());
        holder.image.setImageResource(R.drawable.overlay);
        String temp = weatherDetail.getMinimumTemperature().split(":")[1];
        boolean tempSetting = PreferenceManager.getDefaultSharedPreferences(context).getBoolean("temperature",false);
        temp = tempSetting ? temp.split("\\(")[0] : temp;
        holder.minimumTemperature.setText(temp);
        holder.windSpeed.setText(weatherDetail.getWindSpeed());
        holder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, MoreDetail.class);
                intent.putExtra("data",weatherDetail);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return weatherDetailList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView title;
        TextView minimumTemperature;
        TextView windSpeed;
        TextView windDirection;
        CardView cv;
        Button button;

        public ViewHolder(View itemView) {
            super(itemView);
            image = (ImageView) itemView.findViewById(R.id.card_image);
            title = (TextView) itemView.findViewById(R.id.card_title);
            minimumTemperature = (TextView) itemView.findViewById(R.id.minimum_temperature);
            windDirection = (TextView) itemView.findViewById(R.id.wind_direction);
            windSpeed = (TextView) itemView.findViewById(R.id.wind_speed);
            cv = (CardView) itemView.findViewById(R.id.cv);
            button = (Button) itemView.findViewById(R.id.more_details);
        }
    }
}
