package np.com.paurakh.movierocks.features.dashboard.network;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClint {
    //this url is for web
  //public static final String BASE_URL ="https://api.themoviedb.org/3/";

    //public static final String BASE_URL ="http://10.0.2.2:80";
  // Define the base URL for the API
  //public static final String BASE_URL ="http://192.168.1.68";
    public static final String BASE_URL ="http://100.64.193.18";

    // Defining the API key and base URL for movie poster images
    public static final String API_KEY = "7dc42d542480c8947295b0898e3c71da";
    public static final String IMAGE_BASE_URL = "https://image.tmdb.org/t/p/w200";

    // Create a static variable of the Retrofit instance
    public static Retrofit retrofit = null;

    /**
     * This method is used to create a new instance of the Retrofit client.
     * @return The Retrofit instance
     */
    public static Retrofit getClint(){
        if(retrofit == null){

            // Creating a logging interceptor to log HTTP requests and responses
            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);

            // Creating an OkHttpClient instance with the logging interceptor added to it
            OkHttpClient client = new OkHttpClient.Builder()
                    .addInterceptor(logging)
                    .build();

            // Building the Retrofit instance with the base URL, OkHttpClient instance and Gson converter factory
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
