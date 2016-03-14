/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tpv.principal;

import java.math.BigDecimal;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author daniel
 */
public class LineaTicketData {
    private IntegerProperty CodigoProducto;
    private StringProperty Descripcion;
    private IntegerProperty Cantidad;
    private ObjectProperty<BigDecimal> PrecioUnitario;
    private ObjectProperty<BigDecimal> SubTotal;
    
    public LineaTicketData(){
        
    }
    
    public LineaTicketData(int codigoProducto,String descripcion,int cantidad,BigDecimal precioUnitario){
        this.CodigoProducto = new SimpleIntegerProperty(codigoProducto);
        this.Descripcion = new SimpleStringProperty(descripcion);
        this.Cantidad = new SimpleIntegerProperty(cantidad);
        this.PrecioUnitario = new SimpleObjectProperty(precioUnitario);
        if(cantidad<0)
            cantidad = cantidad * -1;
        this.SubTotal = new SimpleObjectProperty(new BigDecimal(precioUnitario.doubleValue()*cantidad));
    }
    
    public int getCodigoProducto(){
        return codigoProductoProperty().get();
    }
    
    public IntegerProperty codigoProductoProperty(){
        if(CodigoProducto == null){
            CodigoProducto = new SimpleIntegerProperty();
        }
        return CodigoProducto;
    }
    
    
    public String getDescripcion(){
        return descripcionProperty().get();
    }
    
    public StringProperty descripcionProperty(){
        if(Descripcion == null){
            Descripcion = new SimpleStringProperty();
        }
        return Descripcion;
    }
    
    public int getCantidad(){
        return cantidadProperty().get();
    }
    
    public IntegerProperty cantidadProperty(){
        if(Cantidad == null){
            Cantidad = new SimpleIntegerProperty();
        }
        return Cantidad;
    }
    
    public BigDecimal getPrecioUnitario(){
        return precioUnitarioProperty().get();
    }
    
    public ObjectProperty<BigDecimal> precioUnitarioProperty(){
        if(PrecioUnitario == null){
            PrecioUnitario = new SimpleObjectProperty();
        }
        return PrecioUnitario;
    }
    
    public BigDecimal getSubTotal(){
        return subTotalProperty().get();
    }
    
    public ObjectProperty<BigDecimal> subTotalProperty(){
        if(SubTotal == null){
            SubTotal = new SimpleObjectProperty<BigDecimal>();
        }
        return SubTotal;
    }
    
    
}
