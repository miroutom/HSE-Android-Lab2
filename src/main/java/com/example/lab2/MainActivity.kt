package com.example.lab2

import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.lab2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    private lateinit var itemAdapter: ItemAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val dataSet = DataSource(this).loadItems()

        itemAdapter = ItemAdapter(this, dataSet)
        binding.recyclerView.adapter = itemAdapter
        binding.recyclerView.layoutManager = LinearLayoutManager(this)

        binding.buttonAdd.setOnClickListener {
            val newItemText = binding.editText.text.toString().trim()
            if (newItemText.isNotEmpty()){
                val newItem = ShoppingItem(newItemText)
                itemAdapter.addItem(newItem)
                binding.editText.text.clear()
            } else {
                Toast.makeText(this,
                    "Please enter correct name",
                    Toast.LENGTH_SHORT)
                    .show()
            }
        }

        itemAdapter.setOnDeleteClickListener {
            position -> itemAdapter.removeItem(position)
        }

        itemAdapter.setOnEditClickListener { position ->
            val editedItemText = dataSet[position].name
            val editText = EditText(this)
            editText.setText(editedItemText)

            AlertDialog.Builder(this)
                .setTitle("Edit Item")
                .setView(editText)
                .setPositiveButton("Save") { dialog, _ ->
                    val editedText = editText.text.toString().trim()
                    if (editedText.isNotEmpty()) {
                        val editedItem = ShoppingItem(editedText)
                        itemAdapter.editItem(position, editedItem)
                        dialog.dismiss()
                    } else {
                        Toast.makeText(this,
                            "Please enter correct name",
                            Toast.LENGTH_SHORT)
                            .show()
                    }
                }
                .setNegativeButton("Cancel") { dialog, _ ->
                    dialog.dismiss()
                }
                .create()
                .show()
        }
    }
}