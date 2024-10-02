package com.example.productos;
import java.util.ArrayList;
import java.util.List;

public class Pedido {
    private String codigo;
    private String fechaPedido;
    private double total;
    private double iva;
    private List<Producto> productos;

    public Pedido(String codigo, String fechaPedido, double total, double iva) {
        this.codigo = codigo;
        this.fechaPedido = fechaPedido;
        this.total = total;
        this.iva = iva;
        this.productos = new ArrayList<>();
    }

    public void agregarProducto(Producto producto) {
        productos.add(producto);
    }

    public String getCodigo() {
        return codigo;
    }

    public String getFechaPedido() {
        return fechaPedido;
    }

    public double getTotal() {
        return total;
    }

    public double getIva() {
        return iva;
    }

    public List<Producto> getProductos() {
        return productos;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(codigo).append(",").append(fechaPedido).append(",").append(total).append(",").append(iva).append(",");
        for (Producto p : productos) {
            sb.append(p.getCodigo()).append("#").append(p.getNombre()).append("#").append(p.getPrecio()).append("#");
        }
        if (productos.size() > 0) {
            sb.deleteCharAt(sb.length() - 1);
        }
        return sb.toString();
    }

}

