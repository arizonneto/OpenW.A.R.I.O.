/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package organizador.dominio;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Arizon dos Santos Neto
 */
public class CompiladorConversas {
    private ArrayList conversas = new ArrayList<Conversa>();
    String conversaCompilada = null;

    CompiladorConversas(ArrayList conversa) {
        conversas.addAll(conversa);
    }

    void validaEncoding() {
        //TODO validar encoding para utf-8
    }

    void compilaConversas() {
        //TODO compila todas as conversas em um único bloco
        Scanner scanner = null;
        String line = null;

        System.out.println("Início do compilador:");
        for (int i = 0; i < this.conversas.size(); i++) {//percorre toda a conversa
           Conversa temp = (Conversa) conversas.get(i);//variável local que permite a leitura do conteúdo
           ArrayList linhas = new ArrayList<Linha>();
           scanner = new Scanner(temp.getLinhas().toString());//inicializa um novo scanner para cada arquivo
           
           line = scanner.nextLine();
           while (scanner.hasNextLine()){
               line = line + scanner.nextLine();
           } 
           System.out.print(line);
           /*           for (int j=0; i < temp.getLinhas().size(); j++){
           //linhas.add(line);
           //System.out.println(temp.getLinhas().get(j));
           
           }*/
        }     
    }
}
