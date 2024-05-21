package model;

public class Employee {
    private double numeroPPR;
    private String nomArabe;
    private String prenomArabe;
    private String imputationBudgetaire;
    private String grade;
    private int congeJours;

    // Constructeur
    public Employee() {} ; 
    
    public Employee(double numeroPPR, String nomArabe, String prenomArabe, String imputationBudgetaire, String grade, int congeJours) {
        this.numeroPPR = numeroPPR;
        this.nomArabe = nomArabe;
        this.prenomArabe = prenomArabe;
        this.imputationBudgetaire = imputationBudgetaire;
        this.grade = grade;
        this.congeJours = congeJours;
    }

    // Getters et Setters
    public double getNumeroPPR() {
        return numeroPPR;
    }

    public void setNumeroPPR(double numeroPPR) {
        this.numeroPPR = numeroPPR;
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

    public String getImputationBudgetaire() {
        return imputationBudgetaire;
    }

    public void setImputationBudgetaire(String imputationBudgetaire) {
        this.imputationBudgetaire = imputationBudgetaire;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public int getCongeJours() {
        return congeJours;
    }

    public void setCongeJours(int congeJours) {
        this.congeJours = congeJours;
    }
}
