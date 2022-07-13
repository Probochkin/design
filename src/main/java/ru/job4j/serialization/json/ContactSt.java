package ru.job4j.serialization.json;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "contactSt")
public class ContactSt {
    @XmlAttribute
    private String phone;
    @XmlAttribute
    private String adress;

    public ContactSt () {

    }
    public ContactSt(String phone, String adress) {
        this.phone = phone;
        this.adress = adress;
    }

    @Override
    public String toString() {
        return "{"
                + "phone" + "=" + phone +","
                + "adress" + "=" + adress
                + '}';
    }
}