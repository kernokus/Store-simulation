package com.example.a10augportfolio.view

import moxy.MvpView
import moxy.viewstate.strategy.OneExecutionStateStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(value = OneExecutionStateStrategy::class) //разобраться
interface RegFragmentView:MvpView {
     fun showResAddUser(answer: String)
}