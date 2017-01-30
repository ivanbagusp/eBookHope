package com.example.ivanbaguspinuntun.ebookhope;

/**
 * Created by ivanbaguspinuntun on 1/24/17.
 */

public class Ebook{
    private String judul,isi,kelas,penerbit,gambar;
    private long id_ebook;

    public Ebook(){
    }

    public Long getId(){return id_ebook;}
    public void setId(long id_ebook){this.id_ebook = id_ebook;}

    public String getJudul(){return judul;}
    public void setJudul(String judul){this.judul = judul;}

    public String getIsi(){return isi;}
    public void setIsi(String isi){this.isi = isi;}

    public String getKelas(){return kelas;}
    public void setKelas(String kelas){this.kelas = kelas;}

    public String getPenerbit(){return penerbit;}
    public void setPenerbit(String penerbit){this.penerbit = penerbit;}

    public String getGambar(){return gambar;}
    public void setGambar(String gambar){this.gambar = gambar;}
}
