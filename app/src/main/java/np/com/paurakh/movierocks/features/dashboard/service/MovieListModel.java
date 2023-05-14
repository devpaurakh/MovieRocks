package np.com.paurakh.movierocks.features.dashboard.service;

import android.util.Log;

import androidx.annotation.NonNull;

import java.util.List;

import np.com.paurakh.movierocks.features.dashboard.contract.MovieListContract;
import np.com.paurakh.movierocks.features.dashboard.model.Movies;
import np.com.paurakh.movierocks.features.dashboard.model.MoviesListResponse;
import np.com.paurakh.movierocks.features.dashboard.network.ApiClint;
import np.com.paurakh.movierocks.features.dashboard.network.ApiInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieListModel implements MovieListContract.Model {

    private final String TAG = "MovieListModel";
    private final int pageNo = 1;

    @Override
    public void getMovieList(onFinishListener onFinishListener, int pageNo) {
        ApiInterface apiService = ApiClint.getClint().create(ApiInterface.class);
        Call<MoviesListResponse> call = apiService.getPopularMovies(ApiClint.API_KEY,pageNo);
        call.enqueue(new Callback<MoviesListResponse>() {
            @Override
            public void onResponse(@NonNull Call<MoviesListResponse> call, @NonNull Response<MoviesListResponse> response) {
                assert response.body() != null;
                List<Movies> movies = response.body().getResults();
                Log.i(TAG, "Number of movies received "+ movies.size() );
                onFinishListener.onFinished(movies);
            }

            @Override
            public void onFailure(@NonNull Call<MoviesListResponse> call, @NonNull Throwable t) {
                Log.e(TAG, t.toString() );
                onFinishListener.onFailure(t);
            }
        });
    }

    public void getUpComingMovieList(onFinishListener onFinishListener, int pageNo){
        ApiInterface apiService = ApiClint.getClint().create(ApiInterface.class);
        Call<MoviesListResponse> upComingMoviesCall = apiService.getUpComingMovies(ApiClint.API_KEY,pageNo);

        upComingMoviesCall.enqueue(new Callback<MoviesListResponse>() {
            @Override
            public void onResponse(@NonNull Call<MoviesListResponse> call, @NonNull Response<MoviesListResponse> response) {
                assert response.body() != null;
                List<Movies> movies = response.body().getResults();
                Log.i(TAG, "Number of movies received "+ movies.size() );
                onFinishListener.onFinished(movies);
            }

            @Override
            public void onFailure(@NonNull Call<MoviesListResponse> call, @NonNull Throwable t) {
                Log.e(TAG, t.toString() );
                onFinishListener.onFailure(t);
            }
        });

    }

}
