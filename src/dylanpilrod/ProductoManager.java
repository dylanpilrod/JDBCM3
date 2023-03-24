/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dylanpilrod;

import dylanpilrod.dao.ProductoDAO;
import dylanpilrod.dao.ProductoDAOImpl;
import dylanpilrod.model.Producto;
import java.util.Scanner;
/**
 *
 * @author dylanpilrod
 */
public class ProductoManager {
    public static void main(String[] args){
        ProductoDAO producto = new ProductoDAOImpl();
        Scanner sc = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("=== Menú de opciones ===");
            System.out.println("1. Agregar un nuevo producto");
            System.out.println("2. Actualizar un producto existente");
            System.out.println("3. Eliminar un producto");
            System.out.println("4. Buscar un producto por id");
            System.out.println("5. Salir");
            System.out.println("========================");
            System.out.print("Ingrese una opción: ");
            opcion = sc.nextInt();

            switch (opcion) {
                case 1:
                    Producto prodNuevo = new Producto();
                    System.out.print("Ingrese el id del producto: ");
                    prodNuevo.setId(sc.nextInt());
                    System.out.print("Ingrese el nombre del producto: ");
                    prodNuevo.setNombre(sc.next());
                    System.out.print("Ingrese el precio del producto: ");
                    prodNuevo.setPrecio(sc.nextFloat());

                    producto.insert(prodNuevo);
                    System.out.println("Producto insertado exitosamente.");
                    break;
                case 2:
                    Producto prodExistente = new Producto();
                    System.out.print("Ingrese el id del producto a actualizar: ");
                    prodExistente.setId(sc.nextInt());
                    System.out.print("Ingrese el nuevo nombre del producto: ");
                    prodExistente.setNombre(sc.next());
                    System.out.print("Ingrese el nuevo precio del producto: ");
                    prodExistente.setPrecio(sc.nextFloat());

                    producto.update(prodExistente);
                    System.out.println("Producto actualizado exitosamente.");
                    break;
                case 3:
                    System.out.print("Ingrese el id del producto a eliminar: ");
                    int idEliminar = sc.nextInt();

                    producto.delete(idEliminar);
                    System.out.println("Producto eliminado exitosamente.");
                    break;
                case 4:
                    System.out.print("Ingrese el id del producto a buscar: ");
                    int idBuscar = sc.nextInt();

                    Producto prodBuscado = producto.read(idBuscar);
                    if (prodBuscado != null) {
                        System.out.println("El producto encontrado es: " + prodBuscado);
                    } else {
                        System.out.println("No se encontró ningún producto con ese id.");
                    }
                    break;
                case 5:
                    System.out.println("Saliendo del programa...");
                    break;
                default:
                    System.out.println("Opción inválida. Intente de nuevo.");
                    break;
            }
        } while (opcion != 5);

        sc.close();
    }
        
        
}
