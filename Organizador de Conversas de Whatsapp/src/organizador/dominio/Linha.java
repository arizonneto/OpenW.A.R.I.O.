/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package organizador.dominio;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Arizon dos Santos Neto
 */
class Linha {
        String linha;
        Date data;//data do texto
        Date horário;//horário do texto
        String emissor;//pessoa que enviou a mensagem
        int tam;//tamanho mensagem

        public Linha() {
            linha = null;
            data = null;
            horário = null;
            emissor = null;
            tam = 0;
        }

    public String getLinha() {
        return linha;
    }

    public void setLinha(String linha) {
        this.linha = linha;
    }
    
    public void setData(Date data) {
        this.data = data;
    }

    public void setHorário(Date horário, int i) {
        this.horário = horário;
    }

    public void setEmissor(String emissor, int i) {
        this.emissor = emissor;
    }

    public void setTam(int tam, int i) {
        this.tam = tam;
    }
    
    @Override
    public String toString(){
        return linha;
    }
}
