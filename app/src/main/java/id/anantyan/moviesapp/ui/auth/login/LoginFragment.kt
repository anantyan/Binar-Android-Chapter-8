package id.anantyan.moviesapp.ui.auth.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.AndroidEntryPoint
import id.anantyan.moviesapp.R
import id.anantyan.moviesapp.databinding.FragmentLoginBinding
import id.anantyan.moviesapp.utils.Resource
import javax.inject.Inject

@AndroidEntryPoint
class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!
    private val viewModel: LoginViewModel by viewModels()

    @Inject lateinit var auth: FirebaseAuth
    @Inject lateinit var oAuth: GoogleSignInClient

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onBindView()
        onBindObserver(view)
    }

    private fun onBindView() {
        binding.btnGoogle.setOnClickListener {
            contracts.launch(oAuth.signInIntent)
        }
    }

    private fun onBindObserver(view: View) {
        viewModel.signInWithGoogle.observe(viewLifecycleOwner) {
            when (it) {
                is Resource.Success -> {
                    val destination = LoginFragmentDirections.actionLoginFragmentToMainActivity()
                    view.findNavController().navigate(destination)
                    requireActivity().finish()
                }
                is Resource.Error -> {
                    onSnackbar("${it.message}")
                }
                else -> {}
            }
        }
    }

    private fun onSnackbar(message: String) {
        val snackbar = Snackbar.make(binding.root, message, Snackbar.LENGTH_SHORT)
        snackbar.setBackgroundTint(ContextCompat.getColor(requireContext(), R.color.error))
        snackbar.show()
    }

    private val contracts = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
        val data = it.data
        val task = GoogleSignIn.getSignedInAccountFromIntent(data)
        val account = task.result
        viewModel.signInWithGoogle(account)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}