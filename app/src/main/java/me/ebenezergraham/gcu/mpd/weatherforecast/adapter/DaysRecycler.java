package me.ebenezergraham.gcu.mpd.weatherforecast.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import me.ebenezergraham.gcu.mpd.weatherforecast.R;
import me.ebenezergraham.gcu.mpd.weatherforecast.model.WeatherDetail;

public class DaysRecycler extends RecyclerView.Adapter<DaysRecycler.ViewHolder> {

    List<WeatherDetail> weatherDetailList;
    Context context;

    public DaysRecycler(List<WeatherDetail> weatherDetailsList)
    {
        this.weatherDetailList = weatherDetailsList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        context = parent.getContext();
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        WeatherDetail weatherDetail = weatherDetailList.get(position);
        holder.title.setText(weatherDetail.getTitle());
        holder.maximumTemperature.setText(weatherDetail.getMaximumTemperature());
        holder.minimumTemperature.setText(weatherDetail.getMinimumTemperature());
        holder.windSpeed.setText(weatherDetail.getWindSpeed());
        holder.windDirection.setText(weatherDetail.getWindDirection());
        holder.pressure.setText(weatherDetail.getPressure());
    }

    @Override
    public int getItemCount() {
        return weatherDetailList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        ImageView image;
        TextView title;
        TextView maximumTemperature;
        TextView minimumTemperature;
        TextView windSpeed;
        TextView windDirection;
        TextView pressure;
        CardView cv;

        public ViewHolder(View itemView)
        {
            super(itemView);
            image = (ImageView)itemView.findViewById(R.id.card_image);
            title = (TextView)itemView.findViewById(R.id.card_title);
            minimumTemperature = (TextView)itemView.findViewById(R.id.minimum_temperature);
            maximumTemperature = (TextView)itemView.findViewById(R.id.maximum_temperature);
            windDirection = (TextView)itemView.findViewById(R.id.wind_direction);
            windSpeed = (TextView)itemView.findViewById(R.id.wind_speed);
            pressure = (TextView)itemView.findViewById(R.id.pressure);
            cv = (CardView)itemView.findViewById(R.id.cv);
        }
    }
}
