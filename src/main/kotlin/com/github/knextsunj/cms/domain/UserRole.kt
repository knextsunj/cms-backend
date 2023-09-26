package com.github.knextsunj.cms.domain

import java.time.LocalDateTime
import javax.persistence.*

//@Entity
//@Table(name = "user_role")
open class UserRole {

//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    open var id: Long? = 0;

//    @Column(name = "name")
    open var name: String? = null;

//    @Column(name = "is_deleted", insertable = false)
    open var deleted: String? = null

//    @Column(name = "created_date")
    open var createdDate: LocalDateTime? = null;

//    @Column(name = "updated_date")
    open var updatedDate: LocalDateTime? = null;

//    @OneToMany(targetEntity = User::class, mappedBy = "userRole", fetch = FetchType.LAZY)
    open var users: Set<User>? = null
}