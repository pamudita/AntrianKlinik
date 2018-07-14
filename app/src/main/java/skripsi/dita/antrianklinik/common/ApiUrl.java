package skripsi.dita.antrianklinik.common;

/**
 * Created by Kristiawan on 21/04/18.
 */
public class ApiUrl {

    public static String BASE_API = "https://antrian-klinik.herokuapp.com";
    public static String LOGIN_API = BASE_API.concat("/?login");
    public static String DAFTAR_AKUN_API = BASE_API.concat("/?daftar");
    public static String JADWAL_API = BASE_API.concat("/?jadwal");
    public static String DETAILJADWAL_API = BASE_API.concat("/?detailjadwal");
    public static String DAFTAR_PERIKSA_API = BASE_API.concat("/?daftarperiksa");
    public static String CARA_BAYAR_API = BASE_API.concat("/?carabayar");
    public static String DOKTER_API = BASE_API.concat("/?dokter");
    public static String PENDAFTARAN_API = BASE_API.concat("/?daftarp");
    public static String RESUME_DAFTAR_API = BASE_API.concat("/?resdaf");
    public static String DAFTAR_ANTRIAN_API = BASE_API.concat("/?daftarantrian");
    public static String LOGOUT_API = BASE_API.concat("/logout");

}
