/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


function getXMLDoc(xmlStr) {
    if (window.DOMParser)
    {
        let parser = new DOMParser();
        let xmlDoc = parser.parseFromString(xmlStr, "text/xml");
        return xmlDoc;
    }
    let xmlDoc = new ActiveXObject("Microsoft.XMLDOM");
    xmlDoc.async = false;
    xmlDoc.loadXML(xmlStr);
    return xmlDoc;
}

function xpathEval(expr, xml, type) {
    return document.evaluate(expr, xml, null, type);
}