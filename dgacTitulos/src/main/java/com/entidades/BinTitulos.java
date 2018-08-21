package com.entidades;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "cat_titulos")
public class BinTitulos implements Serializable{
	
	public BinTitulos() {
		
	}
	
	@Id
	@Column(name = "idreg_cat_titulos")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idreg_cat_titulos = 0;
	
	@Column(name = "titulo_original")
	private String titulo_original;
	
	@Column(name = "titulo_en_espa")
	private String titulo_en_espa;
	
	@Column(name = "anio")
	private int anio;
	
	@Column(name = "realizador")
	private String realizador;
	
	@Column(name = "pais")
	private String pais;
	
	@Column(name = "observaciones_cat_titulos")
	private String observaciones_cat_titulos;
	
	@Column(name = "productor")
	private String productor;
	
	@Column(name = "circa")
	private byte circa;
	
	@Column(name = "t_revisado")
	private int t_revisado;
	
	@Column(name = "posible_dup")
	private int posible_dup;

	public int getIdreg_cat_titulos() {
		return idreg_cat_titulos;
	}

	public void setIdreg_cat_titulos(int idreg_cat_titulos) {
		this.idreg_cat_titulos = idreg_cat_titulos;
	}

	public String getTitulo_original() {
		return titulo_original;
	}

	public void setTitulo_original(String titulo_original) {
		this.titulo_original = titulo_original;
	}

	public String getTitulo_en_espa() {
		return titulo_en_espa;
	}

	public void setTitulo_en_espa(String titulo_en_espa) {
		this.titulo_en_espa = titulo_en_espa;
	}

	public int getAnio() {
		return anio;
	}

	public void setAnio(int anio) {
		this.anio = anio;
	}

	public String getRealizador() {
		return realizador;
	}

	public void setRealizador(String realizador) {
		this.realizador = realizador;
	}

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	public String getObservaciones_cat_titulos() {
		return observaciones_cat_titulos;
	}

	public void setObservaciones_cat_titulos(String observaciones_cat_titulos) {
		this.observaciones_cat_titulos = observaciones_cat_titulos;
	}

	public String getProductor() {
		return productor;
	}

	public void setProductor(String productor) {
		this.productor = productor;
	}

	public byte getCirca() {
		return circa;
	}

	public void setCirca(byte circa) {
		this.circa = circa;
	}

	public int getT_revisado() {
		return t_revisado;
	}

	public void setT_revisado(int t_revisado) {
		this.t_revisado = t_revisado;
	}

	public int getPosible_dup() {
		return posible_dup;
	}

	public void setPosible_dup(int posible_dup) {
		this.posible_dup = posible_dup;
	}
	
	
	
}
