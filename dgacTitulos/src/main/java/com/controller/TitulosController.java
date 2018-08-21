package com.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.util.UriComponentsBuilder;

import com.entidades.BinTitulos;
import com.service.TitulosService;
import com.util.CustomErrorType;

@Controller
@RequestMapping(value = "/v1")
public class TitulosController {

	@Autowired
	private TitulosService _titulosService;
	
	
	// CREATE
		@RequestMapping(value = "/titulos", method = RequestMethod.POST, headers = "Accept=application/json")
		public ResponseEntity<?> createTitulo(@RequestBody BinTitulos titulo, UriComponentsBuilder ucBuilder) {

			_titulosService.InsertaNuevo(titulo);

			HttpHeaders headers = new HttpHeaders();
			headers.setLocation(ucBuilder.path("/v1/titulos/").buildAndExpand(titulo.getIdreg_cat_titulos()).toUri());
			return new ResponseEntity<String>(headers, HttpStatus.CREATED);
		}

		// GET ALL
		@RequestMapping(value = "/titulos", method = RequestMethod.GET, headers = "Accept=application/json")
		public ResponseEntity<List<BinTitulos>> obtenerTitulos() {

			List<BinTitulos> titulos = new ArrayList<BinTitulos>();
			titulos = _titulosService.BuscaTodosTitulos();
			return new ResponseEntity<List<BinTitulos>>(titulos, HttpStatus.OK);
		}
		// ACTUALIZAR
		@RequestMapping(value = "/titulos/{idreg_cat_titulos}", method = RequestMethod.PATCH)
		public ResponseEntity<?> actualizaTitulo(@PathVariable("idreg_cat_titulos") int idreg_cat_titulos,
				@RequestBody BinTitulos titulo) {

			// busca por id el titulo
			BinTitulos tituloActual = _titulosService.BuscarID(idreg_cat_titulos);

			if (tituloActual == null) {
				return new ResponseEntity(
						new CustomErrorType("No se puede actualizar los datos del TITULO Num:  " + idreg_cat_titulos),
						HttpStatus.NOT_FOUND);
			}

			// actualizar campos de equipo
			tituloActual.setIdreg_cat_titulos(titulo.getIdreg_cat_titulos());
			tituloActual.setTitulo_original(titulo.getTitulo_original());
			tituloActual.setTitulo_en_espa(titulo.getTitulo_en_espa());
			tituloActual.setAnio(titulo.getAnio());
			tituloActual.setRealizador(titulo.getRealizador());
			tituloActual.setPais(titulo.getPais());
			tituloActual.setObservaciones_cat_titulos(titulo.getObservaciones_cat_titulos());
			tituloActual.setProductor(titulo.getProductor());
			tituloActual.setCirca(titulo.getCirca());
			tituloActual.setT_revisado(titulo.getT_revisado());
			tituloActual.setPosible_dup(titulo.getPosible_dup());

			_titulosService.ActualizarTitulo(titulo);
			return new ResponseEntity<BinTitulos>(tituloActual, HttpStatus.OK);
		}
		

	// Buscar ID
	@RequestMapping(value = "/buscaID/{idreg_cat_titulos}", method = RequestMethod.GET, headers = "Accept=application/json")
	public ResponseEntity<?> BuscarID(@PathVariable int idreg_cat_titulos) {
		BinTitulos tituloId = _titulosService.BuscarID(idreg_cat_titulos);
		System.out.println("ID:");
		System.out.println(tituloId.getIdreg_cat_titulos());
		System.out.println("TITULO ORIGINAL:");
		System.out.println(tituloId.getTitulo_original());
		System.out.println("TITULO EN ESPANIOL:");
		System.out.println(tituloId.getTitulo_en_espa());
		System.out.println("REALIZADOR:");
		System.out.println(tituloId.getRealizador());
		System.out.println("AÑO:");
		System.out.println(tituloId.getAnio());
		System.out.println("PAIS:");
		System.out.println(tituloId.getPais());
		System.out.println("Observaciones:");
		System.out.println(tituloId.getObservaciones_cat_titulos());
		System.out.println("PRODUCTOR:");
		System.out.println(tituloId.getProductor());
		System.out.println("CIRCA:");
		System.out.println(tituloId.getCirca());
		System.out.println("REVISADO:");
		System.out.println(tituloId.getT_revisado());
		System.out.println("POSIBLE DUP:");
		System.out.println(tituloId.getPosible_dup());

		return new ResponseEntity<BinTitulos>(tituloId, HttpStatus.OK);

	}

	// GET titulo
	@RequestMapping(value = "/titulosBD/{tituloNuevo}", method = RequestMethod.GET, headers = "Accept=application/json")

	public ResponseEntity<List<BinTitulos>> buscaEnBD(@PathVariable String tituloNuevo) {
		
		List<BinTitulos> listaReturn = new ArrayList<BinTitulos>();
		listaReturn = _titulosService.buscaEnBD(procesaCadena(tituloNuevo));
		
		String lhs = tituloNuevo.toUpperCase();
		
		for (BinTitulos t : listaReturn) {
			String rhs = t.getTitulo_original().toUpperCase();
			
			
			if (levenshteinDistance (lhs, rhs) <= 9) {
			System.out.println("ID: " + t.getIdreg_cat_titulos());
			System.out.println("TITULO ORIGINAL: " + t.getTitulo_original());
			System.out.println("REALIZADOR: " + t.getRealizador());
			System.out.println("AÑO: " + t.getAnio());

			}
		}

		seleccionaId();

		return new ResponseEntity<List<BinTitulos>>(listaReturn, HttpStatus.OK);
	}
	
	private String procesaCadena(String tituloNuevo) { 
		// solicita titulo y procesa cadena

		// Scanner sc=new Scanner(System.in);
		// System.out.println("Ingresa un titulo: ");
		// String titulo=sc.nextLine();

		String[] splited = tituloNuevo.split("\\s+"); // separa la cadena por espacios

		int tamanio = splited.length; // guarda tamaño del arreglo

		int tamanioCadena;
		String cadenaMasGrande;
		int indiceMas = -1;
		int cadenaMax = 0;

		for (int i = 0; i < tamanio; i++) // busca el string mas largo en el arreglo
		{
			tamanioCadena = splited[i].length();

			if (tamanioCadena > cadenaMax) {
				indiceMas = i;
				cadenaMax = tamanioCadena;
			}
		}

		cadenaMasGrande = splited[indiceMas];
		// System.out.println("Cadena mas grande " + cadenaMasGrande);

		return cadenaMasGrande;

	}
	
	private void seleccionaId() {
		Scanner sc = new Scanner(System.in);

		System.out.println("Si el titulo que deseas ingresar se encuentra dentro de la lista anterior, "
				+ "escribe su Id para realizar un cambio en el titulo, si no se encuentra escribe 0 para realizar un nuevo ingreso: ");
		String id1 = sc.nextLine();
		int id = Integer.parseInt(id1);

		if (id >= 1) {
			// redirigir al controller de actualizar
			System.out.println("Actualizar id " + id);

			actualizarTitulo();

		} else {
			// mostrar los datos actuales y redirigir al controller de nuevo ingreso
			System.out.println("Nuevo ingreso ");
			insertaTitulo();

		}
		
	}

	// CREATE SIN REQUEST BODY
		@RequestMapping(value = "/tituloNuevo", method = RequestMethod.POST, headers = "Accept=application/json")
		public ResponseEntity<?> insertaTitulo() {
			
			Scanner sc=new Scanner(System.in);  

			BinTitulos titulo = new BinTitulos();
			
			System.out.println("TITULO ORIGINAL:");
			String tituloN=sc.nextLine(); 
			titulo.setTitulo_original(tituloN);
			
			System.out.println("TITULO EN ESPANIOL:");
			String espaN=sc.nextLine(); 
			titulo.setTitulo_en_espa(espaN);

			System.out.println("REALIZADOR:");
			String realizadN=sc.nextLine(); 
			titulo.setRealizador(realizadN);

			System.out.println("AÑO:");
			String anio=sc.nextLine(); 
			int anioN=Integer.parseInt(anio);
			if (anioN < 0 || anioN>2100 ) {
				System.out.println("error en el año");
				actualizarTitulo();
				return null;
			}else {
			titulo.setAnio(anioN); 
			}
			
			System.out.println("PAIS:");
			String pais=sc.nextLine(); 
			titulo.setPais(pais); 
			
			System.out.println("Observaciones:");
			String observacion=sc.nextLine(); 
			titulo.setObservaciones_cat_titulos(observacion); 

			System.out.println("PRODUCTOR:");
			String prod=sc.nextLine(); 
			titulo.setProductor(prod); 

//			System.out.println("CIRCA:");
//			String circa=sc.nextLine(); 
			titulo.setCirca((byte) 0); 

//			System.out.println("REVISADO:");
//			String revisado=sc.nextLine(); 
			titulo.setT_revisado(0); 

//			System.out.println("POSIBLE DUP:");
//			String dup=sc.nextLine(); 
			titulo.setPosible_dup(0);
			
			_titulosService.InsertaNuevo(titulo);
			return null; 		
		   
		}

	// ACTUALIZAR sin requestbody
	@RequestMapping(value = "/titulos/{idreg_cat_titulos2}", method = RequestMethod.PATCH)
	public ResponseEntity<?> actualizarTitulo() {

		Scanner sc = new Scanner(System.in);
		System.out.println("Vuelve a ingresar el id: ");
		String id = sc.nextLine();
		int idreg_cat_titulos = Integer.parseInt(id);
		// busca por id el titulo
		BinTitulos tituloActual = _titulosService.BuscarID(idreg_cat_titulos);

		if (tituloActual == null) {
			return new ResponseEntity(
					new CustomErrorType("No se puede actualizar los datos del TITULO Num:  " + idreg_cat_titulos),
					HttpStatus.NOT_FOUND);
		}

		// solicitar nuevos datos
		BinTitulos titulo = new BinTitulos();
		
		titulo.setIdreg_cat_titulos(idreg_cat_titulos);
		
		System.out.println("TITULO ORIGINAL:");
		String original = sc.nextLine();
		titulo.setTitulo_original(original);

		System.out.println("TITULO EN ESPANIOL:");
		String espa = sc.nextLine();
		titulo.setTitulo_en_espa(espa);

		System.out.println("REALIZADOR:");
		String realizad = sc.nextLine();
		titulo.setRealizador(realizad);

		System.out.println("AÑO:");
		String anio = sc.nextLine();
		int anioN = Integer.parseInt(anio);
		if (anioN < 0 || anioN>2100 ) {
			System.out.println("error en el año");
			actualizarTitulo();
			return null;
		}else {
		titulo.setAnio(anioN); 
		}

		System.out.println("PAIS:");
		String pais = sc.nextLine();
		titulo.setPais(pais);

		System.out.println("Observaciones:");
		String observacion = sc.nextLine();
		titulo.setObservaciones_cat_titulos(observacion);

		System.out.println("PRODUCTOR:");
		String prod = sc.nextLine();
		titulo.setProductor(prod);

		// System.out.println("CIRCA:");
		// String circa=sc.nextLine();
		titulo.setCirca((byte) 0);

		// System.out.println("REVISADO:");
		// String revisado=sc.nextLine();
		titulo.setT_revisado(0);

		// System.out.println("POSIBLE DUP:");
		// String dup=sc.nextLine();
		titulo.setPosible_dup(0);

		// actualizar campos
		tituloActual.setIdreg_cat_titulos(titulo.getIdreg_cat_titulos());
		tituloActual.setTitulo_original(titulo.getTitulo_original());
		tituloActual.setTitulo_en_espa(titulo.getTitulo_en_espa());
		tituloActual.setAnio(titulo.getAnio());
		tituloActual.setRealizador(titulo.getRealizador());
		tituloActual.setPais(titulo.getPais());
		tituloActual.setObservaciones_cat_titulos(titulo.getObservaciones_cat_titulos());
		tituloActual.setProductor(titulo.getProductor());
		tituloActual.setCirca(titulo.getCirca());
		tituloActual.setT_revisado(titulo.getT_revisado());
		tituloActual.setPosible_dup(titulo.getPosible_dup());

		_titulosService.ActualizarTitulo(titulo);
		return new ResponseEntity<BinTitulos>(tituloActual, HttpStatus.OK);
	}
	

	//me lo robe de https://en.wikibooks.org/wiki/Algorithm_Implementation/Strings/Levenshtein_distance#Java
	public static int levenshteinDistance (CharSequence lhs, CharSequence rhs) {                          
	    int len0 = lhs.length() + 1;                                                     
	    int len1 = rhs.length() + 1;                                                     
	                                                                                    
	    // the array of distances                                                       
	    int[] cost = new int[len0];                                                     
	    int[] newcost = new int[len0];                                                  
	                                                                                    
	    // initial cost of skipping prefix in String s0                                 
	    for (int i = 0; i < len0; i++) cost[i] = i;                                     
	                                                                                    
	    // dynamically computing the array of distances                                  
	                                                                                    
	    // transformation cost for each letter in s1                                    
	    for (int j = 1; j < len1; j++) {                                                
	        // initial cost of skipping prefix in String s1                             
	        newcost[0] = j;                                                             
	                                                                                    
	        // transformation cost for each letter in s0                                
	        for(int i = 1; i < len0; i++) {                                             
	            // matching current letters in both strings                             
	            int match = (lhs.charAt(i - 1) == rhs.charAt(j - 1)) ? 0 : 1;             
	                                                                                    
	            // computing cost for each transformation                               
	            int cost_replace = cost[i - 1] + match;                                 
	            int cost_insert  = cost[i] + 1;                                         
	            int cost_delete  = newcost[i - 1] + 1;                                  
	                                                                                    
	            // keep minimum cost                                                    
	            newcost[i] = Math.min(Math.min(cost_insert, cost_delete), cost_replace);
	        }                                                                           
	                                                                                    
	        // swap cost/newcost arrays                                                 
	        int[] swap = cost; cost = newcost; newcost = swap;                          
	    }                                                                               
	                                                                                    
	    // the distance is the cost for transforming all letters in both strings   
	   // System.out.println("levenstein" + cost[len0 - 1]); 
	    return cost[len0 - 1];                                                          
	}

}
