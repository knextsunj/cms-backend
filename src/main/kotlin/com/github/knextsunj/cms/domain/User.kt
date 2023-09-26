package com.github.knextsunj.cms.domain

//import org.hibernate.annotations.DynamicUpdate
import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name = "users")
@NamedQuery(
    name = "User.findByUsername",
    query = "select user from User user where name=:username",
)
//@DynamicUpdate
open class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    open var id: Long? = 0;

    @Column(name = "name")
    open var username: String? = null;

    @Column(name = "is_deleted", insertable = false)
    open var deleted: String? = null

    @Column(name = "password")
    open var password:String? = null

    @Column(name = "created_date")
    open var createdDate: LocalDateTime? = null;

    @Column(name = "updated_date")
    open var updatedDate: LocalDateTime? = null;

    @Column(name = "role")
    open var role:String? = null;

    @OneToMany(targetEntity = UserPreference::class, mappedBy = "user", fetch = FetchType.LAZY)
    open var userPreferences: Set<UserPreference>? = null
}