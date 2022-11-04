
package com.donlim.aps.webservice.scm;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>SvcHdrsTypes complex type的 Java 类。
 *
 * <p>以下模式片段指定包含在此类中的预期内容。
 *
 * <pre>
 * &lt;complexType name="SvcHdrsTypes">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="RCODE" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="RDESC" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ESBCODE" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ResultJson" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="User01" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SvcHdrsTypes", propOrder = {
    "rcode",
    "rdesc",
    "esbcode",
    "resultJson",
    "user01"
})
public class SvcHdrsTypes {

    @XmlElement(name = "RCODE")
    protected String rcode;
    @XmlElement(name = "RDESC")
    protected String rdesc;
    @XmlElement(name = "ESBCODE")
    protected String esbcode;
    @XmlElement(name = "ResultJson")
    protected String resultJson;
    @XmlElement(name = "User01")
    protected String user01;

    /**
     * 获取rcode属性的值。
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getRCODE() {
        return rcode;
    }

    /**
     * 设置rcode属性的值。
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setRCODE(String value) {
        this.rcode = value;
    }

    /**
     * 获取rdesc属性的值。
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getRDESC() {
        return rdesc;
    }

    /**
     * 设置rdesc属性的值。
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setRDESC(String value) {
        this.rdesc = value;
    }

    /**
     * 获取esbcode属性的值。
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getESBCODE() {
        return esbcode;
    }

    /**
     * 设置esbcode属性的值。
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setESBCODE(String value) {
        this.esbcode = value;
    }

    /**
     * 获取resultJson属性的值。
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getResultJson() {
        return resultJson;
    }

    /**
     * 设置resultJson属性的值。
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setResultJson(String value) {
        this.resultJson = value;
    }

    /**
     * 获取user01属性的值。
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getUser01() {
        return user01;
    }

    /**
     * 设置user01属性的值。
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setUser01(String value) {
        this.user01 = value;
    }

}
