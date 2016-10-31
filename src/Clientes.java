import java.io.*;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.xml.parsers.*;
import org.w3c.dom.*;

public class Clientes {

    public static void Clientes() throws Exception{
        
        String caminho,nome="",cnpj="",num="",logr="",bairro="",cidade="",estado="",cep="",ie="",email="",fone="";
        int cont;
        FileWriter arq = null;
        PrintWriter gravarArq = null;
        
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

                NodeList listaClientes = doc.getElementsByTagName("dest");

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
                                        case "xNome":
                                            nome = elementoFilho.getTextContent();
                                            gravarArq.printf("%n%nRazao Social: "+nome+"%n");
                                            gravarArq.printf("CNPJ: "+cnpj+"%n");
                                            break;                                        
                                        case "CNPJ":
                                            cnpj = elementoFilho.getTextContent();
                                            break;                                        
                                        case "IE":
                                            ie = elementoFilho.getTextContent();
                                            gravarArq.printf("IE: "+ie+"%n");
                                            break;                                                                                
                                        case "email":
                                            email = elementoFilho.getTextContent();
                                            gravarArq.printf("Email: "+email+"%n");
                                            break;                                              
                                    }                                   
                                }
                            }  
                        }
                    }         
                NodeList listaEndereco = doc.getElementsByTagName("enderDest");
                int tamLista = listaEndereco.getLength();

                    for (int p = 0; p < tamanhoLista; p++) {
                        Node noEndereco = listaEndereco.item(p);

                        if(noEndereco.getNodeType() == Node.ELEMENT_NODE){
                            Element elementoEndereco = (Element) noEndereco;
                            NodeList listaDeFilhosEndereco = elementoEndereco.getChildNodes();

                            int tamanhoListaEndereco = listaDeFilhosEndereco.getLength();

                            for (int q = 0; q < tamanhoListaEndereco; q++) {
                                Node noFilhoEnd = listaDeFilhosEndereco.item(q);

                                if(noFilhoEnd.getNodeType() == Node.ELEMENT_NODE){
                                    Element elementoFilho = (Element) noFilhoEnd;
                                    gravarArq = new PrintWriter(arq);
                                    
                                    switch(elementoFilho.getTagName()){    
                                        case "xLgr":
                                            logr = elementoFilho.getTextContent();
                                            gravarArq.printf("Endereco: "+logr+", ");
                                            break;
                                        case "nro":
                                            num = elementoFilho.getTextContent();
                                            gravarArq.printf(num+"%n");
                                            break;
                                        case "xBairro":
                                            bairro = elementoFilho.getTextContent();
                                            gravarArq.printf("Bairro: "+bairro+"%n");
                                            break;
                                        case "xMun":
                                            cidade = elementoFilho.getTextContent();
                                            gravarArq.printf("Cidade: "+cidade+"%n");
                                            break;                                            
                                        case "UF":
                                            estado = elementoFilho.getTextContent();
                                            gravarArq.printf("Estado: "+estado+"%n");
                                            break;  
                                        case "CEP":
                                            cep = elementoFilho.getTextContent();
                                            gravarArq.printf("CEP: "+cep+"%n");
                                            break; 
                                        case "fone":
                                            fone = elementoFilho.getTextContent();
                                            gravarArq.printf("Fone: "+fone+"%n");
                                            break;                                            
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
