package com.velagissellint.presentation.detailed_character

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.velagissellint.domain.models.Character
import com.velagissellint.domain.useCases.character.GetDetailedCharacterUseCase
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.addTo
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class DetailedCharacterViewModel @Inject constructor(
    private val getDetailedCharacterUseCase: GetDetailedCharacterUseCase
) : ViewModel() {
    private val disposable: CompositeDisposable = CompositeDisposable()
    private val mutableDetailedCharacter = MutableLiveData<Character>()
    val detailedCharacter = mutableDetailedCharacter as LiveData<Character>
    private val mutableIsLoading = MutableLiveData<Boolean>()
    val isLoading = mutableIsLoading as LiveData<Boolean>

    fun loadDetailedCharacter(id: String?) {
        getDetailedCharacterUseCase.getCharacter(id ?: "1")
            .subscribeOn(Schedulers.io())
            .doOnSubscribe { mutableIsLoading.postValue(true) }
            .doFinally { mutableIsLoading.postValue(false) }
            .firstOrError()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                mutableDetailedCharacter.value = it
            }, {
                Log.d("ERROR", it.message.toString())
            }).addTo(disposable)
    }

    override fun onCleared() {
        disposable.dispose()
        super.onCleared()
    }
}
