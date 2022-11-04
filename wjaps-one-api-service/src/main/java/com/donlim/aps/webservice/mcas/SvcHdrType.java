
package com.donlim.aps.webservice.mcas;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>SvcHdrType complex type�� Java �ࡣ
 *
 * <p>����ģʽƬ��ָ�������ڴ����е�Ԥ�����ݡ�
 *
 * <pre>
 * &lt;complexType name="SvcHdrType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="SOURCEID" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="DESTINATIONID" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="TYPE" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="IPADDRESS" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="BO" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="NO" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="BodyJson" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="User01" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="User02" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="User03" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="User04" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="User05" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
    "bodyJson",
    "user01",
    "user02",
    "user03",
    "user04",
    "user05"
})
public class SvcHdrType {

    @XmlElement(name = "SOURCEID", required = true)
    protected String sourceid;
    @XmlElement(name = "DESTINATIONID", required = true)
    protected String destinationid;
    @XmlElement(name = "TYPE")
    protected String type;
    @XmlElement(name = "IPADDRESS")
    protected String ipaddress;
    @XmlElement(name = "BO")
    protected String bo;
    @XmlElement(name = "NO", required = true)
    protected String no;
    @XmlElement(name = "BodyJson", required = true)
    protected String bodyJson;
    @XmlElement(name = "User01")
    protected String user01;
    @XmlElement(name = "User02")
    protected String user02;
    @XmlElement(name = "User03")
    protected String user03;
    @XmlElement(name = "User04")
    protected String user04;
    @XmlElement(name = "User05")
    protected String user05;

    /**
     * ��ȡsourceid���Ե�ֵ��
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
     * ����sourceid���Ե�ֵ��
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
     * ��ȡdestinationid���Ե�ֵ��
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
     * ����destinationid���Ե�ֵ��
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
     * ��ȡtype���Ե�ֵ��
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
     * ����type���Ե�ֵ��
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
     * ��ȡipaddress���Ե�ֵ��
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
     * ����ipaddress���Ե�ֵ��
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
     * ��ȡbo���Ե�ֵ��
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
     * ����bo���Ե�ֵ��
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
     * ��ȡno���Ե�ֵ��
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
     * ����no���Ե�ֵ��
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
     * ��ȡbodyJson���Ե�ֵ��
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
     * ����bodyJson���Ե�ֵ��
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setBodyJson(String value) {
        this.bodyJson = value;
    }

    /**
     * ��ȡuser01���Ե�ֵ��
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
     * ����user01���Ե�ֵ��
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setUser01(String value) {
        this.user01 = value;
    }

    /**
     * ��ȡuser02���Ե�ֵ��
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getUser02() {
        return user02;
    }

    /**
     * ����user02���Ե�ֵ��
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setUser02(String value) {
        this.user02 = value;
    }

    /**
     * ��ȡuser03���Ե�ֵ��
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getUser03() {
        return user03;
    }

    /**
     * ����user03���Ե�ֵ��
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setUser03(String value) {
        this.user03 = value;
    }

    /**
     * ��ȡuser04���Ե�ֵ��
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getUser04() {
        return user04;
    }

    /**
     * ����user04���Ե�ֵ��
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setUser04(String value) {
        this.user04 = value;
    }

    /**
     * ��ȡuser05���Ե�ֵ��
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getUser05() {
        return user05;
    }

    /**
     * ����user05���Ե�ֵ��
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setUser05(String value) {
        this.user05 = value;
    }

}
