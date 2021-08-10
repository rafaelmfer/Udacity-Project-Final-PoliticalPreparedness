package com.udacity.politicalpreparedness.representative.model

import com.udacity.politicalpreparedness.network.models.Office
import com.udacity.politicalpreparedness.network.models.Official

data class Representative (
        val official: Official,
        val office: Office
)