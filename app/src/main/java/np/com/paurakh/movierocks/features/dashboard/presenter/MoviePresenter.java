package np.com.paurakh.movierocks.features.dashboard.presenter;

import java.util.List;

import np.com.paurakh.movierocks.features.dashboard.contract.MovieListContract;
import np.com.paurakh.movierocks.features.dashboard.model.Movies;
import np.com.paurakh.movierocks.features.dashboard.service.MovieListModel;

public class MoviePresenter implements MovieListContract.Presenter, MovieListContract.Model.onFinishListener {

    private MovieListContract.View movieListView;
    private final MovieListContract.Model movieListModel;

    public MoviePresenter(MovieListContract.View movieListView) {
        this.movieListView = movieListView;
        movieListModel = new MovieListModel();
    }

    @Override
    public void onDestroy() {
        this.movieListView = null;
    }

    @Override
    public void getMoreData(int pageNo) {
        if (movieListView != null) {
            movieListView.showProgress();
        }
        movieListModel.getMovieList(this, pageNo);
    }

    @Override
    public void requestDataFromServer() {
        if (movieListView != null) {
            movieListView.showProgress();
        }
        movieListModel.getMovieList(this, 1);
    }

    @Override
    public void onFinished(List<Movies> movies) {
        if (movieListView != null) {
            movieListView.setDataToRecyclerView(movies);
            movieListView.hideProgress();
        }
    }

    @Override
    public void onFailure(Throwable t) {
        if (movieListView != null) {
            movieListView.onResponseFailure(t);
            movieListView.hideProgress();
        }
    }
}
