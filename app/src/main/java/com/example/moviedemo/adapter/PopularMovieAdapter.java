package com.example.moviedemo.adapter;

import android.content.Context;
import android.graphics.Point;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.moviedemo.R;
import com.example.moviedemo.constants.Constants;
import com.example.moviedemo.modelClass.Results;

import java.util.ArrayList;

public class PopularMovieAdapter extends RecyclerView.Adapter<PopularMovieAdapter.ViewHolder>
{
    private Context context;
    private ArrayList<Results> movieArrayList;

    public PopularMovieAdapter(Context context,ArrayList<Results> movieArrayList) {
        this.context = context;
        this.movieArrayList = movieArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_item_adapter, parent, false);
        view.getLayoutParams().width = (int) (getScreenWidth() / 2); /// THIS LINE WILL DIVIDE OUR VIEW INTO NUMBERS OF PARTS

        return new PopularMovieAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Results results=movieArrayList.get(position);
        holder.txtMovieName.setText(results.getTitle());

        Glide
                .with(context)
                .load(Constants.IMAGE_BASE_URL+results.getPoster_path())
                .centerCrop()
                .into(holder.imgMovie);
    }

    @Override
    public int getItemCount() {
        return movieArrayList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private AppCompatTextView txtMovieName;
        private AppCompatImageView imgMovie;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtMovieName=itemView.findViewById(R.id.txtMovieName);
            imgMovie=itemView.findViewById(R.id.imgMovie);
        }
    }

    // get screen width
    public int getScreenWidth() {

        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);

        return size.x;
    }
}
