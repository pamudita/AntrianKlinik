package skripsi.dita.antrianklinik.service;

import retrofit2.Call;
import skripsi.dita.antrianklinik.common.ApiUrl;
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

    public Call<Pendaftaran> daftarperiksa(String norm){
        return getApi().daftarperiksa(ApiUrl.DAFTAR_PERIKSA_API, norm);
    }
}
