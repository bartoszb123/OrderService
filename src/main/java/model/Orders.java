package model;

import javax.xml.bind.annotation.*;
import java.sql.Timestamp;

@XmlRootElement(name = "request")
//@XmlType(propOrder = {"clientId", "requestId", "name", "quantity", "price"})
public class Orders {


    //alfanumeryczne, bez spacji nie dłuższe niż 6 znaków,
//    @XmlAttribute(name = "request")
//    private String request;

    private String clientId;   // Nick uzytkownika

    private long requestId;
    //alfanumeryczne ze spacjami nie dłuższe niż 255 znaków,

    private String name;

    private int quantity;
    //numeryczne zmiennoprzecinkowe podwójnej precyzji.

    private double price;

    private String timestamp;

    public Orders(OrderBuilder orderBuilder) {
        this.clientId = orderBuilder.clientId;
        this.requestId = orderBuilder.requestId;
        this.name = orderBuilder.name;
        this.quantity = orderBuilder.quantity;
        this.price = orderBuilder.price;
        this.timestamp=orderBuilder.timestamp;
    }
    public Orders(){}


    @XmlElement
    public void setClientId(String clientId) {
        this.clientId = clientId;
    }
    public String getClientId() {
        return clientId;
    }

//    @XmlElement


    public long getRequestId() {
        return requestId;
    }

    @XmlElement
    public void setRequestId(long requestId) {
        this.requestId = requestId;
    }

    public String getName() {
        return name;
    }

    @XmlElement
    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    @XmlElement
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

   @XmlElement
    public void setPrice(double price) {
        this.price = price;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "Orders {" +
                "clientId='" + clientId + '\'' +
                ", requestId=" + requestId +
                ", name='" + name + '\'' +
                ", quantity=" + quantity +
                ", price=" + price +
                "}\n";
    }

    public static class OrderBuilder {

        private String clientId;
        private long requestId;
        private String name;
        private int quantity;
        private double price;
        private String timestamp;

       public OrderBuilder buildClientId(String clientId){
           this.clientId=clientId;
           return this;
       }

        public OrderBuilder buildRequestId(long requestId){
            this.requestId=requestId;
            return this;
        }
        public OrderBuilder buildName( String name){
            this.name=name;
            return this;
        }
        public OrderBuilder buildQuantity(int quantity){
            this.quantity=quantity;
            return this;
        }
        public OrderBuilder buildPrice(double price){
            this.price=price;
            return this;
        }

        public OrderBuilder buildTimeStmp(String timestamp){
            this.timestamp=timestamp;
            return this;
        }


        public Orders build(){
           return new Orders(this);
        }

    }


}
