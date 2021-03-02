/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dat3.jpademo.entities;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

/**
 *
 * @author jacob
 */
public class Tester {
    
    public static void main(String[] args) {
        
        
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
        EntityManager em = emf.createEntityManager();
       
        Person p1 = new Person("Jacob", 1993);
        Person p2 = new Person("steffen", 1995);
        
        Address a1 = new Address("langgade 62", 2480, "stege");
        Address a2 = new Address("udbyvej 16", 2480, "udby");

        
        
        p1.setAddress(a1);
        p2.setAddress(a2);
        
        Fee f1 = new Fee(100);
        Fee f2 = new Fee(200);
        Fee f3 = new Fee(300);
        
        
        p1.AddFee(f1);
        p1.AddFee(f3);
        p2.AddFee(f2);
        
        
        em.getTransaction().begin();
             em.persist(p1);
             em.persist(p2);
        
        em.getTransaction().commit();
        
        System.out.println("p1: " + p1.getP_id() + ", " + p1.getName());
        
        System.out.println("p2: " + p2.getP_id() + ", " + p1.getName());
        
        System.out.println("Jacobs gade: " + p1.getAddress().getStreet());
        
        System.out.println("Lad os se om to-vejs virker: " + a1.getPerson().getName());
        
        System.out.println("Hvem har betalt f2? Det har: " + f2.getPerson().getName());
        
        
        System.out.println("Hvad er det blevet betalt i alt?");
        
        TypedQuery<Fee> q1 = em.createQuery("SELECT f FROM Fee f", Fee.class);
        List<Fee> fees = q1.getResultList();
        
        for(Fee f: fees){
            System.out.println(f.getPerson().getName() +  ": " +f.getAmount() + "kr. Den: " + f.getPayDate() + ", adr: " + f.getPerson().getAddress().getCity());
        }
        
        
    }
    
}
