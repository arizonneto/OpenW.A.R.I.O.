/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package organizador.dominio;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Arizon dos Santos Neto
 */
class Conversa {

    private ArrayList<Linha> linhas;//Texto do arquivo original
    String fname;//nome da conversa

    Conversa() {
        this.linhas = new ArrayList<Linha>();
    }

    public ArrayList getLinhas() {
        return this.linhas;
    }

    public void setLinhas(ArrayList linhas) {
        this.linhas = linhas;
    }
    
    public void setConteudo(String conteudo, int i) {
        //this.linhas.get(i).linha = conteudo;
        this.linhas.get(i).linha = conteudo;
    }
    
    public void setData(Date data, int i) {
        this.linhas.get(i).data = data;
    }

    public void setHorario(Date horário, int i) {
        this.linhas.get(i).horário = horário;
    }

    public void setEmissor(String emissor, int i) {
        this.linhas.get(i).emissor = emissor;
    }

    public void setTam(int tam, int i) {
        this.linhas.get(i).tam = tam;
    }

    void addLinha(int i) {
        Linha linha = new Linha();
        this.linhas.add(i, linha);
    }
}
