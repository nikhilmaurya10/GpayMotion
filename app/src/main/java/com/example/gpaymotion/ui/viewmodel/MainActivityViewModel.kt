package com.example.gpaymotion.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gpaymotion.data.ContactsRepository
import com.example.gpaymotion.data.HomeDataState
import com.example.gpaymotion.models.BaseModel
import com.example.gpaymotion.models.ContactCardList
import com.example.gpaymotion.models.HeaderSectionModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.core.component.KoinApiExtension

@KoinApiExtension
class MainActivityViewModel(val contactsRepository: ContactsRepository): ViewModel() {
    private val _data : MutableLiveData<HomeDataState> = MutableLiveData()
    val homeData: LiveData<HomeDataState> = _data

    init {
        viewModelScope.launch(Dispatchers.IO + CoroutineExceptionHandler { _, _ ->
            run {
                _data.postValue(
                    HomeDataState.Error(
                        Exception("Something went wrong")
                    )
                )
            }
        }) {
            _data.postValue(HomeDataState.Loading)
            val list: ArrayList<BaseModel> = arrayListOf()
            list.add(HeaderSectionModel("People"))
            val listOfPeople = contactsRepository.getPeopleData()
            list.add(ContactCardList(listOfPeople.take(4)))
            list.add(ContactCardList(listOfPeople.subList(5,9)))
            list.add(ContactCardList(listOfPeople.takeLast(4)))
            list.add(HeaderSectionModel("Business and Bills", "Explore"))
            val listOfBusiness = contactsRepository.getBusinesses()
            list.add(ContactCardList(listOfBusiness.take(4)))
            list.add(ContactCardList(listOfBusiness.subList(5,9)))
            list.add(ContactCardList(listOfBusiness.takeLast(4)))
            _data.postValue(
                HomeDataState.Success(
                    list
                )
            )
        }
    }

}