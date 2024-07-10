package com.example.flowplayground

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import com.example.flowplayground.databinding.ActivityMainBinding
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private val viewModel: FlowViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.loadData()

        lifecycleScope.launch {
            viewModel.errorMessage.collect{error ->
                Toast.makeText(this@MainActivity, error, Toast.LENGTH_LONG).show()
            }
        }

        binding.refreshFAB.setOnClickListener {
            viewModel.loadDataNumber()
        }

        binding.secondRefreshFAB.setOnClickListener {
            viewModel.loadData()
        }

        lifecycleScope.launch {
            viewModel.combinedFlow.collect {
                binding.dataTV.text = it
            }
        }




//        viewModel.apiData.observe(this){
//            binding.dataTV.text = it
//        }
    }
}