
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
 *         &lt;element name="SvcHdrs" type="{http://www.example.org/DONLIM_MCAS_QUERYSYNC/}SvcHdrsTypes"/>
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
    "svcHdrs",
    "user01"
})
@XmlRootElement(name = "DONLIM_MCAS_QUERYSYNCResponse")
public class DONLIMMCASQUERYSYNCResponse {

    @XmlElement(name = "SvcHdrs", required = true)
    protected SvcHdrsTypes svcHdrs;
    @XmlElement(name = "User01")
    protected String user01;

    /**
     * ��ȡsvcHdrs���Ե�ֵ��
     *
     * @return
     *     possible object is
     *     {@link SvcHdrsTypes }
     *
     */
    public SvcHdrsTypes getSvcHdrs() {
        return svcHdrs;
    }

    /**
     * ����svcHdrs���Ե�ֵ��
     *
     * @param value
     *     allowed object is
     *     {@link SvcHdrsTypes }
     *
     */
    public void setSvcHdrs(SvcHdrsTypes value) {
        this.svcHdrs = value;
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
