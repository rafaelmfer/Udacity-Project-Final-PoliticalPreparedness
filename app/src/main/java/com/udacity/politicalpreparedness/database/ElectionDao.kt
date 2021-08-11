package com.udacity.politicalpreparedness.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.udacity.politicalpreparedness.network.models.Election

@Dao
interface ElectionDao {
    //TODO: Add insert query
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertElection(election: Election)

    //TODO: Add select all election query
    @Query("SELECT * FROM election_table")
    fun getAllElections(): LiveData<List<Election>>

    //TODO: Add select single election query
    @Query("SELECT * FROM election_table WHERE id=:electionId")
    suspend fun getElectionById(electionId: Int): Election?

    //TODO: Add delete query
    @Query("DELETE FROM election_table WHERE id=:electionId")
    suspend fun deleteElectionById(electionId: Int)

    //TODO: Add clear query
    @Query("DELETE FROM election_table")
    suspend fun deleteAllElections()
}