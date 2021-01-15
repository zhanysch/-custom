package com.example.radiodaggerforeground.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.radiodaggerforeground.R
import com.example.radiodaggerforeground.data.remote.RadioStation

class RadioAdapter(
        private val listener: (item: RadioStation) -> Unit  // замыкан по клику на recyclervieew
): RecyclerView.Adapter<RadioVH>() {

    private var list = arrayListOf<RadioStation>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RadioVH {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_radiostation,parent,false)
        return RadioVH(view)
    }

    override fun onBindViewHolder(holder: RadioVH, position: Int) {
       holder.bind(list[position])
        holder.itemView.setOnClickListener {
            listener.invoke(list[position])      // замыкан по клику recyclerview
        }
    }

    fun update(data : List<RadioStation>){
        list.clear()
        list.addAll(data)
        notifyDataSetChanged()
    }

    override fun getItemCount() = list.size


}
class RadioVH(view:View): RecyclerView.ViewHolder(view){

    private var tvTitle: TextView = itemView.findViewById(R.id.tvRadio)

     fun bind(radioStation: RadioStation) {
         tvTitle.text = radioStation.name

     }
}