/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores2;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import modelos.modeloFacturas;
import modelos2.modeloCompras;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 *
 * @author diego
 */
public class controladorCrearLibros {
    DateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd");
    DateFormat monthDate = new SimpleDateFormat("MM");
    DateFormat perDate = new SimpleDateFormat("yyyy-MM");
    
    public boolean crearLibroCompras(){
        String mes = monthDate.format(new Date());
        String per = perDate.format(new Date());
        modelos2.modeloCompras compras = new modeloCompras();
        String[] datos = compras.obtenerResumenMes(mes);
        try{
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            Document doc = docBuilder.newDocument();
            
            //Documento
            Element root = doc.createElement("LibroCompraVenta");
            //root.setAttribute("xmlns:xsi", "http://www.w3.org/2001");
            doc.appendChild(root);
            
            //Envio libro
            Element envio = doc.createElement("EnvioLibro");
            envio.setAttribute("ID", "LIBRO_DTE_"+per);
            root.appendChild(envio);
            
            //Caratula
            Element caratula = doc.createElement("Caratula");
            envio.appendChild(caratula);
            
            Element rutEmisor = doc.createElement("RutEmisorLibro");
            rutEmisor.appendChild(doc.createTextNode("77037960-1"));
            Element rutEnvia = doc.createElement("RutEnvia");
            rutEnvia.appendChild(doc.createTextNode("6214387-8"));
            Element periodo = doc.createElement("PeriodoTributario");
            periodo.appendChild(doc.createTextNode(per));
            Element fechaRes = doc.createElement("FchResol");
            fechaRes.appendChild(doc.createTextNode("2014-08-22"));
            Element numRes = doc.createElement("NroResol");
            numRes.appendChild(doc.createTextNode("80"));
            Element tipoOp = doc.createElement("TipoOperacion");
            tipoOp.appendChild(doc.createTextNode("COMPRA"));
            Element tipoLib = doc.createElement("TipoLibro");
            tipoLib.appendChild(doc.createTextNode("MENSUAL"));
            Element tipoEnv = doc.createElement("TipoEnvio");
            tipoEnv.appendChild(doc.createTextNode("TOTAL"));
            
            caratula.appendChild(rutEmisor);
            caratula.appendChild(rutEnvia);
            caratula.appendChild(periodo);
            caratula.appendChild(fechaRes);
            caratula.appendChild(numRes);
            caratula.appendChild(tipoOp);
            caratula.appendChild(tipoLib);
            caratula.appendChild(tipoOp);
            
            //Resumen
            
            Element resumen = doc.createElement("ResumenPeriodo");
            envio.appendChild(resumen);
            
            for(int i = 0; i < 1; i++){
                Element totales = doc.createElement("TotalesPeriodo");
                
                Element tipoDoc = doc.createElement("TpoDoc");
                Element totDoc = doc.createElement("TotDoc");
                Element totEx = doc.createElement("TotMntExe");
                Element totNet = doc.createElement("TotMntNeto");
                Element totIva = doc.createElement("TotMntIVA");
                Element totTot = doc.createElement("TotMntTotal");
                
                tipoDoc.appendChild(doc.createTextNode("33"));
                totDoc.appendChild(doc.createTextNode(datos[0]));
                totEx.appendChild(doc.createTextNode("0"));
                totNet.appendChild(doc.createTextNode(datos[1]));
                totIva.appendChild(doc.createTextNode(datos[2]));
                totTot.appendChild(doc.createTextNode(datos[3]));
                
                totales.appendChild(tipoDoc);
                totales.appendChild(totDoc);
                totales.appendChild(totEx);
                totales.appendChild(totNet);
                totales.appendChild(totIva);
                totales.appendChild(totTot);
                
                resumen.appendChild(totales);
            }
            
            String [][] facturas = compras.obtenerFacturasMes(mes);
            
            for(int i = 0; i < facturas.length; i++){
                Element detalle = doc.createElement("Detalle");
                
                Element tipoDoc = doc.createElement("TpoDoc");
                Element numDoc = doc.createElement("NroDoc");
                Element tipoImp = doc.createElement("TpoImp");
                Element tasaImp = doc.createElement("TasaImp");
                Element numInt = doc.createElement("NumInt");
                Element fecDoc = doc.createElement("FchDoc");
                Element rut = doc.createElement("RUTDoc");
                Element raz = doc.createElement("RznSoc");
                Element ex = doc.createElement("MntExe");
                Element neto = doc.createElement("MntNeto");
                Element iva = doc.createElement("MntIVA");
                Element total = doc.createElement("MntTotal");
                
                tipoDoc.appendChild(doc.createTextNode("33"));
                numDoc.appendChild(doc.createTextNode(facturas[i][0]));
                tipoImp.appendChild(doc.createTextNode("1"));
                tasaImp.appendChild(doc.createTextNode("19"));
                numInt.appendChild(doc.createTextNode(facturas[i][1]));
                fecDoc.appendChild(doc.createTextNode(facturas[i][2]));
                rut.appendChild(doc.createTextNode(facturas[i][3]));
                raz.appendChild(doc.createTextNode(facturas[i][4]));
                ex.appendChild(doc.createTextNode("0"));
                neto.appendChild(doc.createTextNode(facturas[i][5]));
                iva.appendChild(doc.createTextNode(facturas[i][6]));
                total.appendChild(doc.createTextNode(facturas[i][7]));
                
                detalle.appendChild(tipoDoc);
                detalle.appendChild(numDoc);
                detalle.appendChild(tipoImp);
                detalle.appendChild(tasaImp);
                detalle.appendChild(numInt);
                detalle.appendChild(fecDoc);
                detalle.appendChild(rut);
                detalle.appendChild(raz);
                detalle.appendChild(ex);
                detalle.appendChild(neto);
                detalle.appendChild(iva);
                detalle.appendChild(total);
                
                envio.appendChild(detalle);
            }
            
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.ENCODING, "ISO-8859-1");
            DOMSource source = new DOMSource(doc);
            StreamResult result =   new StreamResult(new File("COMPRA-"+per+".xml"));
            transformer.transform(source, result);
            JOptionPane.showMessageDialog(null, "Documento realizado con éxito");
            
        }catch(Exception e){
            e.printStackTrace();
        }
        return true;
    }
    
    public boolean crearLibroCompras(String year, String mes){
        
        String per = year + "-" + mes;
        modelos2.modeloCompras compras = new modeloCompras();
        String[] datos = compras.obtenerResumenMes(mes);
        try{
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            Document doc = docBuilder.newDocument();
            
            //Documento
            Element root = doc.createElement("LibroCompraVenta");
            //root.setAttribute("xmlns:xsi", "http://www.w3.org/2001");
            doc.appendChild(root);
            
            //Envio libro
            Element envio = doc.createElement("EnvioLibro");
            envio.setAttribute("ID", "LIBRO_DTE_"+per);
            root.appendChild(envio);
            
            //Caratula
            Element caratula = doc.createElement("Caratula");
            envio.appendChild(caratula);
            
            Element rutEmisor = doc.createElement("RutEmisorLibro");
            rutEmisor.appendChild(doc.createTextNode("77037960-1"));
            Element rutEnvia = doc.createElement("RutEnvia");
            rutEnvia.appendChild(doc.createTextNode("6214387-8"));
            Element periodo = doc.createElement("PeriodoTributario");
            periodo.appendChild(doc.createTextNode(per));
            Element fechaRes = doc.createElement("FchResol");
            fechaRes.appendChild(doc.createTextNode("2014-08-22"));
            Element numRes = doc.createElement("NroResol");
            numRes.appendChild(doc.createTextNode("80"));
            Element tipoOp = doc.createElement("TipoOperacion");
            tipoOp.appendChild(doc.createTextNode("COMPRA"));
            Element tipoLib = doc.createElement("TipoLibro");
            tipoLib.appendChild(doc.createTextNode("MENSUAL"));
            Element tipoEnv = doc.createElement("TipoEnvio");
            tipoEnv.appendChild(doc.createTextNode("TOTAL"));
            
            caratula.appendChild(rutEmisor);
            caratula.appendChild(rutEnvia);
            caratula.appendChild(periodo);
            caratula.appendChild(fechaRes);
            caratula.appendChild(numRes);
            caratula.appendChild(tipoOp);
            caratula.appendChild(tipoLib);
            caratula.appendChild(tipoOp);
            
            //Resumen
            
            Element resumen = doc.createElement("ResumenPeriodo");
            envio.appendChild(resumen);
            
            for(int i = 0; i < 1; i++){
                Element totales = doc.createElement("TotalesPeriodo");
                
                Element tipoDoc = doc.createElement("TpoDoc");
                Element totDoc = doc.createElement("TotDoc");
                Element totEx = doc.createElement("TotMntExe");
                Element totNet = doc.createElement("TotMntNeto");
                Element totIva = doc.createElement("TotMntIVA");
                Element totTot = doc.createElement("TotMntTotal");
                
                tipoDoc.appendChild(doc.createTextNode("33"));
                totDoc.appendChild(doc.createTextNode(datos[0]));
                totEx.appendChild(doc.createTextNode("0"));
                totNet.appendChild(doc.createTextNode(datos[1]));
                totIva.appendChild(doc.createTextNode(datos[2]));
                totTot.appendChild(doc.createTextNode(datos[3]));
                
                totales.appendChild(tipoDoc);
                totales.appendChild(totDoc);
                totales.appendChild(totEx);
                totales.appendChild(totNet);
                totales.appendChild(totIva);
                totales.appendChild(totTot);
                
                resumen.appendChild(totales);
            }
            
            String [][] facturas = compras.obtenerFacturasMes(mes);
            
            for(int i = 0; i < facturas.length; i++){
                Element detalle = doc.createElement("Detalle");
                
                Element tipoDoc = doc.createElement("TpoDoc");
                Element numDoc = doc.createElement("NroDoc");
                Element tipoImp = doc.createElement("TpoImp");
                Element tasaImp = doc.createElement("TasaImp");
                Element numInt = doc.createElement("NumInt");
                Element fecDoc = doc.createElement("FchDoc");
                Element rut = doc.createElement("RUTDoc");
                Element raz = doc.createElement("RznSoc");
                Element ex = doc.createElement("MntExe");
                Element neto = doc.createElement("MntNeto");
                Element iva = doc.createElement("MntIVA");
                Element total = doc.createElement("MntTotal");
                
                tipoDoc.appendChild(doc.createTextNode("33"));
                numDoc.appendChild(doc.createTextNode(facturas[i][0]));
                tipoImp.appendChild(doc.createTextNode("1"));
                tasaImp.appendChild(doc.createTextNode("19"));
                numInt.appendChild(doc.createTextNode(facturas[i][1]));
                fecDoc.appendChild(doc.createTextNode(facturas[i][2]));
                rut.appendChild(doc.createTextNode(facturas[i][3]));
                raz.appendChild(doc.createTextNode(facturas[i][4]));
                ex.appendChild(doc.createTextNode("0"));
                neto.appendChild(doc.createTextNode(facturas[i][5]));
                iva.appendChild(doc.createTextNode(facturas[i][6]));
                total.appendChild(doc.createTextNode(facturas[i][7]));
                
                detalle.appendChild(tipoDoc);
                detalle.appendChild(numDoc);
                detalle.appendChild(tipoImp);
                detalle.appendChild(tasaImp);
                detalle.appendChild(numInt);
                detalle.appendChild(fecDoc);
                detalle.appendChild(rut);
                detalle.appendChild(raz);
                detalle.appendChild(ex);
                detalle.appendChild(neto);
                detalle.appendChild(iva);
                detalle.appendChild(total);
                
                envio.appendChild(detalle);
            }
            
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.ENCODING, "ISO-8859-1");
            DOMSource source = new DOMSource(doc);
            StreamResult result =   new StreamResult(new File("COMPRA-"+per+".xml"));
            transformer.transform(source, result);
            JOptionPane.showMessageDialog(null, "Documento realizado con éxito");
            
        }catch(Exception e){
            e.printStackTrace();
        }
        return true;
    }
    
    public boolean crearLibroVentas(){
        String mes = monthDate.format(new Date());
        String per = perDate.format(new Date());
        modelos.modeloFacturas modelo = new modeloFacturas();
        String[] datos = modelo.obtenerResumenMes(mes);
        try{
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            Document doc = docBuilder.newDocument();
            
            //Documento
            Element root = doc.createElement("LibroCompraVenta");
            //root.setAttribute(per, per);
            doc.appendChild(root);
            
            //Envio libro
            Element envio = doc.createElement("EnvioLibro");
            envio.setAttribute("ID", "LIBRO_DTE_"+per);
            root.appendChild(envio);
            
            //Caratula
            Element caratula = doc.createElement("Caratula");
            envio.appendChild(caratula);
            
            Element rutEmisor = doc.createElement("RutEmisorLibro");
            rutEmisor.appendChild(doc.createTextNode("77037960-1"));
            Element rutEnvia = doc.createElement("RutEnvia");
            rutEnvia.appendChild(doc.createTextNode("6214387-8"));
            Element periodo = doc.createElement("PeriodoTributario");
            periodo.appendChild(doc.createTextNode(per));
            Element fechaRes = doc.createElement("FchResol");
            fechaRes.appendChild(doc.createTextNode("2014-08-22"));
            Element numRes = doc.createElement("NroResol");
            numRes.appendChild(doc.createTextNode("80"));
            Element tipoOp = doc.createElement("TipoOperacion");
            tipoOp.appendChild(doc.createTextNode("VENTA"));
            Element tipoLib = doc.createElement("TipoLibro");
            tipoLib.appendChild(doc.createTextNode("MENSUAL"));
            Element tipoEnv = doc.createElement("TipoEnvio");
            tipoEnv.appendChild(doc.createTextNode("TOTAL"));
            
            caratula.appendChild(rutEmisor);
            caratula.appendChild(rutEnvia);
            caratula.appendChild(periodo);
            caratula.appendChild(fechaRes);
            caratula.appendChild(numRes);
            caratula.appendChild(tipoOp);
            caratula.appendChild(tipoLib);
            caratula.appendChild(tipoOp);
            
            //Resumen
            
            Element resumen = doc.createElement("ResumenPeriodo");
            envio.appendChild(resumen);
            
            for(int i = 0; i < 1; i++){
                Element totales = doc.createElement("TotalesPeriodo");
                
                Element tipoDoc = doc.createElement("TpoDoc");
                Element totDoc = doc.createElement("TotDoc");
                Element totEx = doc.createElement("TotMntExe");
                Element totNet = doc.createElement("TotMntNeto");
                Element totIva = doc.createElement("TotMntIVA");
                Element totTot = doc.createElement("TotMntTotal");
                
                tipoDoc.appendChild(doc.createTextNode("33"));
                totDoc.appendChild(doc.createTextNode(datos[0]));
                totEx.appendChild(doc.createTextNode("0"));
                totNet.appendChild(doc.createTextNode(datos[1]));
                totIva.appendChild(doc.createTextNode(datos[2]));
                totTot.appendChild(doc.createTextNode(datos[3]));
                
                totales.appendChild(tipoDoc);
                totales.appendChild(totDoc);
                totales.appendChild(totEx);
                totales.appendChild(totNet);
                totales.appendChild(totIva);
                totales.appendChild(totTot);
                
                resumen.appendChild(totales);
            }
            
            datos = modelo.obtenerResumenNDMes(mes);

            Element totales = doc.createElement("TotalesPeriodo");

            Element tipoDoc = doc.createElement("TpoDoc");
            Element totDoc = doc.createElement("TotDoc");
            Element totEx = doc.createElement("TotMntExe");
            Element totNet = doc.createElement("TotMntNeto");
            Element totIva = doc.createElement("TotMntIVA");
            Element totTot = doc.createElement("TotMntTotal");

            tipoDoc.appendChild(doc.createTextNode("56"));
            totDoc.appendChild(doc.createTextNode(datos[0]));
            totEx.appendChild(doc.createTextNode("0"));
            totNet.appendChild(doc.createTextNode(datos[1]));
            totIva.appendChild(doc.createTextNode(datos[2]));
            totTot.appendChild(doc.createTextNode(datos[3]));

            totales.appendChild(tipoDoc);
            totales.appendChild(totDoc);
            totales.appendChild(totEx);
            totales.appendChild(totNet);
            totales.appendChild(totIva);
            totales.appendChild(totTot);

            resumen.appendChild(totales);
            
            
            datos = modelo.obtenerResumenNCMes(mes);

            totales = doc.createElement("TotalesPeriodo");

            tipoDoc = doc.createElement("TpoDoc");
            totDoc = doc.createElement("TotDoc");
            totEx = doc.createElement("TotMntExe");
            totNet = doc.createElement("TotMntNeto");
            totIva = doc.createElement("TotMntIVA");
            totTot = doc.createElement("TotMntTotal");

            tipoDoc.appendChild(doc.createTextNode("61"));
            totDoc.appendChild(doc.createTextNode(datos[0]));
            totEx.appendChild(doc.createTextNode("0"));
            totNet.appendChild(doc.createTextNode(datos[1]));
            totIva.appendChild(doc.createTextNode(datos[2]));
            totTot.appendChild(doc.createTextNode(datos[3]));

            totales.appendChild(tipoDoc);
            totales.appendChild(totDoc);
            totales.appendChild(totEx);
            totales.appendChild(totNet);
            totales.appendChild(totIva);
            totales.appendChild(totTot);

            resumen.appendChild(totales);
                
            
            //FACTURAS
            
            String [][] facturas = modelo.obtenerFacturasMes(mes);

            for(int i = 0; i < facturas.length; i++){
                Element detalle = doc.createElement("Detalle");
                
                tipoDoc = doc.createElement("TpoDoc");
                Element numDoc = doc.createElement("NroDoc");
                Element tipoImp = doc.createElement("TpoImp");
                Element tasaImp = doc.createElement("TasaImp");
                Element numInt = doc.createElement("NumInt");
                Element fecDoc = doc.createElement("FchDoc");
                Element rut = doc.createElement("RUTDoc");
                Element raz = doc.createElement("RznSoc");
                Element ex = doc.createElement("MntExe");
                Element neto = doc.createElement("MntNeto");
                Element iva = doc.createElement("MntIVA");
                Element total = doc.createElement("MntTotal");
                

                tipoDoc.appendChild(doc.createTextNode("33"));
                numDoc.appendChild(doc.createTextNode(facturas[i][0]));
                tipoImp.appendChild(doc.createTextNode("1"));
                tasaImp.appendChild(doc.createTextNode("19"));
                numInt.appendChild(doc.createTextNode(String.valueOf(i+1)));
                fecDoc.appendChild(doc.createTextNode(facturas[i][1]));
                rut.appendChild(doc.createTextNode(facturas[i][2]));
                raz.appendChild(doc.createTextNode(facturas[i][3]));
                ex.appendChild(doc.createTextNode("0"));
                neto.appendChild(doc.createTextNode(facturas[i][4]));
                iva.appendChild(doc.createTextNode(facturas[i][5]));
                total.appendChild(doc.createTextNode(facturas[i][6]));
                
                detalle.appendChild(tipoDoc);
                detalle.appendChild(numDoc);
                detalle.appendChild(tipoImp);
                detalle.appendChild(tasaImp);
                detalle.appendChild(numInt);
                detalle.appendChild(fecDoc);
                detalle.appendChild(rut);
                detalle.appendChild(raz);
                detalle.appendChild(ex);
                detalle.appendChild(neto);
                detalle.appendChild(iva);
                detalle.appendChild(total);
                
                envio.appendChild(detalle);
            }
            
            //NOTADEBITO

            String [][] nd = modelo.obtenerNDMes(mes);
            for(int i = 0; i < nd.length; i++){
                Element detalle = doc.createElement("Detalle");
                
                tipoDoc = doc.createElement("TpoDoc");
                Element numDoc = doc.createElement("NroDoc");
                Element tipoImp = doc.createElement("TpoImp");
                Element tasaImp = doc.createElement("TasaImp");
                Element numInt = doc.createElement("NumInt");
                Element fecDoc = doc.createElement("FchDoc");
                Element rut = doc.createElement("RUTDoc");
                Element raz = doc.createElement("RznSoc");
                Element ex = doc.createElement("MntExe");
                Element neto = doc.createElement("MntNeto");
                Element iva = doc.createElement("MntIVA");
                Element total = doc.createElement("MntTotal");
                
                tipoDoc.appendChild(doc.createTextNode("56"));
                numDoc.appendChild(doc.createTextNode(nd[i][0]));
                tipoImp.appendChild(doc.createTextNode("1"));
                tasaImp.appendChild(doc.createTextNode("19"));
                numInt.appendChild(doc.createTextNode(String.valueOf(i+1)));
                fecDoc.appendChild(doc.createTextNode(nd[i][1]));
                rut.appendChild(doc.createTextNode(nd[i][2]));
                raz.appendChild(doc.createTextNode(nd[i][3]));
                ex.appendChild(doc.createTextNode("0"));
                neto.appendChild(doc.createTextNode(nd[i][4]));
                iva.appendChild(doc.createTextNode(nd[i][5]));
                total.appendChild(doc.createTextNode(nd[i][6]));
                
                detalle.appendChild(tipoDoc);
                detalle.appendChild(numDoc);
                detalle.appendChild(tipoImp);
                detalle.appendChild(tasaImp);
                detalle.appendChild(numInt);
                detalle.appendChild(fecDoc);
                detalle.appendChild(rut);
                detalle.appendChild(raz);
                detalle.appendChild(ex);
                detalle.appendChild(neto);
                detalle.appendChild(iva);
                detalle.appendChild(total);
                
                envio.appendChild(detalle);
            }
            
            //NOTACREDITO
            String [][] nc = modelo.obtenerNCMes(mes);
            for(int i = 0; i < nc.length; i++){
                Element detalle = doc.createElement("Detalle");
                
                tipoDoc = doc.createElement("TpoDoc");
                Element numDoc = doc.createElement("NroDoc");
                Element tipoImp = doc.createElement("TpoImp");
                Element tasaImp = doc.createElement("TasaImp");
                Element numInt = doc.createElement("NumInt");
                Element fecDoc = doc.createElement("FchDoc");
                Element rut = doc.createElement("RUTDoc");
                Element raz = doc.createElement("RznSoc");
                Element ex = doc.createElement("MntExe");
                Element neto = doc.createElement("MntNeto");
                Element iva = doc.createElement("MntIVA");
                Element total = doc.createElement("MntTotal");
                
                tipoDoc.appendChild(doc.createTextNode("61"));
                numDoc.appendChild(doc.createTextNode(nc[i][0]));
                tipoImp.appendChild(doc.createTextNode("1"));
                tasaImp.appendChild(doc.createTextNode("19"));
                numInt.appendChild(doc.createTextNode(String.valueOf(i+1)));
                fecDoc.appendChild(doc.createTextNode(nc[i][1]));
                rut.appendChild(doc.createTextNode(nc[i][2]));
                raz.appendChild(doc.createTextNode(nc[i][3]));
                ex.appendChild(doc.createTextNode("0"));
                neto.appendChild(doc.createTextNode(nc[i][4]));
                iva.appendChild(doc.createTextNode(nc[i][5]));
                total.appendChild(doc.createTextNode(nc[i][6]));
                
                detalle.appendChild(tipoDoc);
                detalle.appendChild(numDoc);
                detalle.appendChild(tipoImp);
                detalle.appendChild(tasaImp);
                detalle.appendChild(numInt);
                detalle.appendChild(fecDoc);
                detalle.appendChild(rut);
                detalle.appendChild(raz);
                detalle.appendChild(ex);
                detalle.appendChild(neto);
                detalle.appendChild(iva);
                detalle.appendChild(total);
                
                envio.appendChild(detalle);
            }
            
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.ENCODING, "ISO-8859-1");
            DOMSource source = new DOMSource(doc);
            StreamResult result =   new StreamResult(new File("VENTA-"+per+".xml"));
            transformer.transform(source, result);
            JOptionPane.showMessageDialog(null, "Documento realizado con éxito");
            
        }catch(Exception e){
            e.printStackTrace();
            System.out.println("Error generar libro ventas");
        }
        return true;
    }
    
    public boolean crearLibroVentas(String year, String mes){
        String per = year + "-" + mes;
        modelos.modeloFacturas modelo = new modeloFacturas();
        String[] datos = modelo.obtenerResumenMes(mes);
        try{
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            Document doc = docBuilder.newDocument();
            
            //Documento
            Element root = doc.createElement("LibroCompraVenta");
            //root.setAttribute(per, per);
            doc.appendChild(root);
            
            //Envio libro
            Element envio = doc.createElement("EnvioLibro");
            envio.setAttribute("ID", "LIBRO_DTE_"+per);
            root.appendChild(envio);
            
            //Caratula
            Element caratula = doc.createElement("Caratula");
            envio.appendChild(caratula);
            
            Element rutEmisor = doc.createElement("RutEmisorLibro");
            rutEmisor.appendChild(doc.createTextNode("77037960-1"));
            Element rutEnvia = doc.createElement("RutEnvia");
            rutEnvia.appendChild(doc.createTextNode("6214387-8"));
            Element periodo = doc.createElement("PeriodoTributario");
            periodo.appendChild(doc.createTextNode(per));
            Element fechaRes = doc.createElement("FchResol");
            fechaRes.appendChild(doc.createTextNode("2014-08-22"));
            Element numRes = doc.createElement("NroResol");
            numRes.appendChild(doc.createTextNode("80"));
            Element tipoOp = doc.createElement("TipoOperacion");
            tipoOp.appendChild(doc.createTextNode("VENTA"));
            Element tipoLib = doc.createElement("TipoLibro");
            tipoLib.appendChild(doc.createTextNode("MENSUAL"));
            Element tipoEnv = doc.createElement("TipoEnvio");
            tipoEnv.appendChild(doc.createTextNode("TOTAL"));
            
            caratula.appendChild(rutEmisor);
            caratula.appendChild(rutEnvia);
            caratula.appendChild(periodo);
            caratula.appendChild(fechaRes);
            caratula.appendChild(numRes);
            caratula.appendChild(tipoOp);
            caratula.appendChild(tipoLib);
            caratula.appendChild(tipoOp);
            
            //Resumen
            
            Element resumen = doc.createElement("ResumenPeriodo");
            envio.appendChild(resumen);
            
            for(int i = 0; i < 1; i++){
                Element totales = doc.createElement("TotalesPeriodo");
                
                Element tipoDoc = doc.createElement("TpoDoc");
                Element totDoc = doc.createElement("TotDoc");
                Element totEx = doc.createElement("TotMntExe");
                Element totNet = doc.createElement("TotMntNeto");
                Element totIva = doc.createElement("TotMntIVA");
                Element totTot = doc.createElement("TotMntTotal");
                
                tipoDoc.appendChild(doc.createTextNode("33"));
                totDoc.appendChild(doc.createTextNode(datos[0]));
                totEx.appendChild(doc.createTextNode("0"));
                totNet.appendChild(doc.createTextNode(datos[1]));
                totIva.appendChild(doc.createTextNode(datos[2]));
                totTot.appendChild(doc.createTextNode(datos[3]));
                
                totales.appendChild(tipoDoc);
                totales.appendChild(totDoc);
                totales.appendChild(totEx);
                totales.appendChild(totNet);
                totales.appendChild(totIva);
                totales.appendChild(totTot);
                
                resumen.appendChild(totales);
            }
            
            datos = modelo.obtenerResumenNCMes(mes);

            Element totales = doc.createElement("TotalesPeriodo");
                
                Element tipoDoc = doc.createElement("TpoDoc");
                Element totDoc = doc.createElement("TotDoc");
                Element totEx = doc.createElement("TotMntExe");
                Element totNet = doc.createElement("TotMntNeto");
                Element totIva = doc.createElement("TotMntIVA");
                Element totTot = doc.createElement("TotMntTotal");
                
                tipoDoc.appendChild(doc.createTextNode("61"));
                totDoc.appendChild(doc.createTextNode(datos[0]));
                totEx.appendChild(doc.createTextNode("0"));
                totNet.appendChild(doc.createTextNode(datos[1]));
                totIva.appendChild(doc.createTextNode(datos[2]));
                totTot.appendChild(doc.createTextNode(datos[3]));
                
                totales.appendChild(tipoDoc);
                totales.appendChild(totDoc);
                totales.appendChild(totEx);
                totales.appendChild(totNet);
                totales.appendChild(totIva);
                totales.appendChild(totTot);
                
                resumen.appendChild(totales);
            
            //FACTURAS
            
            String [][] facturas = modelo.obtenerFacturasMes(mes);
            for(int i = 0; i < facturas.length; i++){
                Element detalle = doc.createElement("Detalle");
                
                tipoDoc = doc.createElement("TpoDoc");
                Element numDoc = doc.createElement("NroDoc");
                Element tipoImp = doc.createElement("TpoImp");
                Element tasaImp = doc.createElement("TasaImp");
                Element numInt = doc.createElement("NumInt");
                Element fecDoc = doc.createElement("FchDoc");
                Element rut = doc.createElement("RUTDoc");
                Element raz = doc.createElement("RznSoc");
                Element ex = doc.createElement("MntExe");
                Element neto = doc.createElement("MntNeto");
                Element iva = doc.createElement("MntIVA");
                Element total = doc.createElement("MntTotal");
                

                tipoDoc.appendChild(doc.createTextNode("33"));
                numDoc.appendChild(doc.createTextNode(facturas[i][0]));
                tipoImp.appendChild(doc.createTextNode("1"));
                tasaImp.appendChild(doc.createTextNode("19"));
                numInt.appendChild(doc.createTextNode(String.valueOf(i+1)));
                fecDoc.appendChild(doc.createTextNode(facturas[i][1]));
                rut.appendChild(doc.createTextNode(facturas[i][2]));
                raz.appendChild(doc.createTextNode(facturas[i][3]));
                ex.appendChild(doc.createTextNode("0"));
                neto.appendChild(doc.createTextNode(facturas[i][4]));
                iva.appendChild(doc.createTextNode(facturas[i][5]));
                total.appendChild(doc.createTextNode(facturas[i][6]));
                
                detalle.appendChild(tipoDoc);
                detalle.appendChild(numDoc);
                detalle.appendChild(tipoImp);
                detalle.appendChild(tasaImp);
                detalle.appendChild(numInt);
                detalle.appendChild(fecDoc);
                detalle.appendChild(rut);
                detalle.appendChild(raz);
                detalle.appendChild(ex);
                detalle.appendChild(neto);
                detalle.appendChild(iva);
                detalle.appendChild(total);
                
                envio.appendChild(detalle);
            }
            //NOTACREDITO
            String [][] nc = modelo.obtenerNCMes(mes);
            for(int i = 0; i < nc.length; i++){
                Element detalle = doc.createElement("Detalle");
                
                tipoDoc = doc.createElement("TpoDoc");
                Element numDoc = doc.createElement("NroDoc");
                Element tipoImp = doc.createElement("TpoImp");
                Element tasaImp = doc.createElement("TasaImp");
                Element numInt = doc.createElement("NumInt");
                Element fecDoc = doc.createElement("FchDoc");
                Element rut = doc.createElement("RUTDoc");
                Element raz = doc.createElement("RznSoc");
                Element ex = doc.createElement("MntExe");
                Element neto = doc.createElement("MntNeto");
                Element iva = doc.createElement("MntIVA");
                Element total = doc.createElement("MntTotal");
                
                tipoDoc.appendChild(doc.createTextNode("61"));
                numDoc.appendChild(doc.createTextNode(facturas[i][0]));
                tipoImp.appendChild(doc.createTextNode("1"));
                tasaImp.appendChild(doc.createTextNode("19"));
                numInt.appendChild(doc.createTextNode(String.valueOf(i+1)));
                fecDoc.appendChild(doc.createTextNode(facturas[i][1]));
                rut.appendChild(doc.createTextNode(facturas[i][2]));
                raz.appendChild(doc.createTextNode(facturas[i][3]));
                ex.appendChild(doc.createTextNode("0"));
                neto.appendChild(doc.createTextNode(facturas[i][4]));
                iva.appendChild(doc.createTextNode(facturas[i][5]));
                total.appendChild(doc.createTextNode(facturas[i][6]));
                
                detalle.appendChild(tipoDoc);
                detalle.appendChild(numDoc);
                detalle.appendChild(tipoImp);
                detalle.appendChild(tasaImp);
                detalle.appendChild(numInt);
                detalle.appendChild(fecDoc);
                detalle.appendChild(rut);
                detalle.appendChild(raz);
                detalle.appendChild(ex);
                detalle.appendChild(neto);
                detalle.appendChild(iva);
                detalle.appendChild(total);
                
                envio.appendChild(detalle);
            }
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.ENCODING, "ISO-8859-1");
            DOMSource source = new DOMSource(doc);
            StreamResult result =   new StreamResult(new File("VENTA-"+per+".xml"));
            transformer.transform(source, result);
            JOptionPane.showMessageDialog(null, "Documento realizado con éxito");
            
        }catch(Exception e){
            e.printStackTrace();
        }
        return true;
    }
}
