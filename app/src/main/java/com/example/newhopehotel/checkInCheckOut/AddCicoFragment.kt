package com.example.newhopehotel.checkInCheckOut

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.newhopehotel.R
import com.example.newhopehotel.database.CheckInCheckOutEntity
import com.example.newhopehotel.databinding.AddCicoBinding
import com.example.newhopehotel.utils.provideRepository
import org.jetbrains.anko.toast

class AddCicoFragment : Fragment() {

    private lateinit var binding: AddCicoBinding
    private lateinit var viewModel: AddCicoViewModel

    init {
        retainInstance = true
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.add_cico_fragment, container, false
        )
        setHasOptionsMenu(true)

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        (requireActivity() as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)

        //If there is no id specified in the arguments, then it should be a new toy
        val chosenRoom: CheckInCheckOutEntity? = arguments?.getParcelable(CHOSEN_CICO)

        //Get the view model instance and pass it to the binding implementation
        val factory = AddCicoViewModelFactory(provideRepository(requireContext()), chosenRoom)
        viewModel = ViewModelProvider(this, factory).get(AddCicoViewModel::class.java)

        binding.myAddCicoViewModel = viewModel

        binding.fab.setOnClickListener {
            saveToy()
        }
    }

    private fun saveToy() {
        // Check if toy name is not empty
        if (viewModel.cicoBeingModified.custName.isBlank()) {
            context?.toast(R.string.cico_empty_warning)
            return
        }
        viewModel.saveCico()
        fragmentManager?.popBackStack()
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        //If up button is clicked, check for changes before popping the fragment off
        if (item.itemId == android.R.id.home) {
            onBackClicked()
        }
        return super.onOptionsItemSelected(item)
    }

    /*This can be triggered either by up or both buttons. In both cases,
    we first need to check whether there are unsaved changes and warn the user if necessary*/
    fun onBackClicked() {
        if (viewModel.isChanged) {
            openAlertDialog()
        } else {
            fragmentManager?.popBackStack()
        }
    }

    private fun openAlertDialog() {
        AlertDialog.Builder(requireContext())
            .setTitle(getString(R.string.unsaved_changes_warning_title))
            .setMessage(getString(R.string.unsaved_changes_warning_message))
            // Specifying a listener allows you to take an action before dismissing the dialog.
            // The dialog is automatically dismissed when a dialog button is clicked.
            .setPositiveButton(getString(R.string.yes)) { _, _ ->
                // Continue with back operation
                fragmentManager?.popBackStack()
            }
            // A null listener allows the button to dismiss the dialog and take no further action.
            .setNegativeButton(android.R.string.no, null)
            .setIcon(android.R.drawable.ic_dialog_alert)
            .show()
    }
}