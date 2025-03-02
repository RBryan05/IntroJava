/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package datosDAO;

import database.Conexion;
import datos.interfaces.CRUDPaginadoInterface;
import entidades.Articulo;
import entidades.Categoria;
import java.util.List;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author Alexis Rauda
 */
public class ArticuloDAO implements CRUDPaginadoInterface<Articulo> {

    private final Conexion conectar;
    private PreparedStatement ps;
    private ResultSet rs;
    private boolean resp;

    public ArticuloDAO() {
        conectar = Conexion.getInstance();
    }

    @Override
    public List<Articulo> getAll(String list, int totalPorPagina, int numPagina) {
        List<Articulo> registros = new ArrayList();
        try {
            ps = conectar.conectar().prepareStatement(
                    "SELECT "
                    + "a.idArticulo,"
                    + "a.categoria_id,"
                    + "c.nombre as categoria_nombre,"
                    + "a.codigo,"
                    + "a.nombre,"
                    + "a.precio_venta,"
                    + "a.stock,"
                    + "a.descripcion,"
                    + "a.imagen,"
                    + "a.estado"
                    + "FROM  articulo a"
                    + "inner join categoria c"
                    + "ON a.categoria_id = c.id"
                    + "Where a.nombre Like ?"
                    + "Order by a.idArticulo ASC"
                    + "Limit ?, ?"
            );
            ps.setString(1, "%" + list + "%");
            ps.setInt(2, (numPagina - 1) * totalPorPagina);
            ps.setInt(2, totalPorPagina);

            rs = ps.executeQuery();
            while (rs.next()) {
                registros.add(new Articulo(
                        rs.getInt(1), // idArticulo
                        rs.getInt(2), //categoria_id
                        rs.getString(3), //Codigo
                        rs.getString(4), //categoria nombre
                        rs.getDouble(5), //precioVenta
                        rs.getInt(6), //stock
                        rs.getString(7),// descipcion
                        rs.getString(8),// imagen
                        rs.getBoolean(9)//estado
                ));
            }
            ps.close();
            rs.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        } finally {
            ps = null;
            rs = null;
            conectar.desconectar();
        }
        return registros;
    }

    @Override
    public boolean insert(Articulo object) {
        resp = false;
        try {
            ps = conectar.conectar().prepareStatement("INSERT INTO categoria "
                    + "(categoria_id,"
                    + "codigo,"
                    + "nombre,"
                    + "precio_venta,"
                    + "stock,"
                    + "descripcion"
                    + "imagen"
                    + "estado) "
                    + "VALUES"
                    + "(?,?,?,?,?,?,?,1)");
            ps.setInt(1, object.getCategoriaId());
            ps.setString(2, object.getCodigo());
            ps.setString(3, object.getNombre());
            ps.setDouble(4, object.getPrecioventa());
            ps.setInt(5, object.getStock());
            ps.setString(6, object.getDescripcion());
            ps.setString(7, object.getImagen());
            if (ps.executeUpdate() > 0) {
                resp = true;
                ps.close();
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Tenemos un problema al insertar el dato " + e.getMessage());
        } finally {
            ps = null;
            conectar.desconectar();
        }
        return resp;
    }

    @Override
    public boolean update(Articulo object) {
        resp = false;
        try {
            ps = conectar.conectar().prepareStatement("Update categoria SET "
                    + "categoria_id=?, "
                    + "codigo=?"
                    + "nombre=?"
                    + "precio_venta=?"
                    + "stock=?"
                    + "desscripocion=?"
                    + "imagen=?"
                    + "where id= ?");
            ps.setInt(1, object.getCategoriaId());
            ps.setString(2, object.getCodigo());
            ps.setString(3, object.getNombre());
            ps.setDouble(4, object.getPrecioventa());
            ps.setInt(5, object.getStock());
            ps.setString(6, object.getDescripcion());
            ps.setString(7, object.getImagen());
            ps.setInt(8, object.getIdArticulo());
            if (ps.executeUpdate() > 0) {
                resp = true;
                ps.close();
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        } finally {
            ps = null;
            conectar.desconectar();
        }
        return resp;
    }

    @Override
    public boolean onVariable(int id) {
        try {
            ps = conectar.conectar().prepareStatement(
                    "UPDATE articulo SET estado=1 WHERE id= ?"
            );
            ps.setInt(1, id);  // Establece el ID en el parámetro de la consulta

            // Ejecuta la actualización y verifica si se modificaron filas
            if (ps.executeUpdate() > 0) {
                resp = true;  // Si la actualización fue exitosa, cambia el estado de la variable
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());  // Muestra el error en caso de fallo
        } finally {
            try {
                if (ps != null) {
                    ps.close();  // Asegúrate de cerrar el PreparedStatement después de su uso
                }
            } catch (SQLException e) {
                // Manejo de errores al cerrar el PreparedStatement (opcional)
                JOptionPane.showMessageDialog(null, e.getMessage());
            }
            conectar.desconectar();  // Desconecta la base de datos
        }
        return resp;  // Retorna el resultado de la operación
    }

    @Override
    public boolean offVariable(int id) {
        resp = false;
        try {
            // Corrige la sintaxis SQL eliminando la coma extra después de 'estado=0'
            ps = conectar.conectar().prepareStatement(
                    "UPDATE articulo SET estado=0 WHERE id= ?"
            );
            ps.setInt(1, id);  // Establece el ID en el parámetro de la consulta

            // Ejecuta la actualización y verifica si se modificaron filas
            if (ps.executeUpdate() > 0) {
                resp = true;  // Si la actualización fue exitosa, cambia el estado de la variable
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());  // Muestra el error en caso de fallo
        } finally {
            try {
                if (ps != null) {
                    ps.close();  // Asegúrate de cerrar el PreparedStatement después de su uso
                }
            } catch (SQLException e) {
                // Manejo de errores al cerrar el PreparedStatement (opcional)
                JOptionPane.showMessageDialog(null, e.getMessage());
            }
            conectar.desconectar();  // Desconecta la base de datos
        }
        return resp;  // Retorna el resultado de la operación
    }

    @Override
    public boolean exist(String text) {
        resp = false;
        try {
            // Especificamos un ResultSet de tipo scrollable
            ps = conectar.conectar().prepareStatement("select nombre from articulo where id = ?",
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
            ps.setString(1, text);
            rs = ps.executeQuery();

            // Verificamos si el resultado tiene al menos una fila
            if (rs.next()) {
                resp = true;
            }

            ps.close();
            rs.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        } finally {
            ps = null;
            rs = null;
            conectar.desconectar();
        }
        return resp;
    }

    @Override
    public int total() {
        int totalRegistro = 0;
        try {
            // Especificamos un ResultSet de tipo scrollable
            ps = conectar.conectar().prepareStatement("select count(id) from articulo",
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
            rs = ps.executeQuery();

            // Nos aseguramos de que haya al menos una fila para obtener el resultado
            if (rs.next()) {
                totalRegistro = rs.getInt(1); // Obtener el conteo de registros
            }

            ps.close();
            rs.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        } finally {
            ps = null;
            rs = null;
            conectar.desconectar();
        }
        return totalRegistro;
    }

    @Override
    public int getID() {
        int idMayor = -1; // Valor por defecto en caso de que no haya registros

        try {
            ps = conectar.conectar().prepareStatement(
                    "SELECT id FROM articulo ORDER BY id DESC LIMIT 1",
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY
            );

            rs = ps.executeQuery();
            if (rs.next()) {
                idMayor = rs.getInt("id"); // Obtiene el ID más alto
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e.getMessage());
            }
            conectar.desconectar();
        }
        return idMayor;
    }
}
