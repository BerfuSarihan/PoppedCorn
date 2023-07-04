package es.usj.poppedcorn

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import es.usj.poppedcorn.R
import es.usj.poppedcorn.databinding.ActivityContactBinding


class ContactActivity : AppCompatActivity() {

    private val view by lazy {
        ActivityContactBinding.inflate(layoutInflater)
    }

    private lateinit var tvEmail: TextView
    private lateinit var tvWeb: TextView
    private lateinit var tvPhone: TextView
    private lateinit var btnEmail: Button
    private lateinit var btnWeb: Button
    private lateinit var btnCall: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(view.root)

        tvEmail = findViewById(R.id.tvEmail)
        tvWeb = findViewById(R.id.tvWeb)
        tvPhone = findViewById(R.id.tvPhone)
        btnEmail = findViewById(R.id.btnEmail)
        btnWeb = findViewById(R.id.btnWeb)
        btnCall = findViewById(R.id.btnCall)

        btnEmail.setOnClickListener { sendEmail() }
        btnWeb.setOnClickListener { openWebsite() }
        btnCall.setOnClickListener { makePhoneCall() }
    }

    private fun sendEmail() {
        val emailIntent = Intent(Intent.ACTION_SENDTO)
        emailIntent.data = Uri.parse("mailto:${tvEmail.text}")
        startActivity(emailIntent)
    }

    private fun openWebsite() {
        val websiteIntent = Intent(Intent.ACTION_VIEW)
        websiteIntent.data = Uri.parse(tvWeb.text.toString())
        startActivity(websiteIntent)
    }

    private fun makePhoneCall() {
        val phoneUri = Uri.parse("tel:${tvPhone.text}")
        val phoneIntent = Intent(Intent.ACTION_DIAL, phoneUri)
        startActivity(phoneIntent)
    }
}
