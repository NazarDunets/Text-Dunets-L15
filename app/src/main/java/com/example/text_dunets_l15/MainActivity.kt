package com.example.text_dunets_l15

import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.text.style.UnderlineSpan
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.text_dunets_l15.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: UsersAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupBinding()
        setupRecycler()
        setListeners()
    }

    private fun setListeners() {
        binding.btAddUser.setOnClickListener {
            if (isInputValid()) {
                val firstName = binding.etFirst.text.toString()
                val lastName = binding.etLast.text.toString()
                val fullName = SpannableString("$firstName $lastName")
                val colorSpan = ForegroundColorSpan(Color.CYAN)

                fullName.apply {
                    setSpan(colorSpan, 0, firstName.length, 0)
                    setSpan(UnderlineSpan(), 0, firstName.length, 0)
                }

                adapter.addUser(fullName)
            }
        }

        binding.etFirst.addTextChangedListener(
            onTextChanged = { _, _, _, _ -> binding.tlFirst.error = null }
        )
        binding.etLast.addTextChangedListener(
            onTextChanged = { _, _, _, _ -> binding.tlLast.error = null }
        )
    }

    private fun setupRecycler() {
        adapter = UsersAdapter()
        binding.rvUsers.adapter = adapter
        binding.rvUsers.addItemDecoration(
            DividerItemDecoration(this, RecyclerView.VERTICAL)
        )
        binding.rvUsers.layoutManager =
            LinearLayoutManager(this, RecyclerView.VERTICAL, false)
    }

    private fun isInputValid(): Boolean {
        var allValid = true

        listOf(binding.tlFirst, binding.tlLast).forEach {
            if (it.editText?.text.isNullOrBlank()) {
                it.error = "Invalid Input"
                allValid = false
            } else if (!it.editText!!.text.startsWithUpper()) {
                it.error = "Invalid Input"
                allValid = false
            }
        }

        return allValid
    }

    private fun setupBinding() {
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}

private fun Editable.startsWithUpper(): Boolean {
    if (toString().toCharArray()[0].isUpperCase()) return true
    return false
}