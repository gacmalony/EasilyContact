package com.example.easilycontact.uiadapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.easilycontact.R
import com.example.easilycontact.databinding.CardItemBinding
import com.example.easilycontact.room.room

class MyAdapter(private val usersList:List<room>, val clickListener: (room)-> Unit) :RecyclerView.Adapter<MyAdapter.MyViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding:CardItemBinding = DataBindingUtil.inflate(layoutInflater, R.layout.card_item, parent, false)
        return MyViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return  usersList.size    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(usersList.get(position), clickListener)
    }

    inner class MyViewHolder(val binding:CardItemBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(user:room, clickListener:(room)->Unit){
            binding.nameTextview.text = user.name
            binding.mailTextview.text = user.email
            binding.phoneTextview.text = user.phonenumber

            binding.listItemLayout.setOnClickListener(){
                clickListener(user)
            }
        }

    }
}