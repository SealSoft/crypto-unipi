package tech.sealsoft.crypto.fragment


import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import tech.sealsoft.crypto.R
import tech.sealsoft.crypto.service.ServiceHelper

class LoginFragment : Fragment() {


    private var mContext: Context? = null

    private lateinit var fragmentView: View
    private lateinit var auth: FirebaseAuth
    private lateinit var snackbar: Snackbar
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        auth = FirebaseAuth.getInstance()

        // Inflate the layout for this fragment
        fragmentView = inflater.inflate(R.layout.fragment_login, container, false)
        val btn: Button = fragmentView.findViewById(R.id.loginBtn)



        btn.setOnClickListener {
            onLogin()
        }
        return fragmentView
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }

    private fun onLogin() {
        val usernameText: EditText = fragmentView.findViewById(R.id.emailLogin)
        val passwordText: EditText = fragmentView.findViewById(R.id.password)
        var username = usernameText.text.toString()
        var password = passwordText.text.toString()
        loginUser(username, password)
    }

    private fun loginUser(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    snackbar = Snackbar.make(fragmentView, "Authenticating Completed..", 4000)
                    snackbar.show()
//                    GlobalScope.launch {
//                        ServiceHelper.callAllCoins(mContext!!)
//                    }
                    Log.d("tag", auth.currentUser?.uid)
                    proceedToCryptoFragment()
                } else {

                }
            }
    }

    private fun proceedToCryptoFragment() {
        val cryptoFragment = CryptoFragment()
        activity?.supportFragmentManager?.popBackStack(
            null,
            FragmentManager.POP_BACK_STACK_INCLUSIVE
        )

        val transaction = activity?.supportFragmentManager?.beginTransaction()
        transaction?.replace(R.id.base_fragment_container, cryptoFragment)

        transaction?.commitAllowingStateLoss()
    }


}
