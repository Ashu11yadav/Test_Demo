package com.example.test

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import android.text.TextUtils
import android.widget.Filter
import java.util.*
import kotlin.collections.ArrayList


class TopImageAdapter :  RecyclerView.Adapter<TopImageAdapter.MyViewHolder> {

    constructor(scrollingActivity: ScrollingActivity)
    {
    }


    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {


        private var image_view: ImageView

        init {
            image_view = view.findViewById<View>(R.id.image_view) as ImageView

        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.top_view, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        /* holder.lable.setText("")*/

    }



    override fun getItemCount(): Int {
        return 20
    }


}

