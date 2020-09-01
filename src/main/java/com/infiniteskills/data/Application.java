package com.infiniteskills.data;

import com.infiniteskills.data.entities.Address;
import com.infiniteskills.data.entities.Bank;
import com.infiniteskills.data.entities.Credential;
import com.infiniteskills.data.entities.TimeTest;
import com.infiniteskills.data.entities.User;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.Calendar;
import java.util.Date;

public class Application {
    public static void main(String[] args) {
        // runTimeTest();
        // runUser();
        // runBank();
        // runUserAddress();
        // runCredentialUser();
        runUserCredential();
    }
    private static void runTimeTest(){
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.getTransaction().begin();

            TimeTest test = new TimeTest(new Date());
            session.save(test);
            session.getTransaction().commit();

            session.refresh(test);

            System.out.println(test.toString());

        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            session.close();
            HibernateUtil.getSessionFactory().close();
        }
    }
    private static void runBank(){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Bank bank = new Bank();
        bank.setName("Federal Trust");
        bank.setAddressLine1("33 Wall st");
        bank.setAddressLine2("N/A");
        bank.setCity("New York");
        bank.setState("NY");
        bank.setZipCode("27914");
        bank.setCreatedBy("Kevin Bowersox");
        bank.setCreatedDate(new Date());
        bank.setLastUpdatedBy("Kevin Bowersox");
        bank.setLastUpdatedDate(new Date());
        bank.setInternational(false);
        bank.getContacts().put("MANGER", "Joe");
        bank.getContacts().put("TELLER", "Mary");
        session.save(bank);
        transaction.commit();
    }
    private static void runUser(){
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        User user = new User();
        user.setBirthDate(getMyBirthday());
        user.setCreatedBy("kevin");
        user.setCreatedDate(new Date());
        user.setEmailAddress("kmb385@yahoo.com");
        user.setFirstName("Kevin");
        user.setLastName("Bowersox");
        user.setLastUpdatedBy("kevin");
        user.setLastUpdatedDate(new Date());
        session.save(user);
        session.getTransaction().commit();
        session.refresh(user);
        System.out.println(user.getAge());
        /*
        session.beginTransaction();
        User dbUser = (User) session.get(User.class, user.getUserId());
        dbUser.setFirstName("ve");
        dbUser.setLastName("ve last");
        dbUser.setLastUpdatedDate(new Date());
        session.getTransaction().commit();
        session.close();

         */
    }

    private static void runUserAddress(){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        User user = new User();
        Address address = new Address();
        Address address2 = new Address();
        user.setAge(22);
        user.setBirthDate(new Date());
        user.setCreatedBy("Kevin");
        user.setCreatedDate(new Date());
        user.setFirstName("Kevin");
        user.setLastName("Bowersox");
        user.setEmailAddress("hello");
        user.setLastUpdatedBy("kmb");
        user.setLastUpdatedDate(new Date());
        setAddressFields(address);
        setAddressFields2(address2);
        user.getAddress().add(address);
        user.getAddress().add(address2);
        session.save(user);
        transaction.commit();
    }

    private static void runCredentialUser(){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        User user = new User();
        user.setAge(22);
        user.setBirthDate(new Date());
        user.setCreatedBy("Kevin");
        user.setCreatedDate(new Date());
        user.setFirstName("Kevin");
        user.setLastName("Bowersox");
        user.setEmailAddress("hello");
        user.setLastUpdatedBy("kmb");
        user.setLastUpdatedDate(new Date());
        Credential credential = new Credential();
        credential.setUsername("kmb");
        credential.setPassword("123");
        credential.setUser(user);
        session.save(credential);
        transaction.commit();
    }

    private static void runUserCredential(){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        User user = new User();
        user.setAge(22);
        user.setBirthDate(new Date());
        user.setCreatedBy("Kevin");
        user.setCreatedDate(new Date());
        user.setFirstName("Kevin");
        user.setLastName("Bowersox");
        user.setEmailAddress("hello");
        user.setLastUpdatedBy("kmb");
        user.setLastUpdatedDate(new Date());
        Credential credential = new Credential();
        credential.setUsername("kmb");
        credential.setPassword("123");
        credential.setUser(user);
        user.setCredential(credential); // manging both side of the relationship
        session.save(credential);
        transaction.commit();

        User dbUser = (User) session.get(User.class, credential.getUser().getUserId());
        System.out.println(dbUser.getFirstName());
        System.out.println(dbUser.getCredential().getCredentialId());
    }

    private static Date getMyBirthday(){
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, 1984);
        calendar.set(Calendar.MONTH, 6);
        calendar.set(Calendar.DATE, 19);
        return calendar.getTime();
    }
    private static void setAddressFields(Address address){
        address.setAddressLine1("line1");
        address.setAddressLine2("line2");
        address.setCity("Philadelphia");
        address.setState("PA");
        address.setZipCode("12345");
    }
    private static void setAddressFields2(Address address){
        address.setAddressLine1("line1");
        address.setAddressLine2("line2");
        address.setCity("Philadelphia");
        address.setState("CO");
        address.setZipCode("12345");
    }
}
