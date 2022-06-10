package id.anantyan.moviesapp.ui.profile

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import coil.load
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.AndroidEntryPoint
import id.anantyan.moviesapp.R
import id.anantyan.moviesapp.databinding.ActivityProfileBinding
import id.anantyan.moviesapp.ui.auth.AuthActivity
import id.anantyan.moviesapp.utils.Resource
import id.anantyan.utils.dividerVertical
import javax.inject.Inject

@AndroidEntryPoint
class ProfileActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProfileBinding
    private val viewModel: ProfileViewModel by viewModels()
    @Inject lateinit var adapterProfile: ProfileAdapter
    @Inject lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        supportActionBar?.title = "Profile"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        if (auth.currentUser == null) {
            val intent = Intent(this, AuthActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP
            finish()
            startActivity(intent)
        }

        bindObserver()
        bindView()
    }

    private fun bindObserver() {
        viewModel.showAccount.observe(this) {
            when(it) {
                is Resource.Success -> {
                    it.data?.let { list ->
                        adapterProfile.submitList(list)
                        binding.rvProfile.adapter = adapterProfile
                    }
                }
                is Resource.Error -> {
                    onSnackbar("${it.message}")
                }
                else -> {}
            }
        }
        viewModel.signOut.observe(this) {
            when(it) {
                is Resource.Success -> {
                    val intent = Intent(this, AuthActivity::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP
                    finish()
                    startActivity(intent)
                }
                is Resource.Error -> {
                    onSnackbar("${it.message}")
                }
                else -> {}
            }
        }
        viewModel.showAccount()
    }

    private fun onSnackbar(message: String) {
        val snackbar = Snackbar.make(binding.root, message, Snackbar.LENGTH_SHORT)
        snackbar.setBackgroundTint(ContextCompat.getColor(this, R.color.error))
        snackbar.show()
    }

    private fun bindView() {
        binding.imgView.load(auth.currentUser?.photoUrl) {

        }
        binding.rvProfile.setHasFixedSize(true)
        binding.rvProfile.layoutManager = LinearLayoutManager(this)
        binding.rvProfile.itemAnimator = DefaultItemAnimator()
        binding.rvProfile.addItemDecoration(dividerVertical(this, 32, 32))
        binding.rvProfile.isNestedScrollingEnabled = false
    }

    private fun dialogLogout(listener: (dialog: AlertDialog) -> Unit) {
        val builder = MaterialAlertDialogBuilder(this)
        builder.setCancelable(false)
        builder.setTitle("Attention!")
        builder.setMessage("Apakah anda ingin keluar dari akun ini?")
        builder.setPositiveButton("Iya", null)
        builder.setNegativeButton("Tidak") { dialog, _ ->
            dialog.dismiss()
        }
        val dialog = builder.show()
        val btnPositif = dialog.getButton(AlertDialog.BUTTON_POSITIVE)
        btnPositif.setOnClickListener {
            listener.invoke(dialog)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.second_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.logoutAppBar -> {
                dialogLogout {
                    viewModel.signOut()
                    it.dismiss()
                }
            }
        }
        return super.onOptionsItemSelected(item)
    }
}