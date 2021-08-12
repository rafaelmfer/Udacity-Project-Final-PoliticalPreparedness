package com.udacity.politicalpreparedness.election

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.udacity.politicalpreparedness.database.ElectionDao
import com.udacity.politicalpreparedness.network.models.Division

//TODO: Create Factory to generate VoterInfoViewModel with provided election datasource
class VoterInfoViewModelFactory(
    private val electionId: Int,
    private val division: Division,
    private val electionDao: ElectionDao
) : ViewModelProvider.Factory {

    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(VoterInfoViewModel::class.java)) {
            return VoterInfoViewModel(electionId, division, electionDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}