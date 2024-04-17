package co.edu.unicauca.apirest.controllers;

import co.edu.unicauca.apirest.entities.ProductoEntity;
import co.edu.unicauca.apirest.repositories.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/productos")
public class ProductoController {
    @Autowired
    private ProductoRepository productoRepository;

    /*
    Get
     */
    @GetMapping
    public List<ProductoEntity> getAllProductos() {
        return productoRepository.findAll();
    }
    /*
    Get by Id
     */
    @GetMapping("/{id}")
    public ProductoEntity getProductoById(@PathVariable Long id) {
        return productoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No se encontró el producto con el ID " + id));
    }
    /*
    Post
     */
    @PostMapping
    public ProductoEntity postProducto(@RequestBody ProductoEntity producto) {
        return productoRepository.save(producto);
    }
    /*
    Put
     */
    @PutMapping("/{id}")
    public ProductoEntity putProducto(@PathVariable Long id, @RequestBody ProductoEntity detailsProducto) {
        ProductoEntity productoEncontrado = productoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No se encontró el producto con el ID " + id));

        productoEncontrado.setNombre(detailsProducto.getNombre());
        productoEncontrado.setPrecio(detailsProducto.getPrecio());

        return productoRepository.save(productoEncontrado);
    }
    /*
    Delete
     */
    @DeleteMapping("/{id}")
    public String deleteProducto(@PathVariable Long id) {
        ProductoEntity productoEncontrado = productoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No se encontró el producto con el ID " + id));

        productoRepository.delete(productoEncontrado);

        return "El producto con el ID " + id + " ha sido eliminado exitosamente";
    }
}
