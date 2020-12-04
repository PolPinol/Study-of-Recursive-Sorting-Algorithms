import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.Objects;

/**
 * Classe on guardem tota la informació referent als clubs
 */
public class Club {

    @SerializedName("name")
    private String nom;

    @SerializedName("nation")
    private String nacionalitat;

    @SerializedName("date")
    private int any;

    @SerializedName("at")
    private ArrayList<Atleta> atletes;

    private String mitjaEdats;

    public Club(String nom, int any, ArrayList<Atleta> atletes, String mitjaEdats){
        this.nom = nom;
        this.any = any;
        this.atletes = atletes;
        this.mitjaEdats = mitjaEdats;
    }

    public boolean equals(Club o) {
        return this.mitjaEdats.equals(o.mitjaEdats);
    }

    public Club copy() {
        return new Club(this.nom, this.any, this.atletes, this.mitjaEdats);
    }

    /**
     * Funcio la qual calcula la mitjana d'edat de tots els atletes d'un club
     */
    public void setMitjaEdats() {
        float suma;
        float mitjana;
        suma = 0;

        for (int i = 0; i < this.atletes.size(); i++) {
            suma = suma + this.atletes.get(i).getEdat();
        }

        mitjana = suma / this.atletes.size();

        this.mitjaEdats = String.valueOf(mitjana);
    }

    public String getMitjaEdats() {
        return mitjaEdats;
    }

    public ArrayList<Atleta> getAtletes() {
        return atletes;
    }

    public String getNom() {
        return nom;
    }

}
