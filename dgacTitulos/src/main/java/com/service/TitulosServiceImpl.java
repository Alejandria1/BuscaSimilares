package com.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.TitulosDao;
import com.entidades.BinTitulos;

@Service("titulosService")
@Transactional
public class TitulosServiceImpl implements TitulosService {

	@Autowired
	private TitulosDao _titulosDao;
	
	@Override
	public void InsertaNuevo(BinTitulos titulo) {
		_titulosDao.InsertaNuevo(titulo);
	}

	@Override
	public List<BinTitulos> BuscaTodosTitulos() {
		return _titulosDao.BuscaTodosTitulos();
	}

	@Override
	public BinTitulos BuscarUnqIdx(int anio, String realizador, String titulo_original) {
		return _titulosDao.BuscarUnqIdx(anio, realizador, titulo_original);
	}

	@Override
	public void ActualizarTitulo(BinTitulos titulo) {
		_titulosDao.ActualizarTitulo(titulo);		
	}

	@Override
	public BinTitulos BuscarID(int idreg_cat_titulos) {
		return _titulosDao.BuscarID(idreg_cat_titulos);
	}
	
	@Override
	public List<BinTitulos> buscaEnBD(String cadenaMasGrande){
		return _titulosDao.buscaEnBD(cadenaMasGrande);
	}
}
