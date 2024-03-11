/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package academiagui;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
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
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    PrintStream ps = new PrintStream(baos);
    PrintStream old = System.out;
    
    public SQLConnection(){
        try{
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
    
    public void insertCliente(int idCliente, int idInstrutor, int idNutricionista, int idPlano, String nome, float peso, float altura, String dataNasc, String email, String telefone) throws ParseException, SQLException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date utilDate = sdf.parse(dataNasc);
        java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
        try (Connection connection = DriverManager.getConnection(jdbcUrl, username, password)) {
            String sql = "INSERT INTO Cliente (id_cliente, id_instrutor, id_nutricionista, id_plano, nome, peso, altura, data_nasc, email, telefone) "
                    + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setInt(1, idCliente);
                ps.setInt(2, idInstrutor);
                ps.setInt(3, idNutricionista);
                ps.setInt(4, idPlano);
                ps.setString(5, nome);
                ps.setFloat(6, peso);
                ps.setFloat(7, altura);
                ps.setDate(8, sqlDate);
                ps.setString(9, email);
                ps.setString(10, telefone);
                
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
                            rs.getInt("id_plano"),
                            rs.getString("nome"),
                            rs.getFloat("peso"),
                            rs.getFloat("altura"),
                            rs.getDate("data_nasc").toString(),
                            rs.getString("email"),
                            rs.getString("telefone")
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
    
    public String showClientesCondensed(){
        String returnString = "|\tNome\t|\tInstrutor\t|\tPlano\t|\n";
        try (Connection connection = DriverManager.getConnection(jdbcUrl, username, password)) {
                Statement st = connection.createStatement();
                ResultSet rs = st.executeQuery("SELECT c.nome AS Nome,"
                        + " f.nome AS Instrutor, "
                        + " p.nome AS Plano "
                        + "FROM Cliente c "
                        + "INNER JOIN Instrutor i ON c.id_instrutor = i.id_funcionario "
                        + "INNER JOIN Plano p ON c.id_plano = p.id_plano "
                        + "INNER JOIN Funcionario f ON f.id_funcionario = i.id_funcionario;");
                
                ResultSetMetaData rsmd = rs.getMetaData();
                int columns = rsmd.getColumnCount();
                

                
                while (rs.next()) {
                    returnString += "|\t";
                    for (int i = 1; i <= columns; i++) {
                    if (i > 1) System.out.print(",  ");
                        String columnValue = rs.getString(i);
                        if (i < 3){
                            returnString += rs.getString(i) + "\t|\t";
                        } else {
                            returnString += rs.getString(i) + "\t|\n";
                        }
                    }
                }
            } catch (SQLException ex) {
                // Log any SQL exceptions that occur during database access
                ex.printStackTrace();
            }
        return returnString;
    }
    
    public String showAtendimentos(){
        String returnString = "|\tNome\t|\tAtendente\t|\tData de atendimento\t|\tDescricao\t|\n";
        try (Connection connection = DriverManager.getConnection(jdbcUrl, username, password)) {
                Statement st = connection.createStatement();
                ResultSet rs = st.executeQuery("SELECT c.nome AS Nome, "
                        + "f.nome AS Atendente, "
                        + "a.data_atendimento AS DataAtendimento, "
                        + "a.descricao AS Descricao "
                        + "FROM Atendimento a "
                        + "INNER JOIN Cliente c ON c.id_cliente = a.id_cliente "
                        + "INNER JOIN Funcionario f ON f.id_funcionario = a.id_funcionario;");
                
                ResultSetMetaData rsmd = rs.getMetaData();
                int columns = rsmd.getColumnCount();
                
                while (rs.next()) {
                    returnString += "|\t";
                    for (int i = 1; i <= columns; i++) {
                    
                    if (i > 1) System.out.print(",  ");
                        String columnValue = rs.getString(i);
                        if (i < columns){
                            returnString += rs.getString(i) + "\t|\t";
                        } else {
                            returnString += rs.getString(i) + "\t|\n";
                        }
                    }
                }
            } catch (SQLException ex) {
                // Log any SQL exceptions that occur during database access
                ex.printStackTrace();
            }
        return returnString;
    }
    
    public void removeClient(String nome) throws SQLException{
        try (Connection connection = DriverManager.getConnection(jdbcUrl, username, password)) {
                String sql = "DELETE FROM cliente WHERE nome = ?;";
                try (PreparedStatement ps = connection.prepareStatement(sql)) {
                    ps.setString(1, nome);
                    ps.executeUpdate();
                } catch (SQLException ex) {
                    // Log any SQL exceptions that occur during database access
                    ex.printStackTrace();
                }

        }
    }
    
    public String clientNames(){
        String clients = "";
        try (Connection connection = DriverManager.getConnection(jdbcUrl, username, password)) {
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery("SELECT nome FROM cliente");

            while (rs.next()) {
                
                clients += rs.getString(1) + "\n";
            }
        } catch (SQLException ex) {
            // Log any SQL exceptions that occur during database access
            ex.printStackTrace();
        }
        return clients;
    }
}