package com.example.test

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.util.*
import kotlin.collections.ArrayList

class BottomImageAdapter  : RecyclerView.Adapter<BottomImageAdapter.MyViewHolder> {

    private lateinit var countryFilterList: ArrayList<String>
    private var labellist: ArrayList<String>

    constructor(scrollingActivity: ScrollingActivity, arrayList: ArrayList<String>)
    {this.labellist = arrayList;
    }


    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {


        private var image_view: ImageView
        var lable: TextView
        init {
            image_view = view.findViewById<View>(R.id.image_view) as ImageView
            lable = view.findViewById<View>(R.id.lable) as TextView
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.bottom_view, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.lable.setText(labellist.get(position))

    }


    override fun getItemCount(): Int {
        return labellist.size
    }

    fun updateList(countryFilterList: ArrayList<String>) {
      this.labellist = countryFilterList;

    }


}

