package com.example.newhopehotel.roomService.viewMorningCallList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.transaction
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.*
import com.example.newhopehotel.R
import com.example.newhopehotel.checkInCheckOut.AddCicoViewModel
import com.example.newhopehotel.data.UIState
import com.example.newhopehotel.database.CheckInCheckOutEntity
import com.example.newhopehotel.database.MorningCallEntity
import com.example.newhopehotel.databinding.FragmentMorningCallListBinding
import com.example.newhopehotel.roomService.viewRoomServiceList.AddRoomServiceFragment
import com.example.newhopehotel.roomService.viewRoomServiceList.CHOSEN_RS
import kotlinx.android.synthetic.main.fragment_morning_call_list.*
import org.jetbrains.anko.design.longSnackbar

const val CHOSEN_MC = "chosenMC"

class MorningCallListFragment : Fragment(), MorningCallAdapter.MorningCallClickListener {

    private lateinit var morningCallActivityViewModel: MorningCallViewModel

    private lateinit var mAdapter: MorningCallAdapter
    private lateinit var binding: FragmentMorningCallListBinding
    private var mMorningCallList: List<CheckInCheckOutEntity>? = null

    init {
        retainInstance = true
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_morning_call_list, container, false)

        morningCallActivityViewModel = ViewModelProvider(this).get(MorningCallViewModel::class.java)

        mAdapter = MorningCallAdapter(this)
        val dividerItemDecoration = DividerItemDecoration(
            requireActivity(), LinearLayoutManager.VERTICAL
        )
        with(binding.recycler) {
            addItemDecoration(dividerItemDecoration)
            itemAnimator = DefaultItemAnimator()
            adapter = mAdapter
        }


        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        (requireActivity() as AppCompatActivity).supportActionBar!!.setDisplayHomeAsUpEnabled(false)

        //Get the view model instance and pass it to the binding implementation
        binding.uiState = morningCallActivityViewModel.uiState

        //Claim list of toys from view model

        morningCallActivityViewModel.morningCallList?.observe(viewLifecycleOwner, { morningCallEntries ->
            if (morningCallEntries.isNullOrEmpty()) {
                morningCallActivityViewModel.uiState.set(UIState.EMPTY)
            } else {
                morningCallActivityViewModel.uiState.set(UIState.HAS_DATA)
                mAdapter.morningCallList = morningCallEntries
                mMorningCallList = morningCallEntries


            }
        })
        btn_8am.setOnClickListener({
            Toast.makeText(activity, "8AM Morning Call List", Toast.LENGTH_SHORT).show()
             filter8AMMc( mMorningCallList !!)
            })
        btn_9am.setOnClickListener({
            Toast.makeText(activity, "9AM Morning Call List", Toast.LENGTH_SHORT).show()
            filter9AMMc( mMorningCallList !!)
        })
        btn_10am.setOnClickListener({
            Toast.makeText(activity, "10AM Morning Call List", Toast.LENGTH_SHORT).show()
            filter10AMMc( mMorningCallList !!)
        })
        btn_11am.setOnClickListener({
            Toast.makeText(activity, "11AM Morning Call List", Toast.LENGTH_SHORT).show()
            filter11AMMc( mMorningCallList !!)
        })



    }

    fun filter8AMMc( mcEntries:List<CheckInCheckOutEntity>)
    {
        val eightAM= mutableMapOf(
            AddCicoViewModel.EIGHT_AM to true, AddCicoViewModel.NINE_AM to false,
            AddCicoViewModel.TEN_AM to false, AddCicoViewModel.ELEVEN_AM to false
        )
        filterMCList(mcEntries,eightAM)
    }
    fun filter9AMMc( mcEntries:List<CheckInCheckOutEntity>)
    {
        val nineAM= mutableMapOf(
            AddCicoViewModel.EIGHT_AM to false, AddCicoViewModel.NINE_AM to true,
            AddCicoViewModel.TEN_AM to false, AddCicoViewModel.ELEVEN_AM to false
        )
        filterMCList(mcEntries,nineAM)
    }
    fun filter10AMMc( mcEntries:List<CheckInCheckOutEntity>)
    {
        val tenAM= mutableMapOf(
            AddCicoViewModel.EIGHT_AM to false, AddCicoViewModel.NINE_AM to false,
            AddCicoViewModel.TEN_AM to true, AddCicoViewModel.ELEVEN_AM to false
        )
        filterMCList(mcEntries,tenAM)
    }
    fun filter11AMMc( mcEntries:List<CheckInCheckOutEntity>)
    {
        val elevenAM= mutableMapOf(
            AddCicoViewModel.EIGHT_AM to false, AddCicoViewModel.NINE_AM to false,
            AddCicoViewModel.TEN_AM to false, AddCicoViewModel.ELEVEN_AM to true
        )
        filterMCList(mcEntries,elevenAM)
    }
    fun filterMCList(mcEntries:List<CheckInCheckOutEntity>, time:Map<String, Boolean>)
    {
        var tempMCList: MutableList<CheckInCheckOutEntity> = ArrayList()

        for (item in mcEntries) {

            if(item.morningCall==time)
            {
                tempMCList.add(item)
            }
        }
        mAdapter.morningCallList = tempMCList
        mAdapter.notifyDataSetChanged()
    }
    override fun onMorningCallClicked(chosenToy: CheckInCheckOutEntity) {

    }
    companion object {
        const val EIGHT_AM = "8 AM"
        const val NINE_AM = "9 AM"
        const val TEN_AM = "10 AM"
        const val ELEVEN_AM = "11 AM"
    }

}