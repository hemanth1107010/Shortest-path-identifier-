package main;

import java.util.ArrayList;
import java.util.HashMap;

public class dijkstra {
    
    private static final int  uzunluk=81; // Toplam �ehir say�s�   
    private static final int ebeveynYok=-1;
    
    ArrayList  <Integer> gidilenMesafe = new ArrayList <Integer>();
    ArrayList  <Integer> gidilenYollar = new ArrayList <Integer>();
    ArrayList  <Integer> hedefsehir = new ArrayList <Integer>();
    ArrayList  <Integer> sehir = new ArrayList <Integer>();
    ArrayList  <Integer> gidilenYollarD = new ArrayList <Integer>();
    private int donusSehir;
    
  private void gidilenYollar(int mevcut , int ebeveynler []){
         if(mevcut == ebeveynYok){
             return;                 
         }
         gidilenYollar(ebeveynler[mevcut],ebeveynler);
         gidilenYollar.add(mevcut);
    }
  
  private void gidilenYollarD(int mevcut , int ebeveynler []){
      if(mevcut == ebeveynYok){
          return;                 
      }
      	gidilenYollarD(ebeveynler[mevcut],ebeveynler);
      	gidilenYollarD.add(mevcut);
 }
  
  private void duzenleyici(ArrayList  <Integer> gidilenYollar) {
    	 int  onceki=gidilenYollar.get(0);
   		 for(int i : gidilenYollar) {
   			if(i==onceki) {	
   				continue;
   			}
   			onceki=i;
   			sehir.add(i);
   		}
       }
             
  public int toplamMesafe(ArrayList <Integer> gidilenMesafe ){
        int sum=0;
        for(int i =0; i<gidilenMesafe.size(); i++){
            sum=sum+gidilenMesafe.get(i);
        }
        return sum;
    }
       
  private int minBulucu(ArrayList <Integer> secim,int mesafe[]){
    	  HashMap<Integer,Integer> map = new HashMap<Integer,Integer>();
          int minIndis=-1;
          for(int i=0; i<secim.size(); i++) {
        	  for(int j=0; j<mesafe.length; j++) {
        		  if(j==secim.get(i)) {      			  
        			  map.put(j, mesafe[j]);
        			  break;
        		  }
        	  }
          }                    
          int min=Integer.MAX_VALUE;               
          for(int i  : map.keySet()) {
        	  if(map.get(i)<min){
                  min=map.get(i);           
                  minIndis=i;
        	  }
          }                 
        return minIndis;
      }
      
  public int dijkstra(int komsulukMatrisi [][], int baslang�c, ArrayList <Integer>secim){
      for(int o=0; o<secim.size(); o++) {
    		   hedefsehir.add(secim.get(o));
    	   }
    	   if(secim.size()==0){
               return dijkstraDonus(komsulukMatrisi,donusSehir);
           }                    
           else{                    	
               if(secim.size()==1){        	
                  donusSehir=secim.get(0);
                    	}  	
           int mesafeler[] = new int[uzunluk]; // Kaynaktan hedeflere olan uzakl�k
           boolean durum[]= new boolean[uzunluk]; // Ziyaret edilme durumu               
           
      for(int i=0; i<uzunluk; i++){
               // Ba�lang��ta t�m d���mlere "sonsuz" ve "false" yani u�ranmam�� atad�k
               mesafeler[i]=Integer.MAX_VALUE;
               durum[i]=false;
    }
           
            mesafeler[baslang�c]=0; // Kayna��n kendine olan uzakl��� her zaman 0 olur
            int  ebeveynler[]  = new int[uzunluk]; // gidilen k�sa yolu tutacak
            ebeveynler[baslang�c]=ebeveynYok; // Ba�lang���n ebeveyni yok
                
      for(int j=0; j<uzunluk; j++){
          int enYak�nIndis=-1;
          int enK�saMesafe=Integer.MAX_VALUE;
        
        for(int k=0; k<uzunluk; k++){
            if(!durum[k] && mesafeler[k]<enK�saMesafe){
                enYak�nIndis=k;
                enK�saMesafe=mesafeler[k];
            }
        }
            durum[enYak�nIndis]=true; // Se�ilene u�rand�
        
        for(int k=0; k<uzunluk; k++){ 
        int mesafe = komsulukMatrisi[enYak�nIndis][k];   
            if(mesafe>0 &&((enK�saMesafe+mesafe)<mesafeler[k])){
                ebeveynler[k]=enYak�nIndis;
                mesafeler[k]=mesafe+enK�saMesafe;
            }
        }    
    }   
    
    int u=minBulucu(secim,mesafeler);
    this.gidilenMesafe.add(mesafeler[u]);
    gidilenYollar(u,ebeveynler);         
    secim.remove(secim.indexOf(u));        
                        return dijkstra(komsulukMatrisi,u,secim);
           }
       }                   
       
  private int dijkstraDonus(int komsulukMatrisi [][], int baslang�c){
    	 
           int mesafeler[] = new int[uzunluk]; // Kaynaktan hedeflere olan uzakl�k
           boolean durum[]= new boolean[uzunluk]; // Ziyaret edilme durumu
           
           		for(int i=0; i<uzunluk; i++){
           			// Ba�lang��ta t�m d���mlere "sonsuz" ve "false" yani u�ranmam�� atad�k
           			mesafeler[i]=Integer.MAX_VALUE;
           			durum[i]=false;
    }
           
           			mesafeler[baslang�c]=0; // Kayna��n kendine olan uzakl��� her zaman 0 olur
           			int  ebeveynler[]  = new int[uzunluk]; // gidilen k�sa yolu tutacak
           			ebeveynler[baslang�c]=ebeveynYok; // Ba�lang���n ebeveyni yok
                
            	for(int j=1; j<uzunluk; j++){
            		int enYak�nIndis=-1;
            		int enK�saMesafe=Integer.MAX_VALUE;
        
            	for(int k=0; k<uzunluk; k++){
            		if(!durum[k] && mesafeler[k]<enK�saMesafe){
            			enYak�nIndis=k;
            			enK�saMesafe=mesafeler[k];
            }
        }
            		durum[enYak�nIndis]=true; // Se�ilene u�rand�
        
            	for(int k=0; k<uzunluk; k++){
            
            		int mesafe = komsulukMatrisi[enYak�nIndis][k];
            
            		if(mesafe>0 &&((enK�saMesafe+mesafe)<mesafeler[k])){           
            			ebeveynler[k]=enYak�nIndis;
            			mesafeler[k]=mesafe+enK�saMesafe;
            }
        }    
    }   
   
            			int u=40;
            			this.gidilenMesafe.add(mesafeler[u]);
            			gidilenYollar(u,ebeveynler);
            			duzenleyici(gidilenYollar);
            			gidilenYollarD(u,ebeveynler);

   return -1;                     
     		}
       }

