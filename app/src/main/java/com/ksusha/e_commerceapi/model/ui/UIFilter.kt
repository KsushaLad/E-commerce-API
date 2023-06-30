package com.ksusha.e_commerceapi.model.ui

import com.ksusha.e_commerceapi.model.domain.Filter

data class UIFilter(
    val filter: Filter,
    val isSelected: Boolean
)