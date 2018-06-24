package skripsi.dita.antrianklinik.service;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.Url;
import skripsi.dita.antrianklinik.model.Pendaftaran;

/**
 * Created by Dita on 21/06/2018.
 */

public interface PendaftaranApi {

    @GET
    Call<Pendaftaran> daftarperiksa(@Url String url,
                              @Query("norm") String norm);
}
