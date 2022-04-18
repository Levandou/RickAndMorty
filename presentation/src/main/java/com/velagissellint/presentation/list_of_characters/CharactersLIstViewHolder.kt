package com.velagissellint.presentation.list_of_characters

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.velagissellint.presentation.R

class CharactersLIstViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
    val ivLogo: ImageView = view.findViewById(R.id.ivLogo)
    val tvName: TextView = view.findViewById(R.id.tvName)
    val tvGender: TextView = view.findViewById(R.id.tvGender)
    val tvSpecies: TextView = view.findViewById(R.id.tvSpecies)
}
