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
        holder.textTvShow.setText(weatherDetail.getTitle());

    }

    @Override
    public int getItemCount() {
        return weatherDetailList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        ImageView imgTvShow;
        TextView textTvShow;
        CardView cv;

        public ViewHolder(View itemView)
        {
            super(itemView);
            imgTvShow = (ImageView)itemView.findViewById(R.id.card_image);
            textTvShow = (TextView)itemView.findViewById(R.id.card_title);
            cv = (CardView)itemView.findViewById(R.id.cv);
        }

    }
}
