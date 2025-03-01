/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;

/**
 *
 * @author Alexis Rauda
 */
public class Articulo {

    private int idArticulo;
    private int categoriaId;
    private int codigo;
    private String npmbre;
    private double precioventa;
    private int stock;
    private String descripcion;
    private boolean estado;

    public Articulo() {
    }

    public Articulo(int idArticulo, int categoriaId, int codigo, String npmbre, double precioventa, int stock, String descripcion, boolean estado) {
        this.idArticulo = idArticulo;
        this.categoriaId = categoriaId;
        this.codigo = codigo;
        this.npmbre = npmbre;
        this.precioventa = precioventa;
        this.stock = stock;
        this.descripcion = descripcion;
        this.estado = estado;
    }

    public int getIdArticulo() {
        return idArticulo;
    }

    public void setIdArticulo(int idArticulo) {
        this.idArticulo = idArticulo;
    }

    public int getCategoriaId() {
        return categoriaId;
    }

    public void setCategoriaId(int categoriaId) {
        this.categoriaId = categoriaId;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNpmbre() {
        return npmbre;
    }

    public void setNpmbre(String npmbre) {
        this.npmbre = npmbre;
    }

    public double getPrecioventa() {
        return precioventa;
    }

    public void setPrecioventa(double precioventa) {
        this.precioventa = precioventa;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "Articulo{" + "idArticulo=" + idArticulo + ", categoriaId=" + categoriaId + ", codigo=" + codigo + ", npmbre=" + npmbre + ", precioventa=" + precioventa + ", stock=" + stock + ", descripcion=" + descripcion + ", estado=" + estado + '}';
    }
}