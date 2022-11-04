
package com.donlim.aps.webservice.aps;

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
 *         &lt;element name="SvcHdrs" type="{http://www.example.org/DONLIM_APS_QUERYSYNC/}SvcHdrsType"/>
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
    "svcHdrs"
})
@XmlRootElement(name = "DONLIM_APS_QUERYSYNCResponse")
public class DONLIMAPSQUERYSYNCResponse {

    @XmlElement(name = "SvcHdrs", required = true)
    protected SvcHdrsType svcHdrs;

    /**
     * ��ȡsvcHdrs���Ե�ֵ��
     *
     * @return
     *     possible object is
     *     {@link SvcHdrsType }
     *
     */
    public SvcHdrsType getSvcHdrs() {
        return svcHdrs;
    }

    /**
     * ����svcHdrs���Ե�ֵ��
     *
     * @param value
     *     allowed object is
     *     {@link SvcHdrsType }
     *
     */
    public void setSvcHdrs(SvcHdrsType value) {
        this.svcHdrs = value;
    }

}
