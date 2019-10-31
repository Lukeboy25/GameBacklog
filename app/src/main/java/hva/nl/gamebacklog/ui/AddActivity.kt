package hva.nl.gamebacklog.ui

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.room.TypeConverters
import hva.nl.gamebacklog.R
import hva.nl.gamebacklog.database.Converters
import hva.nl.gamebacklog.model.Game

import kotlinx.android.synthetic.main.activity_add.*
import kotlinx.android.synthetic.main.content_add.*
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

const val EXTRA_GAME = "EXTRA_GAME"

@TypeConverters(Converters::class)
class AddActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)
        setSupportActionBar(toolbar)

        initViews()
    }

    private fun initViews() {
        fab.setOnClickListener { onSaveClick() }
    }

    @SuppressLint("SimpleDateFormat")
    private fun onSaveClick() {
        if (etTitle.text.toString().isNotBlank() && etPlatform.text.toString().isNotBlank()
            && etDay.text.toString().isNotBlank() && etMonth.text.toString().isNotBlank() &&
            etYear.text.toString().isNotBlank()) {

            val concatenatedString = (etDay.text.toString() + "-" + etMonth.text.toString() + "-" + etYear.text.toString())
            val date = SimpleDateFormat("dd-MM-yyyy").parse(concatenatedString)

            val game = Game(etTitle.text.toString(),
                etPlatform.text.toString(), date
            )

            val resultIntent = Intent()
            resultIntent.putExtra(EXTRA_GAME, game)
            setResult(Activity.RESULT_OK, resultIntent)
            finish()
        } else {
            Toast.makeText(this,"Make sure every field is filled in!"
                , Toast.LENGTH_SHORT).show()
        }
    }

}
