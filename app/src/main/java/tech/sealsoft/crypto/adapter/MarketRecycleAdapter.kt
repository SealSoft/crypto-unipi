package tech.sealsoft.crypto.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import tech.sealsoft.crypto.R
import tech.sealsoft.crypto.model.MarketEntity

class MarketRecycleAdapter(val context: Context) :
    RecyclerView.Adapter<MarketRecycleAdapter.MyViewHolder>() {

    var marketList: List<MarketEntity> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.market_recycler_adapter,parent,false)
        return MarketRecycleAdapter.MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return marketList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

    }



    fun setMarketListItems(marketList: List<MarketEntity?>){
        this.marketList = marketList as List<MarketEntity>
        notifyDataSetChanged()
    }


    class MyViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView!!) {


    }
}