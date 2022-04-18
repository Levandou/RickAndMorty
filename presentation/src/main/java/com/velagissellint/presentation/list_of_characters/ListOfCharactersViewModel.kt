package com.velagissellint.presentation.list_of_characters

import android.util.Log
import android.widget.ImageView
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.rxjava3.cachedIn
import com.squareup.picasso.Picasso
import com.velagissellint.domain.models.Result
import com.velagissellint.domain.useCases.paging.GetCharactersPagesUseCase
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.addTo
import javax.inject.Inject

class ListOfCharactersViewModel @Inject constructor(
    private val getCharactersPagesUseCase: GetCharactersPagesUseCase
) : ViewModel() {
    private val disposable: CompositeDisposable = CompositeDisposable()
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
                Log.d("ERROR", it.message.toString())
            }).addTo(disposable)
    }

    fun loadImage(url: String, imageView: ImageView) {
        Picasso.get()
            .load(url)
            .into(imageView)
    }

    init {
        loadPagesOfCharacters()
    }

    override fun onCleared() {
        disposable.dispose()
        super.onCleared()
    }
}
