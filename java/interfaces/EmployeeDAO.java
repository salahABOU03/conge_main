/**
 * 
 */
package interfaces;

import java.sql.SQLException;

import model.Employee;

public interface EmployeeDAO {
    Employee getEmployeeByNumeroPPRandNomArabe(double numeroPPR, String NOM_ARABE);
   /* Employee getEmployeeById(int employeeId) throws SQLException;*/
    void updateEmployee(Employee employee) throws SQLException;
}