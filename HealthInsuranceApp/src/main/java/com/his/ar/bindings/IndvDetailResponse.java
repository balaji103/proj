//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.7 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2019.02.12 at 03:35:08 AM PST 
//


package com.his.ar.bindings;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="IndvDetail" type="{http://www.usa.gov/ssn/types}IndvDetailType"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "indvDetail"
})
@XmlRootElement(name = "IndvDetailResponse")
public class IndvDetailResponse {

    @XmlElement(name = "IndvDetail", required = true)
    protected IndvDetailType indvDetail;

    /**
     * Gets the value of the indvDetail property.
     * 
     * @return
     *     possible object is
     *     {@link IndvDetailType }
     *     
     */
    public IndvDetailType getIndvDetail() {
        return indvDetail;
    }

    /**
     * Sets the value of the indvDetail property.
     * 
     * @param value
     *     allowed object is
     *     {@link IndvDetailType }
     *     
     */
    public void setIndvDetail(IndvDetailType value) {
        this.indvDetail = value;
    }

}
