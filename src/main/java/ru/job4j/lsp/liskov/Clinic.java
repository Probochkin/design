package ru.job4j.lsp.liskov;
/*
Пример нарушения нарушение принципа LSP
Инварианты (Invariants) — все условия базового класса также должны быть сохранены и в подклассе
 */
public class Clinic {
    public void pulse(int pulse) {
        validatePulse(pulse);
        System.out.println("Your health is good! Your pulse is " + pulse);
    }
    private void validatePulse(int pulse) {
        if (pulse < 60 || pulse > 90) {
            throw new IllegalArgumentException("Your pulse is bad, you need a doctor!");
        }
    }
}

class SomeClinic extends Clinic {
    public void pulse(int pulse) {
        /* Забыли сделать проверку. Возможно не валидное состояние */
        System.out.println("Your health is good! Your pulse is " + pulse);
    }
}

class TestClinic {
    public static void main(String[] args) {
        Clinic someClinic = new SomeClinic();
        someClinic.pulse(15);
        Clinic clinic = new Clinic();
        clinic.pulse(95);
    }
}
