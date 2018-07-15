package skripsi.dita.antrianklinik.service;

/**
 * Created by Dita on 07/03/18.
 *
 * this class service for call all service
 * use this class for implemetation
 * editing this class when your creating class service and add method return your service
 */

public class ApiService {

    private static class BaseApiServiceHolder {
        private static final ApiService INSTANCE = new ApiService();
    }

    public static ApiService newInstance() {
        return BaseApiServiceHolder.INSTANCE;
    }

    public UserService getUserService(){
        return UserService.getInstance();
    }

    public JadwalService getJadwalService(){
        return JadwalService.getInstance();
    }

    public PendaftaranService getPendaftaranService(){
        return PendaftaranService.getInstance();
    }

    public AntrianService getAntrianService(){ return AntrianService.getInstance();}
}
