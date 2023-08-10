package com.ksusha.e_commerceapi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.IdRes
import androidx.lifecycle.asLiveData
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import com.ksusha.e_commerceapi.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import androidx.navigation.ui.setupActionBarWithNavController
import com.airbnb.epoxy.Carousel
import com.ksusha.e_commerceapi.redux.ApplicationState
import com.ksusha.e_commerceapi.redux.Store
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    @Inject
    lateinit var store: Store<ApplicationState>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val appBarConfiguration = AppBarConfiguration(
            topLevelDestinationIds = setOf(
                R.id.productsListFragment,
                R.id.profileFragment,
                R.id.cartFragment
            )
        )
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.navHostFragment) as NavHostFragment
        val navController = navHostFragment.navController
        setupActionBarWithNavController(navController, appBarConfiguration)

        NavigationUI.setupWithNavController(binding.bottomNavigationView, navController)

        Carousel.setDefaultGlobalSnapHelperFactory(null)

        store.stateFlow.map {
            it.inCartProductsId.size
        }.distinctUntilChanged().asLiveData().observe(this) { numberOfProductsInCart ->
            binding.bottomNavigationView.getOrCreateBadge(R.id.cartFragment).apply {
                number = numberOfProductsInCart
                isVisible = numberOfProductsInCart > 0
            }
        }
    }

    fun navigateToTab(@IdRes destinationId: Int) {
        binding.bottomNavigationView.selectedItemId = destinationId
    }

}