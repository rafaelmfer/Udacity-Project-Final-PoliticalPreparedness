package com.udacity.politicalpreparedness.election

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.udacity.politicalpreparedness.R
import com.udacity.politicalpreparedness.database.ElectionDatabase
import com.udacity.politicalpreparedness.databinding.FragmentElectionBinding
import com.udacity.politicalpreparedness.election.adapter.ElectionListAdapter
import com.udacity.politicalpreparedness.election.adapter.ElectionListener

class ElectionsFragment : Fragment() {

    //TODO: Declare ViewModel
    private val viewModel: ElectionsViewModel by viewModels {
        ElectionsViewModelFactory(ElectionDatabase.getInstance(requireContext()).electionDao)
    }
    private lateinit var binding: FragmentElectionBinding

    private val rvAdapterUpcoming = ElectionListAdapter(ElectionListener {
        viewModel.navigateToElectionDetails(it)
    })

    private val rvAdapterSaved = ElectionListAdapter(ElectionListener {
        viewModel.navigateToElectionDetails(it)
    })

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        //TODO: Add ViewModel values and create ViewModel
        //TODO: Add binding values
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_election, container, false)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observables()
        setupRecyclerView()
    }

    private fun observables() {
        //TODO: Populate recycler adapters
        viewModel.upcomingElections.observe(viewLifecycleOwner, { elections ->
            elections?.let {
                rvAdapterUpcoming.submitList(it)
            }
        })

        viewModel.savedElections.observe(viewLifecycleOwner, { elections ->
            elections?.let {
                if (it.isEmpty()) {
//                    setSavedListVisibility(View.INVISIBLE)
                } else {
//                    setSavedListVisibility(View.VISIBLE)
                    rvAdapterSaved.submitList(it)
                }
            }
        })

        //TODO: Link elections to voter info
        viewModel.navigateToSelectedElection.observe(viewLifecycleOwner, { election ->
            if (election != null) {
                findNavController().navigate(
                    ElectionsFragmentDirections.actionElectionsFragmentToVoterInfoFragment(election.id, election.division)
                )
                viewModel.navigationIsCompleted()
            }
        })
    }

    //TODO: Initiate recycler adapters
    private fun setupRecyclerView() {
        binding.rvUpcomingElections.adapter = rvAdapterUpcoming
        binding.rvSavedElections.adapter = rvAdapterSaved
    }

    //TODO: Refresh adapters when fragment loads

}