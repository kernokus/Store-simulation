package com.example.a10augportfolio.view

import com.example.a10augportfolio.room.itemCatalogs
import moxy.MvpView
import moxy.viewstate.strategy.OneExecutionStateStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(value = OneExecutionStateStrategy::class) //разобраться
interface ShopFragmentView:MvpView {
    abstract fun loadCatalogFromDB(catalog: Collection<itemCatalogs?>?)
}