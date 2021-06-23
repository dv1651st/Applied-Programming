package com.example.rockpaperscissors

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.view.View
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase


class RegisterUser : AppCompatActivity(), View.OnClickListener {
    private lateinit var banner: TextView
    private lateinit var registerUser: TextView
    private lateinit var editTextFullName: EditText
    private lateinit var editTextAge: EditText
    private lateinit var editTextEmail: EditText
    private lateinit var editTextPassword: EditText
    private lateinit var progressBar: ProgressBar

    private lateinit var mDatabaseReference: DatabaseReference
    private lateinit var mDatabase: FirebaseDatabase
    private lateinit var auth: FirebaseAuth

    companion object {
        private const val TAG = "Register User"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register_user)

        mDatabase = FirebaseDatabase.getInstance()
        mDatabaseReference = mDatabase!!.reference.child("Users")
        auth = FirebaseAuth.getInstance()

        banner = findViewById(R.id.banner)

        registerUser = findViewById(R.id.registerUser)
        registerUser.setOnClickListener(this)
        editTextFullName = findViewById(R.id.name)
        editTextPassword = findViewById(R.id.password)
        editTextEmail = findViewById(R.id.email)

        progressBar = findViewById(R.id.progressBar)

    }

    override fun onClick(v: View?) {
        when (v?.id){
            R.id.banner ->{
                val intent = Intent(this, Login::class.java)
                startActivity(intent)
            }
            R.id.registerUser -> {
                registerUser()
            }
        }
    }
    private fun registerUser() {
        val email: String = editTextEmail.text.toString().trim()
        val password: String = editTextPassword.text.toString().trim()
        var name: String = editTextFullName.text.toString().trim()



        if(name.isEmpty()){
            editTextFullName.error = "Full name is required!"
            editTextFullName.requestFocus()
            return
        }
        if(email.isEmpty()){
            editTextEmail.error = "Email is required!"
            editTextEmail.requestFocus()
            return
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            editTextEmail.error = "Please provide valid email address"
            editTextEmail.requestFocus()
            return
        }
        if(password.isEmpty()){
            editTextPassword.error = "Password required!"
            editTextPassword.requestFocus()
            return
        }
        if(password.length < 6){
            editTextPassword.error = "Min password length should be 6 characters!"
            editTextPassword.requestFocus()
            return
        }

        progressBar.visibility = View.VISIBLE;

        auth
            .createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->

                val user = UserData(name,email)
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "createUserWithEmail:success")

                    val currentUserDb = mDatabaseReference!!.child(user.name)
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(TAG, "createUserWithEmail:failure", task.exception)
                    Toast.makeText(baseContext, "Authentication failed.",
                        Toast.LENGTH_SHORT).show()
                }
            }


    }
}