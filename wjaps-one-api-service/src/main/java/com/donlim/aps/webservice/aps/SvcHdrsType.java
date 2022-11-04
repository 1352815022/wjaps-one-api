
package com.donlim.aps.webservice.aps;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>SvcHdrsType complex type�� Java �ࡣ
 *
 * <p>����ģʽƬ��ָ�������ڴ����е�Ԥ�����ݡ�
 *
 * <pre>
 * &lt;complexType name="SvcHdrsType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="RCODE" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="RDESC" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ESBCODE" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="User01" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="User02" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="User03" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="User04" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="User05" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ResultJson" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SvcHdrsType", propOrder = {
    "rcode",
    "rdesc",
    "esbcode",
    "user01",
    "user02",
    "user03",
    "user04",
    "user05",
    "resultJson"
})
public class SvcHdrsType {

    @XmlElement(name = "RCODE", required = true)
    protected String rcode;
    @XmlElement(name = "RDESC")
    protected String rdesc;
    @XmlElement(name = "ESBCODE")
    protected String esbcode;
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
    @XmlElement(name = "ResultJson")
    protected String resultJson;

    /**
     * ��ȡrcode���Ե�ֵ��
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
     * ����rcode���Ե�ֵ��
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
     * ��ȡrdesc���Ե�ֵ��
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
     * ����rdesc���Ե�ֵ��
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
     * ��ȡesbcode���Ե�ֵ��
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
     * ����esbcode���Ե�ֵ��
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

    /**
     * ��ȡresultJson���Ե�ֵ��
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
     * ����resultJson���Ե�ֵ��
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setResultJson(String value) {
        this.resultJson = value;
    }

}
