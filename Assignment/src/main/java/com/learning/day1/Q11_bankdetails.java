package main.java.com.learning.day1;//Create 3 sub class of bank SBI,BOI,ICICI all 4 should have method called getDetails which provide there specific details like rateofinterest etc,print details of every banks

import java.util.Scanner;

abstract class Bank
    {
        String name="";
        String type="";
        float roi;
        int branches;
        Bank()
        {

        }
        Bank(String name,float r,int b)
        {
            this.name=name;
            this.roi=r;
            this.branches=b;
        }
        public void getDetails()
        {
            System.out.println("Name: "+name);
            System.out.println("Type: "+type);
            System.out.println("Rate: "+roi);
        }

    }
    class SBI extends Bank
    {
        SBI()
        {
            System.out.println("enter the details for SBI bank");
            Scanner sc = new Scanner(System.in);
            System.out.println("enter name");
            name=sc.nextLine();
            System.out.println("enter type");
            type=sc.nextLine();
            System.out.println("enter rate of interest");
            roi=sc.nextFloat();
        }
    }
    class BOI extends Bank
    {
        String slogan;
        BOI()
        {
            System.out.println("enter the details for BOI bank");
            Scanner sc = new Scanner(System.in);
            System.out.println("enter name");
            name=sc.nextLine();
            System.out.println("enter type");
            type=sc.nextLine();
            System.out.println("enter rate of interest");
            roi=sc.nextFloat();
            System.out.println("enter slogan");
            slogan =sc.nextLine();
        }
        public void getDetails()
        {
            super.getDetails();
            System.out.println("Slogan: "+slogan);
        }
    }
    class ICICI extends Bank
    {
        String facility;
        ICICI()
        {
            System.out.println("enter the details for ICICI bank");
            Scanner sc = new Scanner(System.in);
            System.out.println("enter name");
            name=sc.nextLine();
            System.out.println("enter type");
            type=sc.nextLine();
            System.out.println("enter roi");
            roi=sc.nextFloat();
            System.out.println("enter facility");
            facility=sc.nextLine();
        }

        public void getDetails()
        {
            super.getDetails();
            System.out.println("Facility: "+facility);
        }
    }
    class BankDetails
    {
        public static void main(String[] args)
        {
            SBI sbi=new SBI();
            BOI boi=new BOI();
            ICICI icici=new ICICI();
            sbi.getDetails();
            boi.getDetails();
            icici.getDetails();
        }
    }

