package com.example.productos;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class PedidoApp {
    private static final String ARCHIVO_PEDIDOS = "src/main/resources/pedidos.txt";

    public static void guardarPedidos(List<Pedido> pedidos) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ARCHIVO_PEDIDOS))) {
            for (Pedido pedido : pedidos) {
                writer.write("Código: " + pedido.getCodigo() + "\n");
                writer.write("Fecha: " + pedido.getFechaPedido() + "\n");
                writer.write("Total: " + pedido.getTotal() + "\n");
                writer.write("IVA: " + pedido.getIva() + "\n");
                writer.write("Productos:\n");
                for (Producto producto : pedido.getProductos()) {
                    writer.write("\t" + producto.getCodigo() + ", " + producto.getNombre() + ", " + producto.getPrecio() + "\n");
                }
                writer.write("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<Pedido> leerPedidos() {
        List<Pedido> pedidos = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(ARCHIVO_PEDIDOS))) {
            String line;
            Pedido pedido = null;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith("Código: ")) {
                    if (pedido != null) {
                        pedidos.add(pedido);
                    }
                    String codigo = line.substring(8);
                    String fecha = reader.readLine().substring(7);
                    double total = Double.parseDouble(reader.readLine().substring(7));
                    double iva = Double.parseDouble(reader.readLine().substring(5));
                    pedido = new Pedido(codigo, fecha, total, iva);
                    reader.readLine();
                } else if (line.startsWith("\t")) {
                    String[] partesProducto = line.trim().split(", ");
                    String codigoProducto = partesProducto[0];
                    String nombreProducto = partesProducto[1];
                    double precioProducto = Double.parseDouble(partesProducto[2]);
                    pedido.agregarProducto(new Producto(codigoProducto, nombreProducto, precioProducto));
                }
            }

            if (pedido != null) {
                pedidos.add(pedido);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return pedidos;
    }

    public static void main(String[] args) {
       //Creo un nuevo pedido con 3 productos
        Pedido pedido1 = new Pedido("001", "2024-09-28", 130.00, 19.00);
        pedido1.agregarProducto(new Producto("P001", "Zapatos Brahama", 200.000));
        pedido1.agregarProducto(new Producto("P002", "Corset Dama", 100.000));
        pedido1.agregarProducto(new Producto("P003", "Gafas de Lujo", 90.000));

        List<Pedido> pedidos = new ArrayList<>();
        pedidos.add(pedido1);

        guardarPedidos(pedidos);
        List<Pedido> pedidosLeidos = leerPedidos();
        for (Pedido p : pedidosLeidos) {
            System.out.println(p.getCodigo() + ", " + p.getFechaPedido() + ", " + p.getTotal() + ", " + p.getIva());
            for (Producto producto : p.getProductos()) {
                System.out.println("\t" + producto.getCodigo() + ", " + producto.getNombre() + ", " + producto.getPrecio());
            }
        }
    }
}
