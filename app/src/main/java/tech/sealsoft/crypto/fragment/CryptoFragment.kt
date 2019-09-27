package tech.sealsoft.crypto.fragment


import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

import tech.sealsoft.crypto.R
import tech.sealsoft.crypto.adapter.CoinRecycleAdapter
import tech.sealsoft.crypto.model.CoinEntity
import tech.sealsoft.crypto.service.ServiceHelper

/**
 * A simple [Fragment] subclass.
 */
class CryptoFragment : Fragment(), CoinRecycleAdapter.OnItemClickListener {
    override fun onItemClick(coind: String) {
        proceedToMarketFragment(coind)
    }

    private lateinit var mContext: Context
    var isLastPage: Boolean = false
    var isLoading: Boolean = false

    lateinit var coinRecyclerView: RecyclerView
    lateinit var coinRecyclerAdapter: CoinRecycleAdapter
    private lateinit var fragmentView: View

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        fragmentView = inflater.inflate(R.layout.fragment_crypto, container, false)

        return fragmentView
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        coinRecyclerView = fragmentView.findViewById(R.id.recyclerView)
        coinRecyclerAdapter = CoinRecycleAdapter(mContext)
        coinRecyclerView.layoutManager = LinearLayoutManager(mContext)
        coinRecyclerView.adapter = coinRecyclerAdapter
        coinRecyclerAdapter.mClickListener = this@CryptoFragment
        GlobalScope.launch {
            var coinList = ServiceHelper.callAllCoins(mContext!!)
            if (coinList != null) {
                (mContext as Activity).runOnUiThread {
                    coinRecyclerAdapter.setCoinListItems(coinList)
                }
            }
        }

    }


    private fun proceedToMarketFragment(selected: String) {

        val marketFragment = MarketFragment()
        activity?.supportFragmentManager?.popBackStack(
            null,
            FragmentManager.POP_BACK_STACK_INCLUSIVE
        )

        marketFragment.arguments = Bundle()
        marketFragment.arguments?.putString("coinId", selected)




        val transaction = activity?.supportFragmentManager?.beginTransaction()
        transaction?.replace(R.id.base_fragment_container, marketFragment)

        transaction?.commitAllowingStateLoss()
    }


}
