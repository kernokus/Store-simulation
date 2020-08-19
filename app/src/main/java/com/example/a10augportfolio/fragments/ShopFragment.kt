package com.example.a10augportfolio.fragments

import android.os.Bundle
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
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.item_catalog.view.*
import kotlinx.android.synthetic.main.shop_fragment.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import javax.inject.Inject
import javax.inject.Provider

@AndroidEntryPoint
class ShopFragment:MvpAppCompatFragment(),ShopFragmentView {
    val items: ArrayList<itemCatalogs?>? = arrayListOf()
    private lateinit var myAdapter :AdapterPendingCases

    @Inject
    lateinit var presenterProvider: Provider<ShopPresenter>

    private val shopPresenter by moxyPresenter { presenterProvider.get() }

    @Inject
    lateinit var db: RoomRepo


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
        val items: ArrayList<itemCatalogs?>? = arrayListOf()
        //
        //достать из бд


        //val itemsDB:Collection<itemCatalogs?>?=
            shopPresenter.getCatalog()
       // if (itemsDB!=null) {
        //    items?.addAll(itemsDB)
       // }

        myAdapter=AdapterPendingCases(items)
        rvCatalog.adapter =myAdapter



//        testBtn.setOnClickListener {
//            GlobalScope.launch(Dispatchers.IO) {
//                val test=db.getUsers()
//                withContext(Dispatchers.Main){
//                    testTV.text=test.toString()
//                }
//            }
//        }
    }


    class AdapterPendingCases(private val values:ArrayList<itemCatalogs?>?): RecyclerView.Adapter<AdapterPendingCases.PendingCasesViewHolder>() {
        override fun getItemCount(): Int {
            return values!!.size
        }

        inner class PendingCasesViewHolder constructor(itemView: View):RecyclerView.ViewHolder(itemView) {



            fun bind(item: itemCatalogs) {
                itemView.price.text=item.price
            }


        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PendingCasesViewHolder {
            return PendingCasesViewHolder(LayoutInflater.from(App.ctx).inflate(R.layout.item_catalog, parent, false))}


        override fun onBindViewHolder(holder: PendingCasesViewHolder, position: Int) {
            values?.get(position)?.let { holder.bind(it) }
        }


        fun delAndUpdate(position: Int) {
            values?.removeAt(position)
            notifyDataSetChanged()
        }

        fun addAndUpdate(item: itemCatalogs) {
            values?.add(item)
            notifyDataSetChanged()
        }

        fun  getListValues(): ArrayList<itemCatalogs?>? {
            return values
        }

        fun addList(items:List<itemCatalogs>) {
            values?.addAll(items)
        }

        fun getSizeListRV(): Int {return values!!.size}
        fun clearListRV(){values?.clear()}

    }

    override fun loadCatalogFromDB(catalog: Collection<itemCatalogs?>?) {
        if (catalog != null) {
            items?.addAll(catalog)
        }
        myAdapter.notifyDataSetChanged()
    }
}