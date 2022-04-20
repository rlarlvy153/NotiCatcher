package com.kgp.noticatcher

import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kgp.noticatcher.databinding.RoomItemlistBinding
import java.io.File

class RoomAdapter : RecyclerView.Adapter<RoomAdapter.ChatViewHolder>() {//요즘은 listadapter많이 쓰기때문에 나중에 바꿔보기

    var dataList = listOf<ChatRoom>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatViewHolder {
        val binding =
            RoomItemlistBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ChatViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ChatViewHolder, position: Int) {
        val member = dataList[position]
        holder.setData(member)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    inner class ChatViewHolder(private val binding: RoomItemlistBinding) : RecyclerView.ViewHolder(binding.root) {
        fun setData(data: ChatRoom) {
            binding.title.text = data.user
            binding.message.text = data.message
            val iconFile = File(data.imageFilePath)
            if (iconFile.exists()) {
                val bitmap = BitmapFactory.decodeFile(iconFile.absolutePath)
                binding.profileImage.setImageBitmap(bitmap)
            }
        }
    }

}

