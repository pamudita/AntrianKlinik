package skripsi.dita.antrianklinik.service;


import skripsi.dita.antrianklinik.common.ApiUrl;
import skripsi.dita.antrianklinik.model.User;
import retrofit2.Call;

/**
 * Created by Kristiawan on 21/04/18.
 * this class for example
 */
public class UserService extends BaseService<UserApi> {

    private static UserService instance;

    public static UserService getInstance() {
        if (instance == null) {
            instance = new UserService();
        }

        return instance;
    }

    public UserService() {
        setApi(UserApi.class);
    }

    public Call<User> login(String norm, String password) {
        return getApi().login(ApiUrl.LOGIN_API, norm, password);
    }

    public Call<User> daftar(String norm, String tgl_lahir, String password) {
        return getApi().daftar(ApiUrl.DAFTAR_AKUN_API, norm, password, tgl_lahir);
    }

    public Call<User> updatePassword(String norm, String newPass, String passLama){
        return getApi().updatePassword(ApiUrl.UPDATE_PASSWORD, norm, newPass, passLama);
    }
}
