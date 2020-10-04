package com.aixweather.android.ui.place

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.aixweather.android.R
import kotlinx.android.synthetic.main.fragment_place.*

class PlaceFragment : Fragment() {
    val viewModel by lazy { ViewModelProviders.of(this).get(PlaceViewModel::class.java) }
    private lateinit var adapter: PlaceAdapter

    //创建
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_place, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val layoutManager = LinearLayoutManager(activity)
        recyclerView.layoutManager = layoutManager
        adapter = PlaceAdapter(this, viewModel.placeList)
        recyclerView.adapter = adapter
        //监督输入框的变动
        searchPlaceEdit.addTextChangedListener { editable ->
            val content = editable.toString()
            if (content.isNotEmpty()) {
                viewModel.searchPlaces(content)
            }else{
                recyclerView.visibility = View.GONE
                bgImageView.visibility = View.VISIBLE
                viewModel.placeList.clear()
                adapter.notifyDataSetChanged()
            }
        }
        //观察数据是否变动
        viewModel.placeLiveData.observe(this, Observer { result ->
            val place = result.getOrNull()
            if (place != null) {  //变动，且不为空
                recyclerView.visibility = View.VISIBLE
                bgImageView.visibility = View.GONE
                viewModel.placeList.clear()  //清空老数据
                viewModel.placeList.addAll(place)  //添加新数据
                adapter.notifyDataSetChanged()  //通知改动
            } else {
                Toast.makeText(activity, "未能查到任何地点", Toast.LENGTH_SHORT).show()
                result.exceptionOrNull()?.printStackTrace() //为空，报错
            }
        })
    }
}