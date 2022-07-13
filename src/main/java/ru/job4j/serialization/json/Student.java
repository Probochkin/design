package ru.job4j.serialization.json;


import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.*;
import java.io.StringWriter;
import java.util.Arrays;
@XmlRootElement(name = "student")
@XmlAccessorType(XmlAccessType.FIELD)
public class Student {

    @XmlAttribute
    private  boolean sex;
    @XmlAttribute
    private  int age;
    private  String FIO;
    private  ContactSt contactSt;

    @XmlElementWrapper(name = "optionales")
    @XmlElement(name = "optional")
    private  String[] optionales;

    public Student(boolean sex, int age,String FIO, ContactSt contactSt, String[] optional) {
        this.sex = sex;
        this.age = age;
        this.contactSt = contactSt;
        this.optionales = optional;
        this.FIO = FIO;
    }
    public Student() {

    }

    @Override
    public String toString() {
        return "{"
                + "\"sex\":" + sex
                + ",\"age\":" +  age
                + ",\"contactSt\":"  + contactSt
                + ",\"optional\":" +  Arrays.toString(optionales)
                + ",\"FIO\":" +  "\"" + FIO + "\""
                + '}';
    }
    public static void main(String[] args) throws Exception {

        final Student student = new Student(false, 30, "Petrov Ivan Nikolaevich", new ContactSt("11-111","Penza"), new String[] {"English", "History"});

        JAXBContext context = JAXBContext.newInstance(Student.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

        try (StringWriter writer = new StringWriter()) {
            marshaller.marshal(student, writer);
            String result = writer.getBuffer().toString();
            System.out.println(result);
        } catch (Exception e) {

        }
    }
}
