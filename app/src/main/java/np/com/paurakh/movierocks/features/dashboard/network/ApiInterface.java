package np.com.paurakh.movierocks.features.dashboard.network;

import np.com.paurakh.movierocks.features.dashboard.model.MoviesListResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
public interface ApiInterface {

    /**
     * Get a list of popular movies.
     *
     * @param apiKey The API key to use for authentication.
     * @param pageNo The page number of the results to retrieve.
     * @return A Call object that can be used to send the API request and receive the response.
     */
    @GET("Rest%20APIs/popularMovies.json")
    Call<MoviesListResponse> getPopularMovies(@Query("api_key") String apiKey, @Query("page") int pageNo);

    /**
     * Get a list of upcoming movies.
     *
     * @param apiKey The API key to use for authentication.
     * @param pageNo The page number of the results to retrieve.
     * @return A Call object that can be used to send the API request and receive the response.
     */
    @GET("Rest%20APIs/upComingMovies.json")
    Call<MoviesListResponse> getUpComingMovies(@Query("api_key") String apiKey, @Query("page") int pageNo);
}
