/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package academiagui;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.ArrayList;

/**
 *
 * @author claud
 */
public class SQLConnection{
    
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
    
    /**
     *
     * @return
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public ArrayList<Cliente> showClientes() throws SQLException, ClassNotFoundException {
        var clients = new ArrayList<Cliente>();
        try (Connection connection = DriverManager.getConnection(jdbcUrl, username, password)) {
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM cliente");

            while (rs.next()) {
                try {
                    Cliente client = new Cliente(
                            rs.getInt("id_cliente"),
                            rs.getInt("id_instrutor"),
                            rs.getInt("id_nutricionista"),
                            rs.getString("nome"),
                            rs.getFloat("peso"),
                            rs.getFloat("altura"),
                            rs.getDate("data_nasc").toString(),
                            rs.getString("email"),
                            rs.getString("telefone"),
                            rs.getString("plano")
                    );
                    clients.add(client);
                } catch (SQLException ex) {
                    // Log any exceptions that occur during object creation
                    ex.printStackTrace();
                }
            }
        } catch (SQLException ex) {
            // Log any SQL exceptions that occur during database access
            ex.printStackTrace();
        }
        return clients;
    }   
}