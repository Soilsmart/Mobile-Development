package com.azkaisnandaru.farmerapp.adapter


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.azkaisnandaru.farmerapp.R
import com.azkaisnandaru.farmerapp.databinding.ItemPrediksiBinding
import com.azkaisnandaru.farmerapp.model.DataPrediksi
import com.azkaisnandaru.farmerapp.model.HistoryPredictionItem


class DataAdapter(private val listData: ArrayList<HistoryPredictionItem>) : RecyclerView.Adapter<DataAdapter.ViewHolder>() {

    var onItemClick: ((HistoryPredictionItem) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_prediksi, parent, false))

    override fun getItemCount(): Int = listData.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = listData[position]
        holder.bind(data)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemPrediksiBinding.bind(itemView)

        fun bind(data: HistoryPredictionItem) {
            with(binding) {
                tvPeriodeTanam.text = data.periodeTanam.toString()
                tvLuasPanen.text = data.luasPanen.toString()
                tvPredictedOutput.text = data.predictedOutput
                tvTimestamp.text = data.timestamp

            }
        }

        init {
            binding.root.setOnClickListener {
                onItemClick?.invoke(listData[adapterPosition])
            }
        }
    }
}
