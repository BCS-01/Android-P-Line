package bcs.p_line;

import android.app.Application;

/**
 * Created by BrianCahSantai on 4/29/2015.
 */
public class application {
    private String image;
    private String ruko;
    private String n_menu;
    private int stok;
    private int harga;
    private String jenis;
    private String user;

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public application(String image, String ruko, String n_menu, int stok, int harga, String jenis, String user) {
        this.image = image;
        this.ruko = ruko;
        this.n_menu = n_menu;
        this.stok = stok;
        this.harga = harga;
        this.jenis = jenis;
        this.user = user;
    }

    public String getJenis() {
        return jenis;
    }

    public void setJenis(String jenis) {
        this.jenis = jenis;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getRuko() {
        return ruko;
    }

    public void setRuko(String ruko) {
        this.ruko = ruko;
    }

    public String getN_menu() {
        return n_menu;
    }

    public void setN_menu(String n_menu) {
        this.n_menu = n_menu;
    }

    public int getStok() {
        return stok;
    }

    public void setStok(int stok) {
        this.stok = stok;
    }

    public int getHarga() {
        return harga;
    }

    public void setHarga(int harga) {
        this.harga = harga;
    }

    public application() {
    }
}



