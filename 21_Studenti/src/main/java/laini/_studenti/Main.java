/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package laini._studenti;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.time.Month;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Gian
 */
public class Main 
{
    
    final static int N_MAX_CLASSI=100;
    static long idProssimoVoto=1;              //incremento ogni volta che aggiungo un voto (non decremento)
    static long matricolaProssimoStudente=1;   //incremento ogni volta che aggiungo uno studente (non decremento)
    static Scanner tastiera=new Scanner(System.in);
    
    
    public static void main(String[] args) 
    {
        int esitoOperazione;
        Classe[] elencoClassi= new Classe[N_MAX_CLASSI];
        int nClassiPresenti=0;  
        Classe classe;
        final String nomeFileBinario="elencoClassi.bin";

        String[] elencoVociMenuIniziale={"Esci","Visualizza classi presenti", "Seleziona classe", "Aggiungi nuova classe", "Salva dati"};
        int sceltaUtente;
        Menu menuIniziale=new Menu(elencoVociMenuIniziale);
        
        try
        {
            FileInputStream f2=new FileInputStream(nomeFileBinario);
            ObjectInputStream reader=new ObjectInputStream(f2);
            elencoClassi=(Classe[])reader.readObject();
            reader.close();
            int c=0;
            for (int i=0;i<N_MAX_CLASSI;i++)
            {
                if (elencoClassi[i]!=null)
                    c++;
            }
            nClassiPresenti=c;
            System.out.println("Dati caricati correttamente");
        }
        catch(IOException e1)
        {
            System.out.println("Impossibile accedere al file. I dati non sono stati caricati");
        } 
        catch (ClassNotFoundException ex) 
        {
            System.out.println("Errore nella lettura del file. I dati non sono stati caricati");
        }
        
        
        
        
        do
        {
            sceltaUtente=menuIniziale.sceltaMenu();
            switch(sceltaUtente)
            {
                case 0: //esci
                {
                    System.out.println("L'applicazione verrà terminata");
                    break;
                }
                case 1: //visualizza classi presenti
                {
                    if (nClassiPresenti==0)
                        System.out.println("Nessuna classe presente");
                    else
                    {
                        for (int i=0;i<nClassiPresenti;i++)
                            System.out.println(elencoClassi[i].getAnno()+elencoClassi[i].getSezione()+elencoClassi[i].getIndirizzo());
                    }
                    break;
                }
                case 2: //seleziona classe
                {
                    int anno;
                    String sezione,indirizzo;
                    System.out.println("Anno --> ");
                    anno=tastiera.nextInt();
                    System.out.println("Sezione --> ");
                    sezione=tastiera.next();
                    System.out.println("Indirizzo --> ");
                    indirizzo=tastiera.next();
                    boolean classeTrovata=false;
                    for (int i=0;i<nClassiPresenti;i++)
                    {
                        if (elencoClassi[i]!=null)
                        {
                            classe=elencoClassi[i];
                            if (classe.getAnno()==anno && classe.getSezione().compareToIgnoreCase(sezione)==0 && classe.getIndirizzo().compareToIgnoreCase(indirizzo)==0)
                            {
                                gestioneSottomenu(classe);
                                classeTrovata=true;
                            }
                        }
                    }
                    if (!classeTrovata)
                        System.out.println("La classe indicata non esiste");
                        
                    break;
                }
                case 3: //Aggiungi classe
                {
                    if (nClassiPresenti>=N_MAX_CLASSI)
                        System.out.println("Impossibile aggiungere nuova classe");
                    else
                    {
                        int anno;
                        String sezione,indirizzo;
                        System.out.println("Anno --> ");
                        anno=tastiera.nextInt();
                        System.out.println("Sezione --> ");
                        sezione=tastiera.next();
                        System.out.println("Indirizzo --> ");
                        indirizzo=tastiera.next();
                        elencoClassi[nClassiPresenti]= new Classe(indirizzo, sezione, anno);
                        nClassiPresenti++;
                        System.out.println("Nuova classe aggiunta correttamente");
                    }
                    break;
                }
                case 4:
                {
                    try
                    {
                        FileOutputStream f1= new FileOutputStream(nomeFileBinario);
                        ObjectOutputStream writer=new ObjectOutputStream(f1); 
                        writer.writeObject(elencoClassi);
                        writer.flush();
                        writer.close();
                        System.out.println("Dati salvati correttamente.");
                    }
                    catch(IOException e1)
                    {
                        System.out.println("Impossibile accedre al file. I dati non sono stati salvati");
                    }
                    
                    
                    break;
                }
                
            }
            
        }while (sceltaUtente!=0);
            
       
        
        
        
        
        
        
        
        /*TEST CLASSE CLASSE
        
        
        Studente s1=new Studente(matricolaStudente,"Abba","Lino");
        matricolaStudente++;
        Studente s2=new Studente(matricolaStudente,"Bassi","Lauro");
        matricolaStudente++;
        Studente s3=new Studente(matricolaStudente,"Cilla","Paola");
        matricolaStudente++;
        
        Classe c1=new Classe("INT","A",1);
        
        
        //Tento di eliminare uno studente dalla clase vuota
        esitoOperazione=c1.rimuoviStudente(1);
        if (esitoOperazione==-1)
            System.out.println("Lo studente non è presente nella classe. Impossibile rimuovere lo studente");
        else
            System.out.println("Studente rimosso correttamente");
        //aggiungo tre studenti
        esitoOperazione=c1.aggiungiStudente(s1);
        if (esitoOperazione==-1)
            System.out.println("Massimo numero di studenti raggiunto. Impossibile aggiungere lo studente");
        else
            System.out.println("Studente aggiunto corretamente");
        esitoOperazione=c1.aggiungiStudente(s2);
        if (esitoOperazione==-1)
            System.out.println("Massimo numero di studenti raggiunto. Impossibile aggiungere lo studente");
        else
            System.out.println("Studente aggiunto corretamente");
        esitoOperazione=c1.aggiungiStudente(s3);
        if (esitoOperazione==-1)
            System.out.println("Massimo numero di studenti raggiunto. Impossibile aggiungere lo studente");
        else
            System.out.println("Studente aggiunto corretamente");
        
        //Rimuovo lo studente con matricola 2
        System.out.println("Numero studenti presenti:"+c1.getNStudentiPresenti());
        esitoOperazione=c1.rimuoviStudente(2);
        if (esitoOperazione==-1)
            System.out.println("Lo studente non è presente nella classe. Impossiile rimuovere lo studente");
        else
            System.out.println("Studente rimosso corretamente");
        System.out.println("Numero studenti presenti:"+c1.getNStudentiPresenti());
        
        
        //getStudente con studente esistente
        Studente s;
        s=c1.getStudente(3);
        if (s!=null)
            System.out.println(s.toString());
        else
            System.out.println("Studente non presente");
        
        //getStudente con studente non esistente
        s=c1.getStudente(2);  //è stato eliminato
        if (s!=null)
            System.out.println(s.toString());
        else
            System.out.println("Studente non presente");
                
        

        //aggiungo due voti allo studente 1 e un voto allo studente 3
        Voto v1= new Voto(idVoto,12,3,2021,"Italiano",8,'o');
        idVoto++;
        Voto v2=new Voto(idVoto,12,3,2021,"Inglese",7,'o');
        idVoto++;
        Voto v3=new Voto(idVoto,10,10,2020,"Inglese",6,'s');
        idVoto++;
        
        esitoOperazione=c1.aggiungiVoto(1, v1);
        if (esitoOperazione==-1)
            System.out.println("Studente non presente");
        else if (esitoOperazione==-2)
            System.out.println("Impossibile aggiungere il voto, lo studente ha raggiunto il massimo numero di voti");
        else
            System.out.println("Voto aggiunto correttamete");
        
        esitoOperazione=c1.aggiungiVoto(1, v2);
        if (esitoOperazione==-1)
            System.out.println("Studente non presente");
        else if (esitoOperazione==-2)
            System.out.println("Impossibile aggiungere il voto, lo studente ha raggiunto il massimo numero di voti");
        else
            System.out.println("Voto aggiunto correttamete");
        
        esitoOperazione=c1.aggiungiVoto(3, v3);
        if (esitoOperazione==-1)
            System.out.println("Studente non presente");
        else if (esitoOperazione==-2)
            System.out.println("Impossibile aggiungere il voto, lo studente ha raggiunto il massimo numero di voti");
        else
            System.out.println("Voto aggiunto correttamete");
        
        
        //provo ad aggiungere un voto ad uno studente che non esiste
        esitoOperazione=c1.aggiungiVoto(2, v1);
        if (esitoOperazione==-1)
            System.out.println("Studente non presente");
        else if (esitoOperazione==-2)
            System.out.println("Impossibile aggiungere il voto, lo studente ha raggiunto il massimo numero di voti");
        else
            System.out.println("Voto aggiunto correttamete");
        
        //elimino un voto ad uno studente che non esiste
        LocalDate data=LocalDate.of(2021,3,12);
        esitoOperazione=c1.eliminaVoto(2,data,"Italiano");
        if (esitoOperazione==-1)
            System.out.println("Studente non presente");
        else if (esitoOperazione==-2)
            System.out.println("Voto non presente, impossibile elimnarlo");
        else
            System.out.println("Voto eliminato correttamete");
        
        //elimino un voto che non esiste ad uno studente che esiste
        LocalDate data1=LocalDate.of(2021,3,13);
         esitoOperazione=c1.eliminaVoto(1,data1,"Italiano");
        if (esitoOperazione==-1)
            System.out.println("Studente non presente");
        else if (esitoOperazione==-2)
            System.out.println("Voto non presente, impossibile elimnarlo");
        else
            System.out.println("Voto eliminato correttamete");
    
        //elimino un voto esistente 
        esitoOperazione=c1.eliminaVoto(1,data,"Italiano");
        if (esitoOperazione==-1)
            System.out.println("Studente non presente");
        else if (esitoOperazione==-2)
            System.out.println("Voto non presente, impossibile eliminarlo");
        else
            System.out.println("Voto eliminato correttamete");
        
        //Cerco un voto di uno studente inesistente
        Voto v=new Voto();
        LocalDate data2=LocalDate.of(2020,10,10);
        esitoOperazione=c1.getVoto(10, data2, "Inglese", v);
        if (esitoOperazione==-1)
            System.out.println("Studente non presente");
        else if(esitoOperazione==-2)
            System.out.println("Voto non presente");
        else
            System.out.println("Voto: "+v.toString());
        
        //Cerco un voto inesistente di uno studente esistente
        esitoOperazione=c1.getVoto(3, data2, "Informatica", v);
        if (esitoOperazione==-1)
            System.out.println("Studente non presente");
        else if(esitoOperazione==-2)
            System.out.println("Voto non presente");
        else
            System.out.println("Voto: "+v.toString());
        
         //Cerco un voto esistente 
        esitoOperazione=c1.getVoto(3, data2, "Inglese", v);
        if (esitoOperazione==-1)
            System.out.println("Studente non presente");
        else if(esitoOperazione==-2)
            System.out.println("Voto non presente");
        else
            System.out.println("Voto: "+v.toString());
        
        
        //Mostro tutti i voti di uno studente inesistente
        System.out.println(c1.mostraVoti(10));
        
        //Mostro tutti i voti di uno studente che non ha alcun voto
        Studente s4=new Studente(matricolaStudente,"Dalla","Lucio");
        matricolaStudente++;
        esitoOperazione=c1.aggiungiStudente(s4);
        if (esitoOperazione==-1)
            System.out.println("Massimo numero di studenti raggiunto. Impossibile aggiungere lo studente");
        else
            System.out.println("Studente s4 aggiunto corretamente");
        System.out.println(c1.mostraVoti(4));
        
        //Mostro tutti i voti di uno studente 
        System.out.println(c1.mostraVoti(1));

        
        
        //Calcolo la media in una materia di uno studente inesistente
        float media;
        media=c1.calcolaMedia(10, "Inglese");
        if (media==-1)
            System.out.println("Studente non presente");
        else if (media==-2)
            System.out.println("Lo studente non ha voti per questa materia");
        else
            System.out.println("Media nella materia: "+media);
        
        //Calcolo la media in una materia di uno studente senza voti in quell materia
        media=c1.calcolaMedia(1, "Informatica");
        if (media==-1)
            System.out.println("Studente non presente");
        else if (media==-2)
            System.out.println("Lo studente non ha voti per questa materia");
        else
            System.out.println("Media nella materia: "+media);
        
        
        //Calcolo la media in una materia di uno studente 
        media=c1.calcolaMedia(1, "Inglese");
        if (media==-1)
            System.out.println("Studente non presente");
        else if (media==-2)
            System.out.println("Lo studente non ha voti per questa materia");
        else
            System.out.println("Media nella materia: "+media);
        
        
        //Calcolo la media complessiva di uno studente inesistente
        media=c1.calcolaMedia(10);
        if (media==-1)
            System.out.println("Studente non presente");
        else if (media==-2)
            System.out.println("Lo studente non ha voti");
        else
            System.out.println("Media complessive: "+media);
        
         //Calcolo la media complessiva di uno studente senza voti
        media=c1.calcolaMedia(4);
        if (media==-1)
            System.out.println("Studente non presente");
        else if (media==-2)
            System.out.println("Lo studente non ha voti");
        else
            System.out.println("Media complessive: "+media);
        
         //Calcolo la media complessiva di uno studente (aggiungo un voto allo studente 1)
        esitoOperazione=c1.aggiungiVoto(1, v1);
        if (esitoOperazione==-1)
            System.out.println("Studente non presente");
        else if (esitoOperazione==-2)
            System.out.println("Impossibile aggiungere il voto, lo studente ha raggiunto il massimo numero di voti");
        else
            System.out.println("Voto aggiunto correttamente");
        
        media=c1.calcolaMedia(1);
        if (media==-1)
            System.out.println("Studente non presente");
        else if (media==-2)
            System.out.println("Lo studente non ha voti");
        else
            System.out.println("Media complessiva: "+media);
        
         //Calcolo media di una classe senza studenti 
         Classe c2=new Classe("AFM","A",4);
         media=c2.calcolaMedia();
         if (media==-1)
             System.out.println("Nessuno studente presente");
        else if(media==-2)
             System.out.println("Nessun voto presente");
         else
            System.out.println("Media complessiva della classe: "+media);
        
        
        //Calcolo media di una classe senza voti (aggiungo 2 studenti a C2
        esitoOperazione=c2.aggiungiStudente(s1);
        if (esitoOperazione==-1)
            System.out.println("Massimo numero di studenti raggiunto. Impossibile aggiungere lo studente");
        else
            System.out.println("Studente aggiunto corretamente");
        esitoOperazione=c2.aggiungiStudente(s2);
        if (esitoOperazione==-1)
            System.out.println("Massimo numero di studenti raggiunto. Impossibile aggiungere lo studente");
        else
            System.out.println("Studente aggiunto corretamente");
        
        media=c2.calcolaMedia();
        if (media==-1)
             System.out.println("Nessuno studente presente");
        else if(media==-2)
             System.out.println("Nessun voto presente");
        else
            System.out.println("Media complessiva della classe: "+media);
        
        //Calcolo media di una classe
        media=c1.calcolaMedia();
        if (media==-1)
             System.out.println("Nessuno studente presente");
        else if(media==-2)
             System.out.println("Nessun voto presente");
        else
            System.out.println("Media complessiva della classe: "+media);
        
        
        //elenco studenti di una classe senza studenti
        Classe c3=new Classe("AFM","B",4);
        System.out.println("Elenco studenti 4B AFM:\n"+c3.elencoStudenti());
        
        
        //elenco studenti di una classe
        System.out.println("Elenco studenti 1A INT:\n"+c1.elencoStudenti());
        */
        
        /*TEST CLASSE Studente
        Studente s1=new Studente(matricolaStudente,"Abba","Lino");
        matricolaStudente++;
        
        Voto v1= new Voto(idVoto,12,3,2021,"Italiano",8,'o');
        idVoto++;
        Voto v2=new Voto(idVoto,12,3,2021,"Inglese",7,'o');
        idVoto++;
        Voto v3=new Voto(idVoto,10,10,2020,"Inglese",6,'s');
        idVoto++;
        
        //EliminaVoti in assenza di voti
        esitoOperazione=s1.eliminaVoto(1);
        if (esitoOperazione==-1)
            System.out.println("Voto da eliminare non presente");
        else
            System.out.println("Voto eliminato correttamente");
        
       
        //getNVotiPresenti
        System.out.println("Numero voti presenti: "+s1.getNVotiPresenti());
        
        
        //getVoto in assenza di voti
        voto=s1.getVoto(1);
        if (voto==null)
            System.out.println("Il voto cercato non è presente");
        else
            System.out.println(voto.toString());
        
        //aggiungiVoto
        esitoOperazione=s1.aggiungiVoto(v1);
        if (esitoOperazione==-1)
            System.out.println("Non è stato possibile aggiungere il voto");
        else
            System.out.println("Voto aggiunto correttamente");
        
        esitoOperazione=s1.aggiungiVoto(v2);
        if (esitoOperazione==-1)
            System.out.println("Non è stato possibile aggiungere il voto");
        else
            System.out.println("Voto aggiunto correttamente");
        
        esitoOperazione=s1.aggiungiVoto(v3);
        if (esitoOperazione==-1)
            System.out.println("Non è stato possibile aggiungere il voto");
        else
            System.out.println("Voto aggiunto correttamente");
        
        //getVoto ok
        voto=s1.getVoto(1);
        if (voto==null)
            System.out.println("Il voto cercato non è presente");
        else
            System.out.println(voto.toString());
        voto=s1.getVoto(2);
        if (voto==null)
            System.out.println("Il voto cercato non è presente");
        else
            System.out.println(voto.toString());
        
        //media
        float mediaVoti;
        mediaVoti=s1.calcolaMedia();
        System.out.println("Media voti dello studente "+s1.toString()+":");
        if (mediaVoti==-1)
            System.out.println("Nessun voto presente");
        else
            System.out.println("Media: "+mediaVoti);
        
        //media inglese
        mediaVoti=s1.calcolaMedia("inglese");
        System.out.println("Media voti in inglese dello studente "+s1.toString()+":");
        if (mediaVoti==-1)
            System.out.println("Nessun voto presente");
        else
            System.out.println("Media: "+mediaVoti);
        
        //media italiano
        mediaVoti=s1.calcolaMedia("italiano");
        System.out.println("Media voti in italiano dello studente "+s1.toString()+":");
        if (mediaVoti==-1)
            System.out.println("Nessun voto presente");
        else
            System.out.println("Media: "+mediaVoti);
        
        //media matematica
        mediaVoti=s1.calcolaMedia("matematica");
        System.out.println("Media voti in matematica dello studente "+s1.toString()+":");
        if (mediaVoti==-1)
            System.out.println("Nessun voto presente");
        else
            System.out.println("Media: "+mediaVoti);
        
        //mostraVoti
        System.out.println(s1.mostraVoti());
        
        //eliminaVoto ok
        esitoOperazione=s1.eliminaVoto(1);
        if (esitoOperazione==-1)
            System.out.println("Voto da eliminare non presente");
        else
            System.out.println("Voto eliminato correttamente");
        
        //getNVotiPresenti
        System.out.println("Numero voti presenti: "+s1.getNVotiPresenti());
        
        //costruttore di copia
        Studente s2=new Studente(s1);
        System.out.println("Studente s2:");
        System.out.println(s2.toString());
        System.out.println(s2.mostraVoti());
        
        */
        
    }
    
    
    private static void gestioneSottomenu(Classe classe)
    {
        //variabili generiche
        int esitoOperazione;
        
        //Variabili studente
        Studente studente;
        String cognome;
        String nome;
        long matricola;
        
        //Variabili voto
        Voto voto;
        String materia;
        LocalDate data;
        int giorno,mese,anno;
        float valore;
        char tipologia;
        String stringa;
        float media;
        
        //variabili menu
        String[] elencoVocimenuClasse= new String[13];
        elencoVocimenuClasse[0]="Torna al menu iniziale";
        elencoVocimenuClasse[1]="Aggiungi studente";
        elencoVocimenuClasse[2]="Elimina studente";
        elencoVocimenuClasse[3]="Modifica dati anagrafici studente";
        elencoVocimenuClasse[4]="Aggiungi voto";
        elencoVocimenuClasse[5]="Visualizza voto";
        elencoVocimenuClasse[6]="Elimina voto";
        elencoVocimenuClasse[7]="Visualizza i voti di uno studente";
        elencoVocimenuClasse[8]="Visualizza media voti di uno studente";
        elencoVocimenuClasse[9]="Visualizza media voti di uno studente in una materia";
        elencoVocimenuClasse[10]="Visualizza media voti della classe in una materia";
        elencoVocimenuClasse[11]="Visualizza l'elenco degli studenti";
        elencoVocimenuClasse[12]="Visualizza l'elenco alfabetico degli studenti";
        
        Menu menuClasse=new Menu(elencoVocimenuClasse);
        int sceltaUtente;
        
        do
        {
            System.out.println("Premi un tasto per continuare...");
            tastiera.nextLine();
            sceltaUtente=menuClasse.sceltaMenu();
            System.out.println("Premi un tasto per continuare...");
            tastiera.nextLine();
            switch(sceltaUtente)
            {
                case 0: //Torna al meu iniziale
                {
                    System.out.println("Menu INIZIALE: ");
                    break;
                }
                case 1: //aggiungi studente
                {
                    System.out.println("Inserisci i dati dello studente");
                    System.out.println("Cognome --> ");
                    cognome=tastiera.nextLine();
                    System.out.println("Nome --> ");
                    nome=tastiera.nextLine();
                    studente=new Studente(matricolaProssimoStudente,cognome,nome);
                    matricolaProssimoStudente++;
                    esitoOperazione=classe.aggiungiStudente(studente);
                    if (esitoOperazione==-1)
                        System.out.println("Massimo numero di studenti raggiunto. Impossibile aggiungere lo studente");
                    else
                        System.out.println("Studente aggiunto correttamente");
                    break;
                }
                case 2: //elimina studente
                {
                    System.out.println("Inserisci la matricola dello studente da rimuovere");
                    matricola=tastiera.nextLong();
                    esitoOperazione=classe.rimuoviStudente(matricola);
                    if (esitoOperazione==-1)
                        System.out.println("Lo studente non è presente nella classe. Impossiile rimuovere lo studente");
                    else
                        System.out.println("Studente rimosso correttamente");
                    break;
                }
                case 3: //Modifica dati anagrafici studente
                {
                    System.out.println("Inserisci la matricola dello studente da modificare");
                    matricola=tastiera.nextLong();
                    studente=classe.getStudente(matricola);
                    if (studente==null)
                        System.out.println("Studente non presente");
                    else
                    {
                        System.out.println("Dati studente:"+ studente.toString());
                        tastiera.nextLine();
                        System.out.println("Inserisci cognome e nome: ");
                        System.out.println("Cognome --> ");
                        cognome=tastiera.nextLine();
                        System.out.println("Nome --> ");
                        nome=tastiera.nextLine();
                        esitoOperazione=classe.modificaDatiAnagrficiStudente(matricola, cognome, nome);
                        if (esitoOperazione==-1)
                            System.out.println("Studente non presente");
                        else
                            System.out.println("Modifica avvenuta correttamente");
                    }
   
                    break;
                }
                case 4: //Aggiungi voto
                {
                    
                    System.out.println("Inserisci la matricola dello studente a cui vuoi aggiungere un voto");
                    matricola=tastiera.nextLong();
                    studente=classe.getStudente(matricola);
                    if (studente==null)
                        System.out.println("Studente non presente");
                    else
                    {
                        System.out.println("Studente:"+ studente.toString());
                        System.out.println("Premi un tasto per continuare...");
                        tastiera.nextLine();
                        System.out.println("Materia --> ");
                        materia=tastiera.nextLine();
                        System.out.println("Data: ");
                        System.out.println("Giorno --> ");
                        giorno=tastiera.nextInt();
                        System.out.println("Mese --> ");
                        mese=tastiera.nextInt();
                        System.out.println("Anno --> ");
                        anno=tastiera.nextInt();
                        data=LocalDate.of(anno, mese, giorno);
                        System.out.println("Valore voto --> ");
                        valore=tastiera.nextFloat();
                        System.out.println("Tipologia voto (o,s,p) --> ");
                        stringa=tastiera.next();
                        tipologia=stringa.charAt(0);        
                        voto=new Voto(idProssimoVoto, giorno, mese, anno, materia, valore, tipologia);
                        idProssimoVoto++;
                        esitoOperazione=classe.aggiungiVoto(matricola, voto);
                        if (esitoOperazione==-1)
                            System.out.println("Studente non presente");
                        else if (esitoOperazione==-1)
                            System.out.println("Numero massimo di voti raggiunto per lo studente");
                        else
                            System.out.println("Voto inserito correttamente");
                    }
                    break;
                }
                case 5: //visualizza voto
                {
                    System.out.println("Inserisci la matricola dello studente di cui vuoi visualizzare un voto");
                    matricola=tastiera.nextLong();
                    studente=classe.getStudente(matricola);
                    if (studente==null)
                        System.out.println("Studente non presente");
                    else
                    {
                        System.out.println("Studente:"+ studente.toString());
                        tastiera.nextLine();
                        System.out.println("Materia --> ");
                        materia=tastiera.nextLine();
                        System.out.println("Data: ");
                        System.out.println("Giorno --> ");
                        giorno=tastiera.nextInt();
                        System.out.println("Mese --> ");
                        mese=tastiera.nextInt();
                        System.out.println("Anno --> ");
                        anno=tastiera.nextInt();
                        data=LocalDate.of(anno, mese, giorno);
                        voto=new Voto();
                        esitoOperazione=classe.getVoto(matricola, data, materia, voto);
                        if (esitoOperazione==-1)
                            System.out.println("Studente non presente");
                        else if(esitoOperazione==-2)
                            System.out.println("Voto non presente");
                        else
                            System.out.println("Voto: "+voto.toString());
                    }
                    break;
                }
                case 6: //elimina voto
                {
                    System.out.println("Inserisci la matricola dello studente di cui vuoi eliminare il voto");
                    matricola=tastiera.nextLong();
                    studente=classe.getStudente(matricola);
                    if (studente==null)
                        System.out.println("Studente non presente");
                    else
                    {
                        System.out.println("Studente:"+ studente.toString());
                        tastiera.nextLine();
                        System.out.println("Materia --> ");
                        materia=tastiera.nextLine();
                        System.out.println("Data: ");
                        System.out.println("Giorno --> ");
                        giorno=tastiera.nextInt();
                        System.out.println("Mese --> ");
                        mese=tastiera.nextInt();
                        System.out.println("Anno --> ");
                        anno=tastiera.nextInt();
                        data=LocalDate.of(anno, mese, giorno);
                        
                        esitoOperazione=classe.eliminaVoto(matricola,data,materia);
                        if (esitoOperazione==-1)
                            System.out.println("Studente non presente");            //questo caso non si presenterà mai
                        else if (esitoOperazione==-2)
                            System.out.println("Voto non presente, impossibile eliminarlo");
                        else
                            System.out.println("Voto eliminato correttamete");
                    }
                break;
                }
                case 7:  //mostra tutti i voti di uno studente
                {
                    System.out.println("Inserisci la matricola dello studente di cui vuoi vedere i voti");
                    matricola=tastiera.nextLong();
                    studente=classe.getStudente(matricola);
                    if (studente==null)
                        System.out.println("Studente non presente");
                    else
                    {
                        System.out.println("Studente:"+ studente.toString());
                        tastiera.nextLine();
                        System.out.println(classe.mostraVoti(matricola));
                    }
                        
                    break;
                }
                case 8:  //visualizza media voti di uno studente
                {
                    System.out.println("Inserisci la matricola dello studente di cui vuoi vedere la media");
                    matricola=tastiera.nextLong();
                    studente=classe.getStudente(matricola);
                    if (studente==null)
                        System.out.println("Studente non presente");
                    else
                    {
                        System.out.println("Studente:"+ studente.toString());
                        tastiera.nextLine();  
                        media=classe.calcolaMedia(matricola);
                        if (media==-1)
                            System.out.println("Studente non presente");     //questo caso non si presenterà mai
                        else if (media==-2)
                            System.out.println("Lo studente non ha voti");
                        else
                            System.out.println("Media complessiva: "+media);
                    }
                    break;
                }
                case 9: //visualizza media voti di uno studente in una materia
                {
                    System.out.println("Inserisci la matricola dello studente di cui vuoi la media in una determinata materia");
                    matricola=tastiera.nextLong();
                    studente=classe.getStudente(matricola);
                    if (studente==null)
                        System.out.println("Studente non presente");
                    else
                    {
                        System.out.println("Studente:"+ studente.toString());
                        tastiera.nextLine();                        
                        System.out.println("Materia --> ");
                        materia=tastiera.nextLine();
                        media=classe.calcolaMedia(matricola,materia);
                        if (media==-1)
                            System.out.println("Studente non presente");  //mai
                        else if (media==-2)
                            System.out.println("Lo studente non ha voti per questa materia");
                        else
                            System.out.println("Media nella materia: "+media);   
                    }
                    break;
                }
                case 10: //media della classe in una materia
                {
                    System.out.println("Materia --> ");
                    materia=tastiera.nextLine();
                    System.out.println(classe.getAnno()+classe.getSezione()+classe.getIndirizzo());
                    media=classe.calcolaMediaClasseMateria(materia);
                    if (media==-1)
                         System.out.println("Nessuno studente presente");
                    else if(media==-2)
                         System.out.println("Nessun voto presente");
                    else
                        System.out.println("Media complessiva della classe: "+media);                    
                    break;
                }
                case 11: //mostra elenco studenti della classe
                {
                    System.out.println(classe.getAnno()+classe.getSezione()+classe.getIndirizzo());
                    System.out.println(classe.elencoStudenti());
                    break;
                }
                case 12:
                {
                   Studente elencoAlfabetico[];
                   elencoAlfabetico=classe.ordineAlfabeticoStudenti();
                   for (int i=0;i<elencoAlfabetico.length;i++)
                   {
                       System.out.println(elencoAlfabetico[i].toString());
                   }
                    
                    break;
                }
            }
         
        }while (sceltaUtente!=0);
        
    }
}
