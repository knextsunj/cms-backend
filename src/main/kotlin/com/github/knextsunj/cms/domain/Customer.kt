package com.github.knextsunj.cms.domain

import java.time.LocalDate
import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name = "customer")
@NamedQueries(
    NamedQuery(name = "Customer.findByName", query = "select customer from Customer customer where customer.name=:name"),
    NamedQuery(name = "Customer.findAllByActiveStatusAndDeleted", query = "select customer from Customer customer JOIN FETCH customer.customerStatus where customer.customerStatus.name=:activeStatus and customer.deleted=:deleted")
)
open class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    open var id: Long? = 0;

    @Column(name = "name")
    open var name: String? = null;

    @Column(name = "is_deleted", insertable = false)
    open var deleted: String? = null


    @Column(name = "dob")
    open var dateOfBirth: LocalDate? = null;

    @Column(name = "gender")
    open var gender: String? = null;

    @Column(name = "mobile_no")
    open var mobileNo: Long? = null;

    @Column(name = "email")
    open var emailAddress: String? = null;

    @Column(name = "created_date")
    open var createdDate: LocalDateTime? = null;

    @Column(name = "updated_date")
    open var updatedDate: LocalDateTime? = null;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_status_id")
    open var customerStatus: CustomerStatus? = null

    @OneToMany(targetEntity = Address::class, mappedBy = "customer", fetch = FetchType.LAZY)
    open var addresses: Set<Address>? = null
}