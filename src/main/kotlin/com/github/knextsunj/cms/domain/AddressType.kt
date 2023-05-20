package com.github.knextsunj.cms.domain

import org.hibernate.annotations.DynamicUpdate
import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name = "address_type")
@NamedQueries(
    NamedQuery(name = "AddressType.findAddressTypeByName", query = "select addresstype from AddressType addresstype where addresstype.name=:name"),
    NamedQuery(name = "AddressType.findAllAddressTypes", query = "select addresstype from AddressType addresstype where addresstype.deleted='N'")
)
@DynamicUpdate
open class AddressType {

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

    @OneToMany(targetEntity = Address::class, mappedBy = "addressType", fetch = FetchType.LAZY)
    open var addresses: Set<Address>? = null

}