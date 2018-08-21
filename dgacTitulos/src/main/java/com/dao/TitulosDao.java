package com.dao;

import java.util.List;

import com.entidades.BinTitulos;

public interface TitulosDao {
	
	//inserta nuevo titulo para el caso de no existir
	void InsertaNuevo (BinTitulos titulo); 

	//muestra todos los titulos existentes
	List<BinTitulos> BuscaTodosTitulos();
	
	//busca los titulos similares por indice de unicidad 
	BinTitulos BuscarUnqIdx (int anio, String realizador, String titulo_original);
	
	//buscar por id registro
	BinTitulos BuscarID (int idreg_cat_titulos);
	
	//actualiza el campo
	void ActualizarTitulo(BinTitulos titulo);
	
	//busca en la db
	List<BinTitulos> buscaEnBD(String cadenaMasGrande);
	
	
}
