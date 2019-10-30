package bcs.p_line;

/**
 * Created by BrianCahSantai on 5/3/2015.
 */
public class transaksi {
    private int id_transaksi;
    private int id_user;
    private String n_menu;
    private int id_ruko;
    private int meja;

    public int getMeja() {
        return meja;
    }

    public void setMeja(int meja) {
        this.meja = meja;
    }

    public int getId_transaksi() {
        return id_transaksi;
    }

    public void setId_transaksi(int id_transaksi) {
        this.id_transaksi = id_transaksi;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public String getN_menu() {
        return n_menu;
    }

    public void setN_menu(String n_menu) {
        this.n_menu = n_menu;
    }

    public int getId_ruko() {
        return id_ruko;
    }

    public void setId_ruko(int id_ruko) {
        this.id_ruko = id_ruko;
    }
}
