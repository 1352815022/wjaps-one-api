
package com.donlim.aps.webservice.mcas;

import javax.xml.bind.annotation.*;


/**
 * <p>anonymous complex type�� Java �ࡣ
 *
 * <p>����ģʽƬ��ָ�������ڴ����е�Ԥ�����ݡ�
 *
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="SvcHdr" type="{http://www.example.org/DONLIM_MCAS_QUERYSYNC/}SvcHdrType"/>
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
@XmlType(name = "", propOrder = {
    "svcHdr",
    "user01"
})
@XmlRootElement(name = "DONLIM_MCAS_QUERYSYNC")
public class DONLIMMCASQUERYSYNC_Type {

    @XmlElement(name = "SvcHdr", required = true)
    protected SvcHdrType svcHdr;
    @XmlElement(name = "User01")
    protected String user01;

    /**
     * ��ȡsvcHdr���Ե�ֵ��
     *
     * @return
     *     possible object is
     *     {@link SvcHdrType }
     *
     */
    public SvcHdrType getSvcHdr() {
        return svcHdr;
    }

    /**
     * ����svcHdr���Ե�ֵ��
     *
     * @param value
     *     allowed object is
     *     {@link SvcHdrType }
     *
     */
    public void setSvcHdr(SvcHdrType value) {
        this.svcHdr = value;
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

}
