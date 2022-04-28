package com.example.ezikartf.activities

import android.app.Dialog
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.WindowInsets
import android.view.WindowManager
import android.widget.CheckBox
import android.widget.EditText
import android.widget.TextView
import com.example.ezikartf.R
import org.w3c.dom.Text

class RegisterActivity : BaseActivity() {

    private lateinit var etfirstname:EditText
    private lateinit var etlastname:EditText
    private lateinit var etemail:EditText
    private lateinit var etpassword:EditText
    private lateinit var etconfirmpassword:EditText
    private lateinit var etterms:CheckBox

    override fun onCreate(savedInstanceState: Bundle?) {

        etfirstname = findViewById(R.id.et_first_name)
        etlastname = findViewById(R.id.et_last_name)
        etemail = findViewById(R.id.et_email)
        etpassword = findViewById(R.id.et_password)
        etconfirmpassword = findViewById(R.id.et_confirm_password)
        etterms = findViewById(R.id.cb_terms_and_condition)



        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        @Suppress("DEPRECATION")
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.insetsController?.hide(WindowInsets.Type.statusBars())
        } else {
            window.setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
            )
        }


        val tv_login = findViewById(R.id.tv_login) as TextView

        tv_login.setOnClickListener {
            val intent = Intent(this,LoginActivity::class.java)
            startActivity(intent)
        }

        /*val actionbar = supportActionBar
        actionbar!!.title = "Hi"
        actionbar.setDisplayHomeAsUpEnabled(true)
        actionbar.setDisplayShowHomeEnabled(true)
        */



    }
/*
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true

    }
 */

    private fun validateRegisterDetails(): Boolean {
        return when {
            TextUtils.isEmpty(etfirstname.text.toString().trim { it <= ' ' }) -> {
                showErrorSnackBar(resources.getString(R.string.err_msg_enter_first_name), true)
                false
            }

            TextUtils.isEmpty(etlastname.text.toString().trim { it <= ' ' }) -> {
                showErrorSnackBar(resources.getString(R.string.err_msg_enter_last_name), true)
                false
            }

            TextUtils.isEmpty(etemail.text.toString().trim { it <= ' ' }) -> {
                showErrorSnackBar(resources.getString(R.string.err_msg_enter_email), true)
                false
            }

            TextUtils.isEmpty(etpassword.text.toString().trim { it <= ' ' }) -> {
                showErrorSnackBar(resources.getString(R.string.err_msg_enter_password), true)
                false
            }

            TextUtils.isEmpty(etconfirmpassword.text.toString().trim { it <= ' ' }) -> {
                showErrorSnackBar(resources.getString(R.string.err_msg_enter_confirm_password), true)
                false
            }

            etpassword.text.toString().trim { it <= ' ' } != etconfirmpassword.text.toString()
                .trim { it <= ' ' } -> {
                showErrorSnackBar(resources.getString(R.string.err_msg_password_and_confirm_password_mismatch), true)
                false
            }
            !etterms.isChecked -> {
                showErrorSnackBar(resources.getString(R.string.err_msg_agree_terms_and_condition), true)
                false
            }
            else -> {
                showErrorSnackBar("Your details are valid.", false)
                true
            }
        }
    }
}