package com.example.optistock

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.optistock.databinding.ActivityEachItemBinding
import com.google.firebase.database.ValueEventListener

class RvAdapter(var dataList: ArrayList<DataClass>, var context: ValueEventListener) : RecyclerView.Adapter<RvAdapter.MyViewHolder>(){



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
//        var view=LayoutInflater.from(context).inflate(R.layout.activity_each_item,parent,false)
//        return MyViewHolder(view)
         var binding=ActivityEachItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return MyViewHolder(binding)

    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.binding.warehouseName.text=dataList.get(position).warehouseName
        holder.binding.LocateInput.text=dataList.get(position).locate
        holder.itemView.setOnClickListener{


            TODO()

        }
    }
    inner class MyViewHolder(var binding:ActivityEachItemBinding):RecyclerView.ViewHolder(binding.root){


    }    }

