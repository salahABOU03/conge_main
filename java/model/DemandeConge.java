package model;

import java.util.Date;

public class DemandeConge {
    private int id;
    private double numeroPPR;
    private Date dateDebut;
    private int duree;
    private Date dateFin;
    private String nomArabe;
    private String prenomArabe;
    private String etat; // Nouveau champ pour l'état de la demande de congé

    public DemandeConge() {
    }

    public DemandeConge(double numeroPPR, String prenomArabe, String nomArabe, Date dateDebut, int duree, Date dateFin, String etat) {
        this.nomArabe = nomArabe;
        this.prenomArabe = prenomArabe;
        this.numeroPPR = numeroPPR;
        this.dateDebut = dateDebut;
        this.duree = duree;
        this.dateFin = dateFin;
        this.etat = etat;
    }

    public String getNomArabe() {
        return nomArabe;
    }

    public void setNomArabe(String nomArabe) {
        this.nomArabe = nomArabe;
    }

    public String getPrenomArabe() {
        return prenomArabe;
    }

    public void setPrenomArabe(String prenomArabe) {
        this.prenomArabe = prenomArabe;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getNumeroPPR() {
        return numeroPPR;
    }

    public void setNumeroPPR(double numeroPPR) {
        this.numeroPPR = numeroPPR;
    }

    public Date getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    }

    public int getDuree() {
        return duree;
    }

    public void setDuree(int duree) {
        this.duree = duree;
    }

    public Date getDateFin() {
        return dateFin;
    }

    public void setDateFin(Date dateFin) {
        this.dateFin = dateFin;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }
}
