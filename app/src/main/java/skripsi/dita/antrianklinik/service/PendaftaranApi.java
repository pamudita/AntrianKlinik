package skripsi.dita.antrianklinik.service;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.Url;
import skripsi.dita.antrianklinik.model.CaraBayar;
import skripsi.dita.antrianklinik.model.Dokter;
import skripsi.dita.antrianklinik.model.Pendaftaran;

/**
 * Created by Dita on 21/06/2018.
 */

public interface PendaftaranApi {

    @GET
    Call<List<Pendaftaran>> daftarperiksa(@Url String url,
                                          @Query("norm") String norm);

    @GET
    Call<List<CaraBayar>> carabayar(@Url String url);

    @GET
    Call<List<Dokter>> dokter(@Url String url,
                              @Query("koderuang") String koderuang);

    @GET
    Call<Pendaftaran> daftarp(@Url String url,
                              @Query("norm") String norm,
                              @Query("kdcarapem") String kdcarabayar,
                              @Query("tgl_daftar") String tgldaftar,
                              @Query("tgl_periksa") String tglperiksa,
                              @Query("nokartu") String nokartu,
                              @Query("kode_ruang") String kode_ruang,
                              @Query("kode_dokter") String kode_dokter,
                              @Query("keluhan") String keluhan
                              );

    @GET
    Call<List<Pendaftaran>> resdaf(@Url String url,
                                   @Query("norm") String norm);
}
