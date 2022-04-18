package com.kgp.noticatcher

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kgp.noticatcher.databinding.RoomItemlistBinding

class RoomAdapter : RecyclerView.Adapter<Holder>() {//요즘은 listadapter많이 쓰기때문에 나중에 바꿔보기

    var dataList = listOf<ChatRoom>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val binding =
            RoomItemlistBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return Holder(binding)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val member = dataList[position]
        holder.setData(member)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }
}

class Holder(private val binding: RoomItemlistBinding) : RecyclerView.ViewHolder(binding.root) {
    fun setData(data: ChatRoom) {
        binding.title.text = data.user
        binding.message.text = data.message
    }
}
