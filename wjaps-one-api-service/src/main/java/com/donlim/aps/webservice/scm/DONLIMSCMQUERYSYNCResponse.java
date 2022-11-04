
package com.donlim.aps.webservice.scm;

import javax.xml.bind.annotation.*;


/**
 * <p>anonymous complex type的 Java 类。
 *
 * <p>以下模式片段指定包含在此类中的预期内容。
 *
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="SvcHdrs" type="{http://www.example.org/DONLIM_SCM_QUERYSYNC/}SvcHdrsTypes" minOccurs="0"/>
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
@XmlRootElement(name = "DONLIM_SCM_QUERYSYNCResponse")
public class DONLIMSCMQUERYSYNCResponse {

    @XmlElement(name = "SvcHdrs")
    protected SvcHdrsTypes svcHdrs;

    /**
     * 获取svcHdrs属性的值。
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
     * 设置svcHdrs属性的值。
     *
     * @param value
     *     allowed object is
     *     {@link SvcHdrsTypes }
     *
     */
    public void setSvcHdrs(SvcHdrsTypes value) {
        this.svcHdrs = value;
    }

}
