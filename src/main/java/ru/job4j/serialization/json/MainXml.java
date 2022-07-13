package ru.job4j.serialization.json;



import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;
import java.io.StringWriter;


public class MainXml {
    public static void main(String[] args) throws  Exception {
        final Student student = new Student(false, 30, "Petrov Ivan Nikolaevich", new ContactSt("+79005554784","Penza"),
                new String[] {"English", "History"});
        JAXBContext context = JAXBContext.newInstance(Student.class);
        /* Создаем сериализатор */
        Marshaller marshaller = context.createMarshaller();
        /* Указываем, что нам нужно форматирование */
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        String xml = "";
        try (StringWriter writer = new StringWriter()) {
            /* Сериализуем */
            marshaller.marshal(student, writer);
            xml = writer.getBuffer().toString();
            System.out.println(xml);
        }
        /* Для десериализации нам нужно создать десериализатор */
        Unmarshaller unmarshaller = context.createUnmarshaller();
        try (StringReader reader = new StringReader(xml)) {
            /* десериализуем */
            Student result = (Student) unmarshaller.unmarshal(reader);
            System.out.println(result);
        }

    }
    }

