package com.tothenew.bootcamp.springFramework;


public class TightCoupling {
    public static void main(String[] args) {
        Emplyee emplyee = new Emplyee();
        emplyee.ExcellentWorker();
        emplyee.ExtraordinaryWorker();
        emplyee.LazyWorker();
    }
}
class Emplyee {

    void LazyWorker() {
        System.out.println("lazy");
    }

    void ExcellentWorker() {
        System.out.println("execellent");
    }

    void ExtraordinaryWorker() {
        System.out.println("extraordinary");
    }
}