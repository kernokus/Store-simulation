package com.example.a10augportfolio.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.a10augportfolio.App
import com.example.a10augportfolio.R
import com.example.a10augportfolio.model.RoomRepo

import com.example.a10augportfolio.presenter.ShopPresenter
import com.example.a10augportfolio.room.itemCatalogs
import com.example.a10augportfolio.view.ShopFragmentView
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.item_catalog.view.*
import kotlinx.android.synthetic.main.shop_fragment.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import javax.inject.Inject
import javax.inject.Provider
import kotlin.coroutines.CoroutineContext

@AndroidEntryPoint
class ShopFragment:MvpAppCompatFragment(),ShopFragmentView,CoroutineScope {
    private lateinit var myAdapter :AdapterPendingCases

    @Inject
    lateinit var presenterProvider: Provider<ShopPresenter>

    private val shopPresenter by moxyPresenter { presenterProvider.get() }

    @Inject
    lateinit var db: RoomRepo

    override val coroutineContext: CoroutineContext = SupervisorJob() + Dispatchers.Main.immediate

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(
            R.layout.shop_fragment,
            container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rvCatalog.layoutManager=LinearLayoutManager(App.ctx)
        val items: ArrayList<itemCatalogs>? = arrayListOf()

        shopPresenter.getCatalog()
        myAdapter=AdapterPendingCases(items)
        rvCatalog.adapter =myAdapter


       Log.d("itemCOUNTcatalog",myAdapter.itemCount.toString())
    }


    class AdapterPendingCases(private val values:ArrayList<itemCatalogs>?): RecyclerView.Adapter<AdapterPendingCases.PendingCasesViewHolder>() {
        override fun getItemCount(): Int {
            return values!!.size
        }

        inner class PendingCasesViewHolder constructor(itemView: View):RecyclerView.ViewHolder(itemView) {

            fun bind(item: itemCatalogs) {
                itemView.priceTV.text=item.price
                Picasso.get().load(item.url).into(itemView.photoItem)
                itemView.descriptionTV.text=item.name
            }


        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PendingCasesViewHolder {
            return PendingCasesViewHolder(LayoutInflater.from(App.ctx).inflate(R.layout.item_catalog, parent, false))}


        override fun onBindViewHolder(holder: PendingCasesViewHolder, position: Int) {
            values?.get(position)?.let { holder.bind(it) }
        }

        fun addList(items:List<itemCatalogs>) {
            values?.addAll(items)
        }


    }

    override fun loadCatalogFromDB(catalog: Collection<itemCatalogs>?) {
        if (catalog != null) {
            Log.d("catalogInFragmentShop",catalog.toString())
            myAdapter.addList(catalog as List<itemCatalogs>)
        }
        myAdapter.notifyDataSetChanged()
    }

    override fun loadSizeInBtn(catalogSize: Int?) {
        //button.text=catalogSize.toString()
    }
}