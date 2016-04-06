/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tpv.service;

import com.tpv.exceptions.TpvException;
import com.tpv.modelo.Factura;
import com.tpv.modelo.FacturaDetalle;
import com.tpv.modelo.enums.FacturaEstadoEnum;
import com.tpv.principal.LineaTicketData;
import com.tpv.util.Connection;
import java.math.BigDecimal;
import javafx.beans.property.ListProperty;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import org.apache.log4j.Logger;

/**
 *
 * @author daniel
 */
public class FacturacionService  {
    Logger log = Logger.getLogger(FacturacionService.class);
    
    
    public Factura registrarFactura(Factura factura)throws TpvException{
        EntityManager em = Connection.getEm();
        EntityTransaction tx = em.getTransaction();
        try{
            if(!tx.isActive())
               tx.begin();     
            em.persist(factura);
            tx.commit();
            em.clear();        
        }catch(Exception e){
            String fullTraceStr=e.getMessage()+"\n";
            for(int i=0;i<=e.getStackTrace().length-1;i++){
                fullTraceStr+="Clase: "+e.getStackTrace()[i].getClassName()+"; "
                        +"Archivo: "+e.getStackTrace()[i].getFileName()+"; "
                        +"Mètodo: "+e.getStackTrace()[i].getMethodName()+"; "
                        +"Nro. Línea: "+e.getStackTrace()[i].getLineNumber()+"; "
                        +"\n";
            }
            log.error("Error grave: "+fullTraceStr);
            throw new TpvException(fullTraceStr);
        }
        return factura;
    }
    
    public Factura devolverFactura(Long id) throws TpvException{
        Factura factura = null;
        EntityManager em = Connection.getEm();
        EntityTransaction tx = em.getTransaction();
        if(!tx.isActive())
            tx.begin();

        tx.commit();
        em.clear();
        
        
        return factura;
    }
    
    public void agregarDetalleFactura(Long id,FacturaDetalle facturaDetalle) throws TpvException{
        Factura factura;
        EntityManager em = Connection.getEm();
        EntityTransaction tx = em.getTransaction();
        if(!tx.isActive())
            tx.begin();
        factura = em.find(Factura.class,id);
        facturaDetalle.setFactura(factura);
        factura.getDetalle().add(facturaDetalle);
        tx.commit();
        em.clear();
    }
    
    public void confirmarFactura(Long id)throws TpvException{
        Factura factura;
        EntityManager em = Connection.getEm();
        EntityTransaction tx = em.getTransaction();
        if(!tx.isActive())
            tx.begin();
        factura = em.find(Factura.class, id);
        factura.setEstado(FacturaEstadoEnum.CERRADA);
        tx.commit();
        em.clear();
        
    }
    
    public void cancelarFactura(Long id) throws TpvException{
        Factura factura;
        EntityManager em = Connection.getEm();
        EntityTransaction tx = em.getTransaction();
        if(!tx.isActive())
            tx.begin();
        factura = em.find(Factura.class, id);
        factura.setEstado(FacturaEstadoEnum.ANULADA);
        tx.commit();
        em.clear();
    }
            
    public void registrarCombos(Long id){
        Factura factura;
        EntityManager em = Connection.getEm();
        EntityTransaction tx = em.getTransaction();
        if(!tx.isActive())
            tx.begin();
        factura = em.find(Factura.class,id );
        factura.setEstado(FacturaEstadoEnum.CERRADA);
        tx.commit();
        em.clear();
        
    }
    
    
}
