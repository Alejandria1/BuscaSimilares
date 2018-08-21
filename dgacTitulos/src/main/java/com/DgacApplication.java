package com;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;

import com.entidades.BinTitulos;
import com.service.TitulosService;

@SpringBootApplication(exclude=HibernateJpaAutoConfiguration.class)
public class DgacApplication {
	
//	@Autowired
//	private TitulosService titulosService;
	
	@Autowired
	private static TitulosService titulosService;
	
	public static void main(String[] args) {
		SpringApplication.run(DgacApplication.class, args);
		System.out.println("aplication running");
//		String lhs = "el gato negro"; //ingreso
//		String rhs = "gato negro, el";
//		
//		levenshteinDistance(lhs, rhs);
	}

//	//me lo robe de https://en.wikibooks.org/wiki/Algorithm_Implementation/Strings/Levenshtein_distance#Java
//	public static int levenshteinDistance (CharSequence lhs, CharSequence rhs) {                          
//	    int len0 = lhs.length() + 1;                                                     
//	    int len1 = rhs.length() + 1;                                                     
//	                                                                                    
//	    // the array of distances                                                       
//	    int[] cost = new int[len0];                                                     
//	    int[] newcost = new int[len0];                                                  
//	                                                                                    
//	    // initial cost of skipping prefix in String s0                                 
//	    for (int i = 0; i < len0; i++) cost[i] = i;                                     
//	                                                                                    
//	    // dynamically computing the array of distances                                  
//	                                                                                    
//	    // transformation cost for each letter in s1                                    
//	    for (int j = 1; j < len1; j++) {                                                
//	        // initial cost of skipping prefix in String s1                             
//	        newcost[0] = j;                                                             
//	                                                                                    
//	        // transformation cost for each letter in s0                                
//	        for(int i = 1; i < len0; i++) {                                             
//	            // matching current letters in both strings                             
//	            int match = (lhs.charAt(i - 1) == rhs.charAt(j - 1)) ? 0 : 1;             
//	                                                                                    
//	            // computing cost for each transformation                               
//	            int cost_replace = cost[i - 1] + match;                                 
//	            int cost_insert  = cost[i] + 1;                                         
//	            int cost_delete  = newcost[i - 1] + 1;                                  
//	                                                                                    
//	            // keep minimum cost                                                    
//	            newcost[i] = Math.min(Math.min(cost_insert, cost_delete), cost_replace);
//	        }                                                                           
//	                                                                                    
//	        // swap cost/newcost arrays                                                 
//	        int[] swap = cost; cost = newcost; newcost = swap;                          
//	    }                                                                               
//	                                                                                    
//	    // the distance is the cost for transforming all letters in both strings   
//	    System.out.println("distancia" + cost[len0 - 1]); 
//	    return cost[len0 - 1];                                                          
//	}
}


	
	


