package model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name="requests")
@XmlAccessorType(XmlAccessType.FIELD)
public class Requests {

    @XmlElement(name="request")
    private List<Orders> fields = new ArrayList<Orders>();

    Requests(){

    }

    public List<Orders> getFields() {
        return fields;
    }

    public void setFields(List<Orders> fields) {
        this.fields = fields;
    }
}
