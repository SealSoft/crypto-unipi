package tech.sealsoft.crypto.fragment

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import tech.sealsoft.crypto.R
import tech.sealsoft.crypto.adapter.MarketRecycleAdapter
import tech.sealsoft.crypto.service.ServiceHelper


class MarketFragment : Fragment() {
    private lateinit var mContext: Context

    lateinit var marketRecyclerView: RecyclerView
    lateinit var marketRecyclerAdapter: MarketRecycleAdapter
    private lateinit var fragmentView: View



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        fragmentView =
            inflater.inflate(R.layout.fragment_market, container, false)


        return fragmentView
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
        val coinId = arguments?.getString("coinId")

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        marketRecyclerView = fragmentView.findViewById(R.id.marketRecyclerView)
        marketRecyclerAdapter = MarketRecycleAdapter(mContext)
        marketRecyclerView.layoutManager = LinearLayoutManager(mContext)
        marketRecyclerView.adapter = marketRecyclerAdapter

        GlobalScope.launch {
            var marketList = ServiceHelper.callMarketForCoin(mContext!!, "")
            if (marketList != null) {
                (mContext as Activity).runOnUiThread {
                    marketRecyclerAdapter.setMarketListItems(marketList)
                }
            }
        }

    }

}