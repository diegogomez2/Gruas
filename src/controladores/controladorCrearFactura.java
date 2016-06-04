/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;
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
import modelos.modeloFacturas;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 *
 * @author diego
 */
public class controladorCrearFactura {
    
    DateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd");
    static vistas.vistaFacturasP vistaF;
    
     public String crearFacXML(String[] idOts, String valorNeto, String valorIva, String valorTotal, String fac){
        
        String fecha = formatDate.format(new Date());
        
        try{
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            
            Document doc = docBuilder.newDocument();
            
            //DOCUMENTO
            Element rootElement = doc.createElement("Documento");
            doc.appendChild(rootElement);
            
            //ENCABEZADO
            Element encabezado = doc.createElement("Encabezado");
            rootElement.appendChild(encabezado);
            
            //ID DOC
            Element id = doc.createElement("IdDoc");
            encabezado.appendChild(id);
            
            Element tipo = doc.createElement("TipoDTE");
            tipo.appendChild(doc.createTextNode("33"));
            id.appendChild(tipo);
            
            modelos.modeloFacturas mod = new modeloFacturas();
            String fol_fac = mod.obtenerFolioFac(fac);
            
            Element folio = doc.createElement("Folio");
            folio.appendChild(doc.createTextNode(fol_fac));
            id.appendChild(folio);
            
            Element fecEmision = doc.createElement("FchEmis");
            fecEmision.appendChild(doc.createTextNode(fecha));
            id.appendChild(fecEmision);
            
            Element fpago = doc.createElement("FmaPago");
            fpago.appendChild(doc.createTextNode("VER FORMA"));
            id.appendChild(fpago);
            
            Element mpago = doc.createElement("MedioPago");
            mpago.appendChild(doc.createTextNode("CH"));
            id.appendChild(mpago);
            
            //INFORMACION EMISOR
            Element emisor = doc.createElement("Emisor");
            encabezado.appendChild(emisor);
            
            Element rutEmisor = doc.createElement("RUTEmisor");
            rutEmisor.appendChild(doc.createTextNode("77037960-1"));
            emisor.appendChild(rutEmisor);
            
            Element rznEmisor = doc.createElement("RznSoc");
            rznEmisor.appendChild(doc.createTextNode("GRUAS HORQUILLA SANTA TERESITA F.M.LTDA"));
            emisor.appendChild(rznEmisor);
            
            Element giroEmisor = doc.createElement("GiroEmis");
            giroEmisor.appendChild(doc.createTextNode("ALQUILER DE MAQUINARIAS Y EQUIPOS"));
            emisor.appendChild(giroEmisor);
            
            Element actEmisor = doc.createElement("Acteco");
            actEmisor.appendChild(doc.createTextNode("712900"));
            emisor.appendChild(actEmisor);
            
            Element sucEmisor = doc.createElement("Sucursal");
            sucEmisor.appendChild(doc.createTextNode("712900"));
            emisor.appendChild(sucEmisor);
            
            Element dirEmisor = doc.createElement("DirOrigen");
            dirEmisor.appendChild(doc.createTextNode("ALFONSO DONOSO 1021"));
            emisor.appendChild(dirEmisor);
            
            Element cmnEmisor = doc.createElement("CmnaOrigen");
            cmnEmisor.appendChild(doc.createTextNode("SAN BERNARDO"));
            emisor.appendChild(cmnEmisor);
            
            Element ciuEmisor = doc.createElement("CiudadOrigen");
            ciuEmisor.appendChild(doc.createTextNode("SANTIAGO"));
            emisor.appendChild(ciuEmisor);
            
            //OBTENER LOS DATOS DEL CLIENTE
            modelos.modeloOts ots = new modelos.modeloOts();
            String[] data = ots.obtenerFacturaPorId(idOts[0]);
            
            //DATOS CLIENTE
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
            
            Element regRec = doc.createElement("CiudadRecep");
            regRec.appendChild(doc.createTextNode(data[4]));
            receptor.appendChild(regRec);
            
            //TOTALES
            Element totales = doc.createElement("Totales");
            encabezado.appendChild(totales);
            
            Element neto = doc.createElement("MntNeto");
            neto.appendChild(doc.createTextNode(valorNeto));
            totales.appendChild(neto);
            
            Element tasaiva = doc.createElement("TasaIVA");
            tasaiva.appendChild(doc.createTextNode("19.00"));
            totales.appendChild(tasaiva);
            
            Element iva = doc.createElement("IVA");
            iva.appendChild(doc.createTextNode(valorIva));
            totales.appendChild(iva);
            
            Element bruto = doc.createElement("MntTotal");
            bruto.appendChild(doc.createTextNode(valorTotal));
            totales.appendChild(bruto);
            
            //DETALLE
            Element detalle = doc.createElement("Detalle");
            rootElement.appendChild(detalle);
            
            for(int i = 0; i < idOts.length; i++){
                data = ots.obtenerFacturaPorId(idOts[i]);
                
                Element numLin = doc.createElement("NroLinDet");
                numLin.appendChild(doc.createTextNode(Integer.toString(i+1)));
                detalle.appendChild(numLin);
                
                Element codItem = doc.createElement("CdgItem");
                detalle.appendChild(codItem);
                
                Element tipoCod = doc.createElement("TpoCodigo");
                tipoCod.appendChild(doc.createTextNode("Interna"));
                codItem.appendChild(tipoCod);
                
                Element valCod = doc.createElement("VlrCodigo");
                valCod.appendChild(doc.createTextNode("1"));
                codItem.appendChild(valCod);
                
                Element nomItem = doc.createElement("NmbItem");
                nomItem.appendChild(doc.createTextNode(data[11]+" HORAS DE GRUA HORQUILLA O.T.:"+data[10]));
                detalle.appendChild(nomItem);
                
                Element mtoItem = doc.createElement("MtoItem");
                mtoItem.appendChild(doc.createTextNode(data[7]));
                detalle.appendChild(mtoItem);
            }
                        
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.ENCODING, "ISO-8859-1");
            DOMSource source = new DOMSource(doc);
            StreamResult result =   new StreamResult(new File("33_"+fol_fac+"_normal.xml"));
            
            transformer.transform(source, result);
            JOptionPane.showMessageDialog(vistaF, "Documento realizado con éxito");
            return "correcto";
            
        }catch(ParserConfigurationException pce){
            pce.printStackTrace();
            return "incorrecto";
        }catch(TransformerException tfe){
            tfe.printStackTrace();
            return "incorrecto";
        }
    }
    
    public String crearFacExXML(String[] idOts, String valorNeto, String valorIva, String valorTotal, String fac){
        
        String fecha = formatDate.format(new Date());
        
        try{
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            
            Document doc = docBuilder.newDocument();
            
            //DOCUMENTO
            Element rootElement = doc.createElement("Documento");
            doc.appendChild(rootElement);
            
            //ENCABEZADO
            Element encabezado = doc.createElement("Encabezado");
            rootElement.appendChild(encabezado);
            
            //ID DOC
            Element id = doc.createElement("IdDoc");
            encabezado.appendChild(id);
            
            Element tipo = doc.createElement("TipoDTE");
            tipo.appendChild(doc.createTextNode("34"));
            id.appendChild(tipo);
            
            modelos.modeloFacturas mod = new modeloFacturas();
            String fol_facex = mod.obtenerFolioFac(fac);
            
            Element folio = doc.createElement("Folio");
            folio.appendChild(doc.createTextNode(fol_facex));
            id.appendChild(folio);
            
            Element fecEmision = doc.createElement("FchEmis");
            fecEmision.appendChild(doc.createTextNode(fecha));
            id.appendChild(fecEmision);
            
            Element fpago = doc.createElement("FmaPago");
            fpago.appendChild(doc.createTextNode("VER FORMA"));
            id.appendChild(fpago);
            
            Element mpago = doc.createElement("MedioPago");
            mpago.appendChild(doc.createTextNode("CH"));
            id.appendChild(mpago);
            
            //INFORMACION EMISOR
            Element emisor = doc.createElement("Emisor");
            encabezado.appendChild(emisor);
            
            Element rutEmisor = doc.createElement("RUTEmisor");
            rutEmisor.appendChild(doc.createTextNode("77037960-1"));
            emisor.appendChild(rutEmisor);
            
            Element rznEmisor = doc.createElement("RznSoc");
            rznEmisor.appendChild(doc.createTextNode("GRUAS HORQUILLA SANTA TERESITA F.M.LTDA"));
            emisor.appendChild(rznEmisor);
            
            Element giroEmisor = doc.createElement("GiroEmis");
            giroEmisor.appendChild(doc.createTextNode("ALQUILER DE MAQUINARIAS Y EQUIPOS"));
            emisor.appendChild(giroEmisor);
            
            Element actEmisor = doc.createElement("Acteco");
            actEmisor.appendChild(doc.createTextNode("712900"));
            emisor.appendChild(actEmisor);
            
            Element sucEmisor = doc.createElement("Sucursal");
            sucEmisor.appendChild(doc.createTextNode("712900"));
            emisor.appendChild(sucEmisor);
            
            Element dirEmisor = doc.createElement("DirOrigen");
            dirEmisor.appendChild(doc.createTextNode("ALFONSO DONOSO 1021"));
            emisor.appendChild(dirEmisor);
            
            Element cmnEmisor = doc.createElement("CmnaOrigen");
            cmnEmisor.appendChild(doc.createTextNode("SAN BERNARDO"));
            emisor.appendChild(cmnEmisor);
            
            Element ciuEmisor = doc.createElement("CiudadOrigen");
            ciuEmisor.appendChild(doc.createTextNode("SANTIAGO"));
            emisor.appendChild(ciuEmisor);
            
            //OBTENER LOS DATOS DEL CLIENTE
            modelos.modeloOts ots = new modelos.modeloOts();
            String[] data = ots.obtenerFacturaPorId(idOts[0]);
            
            //DATOS CLIENTE
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
            
            Element regRec = doc.createElement("CiudadRecep");
            regRec.appendChild(doc.createTextNode(data[4]));
            receptor.appendChild(regRec);
            
            //TOTALES
            Element totales = doc.createElement("Totales");
            encabezado.appendChild(totales);
            
            Element neto = doc.createElement("MntExe");
            neto.appendChild(doc.createTextNode(valorNeto));
            totales.appendChild(neto);
                        
            Element bruto = doc.createElement("MntTotal");
            bruto.appendChild(doc.createTextNode(valorNeto));
            totales.appendChild(bruto);
            
            //DETALLE
            Element detalle = doc.createElement("Detalle");
            rootElement.appendChild(detalle);
            
            for(int i = 0; i < idOts.length; i++){
                data = ots.obtenerFacturaPorId(idOts[i]);
                
                Element numLin = doc.createElement("NroLinDet");
                numLin.appendChild(doc.createTextNode(Integer.toString(i+1)));
                detalle.appendChild(numLin);
                
                Element codItem = doc.createElement("CdgItem");
                detalle.appendChild(codItem);
                
                Element tipoCod = doc.createElement("TpoCodigo");
                tipoCod.appendChild(doc.createTextNode("Interna"));
                codItem.appendChild(tipoCod);
                
                Element valCod = doc.createElement("VlrCodigo");
                valCod.appendChild(doc.createTextNode("1"));
                codItem.appendChild(valCod);
                
                Element nomItem = doc.createElement("NmbItem");
                nomItem.appendChild(doc.createTextNode(data[11]+" HORAS DE GRUA HORQUILLA O.T.:"+data[10]));
                detalle.appendChild(nomItem);
                
                Element mtoItem = doc.createElement("MtoItem");
                mtoItem.appendChild(doc.createTextNode(data[7]));
                detalle.appendChild(mtoItem);
            }
                        
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.ENCODING, "ISO-8859-1");
            DOMSource source = new DOMSource(doc);
            StreamResult result =   new StreamResult(new File("34_"+fol_facex+"_exenta.xml"));
            
            transformer.transform(source, result);
            JOptionPane.showMessageDialog(vistaF, "Documento realizado con éxito");
            return "correcto";
            
        }catch(ParserConfigurationException pce){
            pce.printStackTrace();
            return "incorrecto";
        }catch(TransformerException tfe){
            tfe.printStackTrace();
            return "incorrecto";
        }
    }
    
    public String crearNotaCredXML(String id_nc, String[] valores_nc, String[] ots_nc, String razon, String fac){
        
        String fecha = formatDate.format(new Date());
        
        try{
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            
            Document doc = docBuilder.newDocument();
            
            //DOCUMENTO
            Element rootElement = doc.createElement("Documento");
            doc.appendChild(rootElement);
            
            //ENCABEZADO
            Element encabezado = doc.createElement("Encabezado");
            rootElement.appendChild(encabezado);
            
            //ID DOC
            Element id = doc.createElement("IdDoc");
            encabezado.appendChild(id);
            
            Element tipo = doc.createElement("TipoDTE");
            tipo.appendChild(doc.createTextNode("61"));
            id.appendChild(tipo);
            
            modelos.modeloFacturas mod = new modeloFacturas();
            String fol_nc = mod.obtenerFolioNC(id_nc);
            
            Element folio = doc.createElement("Folio");
            folio.appendChild(doc.createTextNode(fol_nc));
            id.appendChild(folio);
            
            Element fecEmision = doc.createElement("FchEmis");
            fecEmision.appendChild(doc.createTextNode(fecha));
            id.appendChild(fecEmision);
            
            Element fpago = doc.createElement("FmaPago");
            fpago.appendChild(doc.createTextNode("VER FORMA"));
            id.appendChild(fpago);
            
            Element mpago = doc.createElement("MedioPago");
            mpago.appendChild(doc.createTextNode("CH"));
            id.appendChild(mpago);
            
            Element fven = doc.createElement("FchaVenc");
            fven.appendChild(doc.createTextNode(fecha));
            id.appendChild(fven);
            
            //DATOS EMISOR
            Element emisor = doc.createElement("Emisor");
            encabezado.appendChild(emisor);
            
            Element rutEmisor = doc.createElement("RUTEmisor");
            rutEmisor.appendChild(doc.createTextNode("77037960-1"));
            emisor.appendChild(rutEmisor);
            
            Element rznEmisor = doc.createElement("RznSoc");
            rznEmisor.appendChild(doc.createTextNode("GRUAS HORQUILLA SANTA TERESITA F.M.LTDA"));
            emisor.appendChild(rznEmisor);
            
            Element giroEmisor = doc.createElement("GiroEmis");
            giroEmisor.appendChild(doc.createTextNode("ALQUILER DE MAQUINARIAS Y EQUIPOS"));
            emisor.appendChild(giroEmisor);
            
            Element actEmisor = doc.createElement("Acteco");
            actEmisor.appendChild(doc.createTextNode("712900"));
            emisor.appendChild(actEmisor);
            
            Element sucEmisor = doc.createElement("Sucursal");
            sucEmisor.appendChild(doc.createTextNode("712900"));
            emisor.appendChild(sucEmisor);
            
            Element dirEmisor = doc.createElement("DirOrigen");
            dirEmisor.appendChild(doc.createTextNode("ALFONSO DONOSO 1021"));
            emisor.appendChild(dirEmisor);
            
            Element cmnEmisor = doc.createElement("CmnaOrigen");
            cmnEmisor.appendChild(doc.createTextNode("SAN BERNARDO"));
            emisor.appendChild(cmnEmisor);
            
            Element ciuEmisor = doc.createElement("CiudadOrigen");
            ciuEmisor.appendChild(doc.createTextNode("SANTIAGO"));
            emisor.appendChild(ciuEmisor);
            
            modelos.modeloOts ots = new modelos.modeloOts();
            String[] data = ots.obtenerFacturaPorId(ots_nc[0]);
            
            //DATOS CLIENTE
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
            
            Element regRec = doc.createElement("CiudadRecep");
            regRec.appendChild(doc.createTextNode(data[4]));
            receptor.appendChild(regRec);
            
            //TOTALES
            Element totales = doc.createElement("Totales");
            encabezado.appendChild(totales);
            
            Element neto = doc.createElement("MntNeto");
            neto.appendChild(doc.createTextNode(valores_nc[1]));
            totales.appendChild(neto);
            
            Element tasaiva = doc.createElement("TasaIVA");
            tasaiva.appendChild(doc.createTextNode("19.00"));
            totales.appendChild(tasaiva);
            
            Element iva = doc.createElement("IVA");
            iva.appendChild(doc.createTextNode(valores_nc[2]));
            totales.appendChild(iva);
            
            Element bruto = doc.createElement("MntTotal");
            bruto.appendChild(doc.createTextNode(valores_nc[3]));
            totales.appendChild(bruto);
            
            //DETALLE
            Element detalle = doc.createElement("Detalle");
            rootElement.appendChild(detalle);
            
            for(int i = 0; i < ots_nc.length; i++){
                data = ots.obtenerFacturaPorId(ots_nc[i]);
                
                Element numLin = doc.createElement("NroLinDet");
                numLin.appendChild(doc.createTextNode(Integer.toString(i+1)));
                detalle.appendChild(numLin);
                
                Element codItem = doc.createElement("CdgItem");
                detalle.appendChild(codItem);
                
                Element tipoCod = doc.createElement("TpoCodigo");
                tipoCod.appendChild(doc.createTextNode("Interna"));
                codItem.appendChild(tipoCod);
                
                Element valCod = doc.createElement("VlrCodigo");
                valCod.appendChild(doc.createTextNode("1"));
                codItem.appendChild(valCod);
                
                Element nomItem = doc.createElement("NmbItem");
                nomItem.appendChild(doc.createTextNode(data[11]+" HORAS DE GRUA HORQUILLA O.T.:"+data[10]));
                detalle.appendChild(nomItem);
                
                Element mtoItem = doc.createElement("MtoItem");
                mtoItem.appendChild(doc.createTextNode(data[7]));
                detalle.appendChild(mtoItem);

            }
            
            Element ref = doc.createElement("Referencia");
            encabezado.appendChild(ref);
            
            Element nrolin = doc.createElement("NroLinRef");
            nrolin.appendChild(doc.createTextNode("1"));
            ref.appendChild(nrolin);
            
            Element tporef = doc.createElement("TpoDocRef");
            tporef.appendChild(doc.createTextNode("33"));
            ref.appendChild(tporef);
            
            String fol_fac = mod.obtenerFolioFac(fac);
            
            Element folioref = doc.createElement("FolioRef");
            folioref.appendChild(doc.createTextNode(fol_fac));
            ref.appendChild(folioref);
            
            String fec_ref = mod.obtenerFechaFac(fac);
            
            Element fecref = doc.createElement("FchRef");
            fecref.appendChild(doc.createTextNode(fec_ref));
            ref.appendChild(fecref);
            
            Element codref = doc.createElement("CodRef");
            codref.appendChild(doc.createTextNode("1"));
            ref.appendChild(codref);
            
            Element razref = doc.createElement("RazonRef");
            razref.appendChild(doc.createTextNode(razon));
            ref.appendChild(razref);
                        
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.ENCODING, "ISO-8859-1");
            DOMSource source = new DOMSource(doc);
            StreamResult result =   new StreamResult(new File("61_"+fol_nc+"_anula_33.xml"));
            
            transformer.transform(source, result);
            JOptionPane.showMessageDialog(vistaF, "Documento realizado con éxito");
            return "correcto";
            
        }catch(ParserConfigurationException pce){
            pce.printStackTrace();
            return "incorrecto";
        }catch(TransformerException tfe){
            tfe.printStackTrace();
            return "incorrecto";
        }
    }
    
    public String crearNotaDebXML(String[] idOts, String valorNeto, String valorIva, String valorTotal, String fac, String id_fac){
        
        String fecha = formatDate.format(new Date());
        
        try{
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            
            Document doc = docBuilder.newDocument();
            
            //DOCUMENTO
            Element rootElement = doc.createElement("Documento");
            doc.appendChild(rootElement);
            
            //ENCABEZADO
            Element encabezado = doc.createElement("Encabezado");
            rootElement.appendChild(encabezado);
            
            //ID DOC
            Element id = doc.createElement("IdDoc");
            encabezado.appendChild(id);
            
            Element tipo = doc.createElement("TipoDTE");
            tipo.appendChild(doc.createTextNode("56"));
            id.appendChild(tipo);
            
            modelos.modeloFacturas mod = new modeloFacturas();
            String fol_nd = mod.obtenerFolioND(fac);
            
            Element folio = doc.createElement("Folio");
            folio.appendChild(doc.createTextNode(fol_nd));
            id.appendChild(folio);
            
            Element fecEmision = doc.createElement("FchEmis");
            fecEmision.appendChild(doc.createTextNode(fecha));
            id.appendChild(fecEmision);
            
            Element fpago = doc.createElement("FmaPago");
            fpago.appendChild(doc.createTextNode("VER FORMA"));
            id.appendChild(fpago);
            
            Element mpago = doc.createElement("MedioPago");
            mpago.appendChild(doc.createTextNode("CH"));
            id.appendChild(mpago);
            
            //DATOS EMISOR
            Element emisor = doc.createElement("Emisor");
            encabezado.appendChild(emisor);
            
            Element rutEmisor = doc.createElement("RUTEmisor");
            rutEmisor.appendChild(doc.createTextNode("77037960-1"));
            emisor.appendChild(rutEmisor);
            
            Element rznEmisor = doc.createElement("RznSoc");
            rznEmisor.appendChild(doc.createTextNode("GRUAS HORQUILLA SANTA TERESITA F.M.LTDA"));
            emisor.appendChild(rznEmisor);
            
            Element giroEmisor = doc.createElement("GiroEmis");
            giroEmisor.appendChild(doc.createTextNode("ALQUILER DE MAQUINARIAS Y EQUIPOS"));
            emisor.appendChild(giroEmisor);
            
            Element actEmisor = doc.createElement("Acteco");
            actEmisor.appendChild(doc.createTextNode("712900"));
            emisor.appendChild(actEmisor);
            
            Element sucEmisor = doc.createElement("Sucursal");
            sucEmisor.appendChild(doc.createTextNode("712900"));
            emisor.appendChild(sucEmisor);
            
            Element dirEmisor = doc.createElement("DirOrigen");
            dirEmisor.appendChild(doc.createTextNode("ALFONSO DONOSO 1021"));
            emisor.appendChild(dirEmisor);
            
            Element cmnEmisor = doc.createElement("CmnaOrigen");
            cmnEmisor.appendChild(doc.createTextNode("SAN BERNARDO"));
            emisor.appendChild(cmnEmisor);
            
            Element ciuEmisor = doc.createElement("CiudadOrigen");
            ciuEmisor.appendChild(doc.createTextNode("SANTIAGO"));
            emisor.appendChild(ciuEmisor);
            
            modelos.modeloOts ots = new modelos.modeloOts();
            String[] data = ots.obtenerFacturaPorId(idOts[0]);
            
            //DATOS CLIENTE
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
            
            Element regRec = doc.createElement("CiudadRecep");
            regRec.appendChild(doc.createTextNode(data[4]));
            receptor.appendChild(regRec);
            
            //TOTALES            
            Element totales = doc.createElement("Totales");
            encabezado.appendChild(totales);
            
            Element neto = doc.createElement("MntNeto");
            neto.appendChild(doc.createTextNode(valorNeto));
            totales.appendChild(neto);
            
            Element tasaiva = doc.createElement("TasaIVA");
            tasaiva.appendChild(doc.createTextNode("19.00"));
            totales.appendChild(tasaiva);
            
            Element iva = doc.createElement("IVA");
            iva.appendChild(doc.createTextNode(valorIva));
            totales.appendChild(iva);
            
            Element bruto = doc.createElement("MntTotal");
            bruto.appendChild(doc.createTextNode(valorTotal));
            totales.appendChild(bruto);
            
            //DETALLE
            Element detalle = doc.createElement("Detalle");
            rootElement.appendChild(detalle);
            
            for(int i = 0; i < idOts.length; i++){
                data = ots.obtenerFacturaPorId(idOts[i]);
                
                Element numLin = doc.createElement("NroLinDet");
                numLin.appendChild(doc.createTextNode(Integer.toString(i+1)));
                detalle.appendChild(numLin);
                
                Element codItem = doc.createElement("CdgItem");
                detalle.appendChild(codItem);
                
                Element tipoCod = doc.createElement("TpoCodigo");
                tipoCod.appendChild(doc.createTextNode("Interna"));
                codItem.appendChild(tipoCod);
                
                Element valCod = doc.createElement("VlrCodigo");
                valCod.appendChild(doc.createTextNode("1"));
                codItem.appendChild(valCod);
                
                Element nomItem = doc.createElement("NmbItem");
                nomItem.appendChild(doc.createTextNode(data[11]+" HORAS DE GRUA HORQUILLA O.T.:"+data[10]));
                detalle.appendChild(nomItem);
                
                Element mtoItem = doc.createElement("MtoItem");
                mtoItem.appendChild(doc.createTextNode(data[7]));
                detalle.appendChild(mtoItem);
            }
                        
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.ENCODING, "ISO-8859-1");
            DOMSource source = new DOMSource(doc);
            StreamResult result =   new StreamResult(new File("56_"+fol_nd+"_normal.xml"));
            
            transformer.transform(source, result);
            JOptionPane.showMessageDialog(vistaF, "Documento realizado con éxito");
            return "correcto";
            
        }catch(ParserConfigurationException pce){
            pce.printStackTrace();
            return "incorrecto";
        }catch(TransformerException tfe){
            tfe.printStackTrace();
            return "incorrecto";
        }
    }
    
    public String crearBolXML(String[] idOts, String valorNeto, String valorIva, String valorTotal, String fac){
        
        String fecha = formatDate.format(new Date());
        
        try{
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            
            Document doc = docBuilder.newDocument();
            
            //DOCUMENTO
            Element rootElement = doc.createElement("Documento");
            doc.appendChild(rootElement);
            
            //ENCABEZADO
            Element encabezado = doc.createElement("Encabezado");
            rootElement.appendChild(encabezado);
            
            //ID DOC
            Element id = doc.createElement("IdDoc");
            encabezado.appendChild(id);
            
            Element tipo = doc.createElement("TipoDTE");
            tipo.appendChild(doc.createTextNode("39"));
            id.appendChild(tipo);
            
            modelos.modeloFacturas mod = new modeloFacturas();
            String fol_bol = mod.obtenerFolioFac(fac);
            
            Element folio = doc.createElement("Folio");
            folio.appendChild(doc.createTextNode(fol_bol));
            id.appendChild(folio);
            
            Element fecEmision = doc.createElement("FchEmis");
            fecEmision.appendChild(doc.createTextNode(fecha));
            id.appendChild(fecEmision);
            
            Element serv = doc.createElement("IndServicio");
            serv.appendChild(doc.createTextNode("VER FORMA")); //1: boleta servicios periodicos
                                                                //2: boleta servicios per. domiciliarios
                                                                //3: boleta de ventas y servicios
            id.appendChild(serv);
            
            //DATOS EMISOR
            Element emisor = doc.createElement("Emisor");
            encabezado.appendChild(emisor);
            
            Element rutEmisor = doc.createElement("RUTEmisor");
            rutEmisor.appendChild(doc.createTextNode("77037960-1"));
            emisor.appendChild(rutEmisor);
            
            Element rznEmisor = doc.createElement("RznSoc");
            rznEmisor.appendChild(doc.createTextNode("GRUAS HORQUILLA SANTA TERESITA F.M.LTDA"));
            emisor.appendChild(rznEmisor);
            
            Element giroEmisor = doc.createElement("GiroEmis");
            giroEmisor.appendChild(doc.createTextNode("ALQUILER DE MAQUINARIAS Y EQUIPOS"));
            emisor.appendChild(giroEmisor);
            
            Element actEmisor = doc.createElement("Acteco");
            actEmisor.appendChild(doc.createTextNode("712900"));
            emisor.appendChild(actEmisor);
            
            Element sucEmisor = doc.createElement("Sucursal");
            sucEmisor.appendChild(doc.createTextNode("712900"));
            emisor.appendChild(sucEmisor);
            
            Element dirEmisor = doc.createElement("DirOrigen");
            dirEmisor.appendChild(doc.createTextNode("ALFONSO DONOSO 1021"));
            emisor.appendChild(dirEmisor);
            
            Element cmnEmisor = doc.createElement("CmnaOrigen");
            cmnEmisor.appendChild(doc.createTextNode("SAN BERNARDO"));
            emisor.appendChild(cmnEmisor);
            
            Element ciuEmisor = doc.createElement("CiudadOrigen");
            ciuEmisor.appendChild(doc.createTextNode("SANTIAGO"));
            emisor.appendChild(ciuEmisor);
            
            //DATOS CLIENTE
            modelos.modeloOts ots = new modelos.modeloOts();
            String[] data = ots.obtenerFacturaPorId(idOts[0]);
            
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
            
            Element regRec = doc.createElement("CiudadRecep");
            regRec.appendChild(doc.createTextNode(data[4]));
            receptor.appendChild(regRec);
            
            //TOTALES
            Element totales = doc.createElement("Totales");
            encabezado.appendChild(totales);
            
            Element bruto = doc.createElement("MntTotal");
            bruto.appendChild(doc.createTextNode(valorTotal));
            totales.appendChild(bruto);
            
            //DETALLE
            Element detalle = doc.createElement("Detalle");
            rootElement.appendChild(detalle);
            
            for(int i = 0; i < idOts.length; i++){
                data = ots.obtenerFacturaPorId(idOts[i]);
                
                Element numLin = doc.createElement("NroLinDet");
                numLin.appendChild(doc.createTextNode(Integer.toString(i+1)));
                detalle.appendChild(numLin);
                
                Element codItem = doc.createElement("CdgItem");
                detalle.appendChild(codItem);
                
                Element tipoCod = doc.createElement("TpoCodigo");
                tipoCod.appendChild(doc.createTextNode("Interna"));
                codItem.appendChild(tipoCod);
                
                Element valCod = doc.createElement("VlrCodigo");
                valCod.appendChild(doc.createTextNode("1"));
                codItem.appendChild(valCod);
                
                Element nomItem = doc.createElement("NmbItem");
                nomItem.appendChild(doc.createTextNode(data[11]+" HORAS DE GRUA HORQUILLA O.T.:"+data[10]));
                detalle.appendChild(nomItem);
                
                Element mtoItem = doc.createElement("MtoItem");
                mtoItem.appendChild(doc.createTextNode(data[7]));
                detalle.appendChild(mtoItem);
                
            }
                        
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.ENCODING, "ISO-8859-1");
            DOMSource source = new DOMSource(doc);
            StreamResult result =   new StreamResult(new File("boleta_39_"+fac+".xml"));
            
            transformer.transform(source, result);
            JOptionPane.showMessageDialog(vistaF, "Documento realizado con éxito");
            return "correcto";
            
        }catch(ParserConfigurationException pce){
            pce.printStackTrace();
            return "incorrecto";
        }catch(TransformerException tfe){
            tfe.printStackTrace();
            return "incorrecto";
        }
    }
    
    public String crearGuiaDespXML(String[] idOts, String fac){
        
        String fecha = formatDate.format(new Date());
        
        try{
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            
            Document doc = docBuilder.newDocument();
            
            //DOCUMENTO
            Element rootElement = doc.createElement("Documento");
            doc.appendChild(rootElement);
            
            //ENCABEZADO
            Element encabezado = doc.createElement("Encabezado");
            rootElement.appendChild(encabezado);
            
            //ID DOC
            Element id = doc.createElement("IdDoc");
            encabezado.appendChild(id);
            
            Element tipo = doc.createElement("TipoDTE");
            tipo.appendChild(doc.createTextNode("52"));
            id.appendChild(tipo);
            
            modelos.modeloFacturas gd = new modelos.modeloFacturas();
            String num_fol = gd.folioGuiaDesp();
            
            Element folio = doc.createElement("Folio");
            folio.appendChild(doc.createTextNode(num_fol));
            id.appendChild(folio);
            
            Element fecEmision = doc.createElement("FchEmis");
            fecEmision.appendChild(doc.createTextNode(fecha));
            id.appendChild(fecEmision);
            
            Element fpago = doc.createElement("FmaPago");
            fpago.appendChild(doc.createTextNode("VER FORMA"));
            id.appendChild(fpago);
            
            Element mpago = doc.createElement("MedioPago");
            mpago.appendChild(doc.createTextNode("CH"));
            id.appendChild(mpago);
            
            //INFORMACION EMISOR
            Element emisor = doc.createElement("Emisor");
            encabezado.appendChild(emisor);
            
            Element rutEmisor = doc.createElement("RUTEmisor");
            rutEmisor.appendChild(doc.createTextNode("77037960-1"));
            emisor.appendChild(rutEmisor);
            
            Element rznEmisor = doc.createElement("RznSoc");
            rznEmisor.appendChild(doc.createTextNode("GRUAS HORQUILLA SANTA TERESITA F.M.LTDA"));
            emisor.appendChild(rznEmisor);
            
            Element giroEmisor = doc.createElement("GiroEmis");
            giroEmisor.appendChild(doc.createTextNode("ALQUILER DE MAQUINARIAS Y EQUIPOS"));
            emisor.appendChild(giroEmisor);
            
            Element actEmisor = doc.createElement("Acteco");
            actEmisor.appendChild(doc.createTextNode("712900"));
            emisor.appendChild(actEmisor);
            
            Element sucEmisor = doc.createElement("Sucursal");
            sucEmisor.appendChild(doc.createTextNode("712900"));
            emisor.appendChild(sucEmisor);
            
            Element dirEmisor = doc.createElement("DirOrigen");
            dirEmisor.appendChild(doc.createTextNode("ALFONSO DONOSO 1021"));
            emisor.appendChild(dirEmisor);
            
            Element cmnEmisor = doc.createElement("CmnaOrigen");
            cmnEmisor.appendChild(doc.createTextNode("SAN BERNARDO"));
            emisor.appendChild(cmnEmisor);
            
            Element ciuEmisor = doc.createElement("CiudadOrigen");
            ciuEmisor.appendChild(doc.createTextNode("SANTIAGO"));
            emisor.appendChild(ciuEmisor);
            
            //OBTENER LOS DATOS DEL CLIENTE
            modelos.modeloOts ots = new modelos.modeloOts();
            String[] data = ots.obtenerFacturaPorId(idOts[0]);
            
            //DATOS CLIENTE
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
            
            Element regRec = doc.createElement("CiudadRecep");
            regRec.appendChild(doc.createTextNode(data[4]));
            receptor.appendChild(regRec);
            
            //DETALLE
            Element detalle = doc.createElement("Detalle");
            rootElement.appendChild(detalle);
            
            for(int i = 0; i < idOts.length; i++){
                data = ots.obtenerFacturaPorId(idOts[i]);
                
                Element numLin = doc.createElement("NroLinDet");
                numLin.appendChild(doc.createTextNode(Integer.toString(i+1)));
                detalle.appendChild(numLin);
                
                Element codItem = doc.createElement("CdgItem");
                detalle.appendChild(codItem);
                
                Element tipoCod = doc.createElement("TpoCodigo");
                tipoCod.appendChild(doc.createTextNode("Interna"));
                codItem.appendChild(tipoCod);
                
                Element valCod = doc.createElement("VlrCodigo");
                valCod.appendChild(doc.createTextNode("1"));
                codItem.appendChild(valCod);
                
                Element nomItem = doc.createElement("NmbItem");
                nomItem.appendChild(doc.createTextNode(data[11]+" HORAS DE GRUA HORQUILLA O.T.:"+data[10]));
                detalle.appendChild(nomItem);
            }
                        
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.ENCODING, "ISO-8859-1");
            DOMSource source = new DOMSource(doc);
            StreamResult result =   new StreamResult(new File("33_"+num_fol+"_REF_GUIA.xml"));
            
            transformer.transform(source, result);
            JOptionPane.showMessageDialog(vistaF, "Documento realizado con éxito");
            return "correcto";
            
        }catch(ParserConfigurationException pce){
            pce.printStackTrace();
            return "incorrecto";
        }catch(TransformerException tfe){
            tfe.printStackTrace();
            return "incorrecto";
        }
    }
    
    public void crearControladorPrincipal(JTabbedPane tabs) {
        controladorPrincipal miControlador = new controladorPrincipal();
        tabs.remove(5);
        tabs.remove(6);
        tabs.insertTab("A facturar", null, miControlador.crearControladorFacturasP(), null, 5);
        tabs.insertTab("Histórico Ots", null, miControlador.crearControladorHistoricoP(), null, 7);
        tabs.setSelectedIndex(5);
    }
}
