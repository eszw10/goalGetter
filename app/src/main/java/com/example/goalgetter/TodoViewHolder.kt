package com.example.goalgetter

import android.view.View
import android.widget.CheckBox
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

public class TodoViewHolder(private  val itemView: View): RecyclerView.ViewHolder(itemView) {
    public val titleView: TextView = itemView.findViewById(R.id.task_title)
    public val descriptionView: TextView = itemView.findViewById(R.id.task_description)
    public val doneTaskView: CheckBox = itemView.findViewById(R.id.task_done)
    public val timeView: TextView = itemView.findViewById(R.id.task_time)
    public val wrapper: LinearLayout = itemView.findViewById(R.id.task_item)
}