/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tpv.modelo;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

/**
 *
 * @author daniel
 */

@Entity
@Table(name="proveedores_productos")
public class ProveedorProducto {

    /**
     * @return the id
     */
    public Id getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Id id) {
        this.id = id;
    }

    /**
     * @return the producto
     */
    public Producto getProducto() {
        return producto;
    }

    /**
     * @param producto the producto to set
     */
    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    /**
     * @return the proveedor
     */
    public Proveedor getProveedor() {
        return proveedor;
    }

    /**
     * @param proveedor the proveedor to set
     */
    public void setProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
    }

    @Embeddable
    public static class Id implements Serializable{
        @Column(name = "idProveedor")
        private Long idProveedor;
        
        @Column(name = "idPRODUCTOS")
        private Long idProducto;
        
        public Id(){
        }
        
        public Id(Long idProveedor,Long idProducto){
            this.idProducto = idProducto;
            this.idProveedor = idProveedor;
        }
        
        public int hashCode(){
            return Long.hashCode(idProducto)+Long.hashCode(idProveedor);
        }
    }

    @EmbeddedId
    private Id id = new Id();
    
    @MapsId("idPRODUCTOS")
    @ManyToOne
    @JoinColumn(name = "idPRODUCTOS", referencedColumnName = "idPRODUCTOS", nullable=false)
    private Producto producto;
    
    @MapsId("idPROVEEDOR")
    @ManyToOne
    @JoinColumn(name = "idProveedor", referencedColumnName = "idProveedor", nullable=false)
    private Proveedor proveedor;    
    
}
