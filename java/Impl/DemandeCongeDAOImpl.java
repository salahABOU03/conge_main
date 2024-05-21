package Impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.DemandeConge;
import DB.DBUtil;
import interfaces.DemandeCongeDAO; 

public class DemandeCongeDAOImpl implements DemandeCongeDAO {

    public void ajouterDemandeConge(DemandeConge demandeConge) throws SQLException {
        String sql = "INSERT INTO demandes_conge (numero_ppr, nom_arabe, prenom_arabe, date_debut_conge, duree_conge, date_fin_conge, etat) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setDouble(1, demandeConge.getNumeroPPR());
            stmt.setString(2, demandeConge.getNomArabe());
            stmt.setString(3, demandeConge.getPrenomArabe());
            stmt.setDate(4, new java.sql.Date(demandeConge.getDateDebut().getTime()));
            stmt.setInt(5, demandeConge.getDuree());
            stmt.setDate(6, new java.sql.Date(demandeConge.getDateFin().getTime()));
            stmt.setString(7, demandeConge.getEtat());
            stmt.executeUpdate();
        }
    }

    public List<DemandeConge> listerDemandesConge() throws SQLException {
        List<DemandeConge> demandes = new ArrayList<>();
        String sql = "SELECT * FROM demandes_conge";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                DemandeConge demandeConge = new DemandeConge();
                demandeConge.setId(rs.getInt("id"));
                demandeConge.setNumeroPPR(rs.getDouble("numero_ppr"));
                demandeConge.setNomArabe(rs.getString("nom_arabe"));
                demandeConge.setPrenomArabe(rs.getString("prenom_arabe"));
                demandeConge.setDateDebut(rs.getDate("date_debut_conge"));
                demandeConge.setDuree(rs.getInt("duree_conge"));
                demandeConge.setDateFin(rs.getDate("date_fin_conge"));
                demandeConge.setEtat(rs.getString("etat")); // Ajout de la récupération de l'état
                demandes.add(demandeConge);
            }
        }
        return demandes;
    }
    
    @Override
    public void updateEtatDemande(int idDemande, String nouvelEtat) throws SQLException {
        // Établir la connexion à la base de données
        Connection conn = null;
        PreparedStatement stmt = null;
        
        try {
            conn = DBUtil.getConnection();
            String sql = "UPDATE demandes_conge SET etat = ? WHERE id = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, nouvelEtat);
            stmt.setInt(2, idDemande);
            
            // Exécuter la mise à jour
            stmt.executeUpdate();
        } finally {
            // Fermer les ressources
            if (stmt != null) {
                stmt.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
    }

    
    @Override
    public void updateDemandeConge(DemandeConge demandeConge) throws SQLException {
        String sql = "UPDATE demandes_conge SET etat = ? WHERE id = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, demandeConge.getEtat());
            stmt.setInt(2, demandeConge.getId());
            stmt.executeUpdate();
        }
    }
    
    @Override
    public DemandeConge getDemandeCongeById(int id) throws SQLException {
        Connection connection = DBUtil.getConnection();
        String query = "SELECT * FROM demandes_conge WHERE id = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, id);
        ResultSet rs = statement.executeQuery();

        if (rs.next()) {
            DemandeConge demande = new DemandeConge();
            demande.setId(rs.getInt("id"));
            demande.setNumeroPPR(rs.getDouble("numero_ppr"));
            demande.setNomArabe(rs.getString("nom_arabe"));
            demande.setPrenomArabe(rs.getString("prenom_arabe"));
            demande.setDateDebut(rs.getDate("date_debut_conge"));
            demande.setDuree(rs.getInt("duree_conge"));
            demande.setDateFin(rs.getDate("date_fin_conge"));
            demande.setEtat(rs.getString("etat")); // Assurez-vous que cette colonne existe dans votre table
            return demande;
        }

        return null;
    }


    @Override
    public List<DemandeConge> getDemandesParNumeroPPR(double numeroPPR) throws SQLException {
        List<DemandeConge> demandes = new ArrayList<>();
        String sql = "SELECT * FROM demandes_conge WHERE numero_ppr = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setDouble(1, numeroPPR);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    DemandeConge demandeConge = new DemandeConge();
                    // Remplir l'objet DemandeConge à partir du résultat de la requête
                    demandeConge.setId(rs.getInt("id"));
                    demandeConge.setDateDebut(rs.getDate("date_debut_conge"));
                    demandeConge.setDateFin(rs.getDate("date_fin_conge"));
                    demandeConge.setEtat(rs.getString("etat"));
                    // Ajouter l'objet DemandeConge à la liste des demandes
                    demandes.add(demandeConge);
                }
            }
        }
        return demandes;
    }

}
