/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package laini._studenti;

/**
 *
 * @author Gian
 */
public class Studente 
{
    private long matricola;
    private final int N_MAX_VOTI=100;
    private int nVotiPresenti;
    private String cognome;
    private String nome;
    private Voto[] elencoVoti;
    
    private String[] MATERIE={"italiano","matematica","inglese","scienze motorie","storia","diritto","sitemi e reti","TPS","informatica","telecomunicazioni","religione"};
    
    
    public Studente(long matricola, String cognome, String nome)
    {
        this.matricola=matricola;  
        this.cognome=cognome;
        this.nome=nome;
        elencoVoti=new Voto[N_MAX_VOTI];    
      
    }
    
    public Studente (Studente studente)
    {
        matricola=studente.getMatricola();
        setCognome(studente.getCognome());
        setNome(studente.getNome());
        nVotiPresenti=studente.getNVotiPresenti();
        elencoVoti=new Voto[N_MAX_VOTI];
        Voto v;
        for (int i=0;i<studente.getNVotiPresenti();i++)
        { 
            
            if (studente.elencoVoti[i]!=null)  // attenzione: uso elencoVoti e non getVoto(i) perchè getVoto cerca in base al codice voto non alla posizione
            {
                v=new Voto(studente.elencoVoti[i]);
                elencoVoti[i]=v;
            }
        }
    }

    public long getMatricola() {
        return matricola;
    }

    public int getN_MAX_VOTI() {
        return N_MAX_VOTI;
    }

    public int getNVotiPresenti() {
        return nVotiPresenti;
    }
    
    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    
    // -1 --> raggiunto limite massimo voti
    // 0  --> ok
    public int aggiungiVoto(Voto voto)
    {
        if (nVotiPresenti>=N_MAX_VOTI)
            return -1;
        elencoVoti[nVotiPresenti]=new Voto(voto);
        nVotiPresenti++;
        return 0;         
    }
    
    
    public Voto getVoto(long idVoto)
    {
        Voto v;
        for (int i=0;i<nVotiPresenti;i++)
        {
            if (elencoVoti[i]!=null)
            {
                v=elencoVoti[i];
                if (v.getIdVoto()==idVoto)
                {
                    return new Voto(v);
                }
            }
        }
        return null;
    }
    
    //overloading: getVoto che restituisce il voto in base alla posizione: metodo comodo da invocare nella classe Classe,
    public Voto getVoto(int posizioneVoto)
    {
        if (elencoVoti[posizioneVoto]!=null)
            return new Voto(elencoVoti[posizioneVoto]);
        else
            return null;
    }
    
    
    // 0 --> ok
    // -1 --> voto non trovato
    public int eliminaVoto(long idVoto)
    {
        Voto v;
        for (int i=0;i<nVotiPresenti;i++)
        {
            if (elencoVoti[i]!=null)
            {
                v=elencoVoti[i];
                if (v.getIdVoto()==idVoto)
                {
                    aggiornaPosizioneVoti(i);
                    return 0; 
                }
            }
        }
        return -1;
    }
    
    private void aggiornaPosizioneVoti(int posizione)
    {
        for (int i=posizione;i<nVotiPresenti-1;i++)  
        {
            elencoVoti[i]=elencoVoti[i+1];
        }
        elencoVoti[nVotiPresenti-1]=null;      //se ne elimini uno, sicuramente l'ultimo deve contenere null ( lo faccio per togliere dall'array il voto eliminato)
        nVotiPresenti--;
    }
    
    
    
    // -1 --> nessun voto presente
    public float calcolaMedia()
    {
        float sommaVoti=0;
        float mediaVoti;
        if (getNVotiPresenti()==0)
            return -1;      
        for (int i=0;i<getNVotiPresenti();i++)
            sommaVoti+=elencoVoti[i].getValore();
        mediaVoti=sommaVoti/getNVotiPresenti();
        return mediaVoti;
    }
    
     // -1 --> nessun voto nella materia presente
     public float calcolaMedia(String materia)
    {
        float sommaVoti=0;
        float mediaVoti=0;
        float contaVoti=0;
        
        if (getNVotiPresenti()==0)
            return -1;
        for (int i=0;i<getNVotiPresenti();i++)
        {
            if (elencoVoti[i].getMateria().equalsIgnoreCase(materia))
            {
                sommaVoti+=elencoVoti[i].getValore();
                contaVoti++;
            }
            
        }
        if (contaVoti==0)       //nessun voto nella materia
            return -1;
        else
            mediaVoti=sommaVoti/contaVoti;
        return mediaVoti;
    }
    
    public String mostraVoti()
    {
        String s="Voti studente "+toString()+":\n";
        for (int i=0;i<MATERIE.length;i++)
        {
            if (calcolaMedia(MATERIE[i])!=-1) //solo se ci sono voti in quelle materie
            {
                s+=MATERIE[i]+": ";
                for(int j=0;j<getNVotiPresenti();j++)
                {  
                    if (elencoVoti[j].getMateria().compareToIgnoreCase(MATERIE[i])==0)
                        s+=elencoVoti[j].getValore()+" ";
                }
                s+="\n";
            }
        }
        return s;
    }
     
    public String toString()
    {
        String s="";
        s+=getMatricola()+";"+getCognome()+";"+getNome()+";";
        return s;
    }   
}

