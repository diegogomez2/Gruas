/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type;
import java.io.File;
import javax.swing.JOptionPane;
import javax.xml.crypto.dsig.TransformException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 *
 * @author diego
 */
public class controladorCrearFactura {
    
    static vistas.vistaFacturasP vistaF;
    
    public void crearDocXML(String idOt){
        modelos.modeloOts ots = new modelos.modeloOts();
        String[] data = ots.obtenerFacturaPorId(idOt);
        try{
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            
            Document doc = docBuilder.newDocument();
            
            Element rootElement = doc.createElement("Documento");
            doc.appendChild(rootElement);
            
            Element encabezado = doc.createElement("Encabezado");
            rootElement.appendChild(encabezado);
            
            Element id = doc.createElement("IdDoc");
            encabezado.appendChild(id);
            
            Element emisor = doc.createElement("Emisor");
            encabezado.appendChild(emisor);
            
            Element receptor = doc.createElement("Receptor");
            encabezado.appendChild(receptor);
            
            Element rutRec = doc.createElement("RUTRecep");
            rutRec.appendChild(doc.createTextNode(data[0]));
            receptor.appendChild(rutRec);
            
            Element rznRec = doc.createElement("RznSocRecep");
            rznRec.appendChild(doc.createTextNode(data[1]));
            receptor.appendChild(rznRec);
            
            Element girRec = doc.createElement("GiroRecep");
            girRec.appendChild(doc.createTextNode(data[2]));
            receptor.appendChild(girRec);
            
            Element dirRec = doc.createElement("DirRecep");
            dirRec.appendChild(doc.createTextNode(data[3]));
            receptor.appendChild(dirRec);
            
            Element comRec = doc.createElement("CmnaRecep");
            comRec.appendChild(doc.createTextNode(data[5]));
            receptor.appendChild(comRec);
            
            Element regRec = doc.createElement("RegRecep");
            regRec.appendChild(doc.createTextNode(data[4]));
            receptor.appendChild(regRec);
            
            Element totales = doc.createElement("Totales");
            encabezado.appendChild(totales);
            
            Element neto = doc.createElement("MntNeto");
            neto.appendChild(doc.createTextNode(data[7]));
            totales.appendChild(neto);
            
            Element iva = doc.createElement("IVA");
            iva.appendChild(doc.createTextNode(data[8]));
            totales.appendChild(iva);
            
            Element bruto = doc.createElement("MntTotal");
            bruto.appendChild(doc.createTextNode(data[6]));
            totales.appendChild(bruto);
            
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.ENCODING, "ISO-8859-1");
            DOMSource source = new DOMSource(doc);
            StreamResult result =   new StreamResult(new File("factura.xml"));
            
            transformer.transform(source, result);
            JOptionPane.showMessageDialog(vistaF, "Factura realizada con éxito");
            
        }catch(ParserConfigurationException pce){
            pce.printStackTrace();
        }catch(TransformerException tfe){
            tfe.printStackTrace();
        }
    }
}
