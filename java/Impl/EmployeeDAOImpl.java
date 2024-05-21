package Impl;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Employee;
import DB.DBUtil;
import interfaces.EmployeeDAO;

public class EmployeeDAOImpl implements EmployeeDAO {

    @Override
    public Employee getEmployeeByNumeroPPRandNomArabe(double numeroPPR, String nomArabe) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Employee employee = null;

        try {
            conn = DBUtil.getConnection();
            String query = "SELECT * FROM Feuil1 WHERE N°_PPR = ? AND NOM_ARABE = ?";
            ps = conn.prepareStatement(query);
            ps.setDouble(1, numeroPPR);
            ps.setString(2, nomArabe);
            rs = ps.executeQuery();

            if (rs.next()) {
                // Récupérer les données de l'employé
                double numPPR = rs.getDouble("N°_PPR");
                String nom = rs.getString("NOM_ARABE");
                String prenom = rs.getString("PRENOM_ARABE");
                String imputation = rs.getString("IMPUTATION_BUDGETAIRE");
                String grade = rs.getString("GRADE");
                int congeJours = rs.getInt("conge_jr");

                // Créer un objet Employee avec les données récupérées
                employee = new Employee(numPPR, nom, prenom, imputation, grade, congeJours);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            DBUtil.close(rs, ps, conn);
        }

        return employee;
    }
    
  /*  @Override
   /* public Employee getEmployeeById(int employeeId) {
        Connection connection = DBUtil.getConnection();
        String query = "SELECT * FROM employees WHERE id = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, employeeId);
        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
            Employee employee = new Employee();
            employee.setId(resultSet.getInt("id"));
            employee.setNomArabe(resultSet.getString("nom_arabe"));
            employee.setNumeroPPR(resultSet.getDouble("numero_ppr"));
            employee.setJoursCongesDisponibles(resultSet.getInt("jours_conges_disponibles"));
            return employee;
        }

        return null;
    }
    
   /* @Override
    public void updateEmployee(Employee employee)  {
        Connection connection = DBUtil.getConnection();
        String query = "UPDATE employees SET conge_jr = ? WHERE N°_PPR = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, employee.getCongeJours());
        statement.setInt(2, employee.getNumeroPPR());
        statement.executeUpdate();
    }*/
    
    @Override
    public void updateEmployee(Employee employee) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        
        try {
            connection = DBUtil.getConnection();
            String query = "UPDATE Feuil1 SET conge_jr = ? WHERE N°_PPR = ?";
            statement = connection.prepareStatement(query);
            statement.setInt(1, employee.getCongeJours());
            statement.setDouble(2, employee.getNumeroPPR()); // Utilisation de setDouble ici
            statement.executeUpdate();
        } catch (SQLException e) {
            // Gérer les erreurs
            e.printStackTrace();
            throw new SQLException("Erreur lors de la mise à jour de l'employé", e);
        } finally {
            // Assurez-vous de fermer les ressources
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}