package edu.cnm.deepdive.freestylerhyme.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import edu.cnm.deepdive.freestylerhyme.BuildConfig;
import edu.cnm.deepdive.freestylerhyme.model.pojo.WordApiResponse;
import io.reactivex.Single;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;

public interface WordApiService {

  @GET("{word}/rhymes")
  Single<WordApiResponse> get(
      @Path("word") String word,
      @Header("x-rapidapi-host") String host,
      @Header("x-rapidapi-key") String apiKey);

  static WordApiService getInstance() {
    return InstanceHolder.INSTANCE;
  }

  class InstanceHolder {

    private static final WordApiService INSTANCE;

    static {
      Gson gson = new GsonBuilder()
          .excludeFieldsWithoutExposeAnnotation()
          .create();
      HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
      interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
      OkHttpClient client = new OkHttpClient.Builder()
          .addInterceptor(interceptor)
          .build();
      Retrofit retrofit = new Retrofit.Builder()
          .addConverterFactory(GsonConverterFactory.create(gson))
          .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
          .client(client)
          .baseUrl(BuildConfig.BASE_URL)
          .build();
      INSTANCE = retrofit.create(WordApiService.class);
    }

  }

}
