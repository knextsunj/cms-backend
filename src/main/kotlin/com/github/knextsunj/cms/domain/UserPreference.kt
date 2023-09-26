package com.github.knextsunj.cms.domain

//import org.hibernate.annotations.DynamicUpdate
import javax.persistence.*

@Entity
@Table(name = "user_preferences")
@NamedQuery(
    name = "UserPreference.findByUserId",
    query = "select userpreference from UserPreference userpreference where userpreference.user.id=:userId",
)
//@DynamicUpdate
open class UserPreference {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    open var id: Long? = 0;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    open var user:User? = null

    @Column(name = "page_name")
    open var pageName: String? = null
}