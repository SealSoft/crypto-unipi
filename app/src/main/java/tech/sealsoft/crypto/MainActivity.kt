package tech.sealsoft.crypto

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.firebase.auth.FirebaseAuth
import tech.sealsoft.crypto.fragment.CryptoFragment
import tech.sealsoft.crypto.fragment.LoginFragment

class MainActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private var firstFragment: Fragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val progressBar = findViewById<ProgressBar>(R.id.progress_bar)
        progressBar.visibility = View.VISIBLE

        auth = FirebaseAuth.getInstance()
        if (auth.currentUser != null) {
            firstFragment = CryptoFragment()
        } else {
            firstFragment = LoginFragment()
        }


        firstFragment!!.arguments = intent.extras
        supportFragmentManager.beginTransaction()
            .add(R.id.base_fragment_container, firstFragment!!).commitAllowingStateLoss()

        progressBar.visibility = View.GONE

    }

    override fun onDestroy() {
        if (auth.currentUser != null) {
            auth.signOut()
        }
        super.onDestroy()
    }
}
