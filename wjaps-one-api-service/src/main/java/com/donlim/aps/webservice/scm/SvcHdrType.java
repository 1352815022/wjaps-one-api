
package com.donlim.aps.webservice.scm;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>SvcHdrType complex type的 Java 类。
 *
 * <p>以下模式片段指定包含在此类中的预期内容。
 *
 * <pre>
 * &lt;complexType name="SvcHdrType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="SOURCEID" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="DESTINATIONID" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="TYPE" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="IPADDRESS" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="BO" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="NO" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="BodyJson" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SvcHdrType", propOrder = {
    "sourceid",
    "destinationid",
    "type",
    "ipaddress",
    "bo",
    "no",
    "bodyJson"
})
public class SvcHdrType {

    @XmlElement(name = "SOURCEID", required = true)
    protected String sourceid;
    @XmlElement(name = "DESTINATIONID", required = true)
    protected String destinationid;
    @XmlElement(name = "TYPE", required = true)
    protected String type;
    @XmlElement(name = "IPADDRESS", required = true)
    protected String ipaddress;
    @XmlElement(name = "BO", required = true)
    protected String bo;
    @XmlElement(name = "NO")
    protected String no;
    @XmlElement(name = "BodyJson")
    protected String bodyJson;

    /**
     * 获取sourceid属性的值。
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getSOURCEID() {
        return sourceid;
    }

    /**
     * 设置sourceid属性的值。
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setSOURCEID(String value) {
        this.sourceid = value;
    }

    /**
     * 获取destinationid属性的值。
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getDESTINATIONID() {
        return destinationid;
    }

    /**
     * 设置destinationid属性的值。
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setDESTINATIONID(String value) {
        this.destinationid = value;
    }

    /**
     * 获取type属性的值。
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getTYPE() {
        return type;
    }

    /**
     * 设置type属性的值。
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setTYPE(String value) {
        this.type = value;
    }

    /**
     * 获取ipaddress属性的值。
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getIPADDRESS() {
        return ipaddress;
    }

    /**
     * 设置ipaddress属性的值。
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setIPADDRESS(String value) {
        this.ipaddress = value;
    }

    /**
     * 获取bo属性的值。
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getBO() {
        return bo;
    }

    /**
     * 设置bo属性的值。
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setBO(String value) {
        this.bo = value;
    }

    /**
     * 获取no属性的值。
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getNO() {
        return no;
    }

    /**
     * 设置no属性的值。
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setNO(String value) {
        this.no = value;
    }

    /**
     * 获取bodyJson属性的值。
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getBodyJson() {
        return bodyJson;
    }

    /**
     * 设置bodyJson属性的值。
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setBodyJson(String value) {
        this.bodyJson = value;
    }

}
