package com.example.demoexamwear.wear;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.demoexamwear.API.MovieParam;
import com.example.demoexamwear.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder>
{
    List<MovieParam> params;
    Context context;

    public Adapter(List<MovieParam> params)
    {
        this.params = params;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(Adapter.ViewHolder holder, int position)
    {
        MovieParam param = params.get(position);
        Picasso.with(context).load("http://cinema.areas.su/up/images/" + param.getPoster()).resize(200, 300).into(holder.image);
    }

    @Override
    public int getItemCount()
    {
        if(params == null)
        {
            return 0;
        }
        return params.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder
    {
        ImageView image;
        ViewHolder(View view)
        {
            super(view);
            image = view.findViewById(R.id.imageView);
        }
    }
}
