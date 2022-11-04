
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
 *         &lt;element name="SvcHdr" type="{http://www.example.org/DONLIM_SCM_QUERYSYNC/}SvcHdrType"/>
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
@XmlRootElement(name = "DONLIM_SCM_QUERYSYNC")
public class DONLIMSCMQUERYSYNC_Type {

    @XmlElement(name = "SvcHdr", required = true)
    protected SvcHdrType svcHdr;

    /**
     * 获取svcHdr属性的值。
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
     * 设置svcHdr属性的值。
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
