/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package organizador.dominio;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

/**
 *
 * @author Arizon dos Santos Neto
 */
public class Formatador {
    private ArrayList conversas = new ArrayList<Conversa>();
    
    Scanner scanner = null;//scanner utilizado para ler o conteúdo das conversas
    
    Formatador(ArrayList conversa) {
        conversas.addAll(conversa);
    }

    public ArrayList formataConversas() throws ParseException {
        String line = "";
        //TODO formatar conversas e retornar o arraylist formatado.
        System.out.println("\n");//teste conteudo
        for (int i=0; i<conversas.size();i++){ //itera pelos elementos da lista
            Conversa temp = (Conversa) conversas.get(i);//variável local que permite a leitura do conteúdo
            scanner = new Scanner(temp.getLinhas().toString());//inicializa um novo scanner para cada arquivo

            //System.out.println(temp.fname + ":\n" + temp.getLinhas());//teste conteudo
            
            ArrayList linhas = new ArrayList<Linha>();
            int count = 0;//contador de iteração
            while (scanner.hasNextLine()){
            //TODO filtros de seções
                if (line.length() > 1){//checa se a linha possui conteúdo ou se trata de uma terminação do array (']')
                    System.out.println(temp.fname + ":");//teste conteudo
                        line = scanner.nextLine();
                    for (int j = 0; j < temp.getLinhas().size(); j++){
                        linhas.add(line);
                        System.out.println(temp.getLinhas().get(j));
                    }
                    //TODO traduzir de line para linhas
                    linhas = traduzConversas(i); 
                    linhas = separaColunas(i);
                    /*line = traduzConversas(line);
                    line = separaColunas(line, i);
                    */
                    
                    System.out.print("\n");//fim do teste conteudo
                    temp.setLinhas(linhas);
                    
                    //linhas.set(count, temp.fname + ":\n" + line);
                    //linhas.add(line);                    
                    //TODO trocar linhas por linhas formatadas
                    System.out.println(temp.fname + "(Formatado):");//teste conteudo formatado
                    for (int j = 0; j < temp.getLinhas().size(); j++){//iteração ṕara checar formatação
                        //linhas.forEach(System.out.println());
                        System.out.print(temp.getLinhas().get(j));
                    }
                    System.out.println(i);//teste: lista a posição na conversa em que está
                    System.out.print("\n");//fim do teste conteudo formatado
                }else{
                    this.conversas.remove(i);
                    line = scanner.nextLine();
                }
            count++;
            }
            temp.setLinhas(linhas);
        }
        //TODO atualizar conversas de acordo
        return conversas;
    }

    private ArrayList<Linha> traduzConversas(int indexOf) {
        //TODO tradutor que facilita linguagem (mídia omitida, etc)
        Conversa aux = (Conversa) conversas.get(indexOf);
        String content = "";
        ArrayList linhas = new ArrayList<Linha>();
        linhas.addAll(aux.getLinhas());
        for (int i=0; i < aux.getLinhas().size(); i++){
            content = linhas.get(i).toString().replace("<Mídia omitida>", "(Arquivo Deletado)");//traduz mídia omitida para arquivo deletado
            //content = linhas.get(i).getLinha();
            //TODO corrigir setConteudo
            aux.setConteudo(content + "\n", i);
            //aux.setConteudo(linhas.get(i).linha.replace("<Mídia omitida>", "(Arquivo Deletado)"), i);
            //aux.setConteudo(linhas.get(i).linha.replace("<Mídia omitida>", "(Arquivo Deletado)"), i);
            //aux.add(linhas.get(i).linha.replace("<Mídia omitida>", "(Arquivo Deletado)"));
            
        }
        //return line.replace("<Mídia omitida>", "(Arquivo Deletado)");
        
        return aux.getLinhas();
    }

    private ArrayList<Linha> separaColunas(int indexOf) throws ParseException {
        //TODO separador de Atributos da linha que compõem a conversa (data, hora, conteúdo)
        Conversa aux = (Conversa) conversas.get(indexOf);
        String content = "";
        ArrayList linhas = new ArrayList<Linha>();
        linhas.addAll(aux.getLinhas());
        //separa a data
        SimpleDateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy");
        String filtroData = null;
        Date data = null;
       
        for (int i=0; i< aux.getLinhas().size(); i++){
            try {
                filtroData = linhas.get(i).toString().substring(1, 8);
                data = formatoData.parse(filtroData);
               
                //TODO WIP adicionar 2000 para o ano (Whatsapp só entrega o final da data).
                /*
                String ano = new SimpleDateFormat("YYYY").format(data); //
                int anoCorrigido = Integer.parseInt(ano)+2000;
                
                filtroData = linhas.get(i).toString().substring(1, 6);
                data = formatoData.parse(String.join("", filtroData, Integer.toString(anoCorrigido)));*/
                //System.out.print(filtroData);
                //System.out.println(String.join("", filtroData, Integer.toString(anoCorrigido)));
                
                aux.setData(data, i);
                conversas.set(indexOf, aux);
                
            } catch (Exception e) {
                aux.setData(formatoData.parse("00/00/00"), i);
                //throw new RuntimeException("Erro ao inserir data");
            }
        }
        //separa a hora
        SimpleDateFormat formatoHora = new SimpleDateFormat("HH:mm aaa");
        String filtroHora = null;
        Date hora = null;
                
        for (int i=0; i< aux.getLinhas().size(); i++){
            try {
                filtroHora = linhas.get(i).toString().substring(10, 18);
                hora = formatoHora.parse(filtroHora);
               
                aux.setHorario(hora, i);
                conversas.set(indexOf, aux);
                
            } catch (Exception e) {
                aux.setHorario(formatoHora.parse("00:00 AM"), i);
                //throw new RuntimeException("Erro ao inserir Hora");
            }
        }
        
        //separa o Emissor
        String filtroEmissor = null;
        String emissor = null;
        
        for (int i=0; i < aux.getLinhas().size(); i++){
            try{
                filtroEmissor = linhas.get(i).toString().split(" - ")[1];
                emissor = filtroEmissor.split(":")[0];     
                //System.out.println(emissor);//teste emissor
                aux.setEmissor(emissor, i);
            } catch (Exception e) {
                aux.setEmissor("N/A", i);
                //throw new RuntimeException("Erro ao inserir Emissor");
            }
        }
        
        //separa o Tamanho (necessita linkar o arquivo)
        //TODO testar se arquivo está sendo testado de acordo
        for (int i = 0; i < aux.getLinhas().size()-1; i++) {
            String filtroArquivo = null;
                   
            try {
                filtroArquivo = linhas.get(i).toString().split(": ")[1];
            } catch (Exception e) {
                //throw new RuntimeException("Erro ao inserir Filtro de Arquivo");
            }
            //System.out.println(filtroArquivo);//Teste de filtro
            File arquivo = null;
            try {
            arquivo = new File(filtroArquivo);
            if (arquivo.isFile()) {
                aux.setTam((int) arquivo.length(), i);
            } else {
                aux.setTam(0, i);
            }
            } catch (Exception e) {
                aux.setTam(0, i);
            }  
        }
        //separa o conteúdo
        for (int i = 0; i < aux.getLinhas().size(); i++) {
            String filtroConteudo = null;
            try {
                filtroConteudo = linhas.get(i).toString().split(": ")[1];
                aux.setConteudo(filtroConteudo, i);
            } catch (Exception e) {
                aux.setConteudo("", i);
            }
        }
        
        return aux.getLinhas();
    }
    
    public ArrayList getConversa(){
        return this.conversas;
    }
    
}
