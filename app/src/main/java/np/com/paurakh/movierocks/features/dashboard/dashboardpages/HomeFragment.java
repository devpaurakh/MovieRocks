package np.com.paurakh.movierocks.features.dashboard.dashboardpages;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import np.com.paurakh.movierocks.R;
import np.com.paurakh.movierocks.features.dashboard.contract.MovieListContract;
import np.com.paurakh.movierocks.features.dashboard.dashboardpages.adapter.MovieAdapter;
import np.com.paurakh.movierocks.features.dashboard.model.Movies;
import np.com.paurakh.movierocks.features.dashboard.presenter.MoviePresenter;

/*
This is an implementation of a HomeFragment class that extends the Fragment class
and implements the MovieListContract.View interface.
This class represents a fragment of the home screen that displays a list of movies.
* */
public class HomeFragment extends Fragment implements MovieListContract.View {

    /*
     * The class has several member variables, including a MoviePresenter object,
     *  a RecyclerView, a list of Movies,a MovieAdapter,
     * a LinearLayoutManager, a ProgressBar, a page number and a Context object.
     * */
    private MoviePresenter moviePresenter;
    private RecyclerView movieRecyclerView;
    private List<Movies> moviesList;
    private MovieAdapter movieAdapter;
    private LinearLayoutManager linearLayoutManager;
    private ProgressBar progressBar;
    private int pageNo = 1;
    private Context context;

    /*
     * In the onAttach method, the Context is initialized when the fragment is attached to the activity.*/
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        return view;
    }

    /*
     * In the onViewCreated method, the RecyclerView and ProgressBar are initialized
     * and the moviesList is created. The LinearLayoutManager is set as the layout manager
     * for the RecyclerView. The MoviePresenter object is initialized and requestDataFromServer
     * method is called to get the movie data from the server.*/
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        movieRecyclerView = view.findViewById(R.id.rvMovieListing);
        progressBar = view.findViewById(R.id.pbProgressing);
        moviesList = new ArrayList<>();
        linearLayoutManager = new LinearLayoutManager(context);
        movieRecyclerView.setLayoutManager(linearLayoutManager);
        movieRecyclerView.setHasFixedSize(true);

        moviePresenter = new MoviePresenter(this);
        moviePresenter.requestDataFromServer();
    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
    }

    @SuppressLint("NotifyDataSetChanged")
    @Override
    public void setDataToRecyclerView(List<Movies> moviesList) {
        this.moviesList.addAll(moviesList);
        if (movieAdapter == null) {
            movieAdapter = new MovieAdapter(this.moviesList, context);
            movieRecyclerView.setAdapter(movieAdapter);
        } else {
            movieAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onResponseFailure(Throwable throwable) {
        Log.e("error", throwable.getMessage());
        Toast.makeText(context, "error", Toast.LENGTH_SHORT).show();
    }
}
