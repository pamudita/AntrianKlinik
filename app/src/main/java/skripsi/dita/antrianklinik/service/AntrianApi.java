package skripsi.dita.antrianklinik.service;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.Url;
import skripsi.dita.antrianklinik.model.Antrian;

/**
 * Created by Dita on 14/07/2018.
 */

public interface AntrianApi {

    @GET
    Call<List<Antrian>> daftarantrian(@Url String url,
                                      @Query("norm") String norm);
}
