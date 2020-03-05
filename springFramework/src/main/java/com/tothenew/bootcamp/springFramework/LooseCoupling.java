//this is a loosely coupled code
package com.tothenew.bootcamp.springFramework;

public class LooseCoupling {
    public static void main(String[] args) {
        Manager manager = new Manager(new LazyWorker());
        manager.managework();
    }
}

class manager {
    private Worker worker;

    public manager(Worker worker) {
        this.worker = worker;
    }

    public void managework() {
        worker.dowork();
    }
}

class LazyWorker implements Worker {
    public void dowork() {
        System.out.println("lazy");
    }
}

class ExcellentWorker implements Worker {
    public void dowork() {
        System.out.println("execellent");
    }
}

