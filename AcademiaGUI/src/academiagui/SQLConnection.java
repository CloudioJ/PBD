/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package academiagui;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 *
 * @author claud
 */
public class SQLConnection {
    
    private final String jdbcUrl = "jdbc:postgresql://localhost:5432/academia";
    private final String username = "postgres";
    private final String password = "ph22s31dm";
    
    public SQLConnection(){
        try{
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
    
    public void insertCliente(int idCliente, int idInstrutor, int idNutricionista, String nome, float peso, float altura, String dataNasc, String email, String telefone, String plano) throws ParseException, SQLException {
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    java.util.Date utilDate = sdf.parse(dataNasc);
    java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
    try (Connection connection = DriverManager.getConnection(jdbcUrl, username, password)) {
        String sql = "INSERT INTO Cliente (id_cliente, id_instrutor, id_nutricionista, nome, peso, altura, data_nasc, email, telefone, plano) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, idCliente);
            ps.setInt(2, idInstrutor);
            ps.setInt(3, idNutricionista);
            ps.setString(4, nome);
            ps.setFloat(5, peso);
            ps.setFloat(6, altura);
            ps.setDate(7, sqlDate);
            ps.setString(8, email);
            ps.setString(9, telefone);
            ps.setString(10, plano);
            ps.executeUpdate();
        } catch (SQLException ex){
            
        }
    } catch (SQLException ex){
        
    }
            
}
}