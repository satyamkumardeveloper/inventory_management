package com.example.optistock

import android.os.Bundle

import androidx.appcompat.app.AppCompatActivity
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.optistock.databinding.ActivityEachItemBinding
import com.example.optistock.databinding.ActivityWarehouseBinding

class RvAdapter(var dataList: ArrayList<DataClass>,var context: Context) : RecyclerView.Adapter<RvAdapter.MyViewHolder>(){



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
//        var view=LayoutInflater.from(context).inflate(R.layout.activity_each_item,parent,false)
//        return MyViewHolder(view)
         var binding=ActivityEachItemBinding.inflate(LayoutInflater.from(context),parent,false)
        return MyViewHolder(binding)

    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.binding.warehouseName.text=(dataList.get(position)).warehouseName
        holder.binding.LocateInput.text=dataList.get(position).locate
        holder.itemView.setOnClickListener{


            Toast.makeText(context,dataList.get(position).toString(),Toast.LENGTH_SHORT).show()

        }
    }
    inner class MyViewHolder(var binding:ActivityEachItemBinding):RecyclerView.ViewHolder(binding.root){


    }    }

