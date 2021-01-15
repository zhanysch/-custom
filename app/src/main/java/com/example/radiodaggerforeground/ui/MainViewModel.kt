package com.example.radiodaggerforeground.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.radiodaggerforeground.data.remote.RadioStation
import com.example.radiodaggerforeground.data.remote.Resources

class MainViewModel: ViewModel() {

    val radioList = Resources.generate()
    var radioLiveData  = MutableLiveData<RadioStation>().apply { value = radioList[0] } //значен по умолчан[0]
    var isBound = false  //  при нажатии на кнопку стоп , когда переключаетс канал не срабатывала музыка!!

    fun nextStation(){    // преключ на след радиостанц по позиции
        val pos = radioList.indexOf(radioLiveData.value) // узнает позиции списка
        if (pos < radioList.size -1) // пример если позиц 6 < последн позиции(7 позиц), то можно + 1позиц, когда последн позиц ничего не происходит
            radioLiveData.postValue(radioList[pos +1])
    }

    fun previousStation(){  // возврат на предыдущ позицию
        val pos = radioList.indexOf(radioLiveData.value) // узнает позиции списка
        if ( pos > 0)   // если позиц > 0 идти с 9 8 7 6 5..
            radioLiveData.postValue(radioList[pos-1])
    }


}