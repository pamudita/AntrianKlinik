package skripsi.dita.antrianklinik.service;

import java.util.List;

import retrofit2.Call;
import skripsi.dita.antrianklinik.common.ApiUrl;
import skripsi.dita.antrianklinik.model.CaraBayar;
import skripsi.dita.antrianklinik.model.Dokter;
import skripsi.dita.antrianklinik.model.Pendaftaran;

/**
 * Created by Dita on 21/06/2018.
 */

public class PendaftaranService extends BaseService<PendaftaranApi>{

    private static PendaftaranService instance;

    public static PendaftaranService getInstance() {
        if (instance == null) {
            instance = new PendaftaranService();
        }
        return instance;
    }

    public PendaftaranService() { setApi(PendaftaranApi.class);}

    public Call<List<Pendaftaran>> daftarperiksa(String norm){
        return getApi().daftarperiksa(ApiUrl.DAFTAR_PERIKSA_API, norm);
    }

    public Call<List<CaraBayar>> carabayar(){
        return getApi().carabayar(ApiUrl.CARA_BAYAR_API);
    }

    public Call<List<Dokter>> dokter(String koderuang){
        return getApi().dokter(ApiUrl.DOKTER_API, koderuang);
    }

    public Call<Pendaftaran> daftarp(String norm, String kd_carabayar, String tgl_daftar, String tgl_periksa, String nokartu, String kode_ruang, String kode_dokter, String keluhan){
        return getApi().daftarp(ApiUrl.PENDAFTARAN_API, norm, kd_carabayar, tgl_daftar, tgl_periksa, nokartu, kode_ruang, kode_dokter, keluhan);
    }

    public  Call<List<Pendaftaran>> resdaf(String norm){
        return getApi().resdaf(ApiUrl.RESUME_DAFTAR_API, norm);
    }
}
