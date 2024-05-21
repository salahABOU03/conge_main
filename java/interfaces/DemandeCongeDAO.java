package interfaces;

import java.sql.SQLException;
import java.util.List;
import model.DemandeConge;

public interface DemandeCongeDAO {
    void ajouterDemandeConge(DemandeConge demandeConge) throws SQLException;

    List<DemandeConge> listerDemandesConge() throws SQLException;

    List<DemandeConge> getDemandesParNumeroPPR(double numeroPPR) throws SQLException;
    
    void updateDemandeConge(DemandeConge demandeConge) throws SQLException;
    
    void updateEtatDemande(int idDemande, String nouvelEtat) throws SQLException;
    
    DemandeConge getDemandeCongeById(int id) throws SQLException; // Nouvelle méthode

    
}
