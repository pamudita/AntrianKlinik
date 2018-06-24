package skripsi.dita.antrianklinik.model;

/**
 * Created by Dita on 20/05/2018.
 */

public class Antrian {
    private String antrian;
    private String namaklinik;
    private String statusantrian;

    public String getAntrian() {
        return antrian;
    }
    public void setAntrian(String antrian) {
        this.antrian = antrian;
    }

    public String getNamaklinik() {
        return namaklinik;
    }
    public void setNamaklinik(String namaklinik) {
        this.namaklinik = namaklinik;
    }

    public String getStatusantrian() {
        return statusantrian;
    }
    public void setStatusantrian(String statusantrian) {
        this.statusantrian = statusantrian;
    }

}
