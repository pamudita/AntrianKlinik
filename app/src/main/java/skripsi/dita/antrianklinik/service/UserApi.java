package skripsi.dita.antrianklinik.service;

import retrofit2.http.GET;
import retrofit2.http.Query;
import skripsi.dita.antrianklinik.model.User;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.POST;
import retrofit2.http.Url;

/**
 * Created by Kristiawan on 21/04/18.
 * this class for example
 */
public interface UserApi {

    @GET
    Call<User> login(@Url String url,
                     @Query("norm") String norm,
                     @Query("password") String password);

    @GET
    Call<User> daftar(@Url String url,
                      @Query("norm") String norm,
                      @Query("password") String password,
                      @Query("tgl_lahir") String tgl_lahir);

    @GET
    Call<User> updatePassword(@Url String url,
                              @Query("norm") String norm,
                              @Query("newpass") String password,
                              @Query("passlama") String passwordLama);

}
