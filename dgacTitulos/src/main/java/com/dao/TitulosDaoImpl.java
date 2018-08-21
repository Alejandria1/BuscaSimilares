package com.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.entidades.BinTitulos;

import com.dao.AbstractSession;

@Repository
@Transactional
public class TitulosDaoImpl extends AbstractSession implements TitulosDao {

	@Override
	public void InsertaNuevo(BinTitulos titulo) {
		getSession().persist(titulo);		
	}

	@Override
	public List<BinTitulos> BuscaTodosTitulos() {
		return getSession().createQuery("from BinTitulos").list();
	}

	@Override
	public BinTitulos BuscarUnqIdx(int anio, String realizador, String titulo_original) {
		return (BinTitulos) getSession().get(realizador, titulo_original);
	}

	@Override
	public void ActualizarTitulo(BinTitulos titulo) {
		getSession().update(titulo);	
	}

	@Override
	public BinTitulos BuscarID(int idreg_cat_titulos) {
		return (BinTitulos) getSession().get(BinTitulos.class, idreg_cat_titulos);
	}
	

	@Override
	public List<BinTitulos> buscaEnBD(String cadenaMasGrande){
		return getSession().createQuery("from BinTitulos where titulo_original like '%"+cadenaMasGrande+"%'").list();
	}
}
