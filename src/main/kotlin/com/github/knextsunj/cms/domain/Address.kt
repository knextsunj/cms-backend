package com.github.knextsunj.cms.domain

import org.hibernate.annotations.DynamicUpdate
import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name = "address")
@NamedQuery(
    name = "Address.findAddressByCustomerIdAndDeleted",
    query = "select address from Address address JOIN FETCH address.customer where address.customer.id=:customerId and address.deleted=:deleted"
)
@DynamicUpdate
open class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    open var id: Long? = 0;

    @Column(name = "name")
    open var name: String? = null;

    @Column(name = "is_deleted", insertable = false)
    open var deleted: String? = null

    @Column(name = "created_date")
    open var createdDate: LocalDateTime? = null;

    @Column(name = "updated_date")
    open var updatedDate: LocalDateTime? = null;

    @Column(name = "street")
    open var street: String? = null;

    @Column(name = "locality")
    open var locality: String? = null;

    @Column(name = "area")
    open var area: String? = null;

    @Column(name = "pincode")
    open var pincode: Long? = 0;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "city_id")
    open var city: City? = null

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "state_id")
    open var state: State? = null

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "country_id")
    open var country: Country? = null

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "address_type_id")
    open var addressType: AddressType? = null

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    open var customer: Customer? = null

}