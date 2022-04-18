package com.kgp.noticatcher

import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kgp.noticatcher.databinding.RoomItemlistBinding
import java.io.File

class RoomAdapter : RecyclerView.Adapter<Holder>() {//요즘은 listadpater로 쓰기 때문에 나중에 고쳐보자

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
        val iconFile = File(data.imageFile)
        val bitmap = BitmapFactory.decodeFile(iconFile.absolutePath)
        binding.profileImage.setImageBitmap(bitmap)
    }
}
