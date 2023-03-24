/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dylanpilrod.dao;

import java.util.logging.Level;
import java.util.logging.Logger;
import dylanpilrod.model.Producto;
import java.sql.*;

/**
 *
 * @author dylanpilrod
 */
public class ProductoDAOImpl implements ProductoDAO {

    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/dbjdbc";
    static final String DB_USR = "root";
    static final String DB_PWD = "";

    private void registerDriver() {
        try {
            Class.forName(JDBC_DRIVER);
        } catch (ClassNotFoundException ex) {
            System.err.println("ERROR: failed to load MySQL jdbc Driver");
            ex.printStackTrace();
        }
    }

    public void insert(Producto producto) {
        Connection conn = null;
        try {
            registerDriver();

            conn = DriverManager.getConnection(DB_URL, DB_USR, DB_PWD);
            Statement stmt = conn.createStatement();

            stmt.executeUpdate("Insert into producto values("
                    + producto.getId() + ",'"
                    + producto.getNombre() + "',"
                    + producto.getPrecio() + ");");
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }

            }
        }
    }

    @Override
    public void update(Producto producto) {
        Connection conn = null;
        try {
            registerDriver();
            conn = DriverManager.getConnection(DB_URL, DB_USR, DB_PWD);
            try ( PreparedStatement ps = conn.prepareStatement("update producto set nombre = ?, precio = ? where id = ?")) {
                ps.setString(1, producto.getNombre());
                ps.setDouble(2, producto.getPrecio());
                ps.setInt(3, producto.getId());
                ps.executeUpdate();
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }
    public void delete(Integer Id) {
        Connection conn = null;
        try {
            registerDriver();
            conn = DriverManager.getConnection(DB_URL, DB_USR, DB_PWD);
            try ( PreparedStatement ps = conn.prepareStatement("delete from producto where id = ?")) {
                ps.setInt(1, Id);
                ps.executeUpdate();
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

    @Override
    public Producto read(Integer id) {
        Connection conn = null;
        Producto prod = null;
        try{
            registerDriver();
            conn = DriverManager.getConnection(DB_URL, DB_USR, DB_PWD);
            try(PreparedStatement ps = conn.prepareStatement("select * from producto where id = ?")){
                ps.setInt(1, id);
                try (ResultSet rs = ps.executeQuery()){
                    if (rs.next()){
                        prod = new Producto(id, rs.getString("nombre"), rs.getFloat("precio"));
                    }
                }
            }
        }catch (SQLException ex){
            throw new RuntimeException(ex);
        }finally{
            if (conn !=null){
                try{
                    conn.close();
                }catch (SQLException ex){
                    ex.printStackTrace();
                }
            }
        }
        return prod;
    }


}
