package com.rvcode.skillaura.models.project

import java.math.BigDecimal
import java.time.LocalDateTime


data class ProjectResponse (
     val id: Long? = null,
     val title: String? = null,
    val description: String? = null,
     val budget: BigDecimal? = null,
     val status: ProjectStatus? = null,
     val clientId: Long? = null,
     val clientName: String? = null,
     val createdAt: LocalDateTime? = null
)
