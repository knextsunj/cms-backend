package com.github.knextsunj.cms.domain

import org.hibernate.annotations.DynamicUpdate
import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name = "customer_status")
@NamedQueries(
    NamedQuery(name = "CustomerStatus.findCustomerStatusByName", query = "select customerstatus from CustomerStatus customerstatus where customerstatus.name=:name"),
    NamedQuery(name = "CustomerStatus.findAllCustomerStatusByDeleted", query = "select customerstatus from CustomerStatus customerstatus where customerstatus.deleted=:deleted")
)
@DynamicUpdate
open class CustomerStatus {

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

    @OneToMany(targetEntity = Customer::class, mappedBy = "customerStatus", fetch = FetchType.LAZY)
    open var customers: Set<Customer>? = null
}