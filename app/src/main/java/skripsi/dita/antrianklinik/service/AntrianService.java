package skripsi.dita.antrianklinik.service;

import java.util.List;

import retrofit2.Call;
import skripsi.dita.antrianklinik.common.ApiUrl;
import skripsi.dita.antrianklinik.model.Antrian;

/**
 * Created by Dita on 14/07/2018.
 */

public class AntrianService extends BaseService<AntrianApi>{

    private static AntrianService instance;

    public static AntrianService getInstance(){
        if (instance == null){
            instance = new AntrianService();
        }
        return instance;
    }

    public AntrianService() {
        setApi(AntrianApi.class);
    }

    public Call<List<Antrian>> daftarantrian(String norm){
        return getApi().daftarantrian(ApiUrl.DAFTAR_ANTRIAN_API, norm);
    }
}
