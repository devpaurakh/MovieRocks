package np.com.paurakh.movierocks.features.dashboard.dashboardpages.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

import np.com.paurakh.movierocks.R;
import np.com.paurakh.movierocks.features.dashboard.model.Movies;
import np.com.paurakh.movierocks.features.dashboard.network.ApiClint;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder> {
    private final List<Movies> moviesList;
    private final Context context;

    public MovieAdapter(List<Movies> moviesList, Context context) {
        this.moviesList = moviesList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.row_item_movie_list,parent,false);
        return new ViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.rate.setText(moviesList.get(position).getVoteAverage().toString());
        holder.releaseDate.setText(moviesList.get(position).getReleaseDate());
        holder.movieTitle.setText(moviesList.get(position).getTitle());
        holder.overView.setText(moviesList.get(position).getOverview());
        Glide.with(context).load(ApiClint.IMAGE_BASE_URL+ moviesList.get(position).getPosterPath()).into(holder.moviePoster);
    }

    @Override
    public int getItemCount() {
        return moviesList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView moviePoster;
        TextView rate,releaseDate,movieTitle,overView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            moviePoster = itemView.findViewById(R.id.ivMoviePoster);
            rate = itemView.findViewById(R.id.tvRate);
            releaseDate = itemView.findViewById(R.id.tvReleaseDate);
            movieTitle = itemView.findViewById(R.id.tvTitle);
            overView = itemView.findViewById(R.id.tvOverview);

        }
    }
}
