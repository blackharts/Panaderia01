package Models;

/**
 *
 * @author kalbl
 */
public class Receta {
    
    private Integer id;
    private Integer producto_id; 
    private Producto producto;
    
    
public Receta(){
}

public Receta(Integer id,Integer producto_id){
    this.id=id;
    this.producto_id=producto_id;
    
}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getProducto_id() {
        return producto_id;
    }

    public void setProducto_id(Integer producto_id) {
        this.producto_id = producto_id;
    }
}
    
