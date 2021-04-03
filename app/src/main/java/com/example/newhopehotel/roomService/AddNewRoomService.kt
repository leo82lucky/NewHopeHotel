package com.example.newhopehotel.roomService

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.newhopehotel.R
import kotlinx.android.synthetic.main.add_new_room_service.*
import android.view.Gravity
import android.widget.EditText
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_room_service.*
import kotlinx.android.synthetic.main.add_new_room_service.toolbar

class AddNewRoomService : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_new_room_service)



        // set toolbar as support action bar
        setSupportActionBar(toolbar)

        supportActionBar?.apply {

            // show back button on toolbar
            // on back button press, it will navigate to parent activity
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
        }


        val clientName= findViewById<EditText>(R.id.editClientName)
        val request=findViewById<EditText>(R.id.editRequest)
        addNewButton.setOnClickListener {

            if(clientName.text.toString().isNullOrEmpty()  || request.text.toString().isNullOrEmpty() )
            {
                val Message: String
                Message="Please fill in both the Client Name and Request"
                val toast1= Toast.makeText(applicationContext, Message, Toast.LENGTH_LONG)
                toast1.setGravity(Gravity.CENTER, 0,0 )
                toast1.show()
            }
            else
            {
                val intent = Intent(this, ViewRoomServiceList::class.java)
                intent.putExtra("clientName", clientName.text.toString());
                intent.putExtra("request",request.text.toString());
                startActivity(intent)

            }

        }
    }
}