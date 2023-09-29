package com.github.knextsunj.cms.domain

import org.hibernate.annotations.DynamicUpdate
import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name = "state")
@NamedQuery(
    name = "state.findStateByCountryIdAndDeleted",
    query = "select state from State state JOIN FETCH state.country where state.country.id=:countryId and state.deleted=:deleted"
)
@NamedQuery(name = "state.findStateByName", query = "select state from State state where state.name=:name")
@DynamicUpdate
open class State {

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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "country_id")
    open var country: Country? = null

    @OneToMany(targetEntity = City::class, mappedBy = "state", fetch = FetchType.LAZY)
    open var cities: Set<City>? = null

    @OneToMany(targetEntity = Address::class, mappedBy = "state", fetch = FetchType.LAZY)
    open var addresses: Set<Address>? = null
}