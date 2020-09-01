package com.infiniteskills.data;

import com.infiniteskills.data.entities.Account;
import com.infiniteskills.data.entities.Address;
import com.infiniteskills.data.entities.Credential;
import com.infiniteskills.data.entities.Transaction;
import com.infiniteskills.data.entities.User;
import org.hibernate.Session;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;

public class UserAccountApplication {
    public static void main(String[] args) {
        Session session = HibernateUtil.getSessionFactory().openSession();

        try {
            org.hibernate.Transaction transaction = session.beginTransaction();

            Account account = createNewAccount();
            Account account2 = createNewAccount();
            User user = createUser();
            User user2 = createUser();

            account.getUsers().add(user);
            account.getUsers().add(user2);

            user.getAccounts().add(account);
            user2.getAccounts().add(account);

            account2.getUsers().add(user);
            account2.getUsers().add(user2);

            user.getAccounts().add(account2);
            user2.getAccounts().add(account2);

            session.save(account);
            session.save(account2);

            transaction.commit();

            Account dbAccount = (Account) session.get(Account.class, account.getAccountId());
            System.out.println(dbAccount.getUsers().iterator().next().getEmailAddress());


        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            session.close();
            HibernateUtil.getSessionFactory().close();
        }
    }

    private static User createUser() {
        User user = new User();
        Address address = createAddress();
        user.setAddresses(Arrays.asList(new Address[]{createAddress()}));
        user.setBirthDate(new Date());
        user.setCreatedBy("Kevin Bowersox");
        user.setCreatedDate(new Date());
        user.setCredential(createCredential(user));
        user.setEmailAddress("test@test.com");
        user.setFirstName("John");
        user.setLastName("Doe");
        user.setLastUpdatedBy("system");
        user.setLastUpdatedDate(new Date());
        return user;
    }

    private static Credential createCredential(User user) {
        Credential credential = new Credential();
        credential.setUser(user);
        credential.setUsername("test_username");
        credential.setPassword("test_password");
        return credential;
    }

    private static Address createAddress() {
        Address address = new Address();
        address.setAddressLine1("101 Address Line");
        address.setAddressLine2("102 Address Line");
        address.setCity("New York");
        address.setState("PA");
        address.setZipCode("10000");
        address.setAddressType("PRIMARY");
        return address;
    }

    private static Account createNewAccount() {
        Account account = new Account();
        account.setCloseDate(new Date());
        account.setOpenDate(new Date());
        account.setCreatedBy("Kevin Bowersox");
        account.setInitialBalance(new BigDecimal("50.00"));
        account.setName("Savings Account");
        account.setCurrentBalance(new BigDecimal("100.00"));
        account.setLastUpdatedBy("Kevin Bowersox");
        account.setLastUpdatedDate(new Date());
        account.setCreatedDate(new Date());
        return account;
    }
}
