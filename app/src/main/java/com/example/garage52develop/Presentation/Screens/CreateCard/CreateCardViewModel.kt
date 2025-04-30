package com.example.garage52develop.Presentation.Screens.CreateCard


import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.garage52develop.Domain.Constant
import com.example.garage52develop.Domain.Constant.supabase
import com.example.garage52develop.Domain.State.DetailState
import com.example.garage52develop.Domain.models.Categories
import io.github.jan.supabase.auth.auth
import io.github.jan.supabase.postgrest.postgrest
import kotlinx.coroutines.launch
import kotlinx.serialization.Serializable

class CreateCardViewModel : ViewModel() {

    private val _detailState = mutableStateOf(DetailState())
    val detailState: DetailState get() = _detailState.value

    private val _userId = mutableStateOf<String?>("")
    val userId:String? get() = _userId.value

    private val _categories = MutableLiveData<List<Categories>>()
    val categories: LiveData<List<Categories>> get() = _categories

    @Serializable
    data class newDetail(
        val title: String,
        val categoryId: String,
        val description: String,
        )

    init{
        selectUser()
        loadCategories()
    }

    fun updateDetail(newDetail: DetailState){
        _detailState.value = newDetail
    }
    fun selectUser(){
        viewModelScope.launch {
            try{
                _userId.value = Constant.supabase.auth.currentUserOrNull()?.id
            }
            catch (ex: Exception){ }
        }
    }
    fun addDetail(){
        viewModelScope.launch {
            try {
                supabase.postgrest.from("detail").insert(
                    newDetail(
                        title = _detailState.value.title,
                        categoryId = _detailState.value.categoryId,
                        description = _detailState.value.description,
                    )
                )
            }
            catch (ex: Exception){
                Log.e("AddDetail", "Full error: ", ex)
                println("Detailed error: ${ex.stackTraceToString()}")}
        }
    }
    private fun loadCategories(){
        viewModelScope.launch {
            try{
                _categories.value = supabase.postgrest.from("category").select().decodeList<Categories>()
            }
            catch (ex: Exception){
            }
        }
    }
}