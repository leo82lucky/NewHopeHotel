package com.example.newhopehotel.roomService.viewMorningCallList

import android.content.Intent
import android.graphics.Color
import android.net.Uri
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
import kotlinx.android.synthetic.main.fragment_worker.*
import kotlinx.android.synthetic.main.morning_call_list_item.*


const val CHOSEN_MC = "chosenMC"

class MorningCallListFragment : Fragment(), MorningCallAdapter.MorningCallClickListener {

    private lateinit var morningCallActivityViewModel: MorningCallViewModel

    var morningCallListIsEmpty: Boolean? = false

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

        mAdapter = MorningCallAdapter(this,requireActivity())
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


        (requireActivity() as AppCompatActivity).supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        (requireActivity() as AppCompatActivity).supportActionBar!!.setDisplayShowHomeEnabled(true)
        //Get the view model instance and pass it to the binding implementation
        binding.uiState = morningCallActivityViewModel.uiState

        //Claim list of toys from view model

        morningCallActivityViewModel.morningCallList?.observe(viewLifecycleOwner, { morningCallEntries ->
            if (morningCallEntries.isNullOrEmpty()) {
                empty_imageview!!.visibility = View.VISIBLE
                no_data!!.visibility = View.VISIBLE
                morningCallListIsEmpty=true
                morningCallActivityViewModel.uiState.set(UIState.EMPTY)
                btn_8am.setTextColor(Color.parseColor("#ffffff"))
                btn_9am.setTextColor(Color.parseColor("#C0C0C0"))
                btn_10am.setTextColor(Color.parseColor("#C0C0C0"))
                btn_11am.setTextColor(Color.parseColor("#C0C0C0"))
            } else {

                empty_imageview!!.visibility = View.GONE
                no_data!!.visibility = View.GONE
                morningCallActivityViewModel.uiState.set(UIState.HAS_DATA)
                morningCallListIsEmpty=false
                mMorningCallList = morningCallEntries

                filter8AMMc( mMorningCallList !!)
                btn_8am.setTextColor(Color.parseColor("#ffffff"))
                btn_9am.setTextColor(Color.parseColor("#C0C0C0"))
                btn_10am.setTextColor(Color.parseColor("#C0C0C0"))
                btn_11am.setTextColor(Color.parseColor("#C0C0C0"))

            }
        })

        btn_8am.setOnClickListener({

            if(morningCallListIsEmpty==false)
            {
                filter8AMMc( mMorningCallList !!)
            }


            btn_8am.setTextColor(Color.parseColor("#ffffff"))
            btn_9am.setTextColor(Color.parseColor("#C0C0C0"))
            btn_10am.setTextColor(Color.parseColor("#C0C0C0"))
            btn_11am.setTextColor(Color.parseColor("#C0C0C0"))
            })
        btn_9am.setOnClickListener({

            if(morningCallListIsEmpty==false)
            {
                filter9AMMc( mMorningCallList !!)
            }


            btn_8am.setTextColor(Color.parseColor("#C0C0C0"))
            btn_9am.setTextColor(Color.parseColor("#ffffff"))
            btn_10am.setTextColor(Color.parseColor("#C0C0C0"))
            btn_11am.setTextColor(Color.parseColor("#C0C0C0"))
        })
        btn_10am.setOnClickListener({

            if(morningCallListIsEmpty==false)
            {
                filter10AMMc( mMorningCallList !!)
            }


            btn_8am.setTextColor(Color.parseColor("#C0C0C0"))
            btn_9am.setTextColor(Color.parseColor("#C0C0C0"))
            btn_10am.setTextColor(Color.parseColor("#ffffff"))
            btn_11am.setTextColor(Color.parseColor("#C0C0C0"))
        })
        btn_11am.setOnClickListener({

            if(morningCallListIsEmpty==false)
            {
                filter11AMMc( mMorningCallList !!)
            }


            btn_8am.setTextColor(Color.parseColor("#C0C0C0"))
            btn_9am.setTextColor(Color.parseColor("#C0C0C0"))
            btn_10am.setTextColor(Color.parseColor("#C0C0C0"))
            btn_11am.setTextColor(Color.parseColor("#ffffff"))
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

        if(tempMCList.size==0)
        {
            empty_imageview!!.visibility = View.VISIBLE
            no_data!!.visibility = View.VISIBLE
        }
        else
        {
            empty_imageview!!.visibility = View.GONE
            no_data!!.visibility = View.GONE
        }
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