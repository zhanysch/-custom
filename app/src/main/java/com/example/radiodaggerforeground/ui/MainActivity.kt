package com.example.radiodaggerforeground.ui

import android.content.ComponentName
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.radiodaggerforeground.data.RadioService
import com.example.radiodaggerforeground.databinding.ActivityMainBinding
import com.example.radiodaggerforeground.util.forceRefresh
import com.example.radiodaggerforeground.util.viewBinding
import com.example.radiodaggerforeground.util.viewModel
import timber.log.Timber

class MainActivity : AppCompatActivity() {

    private val binding by viewBinding(ActivityMainBinding::inflate)
    private val viewModel by viewModel(MainViewModel::class)

    private lateinit var serviceConnection : ServiceConnection
    private val intentService by lazy { Intent(this, RadioService::class.java) }  // intent(bla bla) чтоб ссмогли ее переиспользовать
    private var radioService : RadioService? = null



    private val adapter by lazy {
        RadioAdapter {
            bindService(intentService, serviceConnection, BIND_AUTO_CREATE)
            viewModel.radioLiveData.postValue(it)
            viewModel.isBound = true  //  при нажатии на кнопку стоп , когда переключаетс канал не срабатывала музыка!
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setupListeners()
        setupServiceConnection()
        setupRecyclerView()
        setupViewModel()
    }

    private fun setupViewModel() {
       viewModel.radioLiveData.observe(this, Observer {
          if (viewModel.isBound) radioService?.play(it.station) // play запускаетс в том случае, если нажали на запуск
           binding.viewPlayer.tvTitle.text = it.name           /// изминен текста при клике на recyclerview
       })
    }

    private fun setupRecyclerView() {
      binding.rvRadio.adapter = adapter
        adapter.update(viewModel.radioList)
    }

    private fun setupServiceConnection() {
        serviceConnection = object : ServiceConnection{
            override fun onServiceConnected(p0: ComponentName?, service: IBinder?) {
                Timber.d("onServiceConnected")
                viewModel.isBound = true   //  при нажатии на кнопку стоп , когда переключаетс канал не срабатывала музыка!!
                radioService = (service as RadioService.RadiobBinder).getService()
                viewModel.radioLiveData.forceRefresh()   // force refresh из extension для клике на запуск play  чтб играла музыка
                binding.viewPlayer.imgPlayPause.isActivated = true
            }

            override fun onServiceDisconnected(p0: ComponentName?) {
                Timber.d("onServiceDisconnected")
                binding.viewPlayer.imgPlayPause.isActivated = false
                viewModel.isBound = false  //  при нажатии на кнопку стоп , когда переключаетс канал не срабатывала музыка!!
            }

        }
    }

    private fun setupListeners() {       // ФУНКЦ ЗАПУСКА И ОСТАНОВКИ РАДИО

        binding.viewPlayer.imgPlayPause.setOnClickListener {
            if (it.isActivated){                  // кнопки меняються тоесть при нажатии на play меняеться кнопка на стоп
                unbindService(serviceConnection)
                binding.viewPlayer.imgPlayPause.isActivated = false
                viewModel.isBound = false  //  при нажатии на кнопку стоп , когда переключаетс канал не срабатывала музыка!!
            } else {
                binding.viewPlayer.imgPlayPause.isActivated = true
                viewModel.isBound = true  //  при нажатии на кнопку стоп , когда переключаетс канал не срабатывала музыка!!
                bindService(intentService,serviceConnection, BIND_AUTO_CREATE)
            }
        }

        binding.viewPlayer.imgNext.setOnClickListener {
             viewModel.nextStation()     //перелюч на след станци
        }

        binding.viewPlayer.imgPrevious.setOnClickListener {
            viewModel.previousStation()  //перелюч на предыдущ станц
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        // для уничтожен сервиса когда активити уничтожено
        unbindService(serviceConnection)
        stopService(intent)
    }
}