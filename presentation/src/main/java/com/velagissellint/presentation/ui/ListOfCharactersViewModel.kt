package com.velagissellint.presentation.ui

import android.util.Log
import android.widget.ImageView
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.rxjava3.cachedIn
import com.squareup.picasso.Picasso
import com.velagissellint.domain.pojo.Result
import com.velagissellint.domain.useCases.paging.GetCharactersPagesUseCase
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import javax.inject.Inject

class ListOfCharactersViewModel @Inject constructor(
    private val getCharactersPagesUseCase: GetCharactersPagesUseCase
) : ViewModel() {
    private val mutablePagesOfCharacters = MutableLiveData<PagingData<Result>>()
    val pagesOfCharacters =
        mutablePagesOfCharacters as LiveData<PagingData<Result>>

    private fun loadPagesOfCharacters() {
        getCharactersPagesUseCase.getCharacters().cachedIn(viewModelScope)
            .take(1)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                mutablePagesOfCharacters.value = it
            }, {
                Log.d("Error555", it.message.toString())
            })
    }

    fun loadImage(url: String, imageView: ImageView) {
        Picasso.get()
            .load(url)
            .into(imageView)
    }

    init {
        loadPagesOfCharacters()
    }
}
