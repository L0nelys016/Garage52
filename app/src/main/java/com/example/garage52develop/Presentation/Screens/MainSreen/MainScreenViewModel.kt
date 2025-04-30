package com.example.garage52develop.Presentation.Screens.MainSreen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.garage52develop.Domain.Constant.supabase
import com.example.garage52develop.Domain.State.ResultState
import com.example.garage52develop.Domain.models.Categories
import com.example.garage52develop.Domain.models.Detail
import io.github.jan.supabase.postgrest.postgrest
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MainScreenViewModel : ViewModel(){
    private val _resultState = MutableStateFlow<ResultState>(ResultState.Loading)
    val resultState: StateFlow<ResultState> = _resultState.asStateFlow()

    private val _detail = MutableLiveData<List<Detail>>()
    val detail: LiveData<List<Detail>> get() = _detail

    private val _categories = MutableLiveData<List<Categories>>()
    val categories: LiveData<List<Categories>> get() = _categories

    private var filterDetail: List<Detail> = listOf()

    init {
        loadGames()
        loadCategoriesGames()
    }

    private fun loadGames(){
        _resultState.value = ResultState.Loading
        viewModelScope.launch {
            try{
                filterDetail = supabase.postgrest.from("detail").select().decodeList<Detail>()
                _detail.value = filterDetail
                _resultState.value = ResultState.Success("Все прошло отлично")
            }
            catch (ex: Exception){
                _resultState.value = ResultState.Error(ex.message + "Произошла ошибка")
            }
        }
    }

    private fun loadCategoriesGames(){
        viewModelScope.launch {
            try{
                _categories.value = supabase.postgrest.from("category").select().decodeList<Categories>()
            }
            catch (ex: Exception){
                _resultState.value = ResultState.Error(ex.message + "Произошла ошибка")
            }
        }
    }

    fun filterListDetail(strFilt: String, categoryId: String?){
        val filteredDetail = filterDetail.filter { detail ->
            detail.title.contains(strFilt)
        }
        val filtredDetailCategories = filterDetail.filter{ detail ->
            detail.categoryId == categoryId
        }
        val filtredDetailCategoriesAll = filterDetail.filter{ detail ->
            detail.categoryId == categoryId && detail.title.contains(strFilt)
        }
        if(categoryId != "" && strFilt != ""){
            _detail.value = filtredDetailCategoriesAll
        }
        else if (categoryId == ""){
            _detail.value = filteredDetail
        }
        else
        {
            _detail.value = filtredDetailCategories
        }
    }
}