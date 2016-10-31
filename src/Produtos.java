import java.io.*;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.xml.parsers.*;
import org.w3c.dom.*;

public class Produtos {

    public static void Produtos() throws Exception{
        
        String caminho,nome="",cnpj="",num="",logr="",bairro="",cidade="",estado="",cep="",ie="",email="",fone="";
        int cont;
        FileWriter arq = null;
        PrintWriter gravarArq = null;
        Element elementoFilhoImp = null;
        
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        
        try{
            JFileChooser chooser = new JFileChooser();
            JFileChooser chooserSalvar = new JFileChooser();
            chooser.setCurrentDirectory(new File("F://arquivos novelli//"));
            FileNameExtensionFilter xmlFilter = new FileNameExtensionFilter("Arquivos XML (*.xml)", "xml");            
            chooser.addChoosableFileFilter(xmlFilter);
            chooser.setFileFilter(xmlFilter);
            chooser.setMultiSelectionEnabled(true);
            chooser.showOpenDialog(chooser);
            
            File [] arquivos = chooser.getSelectedFiles();
            int cont5=0;
            cont5=chooser.getSelectedFiles().length;
            
            chooserSalvar.showSaveDialog(chooserSalvar); 
            arq = new FileWriter(chooserSalvar.getSelectedFile()+".txt");
                    
            for(cont=0;cont!=cont5;cont++){
                caminho = arquivos[cont].getPath();          
                Document doc = builder.parse(caminho);

                NodeList listaClientes = doc.getElementsByTagName("prod");

                int tamanhoLista = listaClientes.getLength();

                    for (int i = 0; i < tamanhoLista; i++) {
                        Node noCliente = listaClientes.item(i);

                        if(noCliente.getNodeType() == Node.ELEMENT_NODE){
                            Element elementoCliente = (Element) noCliente;
                            NodeList listaDeFilhosDaPessoa = elementoCliente.getChildNodes();
                            int tamanhoListaFilhos = listaDeFilhosDaPessoa.getLength();

                            for (int j = 0; j < tamanhoListaFilhos; j++) {
                                Node noFilho = listaDeFilhosDaPessoa.item(j);

                                if(noFilho.getNodeType() == Node.ELEMENT_NODE){
                                    Element elementoFilho = (Element) noFilho;
                                    gravarArq = new PrintWriter(arq);
                                    
                                    switch(elementoFilho.getTagName()){
                                        case "xProd":
                                            nome = elementoFilho.getTextContent();
                                            gravarArq.printf("%n%nNome Produto: "+nome+"%n");
                                            
                                            break;                                        
                                        case "NCM":
                                            cnpj = elementoFilho.getTextContent();
                                            gravarArq.printf("NCM: "+cnpj+"%n");
                                            break;                                        
                                        case "uCom":
                                            ie = elementoFilho.getTextContent();
                                            gravarArq.printf("Unid. Comercial: "+ie+"%n");
                                            break;                                                                                
                                        case "uTrib":
                                            email = elementoFilho.getTextContent();
                                            gravarArq.printf("Unid. Tributada: "+email+"%n");
                                            break;                                              
                                    }                                   
                                }
                            }  
                        }
                    }         
                NodeList listaImpost = doc.getElementsByTagName("imposto");
                int tamLista = listaImpost.getLength();

                    for (int p = 0; p < tamLista; p++) {
                        Node noImpost = listaImpost.item(p);

                        if(noImpost.getNodeType() == Node.ELEMENT_NODE){
                            Element elementoImpost = (Element) noImpost;
                            NodeList listaDeFilhosImpost = elementoImpost.getChildNodes();

                            int tamanhoListaImpost = listaDeFilhosImpost.getLength();

                            for (int q = 0; q < tamanhoListaImpost; q++) {
                                Node noFilhoImp = listaDeFilhosImpost.item(q);

                                if(noFilhoImp.getNodeType() == Node.ELEMENT_NODE){
                                    elementoFilhoImp = (Element) noFilhoImp;                                                                   
                                }
                                
                                NodeList listaIcms = elementoFilhoImp.getElementsByTagName("ICMS");
                                int tamListaIcms = listaIcms.getLength();
                                
                                    for(int r = 0; r < tamListaIcms; r++){
                                        Node noIcms = listaIcms.item(r);
                                        
                                        if(noIcms.getNodeType()==Node.ELEMENT_NODE){
                                            Element elementoFilhoIcms = (Element) noIcms;
                                            gravarArq = new PrintWriter(arq);
                                            
                                        switch(elementoFilhoIcms.getTagName()){
                                            case "orig":
                                                nome = elementoFilhoIcms.getTextContent();
                                                gravarArq.printf("%n%nORig: "+nome+"%n");

                                                break;                                        
                                            case "CST":
                                                cnpj = elementoFilhoIcms.getTextContent();
                                                gravarArq.printf("CST: "+cnpj+"%n");
                                                break;                                                                                     
                                        }                                            
                                        }
                                    }
                            }                         
                        }
                    }
                                                                      
                }                    
                                                          
                arq.close();              
        }catch(Exception e){
            System.out.printf(null,e);
        }
    }

}
