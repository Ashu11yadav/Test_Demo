package com.example.test

import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.appbar.AppBarLayout.OnOffsetChangedListener
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.LinearLayoutManager
import android.util.TypedValue
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup.MarginLayoutParams
import android.widget.EditText
import android.widget.ImageView
import androidx.appcompat.widget.Toolbar
import java.util.*
import kotlin.collections.ArrayList

class ScrollingActivity : AppCompatActivity(), OnOffsetChangedListener {
    private lateinit var edit_text: EditText
    private lateinit var search_button: ImageView
    private lateinit var recycle_top: RecyclerView
    private lateinit var recycle: RecyclerView
    private val expandedTextSize = 0f
    private val collapsedTextSize = 0f
    private val expandedTopMargin = 0
    private val collapsedTopMargin = 0
    private val mAppBarLayout: AppBarLayout? = null
    private val mToolbar: Toolbar? = null
    private val editText: EditText? = null
    private val textInputLayout: TextInputLayout? = null
    private var menu: Menu? = null

    private lateinit var countryFilterList: ArrayList<String>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scrolling)

        val mToolbar = findViewById<View>(R.id.toolbar) as Toolbar
         recycle = findViewById<View>(R.id.recycle) as RecyclerView
         recycle_top = findViewById<View>(R.id.top_recycle) as RecyclerView
         search_button = findViewById<View>(R.id.search_button) as ImageView
        edit_text = findViewById<View>(R.id.edit_text) as EditText
        setSupportActionBar(mToolbar)

        val topImageAdapter = TopImageAdapter(this)
        recycle_top.adapter = topImageAdapter
        recycle_top.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)


        val arrayList = ArrayList<String>()//Creating an empty arraylist
        arrayList.add("Lable 1")//Adding object in arraylist
        arrayList.add("Lable 2")
        arrayList.add("Lable 3")
        arrayList.add("Lable 4")
        arrayList.add("Lable 5")
        arrayList.add("Lable 6")
        arrayList.add("Lable 7")
        arrayList.add("Lable 8")
        arrayList.add("Lable 9")
        arrayList.add("Lable 10")

        val bottomImageAdapter = BottomImageAdapter(this,arrayList)
        recycle.adapter = bottomImageAdapter
        recycle.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)


        search_button.setOnClickListener {


            if (edit_text.text.toString().isEmpty()) {
                countryFilterList = arrayList
            } else {
                val resultList = ArrayList<String>()
                for (row in arrayList) {
                    if (row.lowercase(Locale.ROOT).contains(edit_text.text.toString().lowercase(Locale.ROOT))) {
                        resultList.add(row)
                    }
                }
                countryFilterList = resultList

                bottomImageAdapter.updateList(countryFilterList)
                bottomImageAdapter.notifyDataSetChanged();

            }

        }

    }


    override fun onOffsetChanged(appBarLayout: AppBarLayout, offset: Int) {
        val maxScroll = appBarLayout.totalScrollRange
        val percentage = Math.abs(offset).toFloat() / maxScroll
        val textSizeDiff = Math.abs(expandedTextSize - collapsedTextSize)
        val marginDiff = Math.abs(expandedTopMargin - collapsedTopMargin)
        //change text size along with scrolling
        editText!!.setTextSize(
            TypedValue.COMPLEX_UNIT_PX,
            expandedTextSize - textSizeDiff * percentage
        )
        //change top view margin along with scrolling
        (textInputLayout!!.layoutParams as MarginLayoutParams).topMargin =
            (expandedTopMargin - marginDiff * Math.min(
                1f,
                percentage * MARGIN_SCROLLER_MULTIPLIER
            )).toInt()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        this.menu = menu
        menuInflater.inflate(R.menu.menu_scrolling, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        val id = item.itemId
        return if (id == R.id.action_settings) {
            true
        } else super.onOptionsItemSelected(item)
    }

    private fun hideOption(id: Int) {
        val item = menu!!.findItem(id)
        item.isVisible = false
    }

    private fun showOption(id: Int) {
        val item = menu!!.findItem(id)
        item.isVisible = true
    }

    companion object {
        private const val COLLAPSED_TEXT_SIZE_SP = 18f
        private const val COLLAPSED_TOP_MARGIN_DP = 24f
        private const val MARGIN_SCROLLER_MULTIPLIER = 4f
    }
}