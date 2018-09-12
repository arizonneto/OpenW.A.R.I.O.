/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package organizador.dominio;

import java.text.ParseException;

/**
 *
 * @author Arizon dos Santos Neto
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws ParseException {
        // TODO code application logic here
        Leitor leitor = new Leitor();
        leitor.leConversas();
        
        Formatador formatador = new Formatador(leitor.getConversa());
        formatador.formataConversas();
        
        CompiladorConversas compilador = new CompiladorConversas(formatador.getConversa());
        //compilador.validaEncoding();
        compilador.compilaConversas();
    }

}
