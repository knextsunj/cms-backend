package com.github.knextsunj.cms.domain

import org.hibernate.annotations.DynamicUpdate
import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name = "country")
@NamedQueries(
    NamedQuery(name = "Country.findByName", query = "select country from Country country where country.name=:name"),
    NamedQuery(name = "Country.findAllCountriesByDeleted", query= "select country from Country country where country.deleted=:deleted")
)
@DynamicUpdate
open class Country {

    @Id
    @SequenceGenerator(name = "COUNTRY_ID_SEQUENCE", sequenceName = "COUNTRY_ID_SEQUENCE", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "COUNTRY_ID_SEQUENCE")
    open var id: Long? = null

    @Column(name = "name")
    open var name: String? = null

    @Column(name = "is_deleted", insertable = false)
    open var deleted: String? = null

    @Column(name = "created_date")
    open var createdDate: LocalDateTime? = null;

    @Column(name = "updated_date")
    open var updatedDate: LocalDateTime? = null;

    @OneToMany(targetEntity = State::class, mappedBy = "country", fetch = FetchType.LAZY)
    open var states: Set<State>? = null

    @OneToMany(targetEntity = Address::class, mappedBy = "country", fetch = FetchType.LAZY)
    open var addresses: Set<Address>? = null
}