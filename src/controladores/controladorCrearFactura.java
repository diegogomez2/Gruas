/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import java.io.File;
import java.io.PrintStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;
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
import modelos.modeloJornadas;
import modelos.modeloOts;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 *
 * @author diego
 */
public class controladorCrearFactura {
    
    DateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd");
    static vistas.vistaFacturasP vistaF;
    
     public String crearFacXML(String[] idOts, String valorNeto, String valorIva, String valorTotal, String fac) throws ParseException{
        
         modeloFacturas rutas = new modeloFacturas();
         String ruta = rutas.obtenerRuta();
         System.out.println("ruta1: " + ruta);
         File file = new File("test.log");
            PrintStream ps = null;
            try{
                ps = new PrintStream(file);
            }catch(Exception e){
                
            }
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
            
            modelos.modeloOts modOt = new modeloOts();
            String form = modOt.obtenerFormaPago(idOts[0]);
            
//            Element fpago = doc.createElement("FmaPago");
//            fpago.appendChild(doc.createTextNode(form));
//            id.appendChild(fpago);
            
//            Element mpago = doc.createElement("MedioPago");
//            mpago.appendChild(doc.createTextNode("CH"));
//            id.appendChild(mpago);
            
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
            boolean checkHoraMin = true;
            if(data[12].compareTo("0") == 0) checkHoraMin = false;
            
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
            
            
            int contador = 0;
            for(int i = 0; i < idOts.length; i++){
                modelos.modeloJornadas jornada = new modeloJornadas();
                String[] datosDias;
                data = ots.obtenerFacturaPorId(idOts[i]);
                datosDias = ots.obtenerDiasPorIdOt(idOts[i]);
                controladores.controladorIngresarOts miControlador = new controladores.controladorIngresarOts();
//                System.out.println("datos "+datosDias[0]);
                List<List<String>> valores = miControlador.calcularTarifa(datosDias[0], datosDias[1], datosDias[2],
                        datosDias[3], datosDias[4], checkHoraMin);
                String[] infoDespacho = ots.obtenerValorDespachoOt(idOts[i]);
                
                for (int j = 0; j < valores.size() - 1; j++) {
                    //DETALLE
                    Element detalle = doc.createElement("Detalle");
                    rootElement.appendChild(detalle);
                    
                    Element numLin = doc.createElement("NroLinDet");
                    numLin.appendChild(doc.createTextNode(Integer.toString(contador+1)));
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
                    //nomItem.appendChild(doc.createTextNode(data[11]+" HORAS DE GRUA HORQUILLA O.T.:"+data[10]));
                    nomItem.appendChild(doc.createTextNode(valores.get(j).get(3)+" HORAS DE GRUA HORQUILLA O.T.:"+data[10]));
                    detalle.appendChild(nomItem);

                    Element qtyItem = doc.createElement("QtyItem");
                    //nomItem.appendChild(doc.createTextNode(data[11]+" HORAS DE GRUA HORQUILLA O.T.:"+data[10]));
                    qtyItem.appendChild(doc.createTextNode(valores.get(j).get(3)));
                    detalle.appendChild(qtyItem);
                    
                    Element prcItem = doc.createElement("PrcItem");
                    //nomItem.appendChild(doc.createTextNode(data[11]+" HORAS DE GRUA HORQUILLA O.T.:"+data[10]));
                    prcItem.appendChild(doc.createTextNode(valores.get(j).get(0)));
                    detalle.appendChild(prcItem);
                    
                    Element mtoItem = doc.createElement("MontoItem");
                    mtoItem.appendChild(doc.createTextNode(Integer.toString((int)(Float.parseFloat(valores.get(j).get(0))
                            * Float.parseFloat(valores.get(j).get(3))))));
                    detalle.appendChild(mtoItem);
                    
                    contador += 1;
                }
                
                if(infoDespacho[0].compareTo("1") == 0){
                    //DETALLE
                    Element detalle = doc.createElement("Detalle");
                    rootElement.appendChild(detalle);
                    
                    Element numLin = doc.createElement("NroLinDet");
                    numLin.appendChild(doc.createTextNode(Integer.toString(contador+1)));
                    detalle.appendChild(numLin);

                    Element codItem = doc.createElement("CdgItem");
                    detalle.appendChild(codItem);

                    Element tipoCod = doc.createElement("TpoCodigo");
                    tipoCod.appendChild(doc.createTextNode("Interna"));
                    codItem.appendChild(tipoCod);

                    Element valCod = doc.createElement("VlrCodigo");
                    valCod.appendChild(doc.createTextNode("2"));
                    codItem.appendChild(valCod);

                    Element nomItem = doc.createElement("NmbItem");
                    //nomItem.appendChild(doc.createTextNode(data[11]+" HORAS DE GRUA HORQUILLA O.T.:"+data[10]));
                    nomItem.appendChild(doc.createTextNode("TRASLADO GRUA A OBRA"));
                    detalle.appendChild(nomItem);

                    Element qtyItem = doc.createElement("QtyItem");
                    qtyItem.appendChild(doc.createTextNode("1"));
                    detalle.appendChild(qtyItem);
                    
                    Element prcItem = doc.createElement("PrcItem");
                    //nomItem.appendChild(doc.createTextNode(data[11]+" HORAS DE GRUA HORQUILLA O.T.:"+data[10]));
                    prcItem.appendChild(doc.createTextNode(infoDespacho[1]));
                    detalle.appendChild(prcItem);
                    
                    Element mtoItem = doc.createElement("MontoItem");
                    mtoItem.appendChild(doc.createTextNode(infoDespacho[1]));
                    detalle.appendChild(mtoItem);
                    
                    contador += 1;
                }
                
            }
                Element adj = doc.createElement("Adjuntos");
                rootElement.appendChild(adj);
            
                Element obs = doc.createElement("Observacion");
                obs.appendChild(doc.createTextNode("OBSERVACIONES"));
                adj.appendChild(obs);
                
                Element imp = doc.createElement("Impresora");
                imp.appendChild(doc.createTextNode("impresora"));
                adj.appendChild(imp);
                
                Element copias = doc.createElement("Copias");
                copias.appendChild(doc.createTextNode("2"));
                adj.appendChild(copias);
                        
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.ENCODING, "ISO-8859-1");
            DOMSource source = new DOMSource(doc);
            File f = new File(ruta);
            if(f.exists()){
//                StreamResult result =   new StreamResult(new File("C:/AppServ/www/eugcomdte/entregas/XML_pre/33_"+fol_fac+"_normal.xml"));
//                StreamResult result =   new StreamResult(new File("\\SGST/DTEService/input-pruebas/33_"+fol_fac+"_normal.xml"));
                System.out.println(ruta);
                StreamResult result =   new StreamResult(new File(ruta + "/33_"+fol_fac+"_normal.xml"));
                transformer.transform(source, result);
                JOptionPane.showMessageDialog(vistaF, "Documento realizado con éxito");
                return "correcto";
            }else{
                StreamResult result =   new StreamResult(new File("33_"+fol_fac+"_normal.xml"));
                transformer.transform(source, result);
                JOptionPane.showMessageDialog(vistaF, "Documento realizado con éxito en ruta por defecto");
                return "correcto";
            }            
        }catch(ParserConfigurationException | TransformerException pce){
            //pce.printStackTrace(ps);
            pce.printStackTrace();
            return "incorrecto";
        }
    }
    
    public String crearFacExXML(String[] idOts, String valorNeto, String valorIva, String valorTotal, String fac){
        
        modeloFacturas rutas = new modeloFacturas();
        String ruta = rutas.obtenerRuta();
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
            
            modelos.modeloOts modOt = new modeloOts();
            String form = modOt.obtenerFormaPago(idOts[0]);
            
//            Element fpago = doc.createElement("FmaPago");
//            fpago.appendChild(doc.createTextNode(form));
//            id.appendChild(fpago);
            
//            Element mpago = doc.createElement("MedioPago");
//            mpago.appendChild(doc.createTextNode("CH"));
//            id.appendChild(mpago);
            
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
                
                Element mtoItem = doc.createElement("MontoItem");
                mtoItem.appendChild(doc.createTextNode(data[7]));
                detalle.appendChild(mtoItem);
            }
                     
            Element adj = doc.createElement("Adjuntos");
                rootElement.appendChild(adj);
            
                Element obs = doc.createElement("Observacion");
                obs.appendChild(doc.createTextNode("OBSERVACIONES"));
                adj.appendChild(obs);
                
                Element imp = doc.createElement("Impresora");
                imp.appendChild(doc.createTextNode("impresora"));
                adj.appendChild(imp);
                
                Element copias = doc.createElement("Copias");
                copias.appendChild(doc.createTextNode("2"));
                adj.appendChild(copias);
                
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.ENCODING, "ISO-8859-1");
            DOMSource source = new DOMSource(doc);
//            File f = new File("C:/AppSersv/www/eugcomdte/entregas/XML_pre");
            File f = new File(ruta);
            if(f.exists()){
//                StreamResult result =   new StreamResult(new File("C:/AppServ/www/eugcomdte/entregas/XML_pre/34_"+fol_facex+"_exenta.xml"));
//                StreamResult result =   new StreamResult(new File("\\SGST/DTEService/input-pruebas/34_"+fol_facex+"_exenta.xml"));
                StreamResult result =   new StreamResult(new File(ruta + "/34_"+fol_facex+"_exenta.xml"));
                transformer.transform(source, result);
                JOptionPane.showMessageDialog(vistaF, "Documento realizado con éxito");
                return "correcto";
            }else{
                StreamResult result =   new StreamResult(new File("34_"+fol_facex+"_exenta.xml"));
                transformer.transform(source, result);
                JOptionPane.showMessageDialog(vistaF, "Documento realizado con éxito en ruta por defecto");
                return "correcto";
            }
            
        }catch(ParserConfigurationException pce){
            pce.printStackTrace();
            return "incorrecto";
        }catch(TransformerException tfe){
            tfe.printStackTrace();
            return "incorrecto";
        }
    }
    
    public String crearNotaCredXML(String id_nc, String[] valores_nc, String[] ots_nc, String razon, String fac, String tipo_fac) throws ParseException{
        
        modeloFacturas rutas = new modeloFacturas();
        String ruta = rutas.obtenerRuta();
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
            
            modelos.modeloOts modOt = new modeloOts();
            String form = modOt.obtenerFormaPago(ots_nc[0]);
            
//            Element fpago = doc.createElement("FmaPago");
//            fpago.appendChild(doc.createTextNode(form));
//            id.appendChild(fpago);
            
//            Element mpago = doc.createElement("MedioPago");
//            mpago.appendChild(doc.createTextNode("CH"));
//            id.appendChild(mpago);
            
            Element fven = doc.createElement("FchVenc");
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
            boolean checkHoraMin = true;
            if(data[12].compareTo("0") == 0) checkHoraMin = false;
            
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
            
//            //DETALLE
//            Element detalle = doc.createElement("Detalle");
//            rootElement.appendChild(detalle);
            
            int contador = 0;
            for(int i = 0; i < ots_nc.length; i++){
                modelos.modeloJornadas jornada = new modeloJornadas();
                String[] datosDias;
                data = ots.obtenerFacturaPorId(ots_nc[i]);
                datosDias = ots.obtenerDiasPorIdOt(ots_nc[i]);
                controladores.controladorIngresarOts miControlador = new controladores.controladorIngresarOts();
                List<List<String>> valores = miControlador.calcularTarifa(datosDias[0], datosDias[1], datosDias[2],
                        datosDias[3], datosDias[4], checkHoraMin);
                String[] infoDespacho = ots.obtenerValorDespachoOt(ots_nc[i]);
                
                for (int j = 0; j < valores.size() - 1; j++) {
                    //DETALLE
                    Element detalle = doc.createElement("Detalle");
                    rootElement.appendChild(detalle);
                    
                    Element numLin = doc.createElement("NroLinDet");
                    numLin.appendChild(doc.createTextNode(Integer.toString(contador+1)));
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
                    //nomItem.appendChild(doc.createTextNode(data[11]+" HORAS DE GRUA HORQUILLA O.T.:"+data[10]));
                    nomItem.appendChild(doc.createTextNode(valores.get(j).get(3)+" HORAS DE GRUA HORQUILLA O.T.:"+data[10]));
                    detalle.appendChild(nomItem);

                    Element qtyItem = doc.createElement("QtyItem");
                    //nomItem.appendChild(doc.createTextNode(data[11]+" HORAS DE GRUA HORQUILLA O.T.:"+data[10]));
                    qtyItem.appendChild(doc.createTextNode(valores.get(j).get(3)));
                    detalle.appendChild(qtyItem);
                    
                    Element prcItem = doc.createElement("PrcItem");
                    //nomItem.appendChild(doc.createTextNode(data[11]+" HORAS DE GRUA HORQUILLA O.T.:"+data[10]));
                    prcItem.appendChild(doc.createTextNode(valores.get(j).get(0)));
                    detalle.appendChild(prcItem);
                    
                    Element mtoItem = doc.createElement("MontoItem");
                    mtoItem.appendChild(doc.createTextNode(Integer.toString((int)(Float.parseFloat(valores.get(j).get(0))
                            * Float.parseFloat(valores.get(j).get(3))))));
                    detalle.appendChild(mtoItem);
                    
                    contador += 1;
                }
                
                if(infoDespacho[0].compareTo("1") == 0){
                    //DETALLE
                    Element detalle = doc.createElement("Detalle");
                    rootElement.appendChild(detalle);
                    
                    Element numLin = doc.createElement("NroLinDet");
                    numLin.appendChild(doc.createTextNode(Integer.toString(contador+1)));
                    detalle.appendChild(numLin);

                    Element codItem = doc.createElement("CdgItem");
                    detalle.appendChild(codItem);

                    Element tipoCod = doc.createElement("TpoCodigo");
                    tipoCod.appendChild(doc.createTextNode("Interna"));
                    codItem.appendChild(tipoCod);

                    Element valCod = doc.createElement("VlrCodigo");
                    valCod.appendChild(doc.createTextNode("2"));
                    codItem.appendChild(valCod);

                    Element nomItem = doc.createElement("NmbItem");
                    //nomItem.appendChild(doc.createTextNode(data[11]+" HORAS DE GRUA HORQUILLA O.T.:"+data[10]));
                    nomItem.appendChild(doc.createTextNode("TRASLADO GRUA A OBRA"));
                    detalle.appendChild(nomItem);

                    Element qtyItem = doc.createElement("QtyItem");
                    qtyItem.appendChild(doc.createTextNode("1"));
                    detalle.appendChild(qtyItem);
                    
                    Element prcItem = doc.createElement("PrcItem");
                    //nomItem.appendChild(doc.createTextNode(data[11]+" HORAS DE GRUA HORQUILLA O.T.:"+data[10]));
                    prcItem.appendChild(doc.createTextNode(infoDespacho[1]));
                    detalle.appendChild(prcItem);
                    
                    Element mtoItem = doc.createElement("MontoItem");
                    mtoItem.appendChild(doc.createTextNode(infoDespacho[1]));
                    detalle.appendChild(mtoItem);
                    
                    contador += 1;
                }
//                Element numLin = doc.createElement("NroLinDet");
//                numLin.appendChild(doc.createTextNode(Integer.toString(i+1)));
//                detalle.appendChild(numLin);
//                
//                Element codItem = doc.createElement("CdgItem");
//                detalle.appendChild(codItem);
//                
//                Element tipoCod = doc.createElement("TpoCodigo");
//                tipoCod.appendChild(doc.createTextNode("Interna"));
//                codItem.appendChild(tipoCod);
//                
//                Element valCod = doc.createElement("VlrCodigo");
//                valCod.appendChild(doc.createTextNode("1"));
//                codItem.appendChild(valCod);
//                
//                Element nomItem = doc.createElement("NmbItem");
//                nomItem.appendChild(doc.createTextNode(data[11]+" HORAS DE GRUA HORQUILLA O.T.:"+data[10]));
//                detalle.appendChild(nomItem);
//                
//                Element mtoItem = doc.createElement("MontoItem");
//                mtoItem.appendChild(doc.createTextNode(data[7]));
//                detalle.appendChild(mtoItem);

            }
            
            //REFERENCIA
            Element ref = doc.createElement("Referencia");
            rootElement.appendChild(ref);
            
            Element nrolin = doc.createElement("NroLinRef");
            nrolin.appendChild(doc.createTextNode("1"));
            ref.appendChild(nrolin);
            
            Element tporef = doc.createElement("TpoDocRef");
            tporef.appendChild(doc.createTextNode("33"));
            ref.appendChild(tporef);
            
//            String fol_fac = mod.obtenerFolioFac(fac);
            
            Element folioref = doc.createElement("FolioRef");
            folioref.appendChild(doc.createTextNode(fac));
            ref.appendChild(folioref);
            
            String fec_ref = mod.obtenerFechaFac(fac, tipo_fac);
            
            Element fecref = doc.createElement("FchRef");
            fecref.appendChild(doc.createTextNode(fec_ref));
            ref.appendChild(fecref);
            
            Element codref = doc.createElement("CodRef");
            codref.appendChild(doc.createTextNode("1"));
            ref.appendChild(codref);
            
            Element razref = doc.createElement("RazonRef");
            razref.appendChild(doc.createTextNode(razon));
            ref.appendChild(razref);
                      
            Element adj = doc.createElement("Adjuntos");
                rootElement.appendChild(adj);
            
                Element obs = doc.createElement("Observacion");
                obs.appendChild(doc.createTextNode("Folio factura: " + fac));
                adj.appendChild(obs);
                
                Element imp = doc.createElement("Impresora");
                imp.appendChild(doc.createTextNode("impresora"));
                adj.appendChild(imp);
                
                Element copias = doc.createElement("Copias");
                copias.appendChild(doc.createTextNode("2"));
                adj.appendChild(copias);
                
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.ENCODING, "ISO-8859-1");
            DOMSource source = new DOMSource(doc);
//            File f = new File("C:/AppSersv/www/eugcomdte/entregas/XML_pre");
            File f = new File(ruta);
            if(f.exists()){
//                StreamResult result =   new StreamResult(new File("C:/AppServ/www/eugcomdte/entregas/XML_pre/61_"+fol_nc+"_anula_33.xml"));
//                StreamResult result =   new StreamResult(new File("\\SGST/DTEService/input-pruebas/61_"+fol_nc+"_anula_33.xml"));
                StreamResult result =   new StreamResult(new File(ruta + "/61_"+fol_nc+"_anula_33.xml"));
                transformer.transform(source, result);
                JOptionPane.showMessageDialog(vistaF, "Documento realizado con éxito");
                return "correcto"; 
            }
            else{
                StreamResult result =   new StreamResult(new File("61_"+fol_nc+"_anula_33.xml"));
                transformer.transform(source, result);
                JOptionPane.showMessageDialog(vistaF, "Documento realizado con éxito en ruta por defecto");
                return "correcto";
            }
            
        }catch(ParserConfigurationException pce){
            pce.printStackTrace();
            return "incorrecto";
        }catch(TransformerException tfe){
            tfe.printStackTrace();
            return "incorrecto";
        }
    }
    
    public String crearNotaCredNDXML(String id_nc, String[] valores_nc, String[] ots_nc, String razon, String fac, String tipo_fac) throws ParseException{
        
        modeloFacturas rutas = new modeloFacturas();
        String ruta = rutas.obtenerRuta();
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
            
            modelos.modeloOts modOt = new modeloOts();
            String form = modOt.obtenerFormaPago(ots_nc[0]);
            
//            Element fpago = doc.createElement("FmaPago");
//            fpago.appendChild(doc.createTextNode(form));
//            id.appendChild(fpago);
            
//            Element mpago = doc.createElement("MedioPago");
//            mpago.appendChild(doc.createTextNode("CH"));
//            id.appendChild(mpago);
            
            Element fven = doc.createElement("FchVenc");
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
            boolean checkHoraMin = true;
            if(data[12].compareTo("0") == 0) checkHoraMin = false;
            
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
            
//            //DETALLE
//            Element detalle = doc.createElement("Detalle");
//            rootElement.appendChild(detalle);
            
            int contador = 0;
            for(int i = 0; i < ots_nc.length; i++){
                modelos.modeloJornadas jornada = new modeloJornadas();
                String[] datosDias;
                data = ots.obtenerFacturaPorId(ots_nc[i]);
                datosDias = ots.obtenerDiasPorIdOt(ots_nc[i]);
                controladores.controladorIngresarOts miControlador = new controladores.controladorIngresarOts();
                List<List<String>> valores = miControlador.calcularTarifa(datosDias[0], datosDias[1], datosDias[2],
                        datosDias[3], datosDias[4], checkHoraMin);
                String[] infoDespacho = ots.obtenerValorDespachoOt(ots_nc[i]);
                
                for (int j = 0; j < valores.size() - 1; j++) {
                    //DETALLE
                    Element detalle = doc.createElement("Detalle");
                    rootElement.appendChild(detalle);
                    
                    Element numLin = doc.createElement("NroLinDet");
                    numLin.appendChild(doc.createTextNode(Integer.toString(contador+1)));
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
                    //nomItem.appendChild(doc.createTextNode(data[11]+" HORAS DE GRUA HORQUILLA O.T.:"+data[10]));
                    nomItem.appendChild(doc.createTextNode(valores.get(j).get(3)+" HORAS DE GRUA HORQUILLA O.T.:"+data[10]));
                    detalle.appendChild(nomItem);

                    Element qtyItem = doc.createElement("QtyItem");
                    //nomItem.appendChild(doc.createTextNode(data[11]+" HORAS DE GRUA HORQUILLA O.T.:"+data[10]));
                    qtyItem.appendChild(doc.createTextNode(valores.get(j).get(3)));
                    detalle.appendChild(qtyItem);
                    
                    Element prcItem = doc.createElement("PrcItem");
                    //nomItem.appendChild(doc.createTextNode(data[11]+" HORAS DE GRUA HORQUILLA O.T.:"+data[10]));
                    prcItem.appendChild(doc.createTextNode(valores.get(j).get(0)));
                    detalle.appendChild(prcItem);
                    
                    Element mtoItem = doc.createElement("MontoItem");
                    mtoItem.appendChild(doc.createTextNode(Integer.toString((int)(Float.parseFloat(valores.get(j).get(0))
                            * Float.parseFloat(valores.get(j).get(3))))));
                    detalle.appendChild(mtoItem);
                    
                    contador += 1;
                }
                
                if(infoDespacho[0].compareTo("1") == 0){
                    //DETALLE
                    Element detalle = doc.createElement("Detalle");
                    rootElement.appendChild(detalle);
                    
                    Element numLin = doc.createElement("NroLinDet");
                    numLin.appendChild(doc.createTextNode(Integer.toString(contador+1)));
                    detalle.appendChild(numLin);

                    Element codItem = doc.createElement("CdgItem");
                    detalle.appendChild(codItem);

                    Element tipoCod = doc.createElement("TpoCodigo");
                    tipoCod.appendChild(doc.createTextNode("Interna"));
                    codItem.appendChild(tipoCod);

                    Element valCod = doc.createElement("VlrCodigo");
                    valCod.appendChild(doc.createTextNode("2"));
                    codItem.appendChild(valCod);

                    Element nomItem = doc.createElement("NmbItem");
                    //nomItem.appendChild(doc.createTextNode(data[11]+" HORAS DE GRUA HORQUILLA O.T.:"+data[10]));
                    nomItem.appendChild(doc.createTextNode("TRASLADO GRUA A OBRA"));
                    detalle.appendChild(nomItem);

                    Element qtyItem = doc.createElement("QtyItem");
                    qtyItem.appendChild(doc.createTextNode("1"));
                    detalle.appendChild(qtyItem);
                    
                    Element prcItem = doc.createElement("PrcItem");
                    //nomItem.appendChild(doc.createTextNode(data[11]+" HORAS DE GRUA HORQUILLA O.T.:"+data[10]));
                    prcItem.appendChild(doc.createTextNode(infoDespacho[1]));
                    detalle.appendChild(prcItem);
                    
                    Element mtoItem = doc.createElement("MontoItem");
                    mtoItem.appendChild(doc.createTextNode(infoDespacho[1]));
                    detalle.appendChild(mtoItem);
                    
                    contador += 1;
                }
            }
            
            //REFERENCIA
            Element ref = doc.createElement("Referencia");
            rootElement.appendChild(ref);
            
            Element nrolin = doc.createElement("NroLinRef");
            nrolin.appendChild(doc.createTextNode("1"));
            ref.appendChild(nrolin);
            
            Element tporef = doc.createElement("TpoDocRef");
            tporef.appendChild(doc.createTextNode("56"));
            ref.appendChild(tporef);
            
//            String fol_fac = mod.obtenerFolioFac(fac);
            
            Element folioref = doc.createElement("FolioRef");
            folioref.appendChild(doc.createTextNode(fac));
            ref.appendChild(folioref);
            
            String fec_ref = mod.obtenerFechaND(fac, tipo_fac);
            
            Element fecref = doc.createElement("FchRef");
            fecref.appendChild(doc.createTextNode(fec_ref));
            ref.appendChild(fecref);
            
            Element codref = doc.createElement("CodRef");
            codref.appendChild(doc.createTextNode("1"));
            ref.appendChild(codref);
            
            Element razref = doc.createElement("RazonRef");
            razref.appendChild(doc.createTextNode(razon));
            ref.appendChild(razref);
                      
            Element adj = doc.createElement("Adjuntos");
                rootElement.appendChild(adj);
            
                Element obs = doc.createElement("Observacion");
                obs.appendChild(doc.createTextNode("Folio nota de crédito: " + fac));
                adj.appendChild(obs);
                
                Element imp = doc.createElement("Impresora");
                imp.appendChild(doc.createTextNode("impresora"));
                adj.appendChild(imp);
                
                Element copias = doc.createElement("Copias");
                copias.appendChild(doc.createTextNode("2"));
                adj.appendChild(copias);
                
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.ENCODING, "ISO-8859-1");
            DOMSource source = new DOMSource(doc);
//            File f = new File("C:/AppSersv/www/eugcomdte/entregas/XML_pre");
            File f = new File(ruta);
            if(f.exists()){
//                StreamResult result =   new StreamResult(new File("C:/AppServ/www/eugcomdte/entregas/XML_pre/61_"+fol_nc+"_anula_56.xml"));
//                StreamResult result =   new StreamResult(new File("\\SGST/DTEService/input-pruebas/61_"+fol_nc+"_anula_56.xml"));
                StreamResult result =   new StreamResult(new File(ruta + "/61_"+fol_nc+"_anula_56.xml"));
                transformer.transform(source, result);
                JOptionPane.showMessageDialog(vistaF, "Documento realizado con éxito");
                return "correcto"; 
            }
            else{
                StreamResult result =   new StreamResult(new File("61_"+fol_nc+"_anula_56.xml"));
                transformer.transform(source, result);
                JOptionPane.showMessageDialog(vistaF, "Documento realizado con éxito en ruta por defecto");
                return "correcto";
            }
            
        }catch(ParserConfigurationException pce){
            pce.printStackTrace();
            return "incorrecto";
        }catch(TransformerException tfe){
            tfe.printStackTrace();
            return "incorrecto";
        }
    }
    
    public String crearNotaDebXML(String[] idOts, String valorNeto, String valorIva, String valorTotal, String fac, String id_fac) throws ParseException{
        
        modeloFacturas rutas = new modeloFacturas();
        String ruta = rutas.obtenerRuta();
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
            
            modelos.modeloOts modOt = new modeloOts();
            String form = modOt.obtenerFormaPago(idOts[0]);
            
//            Element fpago = doc.createElement("FmaPago");
//            fpago.appendChild(doc.createTextNode(form));
//            id.appendChild(fpago);
            
//            Element mpago = doc.createElement("MedioPago");
//            mpago.appendChild(doc.createTextNode("CH"));
//            id.appendChild(mpago);
            
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
            boolean checkHoraMin = true;
            if(data[12].compareTo("0") == 0){
                checkHoraMin = false;
            }
            
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
            
//            //DETALLE
//            Element detalle = doc.createElement("Detalle");
//            rootElement.appendChild(detalle);
            
            int contador = 0;
            for(int i = 0; i < idOts.length; i++){
                modelos.modeloJornadas jornada = new modeloJornadas();
                String[] datosDias;
                data = ots.obtenerFacturaPorId(idOts[i]);
                datosDias = ots.obtenerDiasPorIdOt(idOts[i]);
                controladores.controladorIngresarOts miControlador = new controladores.controladorIngresarOts();
                List<List<String>> valores = miControlador.calcularTarifa(datosDias[0], datosDias[1], datosDias[2],
                        datosDias[3], datosDias[4], checkHoraMin);
                String[] infoDespacho = ots.obtenerValorDespachoOt(idOts[i]);
                
                for (int j = 0; j < valores.size() - 1; j++) {
                    //DETALLE
                    Element detalle = doc.createElement("Detalle");
                    rootElement.appendChild(detalle);
                    
                    Element numLin = doc.createElement("NroLinDet");
                    numLin.appendChild(doc.createTextNode(Integer.toString(contador+1)));
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
                    //nomItem.appendChild(doc.createTextNode(data[11]+" HORAS DE GRUA HORQUILLA O.T.:"+data[10]));
                    nomItem.appendChild(doc.createTextNode(valores.get(j).get(3)+" HORAS DE GRUA HORQUILLA O.T.:"+data[10]));
                    detalle.appendChild(nomItem);

                    Element qtyItem = doc.createElement("QtyItem");
                    //nomItem.appendChild(doc.createTextNode(data[11]+" HORAS DE GRUA HORQUILLA O.T.:"+data[10]));
                    qtyItem.appendChild(doc.createTextNode(valores.get(j).get(3)));
                    detalle.appendChild(qtyItem);
                    
                    Element prcItem = doc.createElement("PrcItem");
                    //nomItem.appendChild(doc.createTextNode(data[11]+" HORAS DE GRUA HORQUILLA O.T.:"+data[10]));
                    prcItem.appendChild(doc.createTextNode(valores.get(j).get(0)));
                    detalle.appendChild(prcItem);
                    
                    Element mtoItem = doc.createElement("MontoItem");
                    mtoItem.appendChild(doc.createTextNode(Integer.toString((int)(Float.parseFloat(valores.get(j).get(0))
                            * Float.parseFloat(valores.get(j).get(3))))));
                    detalle.appendChild(mtoItem);
                    
                    contador += 1;
                }
                
//                Element numLin = doc.createElement("NroLinDet");
//                numLin.appendChild(doc.createTextNode(Integer.toString(i+1)));
//                detalle.appendChild(numLin);
//                
//                Element codItem = doc.createElement("CdgItem");
//                detalle.appendChild(codItem);
//                
//                Element tipoCod = doc.createElement("TpoCodigo");
//                tipoCod.appendChild(doc.createTextNode("Interna"));
//                codItem.appendChild(tipoCod);
//                
//                Element valCod = doc.createElement("VlrCodigo");
//                valCod.appendChild(doc.createTextNode("1"));
//                codItem.appendChild(valCod);
//                
//                Element nomItem = doc.createElement("NmbItem");
//                nomItem.appendChild(doc.createTextNode(data[11]+" HORAS DE GRUA HORQUILLA O.T.:"+data[10]));
//                detalle.appendChild(nomItem);
//                
//                Element mtoItem = doc.createElement("MontoItem");
//                mtoItem.appendChild(doc.createTextNode(data[7]));
//                detalle.appendChild(mtoItem);
                
                if(infoDespacho[0].compareTo("1") == 0){
                    //DETALLE
                    Element detalle = doc.createElement("Detalle");
                    rootElement.appendChild(detalle);
                    
                    Element numLin = doc.createElement("NroLinDet");
                    numLin.appendChild(doc.createTextNode(Integer.toString(contador+1)));
                    detalle.appendChild(numLin);

                    Element codItem = doc.createElement("CdgItem");
                    detalle.appendChild(codItem);

                    Element tipoCod = doc.createElement("TpoCodigo");
                    tipoCod.appendChild(doc.createTextNode("Interna"));
                    codItem.appendChild(tipoCod);

                    Element valCod = doc.createElement("VlrCodigo");
                    valCod.appendChild(doc.createTextNode("2"));
                    codItem.appendChild(valCod);

                    Element nomItem = doc.createElement("NmbItem");
                    //nomItem.appendChild(doc.createTextNode(data[11]+" HORAS DE GRUA HORQUILLA O.T.:"+data[10]));
                    nomItem.appendChild(doc.createTextNode("TRASLADO GRUA A OBRA"));
                    detalle.appendChild(nomItem);

                    Element qtyItem = doc.createElement("QtyItem");
                    qtyItem.appendChild(doc.createTextNode("1"));
                    detalle.appendChild(qtyItem);
                    
                    Element prcItem = doc.createElement("PrcItem");
                    //nomItem.appendChild(doc.createTextNode(data[11]+" HORAS DE GRUA HORQUILLA O.T.:"+data[10]));
                    prcItem.appendChild(doc.createTextNode(infoDespacho[1]));
                    detalle.appendChild(prcItem);
                    
                    Element mtoItem = doc.createElement("MontoItem");
                    mtoItem.appendChild(doc.createTextNode(infoDespacho[1]));
                    detalle.appendChild(mtoItem);
                    
                    contador += 1;
                }
                
            }
            
            //REFERENCIA
            Element ref = doc.createElement("Referencia");
            rootElement.appendChild(ref);
            
            Element nrolin = doc.createElement("NroLinRef");
            nrolin.appendChild(doc.createTextNode("1"));
            ref.appendChild(nrolin);
            
            Element tporef = doc.createElement("TpoDocRef");
            tporef.appendChild(doc.createTextNode("33"));
            ref.appendChild(tporef);
            
//            String fol_fac = mod.obtenerFolioFac(fac);
            
            Element folioref = doc.createElement("FolioRef");
            folioref.appendChild(doc.createTextNode(fac));
            ref.appendChild(folioref);
            
            String fec_ref = mod.obtenerFechaFac(id_fac, "factura");
            
            Element fecref = doc.createElement("FchRef");
            fecref.appendChild(doc.createTextNode(fec_ref));
            ref.appendChild(fecref);
            
            Element codref = doc.createElement("CodRef");
            codref.appendChild(doc.createTextNode("1"));
            ref.appendChild(codref);
            
            
            Element adj = doc.createElement("Adjuntos");
                rootElement.appendChild(adj);
            
                Element obs = doc.createElement("Observacion");
                obs.appendChild(doc.createTextNode("OBSERVACIONES"));
                adj.appendChild(obs);
                
                Element imp = doc.createElement("Impresora");
                imp.appendChild(doc.createTextNode("impresora"));
                adj.appendChild(imp);
                
                Element copias = doc.createElement("Copias");
                copias.appendChild(doc.createTextNode("2"));
                adj.appendChild(copias);
                        
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.ENCODING, "ISO-8859-1");
            DOMSource source = new DOMSource(doc);
//            File f = new File("C:/AppSersv/www/eugcomdte/entregas/XML_pre");
            File f = new File(ruta);
            if(f.exists()){
//                StreamResult result =   new StreamResult(new File("C:/AppServ/www/eugcomdte/entregas/XML_pre/56_"+fol_nd+"_normal.xml"));
//                StreamResult result =   new StreamResult(new File("\\SGST/DTEService/input-pruebas/56_"+fol_nd+"_normal.xml"));
                StreamResult result =   new StreamResult(new File(ruta + "/56_"+fol_nd+"_normal.xml"));
                transformer.transform(source, result);
                JOptionPane.showMessageDialog(vistaF, "Documento realizado con éxito");
                return "correcto";
            }else{
                StreamResult result =   new StreamResult(new File("56_"+fol_nd+"_normal.xml"));
                transformer.transform(source, result);
                JOptionPane.showMessageDialog(vistaF, "Documento realizado con éxito en ruta por defecto");
                return "correcto";   
            }
            
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
        modeloFacturas rutas = new modeloFacturas();
        String ruta = rutas.obtenerRuta();
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
                
                Element mtoItem = doc.createElement("MontoItem");
                mtoItem.appendChild(doc.createTextNode(data[7]));
                detalle.appendChild(mtoItem);
                
            }
            
            Element adj = doc.createElement("Adjuntos");
                rootElement.appendChild(adj);
            
                Element obs = doc.createElement("Observacion");
                obs.appendChild(doc.createTextNode("OBSERVACIONES"));
                adj.appendChild(obs);
                
                Element imp = doc.createElement("Impresora");
                imp.appendChild(doc.createTextNode("impresora"));
                adj.appendChild(imp);
                
                Element copias = doc.createElement("Copias");
                copias.appendChild(doc.createTextNode("2"));
                adj.appendChild(copias);
                        
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.ENCODING, "ISO-8859-1");
            DOMSource source = new DOMSource(doc);
//            File f = new File("C:/AppSersv/www/eugcomdte/entregas/XML_pre");
            File f = new File(ruta);
            if(f.exists()){
//                StreamResult result =   new StreamResult(new File("C:/AppServ/www/eugcomdte/entregas/XML_pre/boleta_39_"+fac+".xml"));
//                StreamResult result =   new StreamResult(new File("\\SGST/DTEService/input-pruebas/boleta_39_"+fac+".xml"));
                StreamResult result =   new StreamResult(new File(ruta + "/boleta_39_"+fac+".xml"));
                transformer.transform(source, result);
                JOptionPane.showMessageDialog(vistaF, "Documento realizado con éxito");
                return "correcto";
            }else{
                StreamResult result =   new StreamResult(new File("boleta_39_"+fac+".xml"));
                transformer.transform(source, result);
                JOptionPane.showMessageDialog(vistaF, "Documento realizado con éxito en ruta por defecto");
                return "correcto";
            }
        }catch(ParserConfigurationException pce){
            pce.printStackTrace();
            return "incorrecto";
        }catch(TransformerException tfe){
            tfe.printStackTrace();
            return "incorrecto";
        }
    }
    
    public String crearGuiaDespXML(String[] datos, String id_jor, int valorNeto, String pat, String rut, String dir, String com, String ciu, String ind){
        
        modeloFacturas rutas = new modeloFacturas();
        String ruta = rutas.obtenerRuta();
        String fecha = formatDate.format(new Date());
        double valorIva = valorNeto * 0.19;
        double valorTotal = valorNeto * 1.19;
        
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
            
            Element indTras = doc.createElement("IndTraslado");
            indTras.appendChild(doc.createTextNode(ind));
            id.appendChild(indTras);
            
            
//            Element fpago = doc.createElement("FmaPago");
//            fpago.appendChild(doc.createTextNode("VER FORMA"));
//            id.appendChild(fpago);
            
//            Element mpago = doc.createElement("MedioPago");
//            mpago.appendChild(doc.createTextNode("CH"));
//            id.appendChild(mpago);
            
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
            
            //GUIA
            Element guia = doc.createElement("GuiaExport");
            emisor.appendChild(guia);
            
            Element codTras = doc.createElement("CdgTraslado");
            codTras.appendChild(doc.createTextNode("3"));
            guia.appendChild(codTras);
            
//            Element sucEmisor = doc.createElement("Sucursal");
//            sucEmisor.appendChild(doc.createTextNode("712900"));
//            emisor.appendChild(sucEmisor);
            
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
            
            modelos.modeloJornadas jornada = new modelos.modeloJornadas();
            String[] data = jornada.obtenerClienteIdJornada(id_jor);
            
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
            
            //TRANSPORTE
            Element trans = doc.createElement("Transporte");
            encabezado.appendChild(trans);
            
            Element patente =  doc.createElement("Patente");
            patente.appendChild(doc.createTextNode(pat));
            trans.appendChild(patente);
            
            Element rutTrans = doc.createElement("RUTTrans");
            rutTrans.appendChild(doc.createTextNode(rut));
            trans.appendChild(rutTrans);
            
            Element dirDest = doc.createElement("DirDest");
            dirDest.appendChild(doc.createTextNode(dir));
            trans.appendChild(dirDest);
            
            Element cmnaDest = doc.createElement("CmnaDest");
            cmnaDest.appendChild(doc.createTextNode(com));
            trans.appendChild(cmnaDest);
            
            Element ciuDest = doc.createElement("CiudadDest");
            ciuDest.appendChild(doc.createTextNode(ciu));
            trans.appendChild(ciuDest);
            
            //TOTALES
            Element totales = doc.createElement("Totales");
            encabezado.appendChild(totales);
            
            Element neto = doc.createElement("MntNeto");
            neto.appendChild(doc.createTextNode(String.valueOf((int)valorNeto)));
            totales.appendChild(neto);
            
            Element tasaiva = doc.createElement("TasaIVA");
            tasaiva.appendChild(doc.createTextNode("19.00"));
            totales.appendChild(tasaiva);
            
            Element iva = doc.createElement("IVA");
            iva.appendChild(doc.createTextNode(String.valueOf((int)valorIva)));
            totales.appendChild(iva);
            
            Element bruto = doc.createElement("MntTotal");
            bruto.appendChild(doc.createTextNode(String.valueOf((int)valorTotal)));
            totales.appendChild(bruto);
            
            //DETALLE
            Element detalle = doc.createElement("Detalle");
            rootElement.appendChild(detalle);
            
            
            String grua = jornada.obtenerGruaPorIdJornada(id_jor);
                
                Element numLin = doc.createElement("NroLinDet");
                numLin.appendChild(doc.createTextNode(Integer.toString(1)));
                detalle.appendChild(numLin);
                
//                Element codItem = doc.createElement("CdgItem");
//                detalle.appendChild(codItem);
                
//                Element tipoCod = doc.createElement("TpoCodigo");
//                tipoCod.appendChild(doc.createTextNode("Interna"));
//                codItem.appendChild(tipoCod);
                
//                Element valCod = doc.createElement("VlrCodigo");
//                valCod.appendChild(doc.createTextNode("2"));
//                codItem.appendChild(valCod);
                
                Element nomItem = doc.createElement("NmbItem");
                nomItem.appendChild(doc.createTextNode("DESPACHO A OBRA DE GRUA " + grua));
                detalle.appendChild(nomItem);
                
                Element qtyItem = doc.createElement("QtyItem");
                qtyItem.appendChild(doc.createTextNode("1"));
                detalle.appendChild(qtyItem);
                
                Element unmItem = doc.createElement("UnmdItem");
                unmItem.appendChild(doc.createTextNode("UND"));
                detalle.appendChild(unmItem);
                
                Element mntItem = doc.createElement("MontoItem");
                mntItem.appendChild(doc.createTextNode(String.valueOf(valorNeto)));
                detalle.appendChild(mntItem);
                    
            Element adj = doc.createElement("Adjuntos");
                rootElement.appendChild(adj);
            
                Element obs = doc.createElement("Observacion");
                obs.appendChild(doc.createTextNode("OBSERVACIONES"));
                adj.appendChild(obs);
                
                Element imp = doc.createElement("Impresora");
                imp.appendChild(doc.createTextNode("impresora"));
                adj.appendChild(imp);
                
                Element copias = doc.createElement("Copias");
                copias.appendChild(doc.createTextNode("2"));
                adj.appendChild(copias);
                
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.ENCODING, "ISO-8859-1");
            DOMSource source = new DOMSource(doc);
//            File f = new File("C:/AppSersv/www/eugcomdte/entregas/XML_pre");
            File f = new File(ruta);
            System.out.println(ruta);
            if(f.exists()){
//                StreamResult result =   new StreamResult(new File("C:/AppServ/www/eugcomdte/entregas/XML_pre/33_"+num_fol+"_REF_GUIA.xml"));
//                StreamResult result =   new StreamResult(new File("\\SGST/DTEService/input-pruebas/33_"+num_fol+"_REF_GUIA.xml"));
                StreamResult result =   new StreamResult(new File(ruta + "/33_"+num_fol+"_REF_GUIA.xml"));
                transformer.transform(source, result);
                JOptionPane.showMessageDialog(vistaF, "Documento realizado con éxito");
                return "correcto";
            }else{
                System.out.println("ola");
                System.out.println(valorNeto);
                StreamResult result =   new StreamResult(new File("33_"+num_fol+"_REF_GUIA.xml"));
                transformer.transform(source, result);
                JOptionPane.showMessageDialog(vistaF, "Documento realizado con éxito en ruta por defecto");
                return "correcto";  
            }
        }catch(ParserConfigurationException | TransformerException pce){
            pce.printStackTrace();
            return "incorrecto";
        }
    }
    
    public void crearControladorPrincipal(JTabbedPane tabs) {
        controladorPrincipal miControlador = new controladorPrincipal();
        tabs.remove(5);
        tabs.remove(6);
        tabs.insertTab("A facturar", null, miControlador.crearControladorFacturasP(), null, 5);
        tabs.insertTab("Histórico", null, miControlador.crearControladorHistoricoP(), null, 7);
        tabs.setSelectedIndex(5);
    }
}
