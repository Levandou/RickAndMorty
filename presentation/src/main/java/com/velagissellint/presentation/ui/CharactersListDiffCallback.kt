package com.velagissellint.presentation.ui

import androidx.recyclerview.widget.DiffUtil
import com.velagissellint.domain.pojo.Result

class CharactersListDiffCallback : DiffUtil.ItemCallback<Result>() {
    override fun areItemsTheSame(
        oldItem: Result,
        newItem: Result
    ) = oldItem.id == newItem.id

    override fun areContentsTheSame(
        oldItem: Result,
        newItem: Result
    ) = oldItem == newItem
}
