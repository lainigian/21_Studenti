/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package laini._studenti;

/**
 *
 * @author User
 */
public class Ordinatore 
{
    
     //Ordine crescente alfabetico Studenti
    //funzione che scambia due studenti
    public static void  scambia(Studente v[],int posizione1, int posizione2)
    {
        Studente c;
        c=v[posizione1];
        v[posizione1]=v[posizione2];
        v[posizione2]=c;
    }
    
    //Selection sort Studenti
     //Ordina un array di Studenti in ordine alfabetico
    public static Studente[] selectionSortCrescente(Studente[] a)
    {
        Studente[] ordinato=new Studente[a.length];
        
        //Creo un vettore di copia
        //che sarà il vettore ordinato restituito
        for (int i=0;i<ordinato.length;i++)
            ordinato[i]=a[i];
        
        //Applico l'algoritmo 
        //selection sort al vettore di copia
        for (int i=0;i<ordinato.length-1;i++)
        {
            for (int j=i+1;j<ordinato.length;j++)
            {
                if ((ordinato[j].getCognome().compareToIgnoreCase(ordinato[i].getCognome())<0) || (ordinato[j].getCognome().compareToIgnoreCase(ordinato[i].getCognome())==0 && ordinato[j].getNome().compareToIgnoreCase(ordinato[i].getNome())<0))
                    scambia(ordinato,i,j);
            }
        }
        return ordinato;
    }
    
     //Selection sort Studenti
     //Ordina un array di Studenti in base alla media decrescente
    public static Studente[] ordinaStudentiPerMediaEAlfabetico(Studente[] a)
    {
        Studente[] ordinato=new Studente[a.length];
        
        //Creo un vettore di copia
        //che sarà il vettore ordinato restituito
        for (int i=0;i<ordinato.length;i++)
            ordinato[i]=a[i];
                
        //Applico l'algoritmo 
        //selection sort al vettore di copia
        for (int i=0;i<ordinato.length-1;i++)
        {
            for (int j=i+1;j<ordinato.length;j++)
            {
                if ((ordinato[j].calcolaMedia()>ordinato[i].calcolaMedia()) || (ordinato[j].calcolaMedia()==ordinato[i].calcolaMedia() && ((ordinato[j].getCognome().compareToIgnoreCase(ordinato[i].getCognome())<0) || (ordinato[j].getCognome().compareToIgnoreCase(ordinato[i].getCognome())==0 && ordinato[j].getNome().compareToIgnoreCase(ordinato[i].getNome())<0))))
                    scambia(ordinato,i,j);
            }
        }
        return ordinato;
    }
    
    
    
    public static void  scambia(String v[],int posizione1, int posizione2)
    {
        String c;
        c=v[posizione1];
        v[posizione1]=v[posizione2];
        v[posizione2]=c;
    }
    
    public static void  scambia(int v[],int posizione1, int posizione2)
    {
        int c;
        c=v[posizione1];
        v[posizione1]=v[posizione2];
        v[posizione2]=c;
    }
    
    public static String[] bubbleSortCrescente(String a[])
    {
        String[] ordinato=new String[a.length];
        boolean scambioAvvenuto=false;
        //Creo un vettore di copia
        //che sarà il vettore ordinato restituito
        for (int i=0;i<ordinato.length;i++)
            ordinato[i]=a[i];
        
        do
        {
            scambioAvvenuto=false;
            for (int i=0;i<ordinato.length-1;i++)
            {
                if(ordinato[i].compareToIgnoreCase(ordinato[i+1])>0)
                {
                    scambia(ordinato,i,i+1);
                    scambioAvvenuto=true;
                }
                    
            }
        }while(scambioAvvenuto);
        
        return ordinato;
    }
    public static String[] bubbleSortDecrescente(String a[])
    {
        String[] ordinato=new String[a.length];
        boolean scambioAvvenuto=false;
        //Creo un vettore di copia
        //che sarà il vettore ordinato restituito
        for (int i=0;i<ordinato.length;i++)
            ordinato[i]=a[i];
        
        do
        {
            scambioAvvenuto=false;
            for (int i=0;i<ordinato.length-1;i++)
            {
                if(ordinato[i].compareToIgnoreCase(ordinato[i+1])<0)
                {
                    scambia(ordinato,i,i+1);
                    scambioAvvenuto=true;
                }
                    
            }
        }while(scambioAvvenuto);
        
        return ordinato;
    }
    
    public static int[] bubbleSortCrescente(int a[])
    {
        int[] ordinato=new int[a.length];
        boolean scambioAvvenuto=false;
        //Creo un vettore di copia
        //che sarà il vettore ordinato restituito
        for (int i=0;i<ordinato.length;i++)
            ordinato[i]=a[i];
        
        do
        {
            scambioAvvenuto=false;
            for (int i=0;i<ordinato.length-1;i++)
            {
                if(ordinato[i]>ordinato[i+1])
                {
                    scambia(ordinato,i,i+1);
                    scambioAvvenuto=true;
                }
                    
            }
        }while(scambioAvvenuto);
        
        return ordinato;
    }
    
    public static int[] bubbleSortDecrescente(int a[])
    {
        int[] ordinato=new int[a.length];
        boolean scambioAvvenuto=false;
        //Creo un vettore di copia
        //che sarà il vettore ordinato restituito
        for (int i=0;i<ordinato.length;i++)
            ordinato[i]=a[i];
        
        do
        {
            scambioAvvenuto=false;
            for (int i=0;i<ordinato.length-1;i++)
            {
                if(ordinato[i]<ordinato[i+1])
                {
                    scambia(ordinato,i,i+1);
                    scambioAvvenuto=true;
                }
                    
            }
        }while(scambioAvvenuto);
        
        return ordinato;
    }
    
    
    //Ordina un array di Stringhe in ordine alfabetico
    public static String[] selectionSortCrescente(String[] a)
    {
        String[] ordinato=new String[a.length];
        
        //Creo un vettore di copia
        //che sarà il vettore ordinato restituito
        for (int i=0;i<ordinato.length;i++)
            ordinato[i]=a[i];
        
        //Applico l'algoritmo 
        //selection sort al vettore di copia
        for (int i=0;i<ordinato.length-1;i++)
        {
            for (int j=i+1;j<ordinato.length;j++)
            {
                if (ordinato[j].compareToIgnoreCase(ordinato[i])<0)
                    scambia(ordinato,i,j);
            }
        }
        return ordinato;
    }
    
    //Ordina un array di Stringhe in ordine alfabetico inverso
    public static String[] selectionSortDecrescente(String[] a)
    {
        String[] ordinato=new String[a.length];
        
        //Creo un vettore di copia
        //che sarà il vettore ordinato restituito
        for (int i=0;i<ordinato.length;i++)
            ordinato[i]=a[i];
        
        //Applico l'algoritmo 
        //selection sort al vettore di copia
        for (int i=0;i<ordinato.length-1;i++)
        {
            for (int j=i+1;j<ordinato.length;j++)
            {
                if (ordinato[j].compareToIgnoreCase(ordinato[i])>0)
                    scambia(ordinato,i,j);
            }
        }
        return ordinato;
    }
    
    
    public static int[] selectionSortCrescente(int[] a)
    {
        int[] ordinato=new int[a.length];
        
        //Creo un vettore di copia
        //che sarà il vettore ordinato restituito
        for (int i=0;i<ordinato.length;i++)
            ordinato[i]=a[i];
        
        //Applico l'algoritmo 
        //selection sort al vettore di copia
        for (int i=0;i<ordinato.length-1;i++)
        {
            for (int j=i+1;j<ordinato.length;j++)
            {
                if (ordinato[j]<ordinato[i])
                    scambia(ordinato,i,j);
            }
        }
        return ordinato;
    }
    
     public static int[] selectionSortDecrescente(int[] a)
    {
        int[] ordinato=new int[a.length];
        
        //Creo un vettore di copia
        //che sarà il vettore ordinato restituito
        for (int i=0;i<ordinato.length;i++)
            ordinato[i]=a[i];
        
        //Applico l'algoritmo 
        //selection sort al vettore di copia
        for (int i=0;i<ordinato.length-1;i++)
        {
            for (int j=i+1;j<ordinato.length;j++)
            {
                if (ordinato[j]>ordinato[i])
                    scambia(ordinato,i,j);
            }
        }
        return ordinato;
    }
    
    
    
    
}
