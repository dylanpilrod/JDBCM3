/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dylanpilrod.dao;
import dylanpilrod.model.Producto;
/**
 *
 * @author dylanpilrod
 */
public interface ProductoDAO {
    public void insert (Producto producto);
    public void update (Producto producto);
    public void delete (Integer id);
    public Producto read (Integer id);
}
