/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tpv.modelo;

import com.tpv.modelo.enums.FacturaEstadoEnum;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 *
 * @author daniel
 */

@Entity
@Table(name="facturas")
public class Factura {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "idFACTURAS")
    private Long id;
    
    @Column(name = "FECHAALTA")
    private java.util.Date fechaAlta;
    
    @Column(name = "TIPOCOMPROBANTE")
    private String tipoComprobante;
    
    @Column(name = "NUMEROCOMPROBANTE")
    private String numeroComprobante;
    
    @Column(name = "PREFIJOFISCAL")
    private Long prefijoFiscal;
    
    
    @Column(name = "TOTAL")
    private BigDecimal total;
    
    @Column(name = "NETO")
    private BigDecimal neto;
    
    @Column(name = "IVA")
    private BigDecimal iva;
    
    @Column(name = "IMPUESTOINTERNO")
    private BigDecimal impuestoInterno;
            
    @Column(name = "RETENCION")
    private BigDecimal retencion;
    
    @Column(name = "INTERESTARJETA")
    private BigDecimal interesTarjeta;
    
    @Column(name = "BONIFICATARJETA")
    private BigDecimal bonificaTarjeta;
    
    @Column(name = "IVABONIFTARJETA")
    private BigDecimal ivaBonificaTarjeta;
    
    @Column(name = "ANULADO", nullable = false, columnDefinition = "TINYINT(1)") 
    private boolean anulada;
    
    @Column(name = "CAJA", nullable = false, columnDefinition = "TINYINT(2)")
    private int caja;
    
    
    @Column(name = "ESTADO",nullable = false)
    @Enumerated(EnumType.STRING)
    private FacturaEstadoEnum estado;
    
    @OneToMany(cascade = CascadeType.ALL,mappedBy="factura", fetch = FetchType.EAGER)
    //@org.hibernate.annotations.IndexColumn(name = "BID_POSITION")
    private List<FacturaDetalle> detalle = new ArrayList<FacturaDetalle>();
    
    @OneToMany(cascade = CascadeType.ALL,mappedBy="factura", fetch = FetchType.EAGER)
    //@org.hibernate.annotations.IndexColumn(name = "BID_POSITION")
    private List<FacturaDetalleCombo> detalleCombos = new ArrayList<FacturaDetalleCombo>();    

    @Transient
    private List<FacturaDetalleCombo> detalleCombosAux = new ArrayList<FacturaDetalleCombo>();   
    
    @Transient
    private List<ProductoAgrupadoEnFactura> productosAgrupados = new ArrayList<ProductoAgrupadoEnFactura>();
    
    
    
    @OneToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL,mappedBy="factura")
    private List<FacturaFormaPagoDetalle> detallePagos = new ArrayList<FacturaFormaPagoDetalle>();


    
    @ManyToOne
    @JoinColumn(name = "idClientes", referencedColumnName = "idClientes", nullable=true)
    private Cliente cliente;
    
    @ManyToOne
    @JoinColumn(name = "idUSUARIOALTA", referencedColumnName = "idUSUARIOS",nullable=false)
    private Usuario usuario;
    
    @ManyToOne
    @JoinColumn(name = "idCHECKOUT", referencedColumnName = "idCHECKOUT",nullable=false)
    private Checkout checkout;
    
    @ManyToOne
    @JoinColumn(name = "idCONDICIONESIVA", referencedColumnName = "idCONDICIONESIVA", nullable=true)
    private CondicionIva condicionIva;
    
    
    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return the fechaAlta
     */
    public java.util.Date getFechaAlta() {
        return fechaAlta;
    }

    /**
     * @param fechaAlta the fechaAlta to set
     */
    public void setFechaAlta(java.util.Date fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    /**
     * @return the tipoComprobante
     */
    public String getTipoComprobante() {
        return tipoComprobante;
    }

    /**
     * @param tipoComprobante the tipoComprobante to set
     */
    public void setTipoComprobante(String tipoComprobante) {
        this.tipoComprobante = tipoComprobante;
    }

    /**
     * @return the numeroComprobante
     */
    public String getNumeroComprobante() {
        return numeroComprobante;
    }

    /**
     * @param numeroComprobante the numeroComprobante to set
     */
    public void setNumeroComprobante(String numeroComprobante) {
        this.numeroComprobante = numeroComprobante;
    }

    /**
     * @return the total
     */
    public BigDecimal getTotal() {
        return total;
    }

    /**
     * @param total the total to set
     */
    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    /**
     * @return the detalle
     */
    public List<FacturaDetalle> getDetalle() {
        return detalle;
    }

    /**
     * @return the cliente
     */
    public Cliente getCliente() {
        return cliente;
    }

    /**
     * @param cliente the cliente to set
     */
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    /**
     * @return the usuario
     */
    public Usuario getUsuario() {
        return usuario;
    }

    /**
     * @param usuario the usuario to set
     */
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    /**
     * @return the estado
     */
    public FacturaEstadoEnum getEstado() {
        return estado;
    }

    /**
     * @param estado the estado to set
     */
    public void setEstado(FacturaEstadoEnum estado) {
        this.estado = estado;
    }

    /**
     * @return the detallePagos
     */
    public List<FacturaFormaPagoDetalle> getDetallePagos() {
        return detallePagos;
    }

    /**
     * @return the prefijoFiscal
     */
    public Long getPrefijoFiscal() {
        return prefijoFiscal;
    }

    /**
     * @param prefijoFiscal the prefijoFiscal to set
     */
    public void setPrefijoFiscal(Long prefijoFiscal) {
        this.prefijoFiscal = prefijoFiscal;
    }

    /**
     * @return the caja
     */
    public int getCaja() {
        return caja;
    }

    /**
     * @param caja the caja to set
     */
    public void setCaja(int caja) {
        this.caja = caja;
    }

    /**
     * @return the impuestoInterno
     */
    public BigDecimal getImpuestoInterno() {
        return impuestoInterno;
    }

    /**
     * @param impuestoInterno the impuestoInterno to set
     */
    public void setImpuestoInterno(BigDecimal impuestoInterno) {
        this.impuestoInterno = impuestoInterno;
    }

    /**
     * @return the bonificaTarjeta
     */
    public BigDecimal getBonificaTarjeta() {
        return bonificaTarjeta;
    }

    /**
     * @param bonificaTarjeta the bonificaTarjeta to set
     */
    public void setBonificaTarjeta(BigDecimal bonificaTarjeta) {
        this.bonificaTarjeta = bonificaTarjeta;
    }

    /**
     * @return the ivaBonificaTarjeta
     */
    public BigDecimal getIvaBonificaTarjeta() {
        return ivaBonificaTarjeta;
    }

    /**
     * @param ivaBonificaTarjeta the ivaBonificaTarjeta to set
     */
    public void setIvaBonificaTarjeta(BigDecimal ivaBonificaTarjeta) {
        this.ivaBonificaTarjeta = ivaBonificaTarjeta;
    }

    /**
     * @return the checkout
     */
    public Checkout getCheckout() {
        return checkout;
    }

    /**
     * @param checkout the checkout to set
     */
    public void setCheckout(Checkout checkout) {
        this.checkout = checkout;
    }

    /**
     * @return the condicionIva
     */
    public CondicionIva getCondicionIva() {
        return condicionIva;
    }

    /**
     * @param condicionIva the condicionIva to set
     */
    public void setCondicionIva(CondicionIva condicionIva) {
        this.condicionIva = condicionIva;
    }

    /**
     * @return the neto
     */
    public BigDecimal getNeto() {
        return neto;
    }

    /**
     * @param neto the neto to set
     */
    public void setNeto(BigDecimal neto) {
        this.neto = neto;
    }

    /**
     * @return the iva
     */
    public BigDecimal getIva() {
        return iva;
    }

    /**
     * @param iva the iva to set
     */
    public void setIva(BigDecimal iva) {
        this.iva = iva;
    }

    /**
     * @return the detalleCombos
     */
    public List<FacturaDetalleCombo> getDetalleCombos() {
        return detalleCombos;
    }

    /**
     * @param detalleCombos the detalleCombos to set
     */
    public void setDetalleCombos(List<FacturaDetalleCombo> detalleCombos) {
        this.detalleCombos = detalleCombos;
    }

    /**
     * @return the detalleCombosAux
     */
    public List<FacturaDetalleCombo> getDetalleCombosAux() {
        return detalleCombosAux;
    }

    /**
     * @param detalleCombosAux the detalleCombosAux to set
     */
    public void setDetalleCombosAux(List<FacturaDetalleCombo> detalleCombosAux) {
        this.detalleCombosAux = detalleCombosAux;
    }

    public void recuperoCantidadFueraDeCombo(){
//        for(Iterator<FacturaDetalleCombo> itFactDetCombo=this.getDetalleCombosAux().iterator();
//                itFactDetCombo.hasNext();){
//            FacturaDetalleCombo fdc = itFactDetCombo.next();
//            for(Iterator<ComboGrupo> itCombo = fdc.getCombo().getCombosGrupo().iterator()
//                    ;itCombo.hasNext();){
//                ComboGrupo cg = itCombo.next();
//                int cantProductos = cg.getCombo().getCantidadCombosArmados()*cg.getCantidad();
//                int acumulador = 0;
//                int difCantidadFueradeCombo=0;
//                for(Iterator<ComboGrupoDetallePrecioProducto> itComboGDetPP=cg.getDetallePreciosProductos().iterator()
//                        ;itComboGDetPP.hasNext();){
//                    ComboGrupoDetallePrecioProducto comboGDetPP = itComboGDetPP.next();
//                    if(acumulador+comboGDetPP.getCantidad()<=cantProductos){
//                        acumulador+=comboGDetPP.getCantidad();
//                    }else{
//                        if(acumulador+comboGDetPP.getCantidad()==cantProductos){
//                            acumulador+=comboGDetPP.getCantidad();
//                        }else{
//                            if(acumulador==cantProductos){
//                                difCantidadFueradeCombo = comboGDetPP.getCantidad();
//                            }else{
//                                difCantidadFueradeCombo = acumulador+comboGDetPP.getCantidad()-cantProductos;
//                            }
//                            comboGDetPP.getFactDetalle().incrementarCantidadAuxCombo(difCantidadFueradeCombo);
//                        }
//                    }
//                }
//            }
//        }
    }
    
    @Transient
    public BigDecimal getBonificacionCombosAux(){
        BigDecimal totalBonificado = BigDecimal.ZERO;
        for(Iterator<FacturaDetalleCombo> iterator = getDetalleCombosAux().iterator();iterator.hasNext();){
            FacturaDetalleCombo fdc = iterator.next();
            totalBonificado.add(fdc.getBonificacionCalculada());
        }
        return totalBonificado;
    }
    
    @Transient
    public BigDecimal getBonificacionCombos(){
        BigDecimal totalBonificado = BigDecimal.ZERO;
        for(Iterator<FacturaDetalleCombo> iterator = getDetalleCombos().iterator();iterator.hasNext();){
            FacturaDetalleCombo fdc = iterator.next();
            totalBonificado.add(fdc.getBonificacion());
        }
        return totalBonificado;

    }

    private void addProductoAgrupadoEnFactura(FacturaDetalle fd){
        for(Iterator<ProductoAgrupadoEnFactura> it = getProductosAgrupados().iterator();it.hasNext();){
            ProductoAgrupadoEnFactura paf = it.next();
            if(paf.getProducto().equals(fd.getProducto())){
                paf.incCantidad(fd.getCantidad().intValue());
                return;
            }
        }
        ProductoAgrupadoEnFactura paf = new ProductoAgrupadoEnFactura();
        paf.setPrecioUnitario(fd.getPrecioUnitario());
        paf.setProducto(fd.getProducto());
        paf.setCantidad(fd.getCantidad().intValue());
        getProductosAgrupados().add(paf);
    }
    
    public void agruparProductosEnFactura(){
        for(Iterator<FacturaDetalle> it = getDetalle().iterator();it.hasNext(); ){
            FacturaDetalle fd = it.next();
            addProductoAgrupadoEnFactura(fd);
        }
    }

    /**
     * @return the productosAgrupados
     */
    public List<ProductoAgrupadoEnFactura> getProductosAgrupados() {
        return productosAgrupados;
    }

    /**
     * @param productosAgrupados the productosAgrupados to set
     */
    public void setProductosAgrupados(List<ProductoAgrupadoEnFactura> productosAgrupados) {
        this.productosAgrupados = productosAgrupados;
    }
    
}
