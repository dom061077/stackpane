/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tpv.service;


import com.tpv.modelo.Cliente;
import com.tpv.util.Connection;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import org.apache.log4j.Logger;

/**
 *
 * @author daniel
 */
public class ClienteService {
    Logger logger = Logger.getLogger(ClienteService.class);
    public Cliente getClientePorCodYDni(int filtroCodigo){
        Cliente cliente = null;
        EntityManager em = Connection.getEm();
        EntityTransaction tx = em.getTransaction();
        if(!tx.isActive())
            tx.begin();
        Query q = em.createQuery("FROM Cliente c WHERE c.id = :id or c.dni = :dni").setParameter("id"
                ,filtroCodigo).setParameter("dni", filtroCodigo);
        
        
        try{
            cliente = (Cliente)q.getSingleResult();
        }catch(Exception e){
            
        }
        tx.commit();
        em.clear();
       return cliente;         
    }
}
