/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package laini._studenti;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.time.LocalDate;
import utility.Ordinatore;

/**
 *
 * @author User
 */
public class Classe implements Serializable
{
    private String indirizzo;
    private String sezione;
    private int anno;
    private int nStudentiPresenti;
    private final int N_MAX_STUDENTI=50;
    private Studente[] elencoStudenti;
    
    public Classe(String indirizzo, String sezione, int anno)
    {
        setIndirizzo(indirizzo);
        setSezione(sezione);
        setAnno(anno);
        elencoStudenti=new Studente[N_MAX_STUDENTI];
        nStudentiPresenti=0;
    }

    public String getIndirizzo() {
        return indirizzo;
    }

    public void setIndirizzo(String indirizzo) {
        this.indirizzo = indirizzo;
    }

    public String getSezione() {
        return sezione;
    }

    public void setSezione(String sezione) {
        this.sezione = sezione;
    }

    public int getAnno() {
        return anno;
    }

    public void setAnno(int anno) {
        this.anno = anno;
    }

    public int getNStudentiPresenti() {
        return nStudentiPresenti;
    }

    public int getN_MAX_STUDENTI() {
        return N_MAX_STUDENTI;
    }
    
    //-1 --> Massimo numero di studenti raggiunto
    //0 --> OK
    //se lo studente viene aggiunto alla classe possiede dei voti, tali voti vengono "persi"
    public int aggiungiStudente(Studente studente)
    {
        if (nStudentiPresenti>N_MAX_STUDENTI)
            return -1;      //LA CLASSE HA RAGGIUNTO IL NUMERO MASSIMO DI 
        elencoStudenti[nStudentiPresenti]=new Studente(studente.getMatricola(),studente.getCognome(),studente.getNome());
        nStudentiPresenti++;
        return 0;
    }
    
    //metodo che consente di modificare i dati anagrafici di uno studente della classe
    // -1 --> studente non presente
    //  0 --> ok
    public int modificaDatiAnagrficiStudente(long matricola, String cognome, String nome)
    {
        int posizioneStudenteArray;
        posizioneStudenteArray=posizioneStudente(matricola);
        if (posizioneStudenteArray==-1)
            return -1;
        elencoStudenti[posizioneStudenteArray].setCognome(cognome);
        elencoStudenti[posizioneStudenteArray].setNome(nome);
        return 0;      
    }
    
    //0--> ok
    //-1--> Studente non trovato        
    public int rimuoviStudente(long matricola)
    {
        int posizioneStudente;
        for (int i=0;i<nStudentiPresenti;i++)   //cerco lo studente
        {
            if (elencoStudenti[i]!=null)
            {
                if (elencoStudenti[i].getMatricola()==matricola)
                {
                    posizioneStudente=i;
                    aggiornaPosizioniStudenti(i);
                    return 0;
                }
                    
            }
        }
        return -1;
    }
    
    
    private void aggiornaPosizioniStudenti(int posizione)
    {
        for (int i=posizione;i<nStudentiPresenti-1;i++)
        {
            elencoStudenti[i]=elencoStudenti[i+1];
        }
        elencoStudenti[nStudentiPresenti-1]=null;      //Affinchè se elimino l'ultimo studente, in ultima posizione vi sia null
        nStudentiPresenti--;        
    }
    
    // null --> studente non presente
    public Studente getStudente(long matricola)
    {
        Studente s;
        for (int i=0;i<nStudentiPresenti;i++)
        {
            if (elencoStudenti[i]!=null)    //per sicurezza
            {
                s=elencoStudenti[i];
                if (s.getMatricola()==matricola)
                    return new Studente(s);
            }
        }
        return null;
    }
    
    //-1 --> Studente non presente
    //-2 --> Impossibile aggiungere voti , numero massimo voti raggiunto
    // 0 --> OK
    public int aggiungiVoto(long matricolaStudente, Voto voto)
    {
        Studente s;
        int esitoOperazione;
        
        for (int i=0;i<nStudentiPresenti;i++)
        {
            if (elencoStudenti[i]!=null)
            {
                if (elencoStudenti[i].getMatricola()==matricolaStudente)
                {
                    s=elencoStudenti[i];
                    esitoOperazione=s.aggiungiVoto(voto);
                    if (esitoOperazione==-1)
                        return -2;      //numero max voti raggiunto per quello studente
                    else
                        return 0;
                }                
            }
        }
        return -1;  //studente non presente
    }
    
    
    //metodo privato di utilità che restituisce la posizione dello studente nell' elencoStudenti
    //-1 --> studente non presente;
    private int posizioneStudente(long matricola)
    {
        Studente s;
        
        for (int i=0;i<nStudentiPresenti;i++)
        {
            if (elencoStudenti[i]!=null)
            {
                s=elencoStudenti[i];
                if (s.getMatricola()==matricola)
                    return i;
            }
        }
        return -1;  //studente non presente
    }
    
    //-1--> Studente non presente
    //-2 -> voto non presente
    // 0--> ok
    public int eliminaVoto(long matricola, LocalDate dataVoto, String materia)
    {
        int posizioneStudente;
        Voto v;
        int esitoOperazione;
        posizioneStudente=posizioneStudente(matricola);
        if (posizioneStudente==-1)
            return -1;  //Studente non presente
        
        for (int i=0;i<elencoStudenti[posizioneStudente].getNVotiPresenti();i++)  //faccio passare tutti i voti dello studente
        {
            v=elencoStudenti[posizioneStudente].getVoto(i);
            if ((v.getData().compareTo(dataVoto)==0) && (v.getMateria().compareToIgnoreCase(materia)==0))  //individuo il voro da eliminare
            {
                esitoOperazione=elencoStudenti[i].eliminaVoto(v.getIdVoto());
                if (esitoOperazione==-1)
                    return -2;  //Voto non presente (impossibile)
                else
                    return 0;
               
            }
        }
        return -2;  //voto non trovato
    }
    
    
    //Restituisce il voto a partire dal codice dello studente, data e materia (non utilizzato in realtà nella versione chiesta agli studenti)
    //-1 --> studente non trovato
    //-2 --> voto non trovato
    //0 --> ok
    //uso un parametro voto così posso restituire un int che distingue i 
    //casi -1 e -2
    public int getVoto(long matricolaStudente, LocalDate data, String materia, Voto voto)
    {
        Studente s;
        Voto v;
        
        s=getStudente(matricolaStudente);
        if (s==null)
            return -1;    //studente non trovato
        
        for (int i=0;i<s.getNVotiPresenti();i++)
        {
            v=s.getVoto(i);
            if (v.getMateria().compareToIgnoreCase(materia)==0 && v.getData().isEqual(data))
            {
                //devo modificare gli attributi del voto passato come parametro (vedi stack e e heap)
                LocalDate dataV=v.getData();
                voto.setData(dataV.getDayOfMonth(), dataV.getMonthValue(), dataV.getYear());
                voto.setMateria(v.getMateria());
                voto.setTipologia(v.getTipologia());
                voto.setValore(v.getValore());
                return 0;   //ok       
            }
                
        }
        return -2;    //voto non trovato;
    }
    
    
    public String mostraVoti(long matricolaStudente)
    {
        Studente studente;
        String s;
        
        studente=getStudente(matricolaStudente);
        if (studente==null)
            s="Studente non presente";
        else
            s=studente.mostraVoti();
        
        return s;
        
    }
    
    //-1 --> studente non presente
    //-2 --> nessun voto in quella materia
    // media--> ok
    public float calcolaMedia(long matricolaStudente, String materia)
    {
        Studente studente;
        float media;
        
        studente=getStudente(matricolaStudente);
        if (studente==null)
            return -1;  //studente non presente;
        
        media=studente.calcolaMedia(materia);
        if (media==-1)
            return -2;   //nessun voto in quella materia
        return media;
    }
    
    //-1 --> studente non presente
    //-2 --> nessun voto 
    // media--> ok
    public float calcolaMedia(long matricolaStudente)
    {
        Studente studente;
        float media;
        
        studente=getStudente(matricolaStudente);
        if (studente==null)
            return -1;  //studente non presente;
               
        media=studente.calcolaMedia();
        if (media==-1)
            return -2;   //nessun voto
        return media;
    }
    
    //Media complessiva di tutti i voti degli studenti della classe in una materia
    //-1--> nessuno studente presente
    //-2 --> nessun voto presente
    
    public float calcolaMediaClasseMateria(String materia)
    {
        Studente s;
        float sommaVoti=0;
        int numeroVoti=0;
        float media=0;
        Voto v;
        
        
        if (nStudentiPresenti==0)
            return -1;  //nssuno studente presente
        
        for (int i=0;i<nStudentiPresenti;i++)
        {
            s=elencoStudenti[i];
            for (int j=0;j<s.getNVotiPresenti();j++)
            {
                v=elencoStudenti[i].getVoto(j);
                if (v.getMateria().compareTo(materia)==0)
                {
                    sommaVoti+=v.getValore();
                    numeroVoti++;
                }
            }
                        
        }
        
        if (numeroVoti==0)
            return -2;
        
        media=sommaVoti/numeroVoti;
        return media;     
    }
    
    public String elencoStudenti()
    {
        String s="";
        Studente studente;
        if (nStudentiPresenti==0)
            s+="Nessuno studente presente";
        else
        {
            for (int i=0;i<nStudentiPresenti;i++)
            {
                studente=elencoStudenti[i];
                s+=studente.toString()+"\n";
            }
        }
        return s;
            
    }
    
    public Studente[] ordineAlfabeticoStudenti()
    {
        Studente[] elencoStudentiAlfabetico=new Studente[getNStudentiPresenti()];
        for (int i=0;i<getNStudentiPresenti();i++)
        {
            elencoStudentiAlfabetico[i]=new Studente(elencoStudenti[i]);
        }
        
        elencoStudentiAlfabetico=Ordinatore.ordinaStudenti(elencoStudentiAlfabetico);
        return elencoStudentiAlfabetico;
        
    }
    
    /*
    public void salvaClasse(String nomeFile) throws IOException
    {
        FileOutputStream f1= new FileOutputStream(nomeFile);
        ObjectOutputStream writer=new ObjectOutputStream(f1);
        writer.writeObject(this);
        writer.flush();
        writer.close();   
    }
    
    public Classe caricaClasse(String nomeFile) throws IOException
    {
        FileInputStream f1= new FileInputStream(nomeFile);
        ObjectInputStream reader=new ObjectInputStream(f1);
        Classe c1;
        c1=reader.readObject()
    }
    */
}
