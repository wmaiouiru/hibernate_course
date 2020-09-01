package com.infiniteskills.data.entities;

import org.hibernate.annotations.Formula;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Transient;

@Entity
@Table(name="finances_user")
public class User {

    /*
    @Id
    @GeneratedValue(strategy= GenerationType.TABLE, generator="user_table_generator")
    @TableGenerator(name="user_table_generator",
        table="ifinances_keys", pkColumnName = "PK_NAME", valueColumnName = "PK_VALUE")

    */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="USER_ID")
    private Long userId;

    @ManyToMany(cascade = CascadeType.ALL, mappedBy = "users")
    private Set<Account> accounts = new HashSet<Account>();

    @Column(name="FIRST_NAME", updatable = true)
    private String firstName;

    @Column(name="LAST_NAME", updatable = true)
    private String lastName;

    @Column(name="BIRTH_DATE")
    private Date birthDate;

    @Column(name="EMAIL_ADDRESS")
    private String emailAddress;

    @Column(name="LAST_UPDATED_DATE", updatable = true)
    private Date lastUpdatedDate;

    @Column(name="LAST_UPDATED_BY")
    private String lastUpdatedBy;

    @Column(name="CREATED_DATE")
    private Date createdDate;

    @Column(name="CREATED_BY")
    private String createdBy;

    @ElementCollection
    @CollectionTable(name="user_address", joinColumns = @JoinColumn(name="USER_ID"))
    @AttributeOverrides({
            @AttributeOverride(name="addressLine1", column = @Column(name="USER_ADDRESS_LINE_1")),
            @AttributeOverride(name="addressLine2", column = @Column(name="USER_ADDRESS_LINE_2"))
    })
    private List<Address> address = new ArrayList<Address>();
    /*
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name="addressLine1", column = @Column(name="USER_ADDRESS_LINE_1")),
            @AttributeOverride(name="addressLine2", column = @Column(name="USER_ADDRESS_LINE_2"))
    })
    private Address address;
    */
    @ElementCollection
    @CollectionTable(name = "USER_ADDRESS", joinColumns = @JoinColumn(name = "USER_ID"))
    @AttributeOverrides({
            @AttributeOverride(name = "addressLine1", column = @Column(name = "USER_ADDRESS_LINE_1")),
            @AttributeOverride(name = "addressLine2", column = @Column(name = "USER_ADDRESS_LINE_2")) })
    private List<Address> addresses = new ArrayList<Address>();

    // Inverse asscoiation
    @OneToOne(mappedBy = "user")
    private Credential credential;


    @Formula("lower(datediff(curdate(), birth_date)/365)")
    private int age;

    public User() {
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Transient
    private boolean valid;

    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public Date getLastUpdatedDate() {
        return lastUpdatedDate;
    }

    public void setLastUpdatedDate(Date lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
    }

    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public List <Address> getAddress() {
        return address;
    }

    public void setAddress(List<Address> address) {
        this.address = address;
    }

    public Credential getCredential() {
        return credential;
    }

    public void setCredential(Credential credential) {
        this.credential = credential;
    }

    public List<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }

    public Set<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(Set<Account> accounts) {
        this.accounts = accounts;
    }
}
