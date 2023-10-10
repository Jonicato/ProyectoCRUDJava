package org.example.platzi;

import org.example.platzi.model.Employee;
import org.example.platzi.repository.EmployeeRepository;
import org.example.platzi.repository.Repository;
import org.example.platzi.util.DatabaseConnection;

import java.sql.Connection;
import java.sql.SQLException;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) throws SQLException {
        try (Connection myConn = DatabaseConnection.getInstance()) {
            if (myConn.getAutoCommit()) {
                myConn.setAutoCommit(false);
            }

            try {
                Repository<Employee> repository = new EmployeeRepository(myConn);

                System.out.println("----Insertar un nuevo empleado----");
                Employee employee = new Employee();
                employee.setFirst_name("Miguel");
                employee.setPa_surname("Garcia");
                employee.setMa_surname("Tapia");
                employee.setEmail("migue@example.com");
                employee.setSalary(30000F);
                employee.setCurp("MIGT090909MGHTCZ08");
                repository.save(employee);

                employee.setFirst_name("David");
                employee.setPa_surname("Gutiérrez");
                employee.setMa_surname("Olvera");
                employee.setEmail("olv@example.com");
                employee.setSalary(3000F);
                employee.setCurp("AMEC2345678YLDGM06");
                repository.save(employee);
                myConn.commit();

            } catch (SQLException e) {
                myConn.rollback();
                throw new RuntimeException(e);
            }
        }
    }
}