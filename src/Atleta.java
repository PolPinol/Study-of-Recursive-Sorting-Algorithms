import com.google.gson.annotations.SerializedName;

import java.util.Objects;

/**
 * Classe en la qual guardem tota la informació dels atletes
 */
public class Atleta  {

    @SerializedName("name")
    private String nom;

    @SerializedName("lastname")
    private String cognom;

    @SerializedName("age")
    private int edat;

    @SerializedName("nation")
    private String nacionalitat;

    @SerializedName("distance")
    private float distancia;

    @SerializedName("time")
    private float temps;

    @SerializedName("type")
    private String tipus;

    private float estatFisic;

    public Atleta(String nom, String cognom, int edat, String nacionalitat, float distancia, float temps, String tipus, float estatFisic){
        this.nom = nom;
        this.cognom = cognom;
        this.nacionalitat = nacionalitat;
        this.edat = edat;
        this.distancia = distancia;
        this.temps = temps;
        this.tipus = tipus;
        this.estatFisic = estatFisic;
    }

    public boolean equals(Atleta o) {
        return this.nom.equals(o.nom) && this.cognom.equals(o.cognom);
    }

    public Atleta copy() {
        return new Atleta(nom, cognom, edat, nacionalitat, distancia, temps, tipus,estatFisic);
    }

    public void setEstatFisic() {
        if (this.temps == 0)
            this.estatFisic = 0;
        else
            this.estatFisic = this.distancia *  this.distancia *  this.distancia / this.temps;
    }

    public int getEdat() {
        return edat;
    }

    public String getNom() {
        return nom;
    }

    public String getCognom() {
        return cognom;
    }

    public String getNacionalitat() {
        return nacionalitat;
    }

    public float getEstatFisic() {
        return estatFisic;
    }

    public float getTemps() {
        return temps;
    }

    public float getDistancia() {
        return distancia;
    }


}
