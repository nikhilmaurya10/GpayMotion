package com.example.gpaymotion.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.gpaymotion.R
import com.example.gpaymotion.models.BaseModel
import com.example.gpaymotion.models.ContactCardList
import com.example.gpaymotion.models.HeaderSectionModel

class GpayContactsAdapter(var data: List<BaseModel>): RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val vh = when(viewType) {
            1 -> {
                ViewHolder1(
                    LayoutInflater.from(parent.context).inflate(
                        R.layout.layout_section_header,
                        parent,
                        false
                    )
                )
            }
            2 -> {
                ViewHolder2(
                    LayoutInflater.from(parent.context).inflate(
                        R.layout.layout_contact_card_row,
                        parent,
                        false
                    )
                )

            }
            else -> {
                ViewHolder1(
                    LayoutInflater.from(parent.context).inflate(
                        R.layout.layout_section_header,
                        parent,
                        false
                    )
                )

            }
        }
        return vh
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (getItemViewType(position)) {
            1 -> {
                val holder1 = holder as? ViewHolder1
                holder1?.apply {
                    val data = (data[position] as HeaderSectionModel)
                    sectionTitle.text = data.title
                    if (data.showMoreBtnText.isNullOrEmpty()) {
                        moreBtn.visibility = View.GONE
                    } else {
                        moreBtn.text = data.showMoreBtnText
                    }
                    root.post {
                        root.layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT
                    }
                }
            }

            2 -> {
                val holder2 = holder as? ViewHolder2
                holder2?.apply {
                    val data = data[position] as ContactCardList
                    data.contactCardsRow.forEach {
                        val view = LayoutInflater.from(container.context).inflate(R.layout.layout_contact_icon, container, false)
                        val icon = view.findViewById<ImageView>(R.id.avatar)
                        icon.load(it.avatarURL) {
                            crossfade(true)
                        }
                        val name = view.findViewById<TextView>(R.id.contactNameTV)
                        name.text = it.name
                        container.addView(view)
                    }

                    container.post {
                        container.layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT
                    }
                }
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when(position) {
            0, 4, 8 -> 1
            else -> 2
        }
    }
    class ViewHolder1(private val view:View): RecyclerView.ViewHolder(view) {
        val sectionTitle = view.findViewById<TextView>(R.id.textView)
        val moreBtn = view.findViewById<Button>(R.id.button2)
        val root = view.findViewById<View>(R.id.root)

    }
    class ViewHolder2(view: View): RecyclerView.ViewHolder(view) {
        val container = view.findViewById<LinearLayout>(R.id.ccContainer)
    }

}
