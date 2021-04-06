/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package laini._studenti;

import java.time.*;

/**
 *
 * @author Gian
 */
public class Voto 
{
    private long idVoto;
    private LocalDate data;
    private String materia;
    private float valore;
    private char tipologia;

    
    public Voto(long idVoto, int giorno, int mese, int anno, String materia, float valore, char tipologia) 
    {
        
        this.idVoto=idVoto;  
        data=LocalDate.of(anno, mese, giorno);
        this.materia = materia;
        this.valore = valore;
        this.tipologia = tipologia;
    }
    public Voto(Voto voto)
    {
        idVoto=voto.getIdVoto();   
        data=LocalDate.from(voto.getData());
        setMateria(voto.getMateria());
        setValore(voto.getValore());
        setTipologia(voto.getTipologia());
    }
    public Voto()
    {
        idVoto=0;
        data=LocalDate.now();
        setMateria("");
        setValore(0);
        setTipologia(' ');
    }
    
    public long getIdVoto()
    {
        return idVoto;
    }
    public LocalDate getData()
    {
        return data;
    }
    
    public void setData(int giorno,int mese, int anno)
    {
        data=data.withYear(anno);
        data=data.withMonth(mese);
        data=data.withDayOfMonth(giorno);
    }
    
    public String getMateria() {
        return materia;
    }

    public void setMateria(String materia) {
        this.materia = materia;
    }

    public float getValore() {
        return valore;
    }

    public void setValore(float valore) {
        this.valore = valore;
    }

    public char getTipologia() {
        return tipologia;
    }

    public void setTipologia(char tipologia) {
        this.tipologia = tipologia;
    }
    
    public String toString()
    {
        String s=getMateria()+" , "+getData().toString()+" , "+getTipologia()+" , "+getValore();
        return s;
    }
    
    
    
    
    
}
