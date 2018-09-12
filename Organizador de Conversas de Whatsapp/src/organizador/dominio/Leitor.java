/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package organizador.dominio;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author Arizon dos Santos Neto
 */
public class Leitor {
    File dir = new File("/run/media/anon/38CB-1B08/WhatsApp/media");//diretório de conversas
    private final ArrayList conversas = new ArrayList<Conversa>();
    
    public void leConversas (){//lê conversas e retorna elementos de um set
        if(dir.isDirectory()) {
            for(File file : dir.listFiles()) {
                if(file.isFile()) {//se o arquivo é válido (não é um diretório)                    
                    String fname = file.getName();//nome do arquivo
                    
                   
                    String line = null;
                    try{
                        // FileReader lê arquivos com encoding padrão
                        FileReader fileReader = new FileReader(file.getCanonicalFile());

                        // manda o FileReader para um buffer
                        BufferedReader bufferedReader = new BufferedReader(fileReader);

                        //lê linha e salva como atributo
                        line = bufferedReader.readLine();
                        //fecha o arquivo ao terminar a leitura
                        //bufferedReader.close();
                        
                        String conteudo = "";
      
                        if (isValid(line) == true) {
                            line = "";
                            //bufferedReader = new BufferedReader(fileReader);
                            
                            /*do {
                            conteudo = conteudo + line;//concatena linha por linha
                            } */
                            Conversa temp = new Conversa();
                            ArrayList linhas = new ArrayList<Linha>();
                            temp.setLinhas(linhas);
                            int i = 0;
                            while ((line = bufferedReader.readLine()) != null){
                                temp.addLinha(i);
                                temp.setConteudo(line, i);
                                //linhas.add(line + "\n");
                                //temp.setConteudo((line + "\n"), i);
                                i++;
                            }
                            temp.setLinhas(linhas);
                            temp.fname=fname;
                            //int tam = (int) file.length();//recebe o tamanho da mensagem
                            this.conversas.add(temp);
                        }else{
                            System.out.println("Erro lendo arquivo '" + fname + "': Arquivo Inválido");//Erro quando arquivo não possuir requisitos
                        }

                        //fecha o arquivo ao terminar a leitura
                        bufferedReader.close();
                    }
                     catch(IOException ex){
                        System.out.println("Erro lendo arquivo '" + fname + "': Erro Inesperado");//Erro quando arquivo não possuir requisitos
                    }
                }
            }
        } else {
            throw new RuntimeException("Diretório Inválido");
    }
}
    private boolean isValid(String line){//retorna se a linha é válida
        String validata = "/";//TODO arrumar validata para utilizar wildcards
        /*validata equivale uma string com dois wildcards de digito, seguido de uma 
        barra e mais dois wildcards, usado para validar as conversas checando 
        se iniciam com uma data */
        return line.contains(validata);
    }
    public ArrayList getConversa(){
        return this.conversas;
    }
}