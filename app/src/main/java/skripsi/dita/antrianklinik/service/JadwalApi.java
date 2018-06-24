package skripsi.dita.antrianklinik.service;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Query;
import skripsi.dita.antrianklinik.model.Dokter;
import skripsi.dita.antrianklinik.model.Klinik;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.POST;
import retrofit2.http.Url;

/**
 * Created by Dita on 03/06/2018.
 */

public interface JadwalApi {

    @GET
    Call<List<Klinik>> jadwal(@Url String url);

    @GET
    Call<List<Klinik>> detailjadwal(@Url String url,
                                    @Query("kode_ruang") String kode_ruang);


}
