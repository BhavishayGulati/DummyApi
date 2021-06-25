package com.example.dummyapi

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RVAdapter(val context: Context, var markets: ArrayList<Market>) :
    RecyclerView.Adapter<RVAdapter.RVViewHolder?>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RVViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_rv, parent, false)

        return RVViewHolder(view)
    }

    override fun onBindViewHolder(holder: RVViewHolder, position: Int) {
        holder.bindView(markets[position])
    }

    override fun getItemCount(): Int {
        return markets.size
    }


    inner class RVViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val tvSymbol = itemView.findViewById<TextView>(R.id.tvSymbol)
        private val tvExchangeID = itemView.findViewById<TextView>(R.id.tvExchangeId)
        private val tvPrice = itemView.findViewById<TextView>(R.id.tvPrice)
        private val tvPriceConverted = itemView.findViewById<TextView>(R.id.tvPriceConverted)
        private val tvChange24hNegative = itemView.findViewById<TextView>(R.id.tvChange24hNegative)
        private val tvChange24hPositive = itemView.findViewById<TextView>(R.id.tvChange24hPositive)
        private val tvSpread = itemView.findViewById<TextView>(R.id.tvSpread)
        private val tvVolume24h = itemView.findViewById<TextView>(R.id.tvVolume24h)
        private val tvStatus = itemView.findViewById<TextView>(R.id.tvStatus)
        private val tvTime = itemView.findViewById<TextView>(R.id.tvTime)

        fun bindView(market: Market) {
            with(market){
                tvSymbol.text = symbol
                tvExchangeID.text = exchange_id
                tvPrice.text = "$$price"
                tvPriceConverted.text = "$$price_unconverted"
                tvSpread.text = spread.toString().subSequence(0,6)
                tvTime.text = "${time.subSequence(0,10)} \n ${time.subSequence(11,18)}"
                tvVolume24h.text = volume_24h.toString().subSequence(0,6)
                tvStatus.text =status
                if (change_24h<0){
                    tvChange24hPositive.visibility = View.GONE
                    tvChange24hNegative.visibility = View.VISIBLE
                    tvChange24hNegative.text = change_24h.toString().subSequence(0,6)
                }else{
                    tvChange24hPositive.text = change_24h.toString().subSequence(0,6)
                }

            }


        }
    }

}