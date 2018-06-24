package skripsi.dita.antrianklinik.service;

import java.util.List;

import retrofit2.Call;
import skripsi.dita.antrianklinik.common.ApiUrl;
import skripsi.dita.antrianklinik.model.Dokter;
import skripsi.dita.antrianklinik.model.Klinik;

/**
 * Created by Dita on 03/06/2018.
 */

public class JadwalService extends BaseService<JadwalApi> {

    private static JadwalService instance;

    public static JadwalService getInstance() {
        if (instance == null) {
            instance = new JadwalService();
        }

        return instance;
    }

    public JadwalService() {
        setApi(JadwalApi.class);
    }

    public Call<List<Klinik>> jadwal() {
        return getApi().jadwal(ApiUrl.JADWAL_API);
    }

    public Call<List<Klinik>> detailjadwal(String kode_ruang) {
        return getApi().detailjadwal(ApiUrl.DETAILJADWAL_API, kode_ruang);
    }
}

