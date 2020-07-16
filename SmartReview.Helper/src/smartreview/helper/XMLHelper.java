/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smartreview.helper;

import com.sun.codemodel.JCodeModel;
import com.sun.tools.xjc.api.ErrorListener;
import com.sun.tools.xjc.api.S2JJAXBModel;
import com.sun.tools.xjc.api.SchemaCompiler;
import com.sun.tools.xjc.api.XJC;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.io.StringWriter;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.transform.Templates;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamSource;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathFactory;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

/**
 *
 * @author TNT
 */
public class XMLHelper {

    public static XMLEventReader getXMLEventReader(InputStream stream) throws XMLStreamException {
        XMLInputFactory factory = XMLInputFactory.newFactory();
        XMLEventReader reader = factory.createXMLEventReader(stream);
        return reader;
    }

    public static Templates getTemplates(String xslFile) throws TransformerConfigurationException, FileNotFoundException {
        TransformerFactory factory = TransformerFactory.newInstance();
        // Use the factory to create a template containing the xsl file
        Templates template = factory.newTemplates(new StreamSource(
                new FileInputStream(xslFile)));
        return template;
    }

    public static XPath getXPath() {
        XPathFactory factory = XPathFactory.newInstance();
        XPath xpath = factory.newXPath();
        return xpath;
    }

    public static Document parseDOMFromString(String srcXml) throws ParserConfigurationException, SAXException, IOException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        InputStreamReader reader = new InputStreamReader(FileHelper.getStreamFromString(srcXml), "UTF-8");
        InputSource is = new InputSource(reader);
        is.setEncoding("UTF-8");
        Document doc = builder.parse(is);
        return doc;
    }

    public static <T> T unmarshallElementFile(String file, Class objectFactoryClass) throws JAXBException {
        JAXBContext jc = JAXBContext.newInstance(objectFactoryClass);
        Unmarshaller u = jc.createUnmarshaller();
        File f = new File(file);
        T obj = (T) ((JAXBElement) u.unmarshal(f)).getValue();
        return obj;
    }

    public static String marshall(Object obj, Class... objClasses) throws Exception {
        JAXBContext jaxbContext = JAXBContext.newInstance(objClasses);
        Marshaller marshaller = jaxbContext.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, false);
        StringWriter sw = new StringWriter();
        marshaller.marshal(obj, sw);
        String xml = sw.toString();
        return xml;
    }

    public static <T> T unmarshallDocFile(String file, Class objectFactoryClass) throws JAXBException {
        JAXBContext jc = JAXBContext.newInstance(objectFactoryClass);
        Unmarshaller u = jc.createUnmarshaller();
        File f = new File(file);
        T obj = (T) u.unmarshal(f);
        return obj;
    }

    public static <T> T unmarshallDocXml(String xml, Class objectFactoryClass) throws JAXBException {
        JAXBContext jc = JAXBContext.newInstance(objectFactoryClass);
        Unmarshaller u = jc.createUnmarshaller();
        T obj = (T) u.unmarshal(new InputSource(new StringReader(xml)));
        return obj;
    }

    public static void generateCodeFromSchema(String output, String forcePkgName, String xsd, ErrorListener errListener) throws IOException {
        SchemaCompiler sc = XJC.createSchemaCompiler();
        if (errListener != null) {
            sc.setErrorListener(errListener);
        }
        sc.forcePackageName(forcePkgName);
        File schema = new File(xsd);
        InputSource is = new InputSource(schema.toURI().toString());
        sc.parseSchema(is);
        S2JJAXBModel model = sc.bind();
        JCodeModel code = model.generateCode(null, null);
        code.build(new File(output));
    }

}
