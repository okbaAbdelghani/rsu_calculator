package com.example.calculatersunotes.ui.edit

import android.app.AlertDialog
import android.content.Context
import android.provider.Settings.Global.getString
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.calculatersunotes.R
import com.example.calculatersunotes.data.models.RuralMember

class MemberAdapter(private var members: MutableList<RuralMember>) : RecyclerView.Adapter<MemberAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MemberAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_member, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val member = members[position]

        holder.textView.text = member.fullName
        holder.deleteButton.setOnClickListener {
            showDeleteConfirmationPopup(it.context, position)
        }

        holder.editButton.setOnClickListener {

        }
    }

    override fun getItemCount(): Int {
        return members.size
    }

    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val textView: TextView = itemView.findViewById(R.id.member_name)
        val deleteButton : ImageButton = itemView.findViewById(R.id.delete_member_btn)
        val editButton : ImageButton = itemView.findViewById(R.id.edit_member_btn)
    }

    private fun showDeleteConfirmationPopup(context: Context, position: Int) {
        val dialogView = LayoutInflater.from(context).inflate(R.layout.delete_confirmation_dialog, null)

        val titleTextView: TextView = dialogView.findViewById(R.id.deleteConfirmationTitle)
        val messageTextView: TextView = dialogView.findViewById(R.id.deleteConfirmationMessage)
        val btnYes: Button = dialogView.findViewById(R.id.btnDeleteYes)
        val btnNo: Button = dialogView.findViewById(R.id.btnDeleteNo)




        val alertDialog = AlertDialog.Builder(context, R.style.RoundedDialog)
            .setView(dialogView)
            .create()

        btnYes.setOnClickListener {
            members.removeAt(position)
            notifyItemRemoved(position)
            Toast.makeText(context, "Item deleted", Toast.LENGTH_SHORT).show()
            alertDialog.dismiss()
        }

        btnNo.setOnClickListener {
            alertDialog.dismiss()
        }

        alertDialog.show()
    }
}