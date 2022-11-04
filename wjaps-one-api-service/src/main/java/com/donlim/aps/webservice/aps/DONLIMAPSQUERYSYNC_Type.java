
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
 *         &lt;element name="SvcHdr" type="{http://www.example.org/DONLIM_APS_QUERYSYNC/}SvcHdrType"/>
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
    "svcHdr"
})
@XmlRootElement(name = "DONLIM_APS_QUERYSYNC")
public class DONLIMAPSQUERYSYNC_Type {

    @XmlElement(name = "SvcHdr", required = true)
    protected SvcHdrType svcHdr;

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

}
