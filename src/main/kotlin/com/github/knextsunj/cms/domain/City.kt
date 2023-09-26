package com.github.knextsunj.cms.domain

//import org.hibernate.annotations.DynamicUpdate
import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name = "city")
@NamedQuery(
    name = "city.findCityByStateIdAndDeleted",
    query = "select city from City city JOIN FETCH city.state where city.state.id=:stateId and city.deleted=:deleted"
)
@NamedQuery(name = "city.findCityByName", query = "select city from City city where city.name=:name")
//@DynamicUpdate
open class City {

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

    @ManyToOne
    @JoinColumn(name = "state_id")
    open var state: State? = null

    @OneToMany(targetEntity = Address::class, mappedBy = "city", fetch = FetchType.LAZY)
    open var addresses: Set<Address>? = null
}