package ru.job4j.serialization.json;

public class ContactSt {
    private final String phone;
    private final String adress;

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