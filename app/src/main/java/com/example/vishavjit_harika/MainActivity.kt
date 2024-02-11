package com.example.vishavjit_harika

/**
 * Resources used:
 * -> For Navigation Drawer implementation:
 * 1. https://medium.com/@ezichukwuamarachi/using-the-android-navigation-drawer-in-kotlin-6cf2cdd0e42f
 * 2. https://www.geeksforgeeks.org/navigation-drawer-in-android/
 * -> For GridView Implementation and loading images in GridView:
 * 3. https://github.com/raunakbhojwani/Stress-Meter/tree/master
 * 4. https://developer.android.com/reference/kotlin/android/widget/GridView
 * 5. https://stackoverflow.com/questions/17463134/display-grid-view-with-multiple-images-android
 * 6. https://www.youtube.com/watch?v=cKevqBoV2ws
 * 7. https://developer.android.com/reference/kotlin/android/view/ViewGroup.LayoutParams
 * 8. https://www.w3adda.com/kotlin-android-tutorial/kotlin-gridview
 * -> For going back to the previous activity on the click of the cancel button:
 * 9. https://stackoverflow.com/questions/4038479/android-go-back-to-previous-activity
 * -> How to change the title or hide the supportActionBar:
 * 10. https://stackoverflow.com/questions/36236181/how-to-remove-title-bar-from-the-android-activity
 * -> Alternative for deprecated startActivityForResult:
 * 11. https://tedblob.com/alternative-to-startactivityforresult-deprecated/
 * -> Implementing AnyCharts:
 * 12. https://github.com/AnyChart/AnyChart-Android
 * 13. https://github.com/AnyChart/AnyChart-Android/wiki/Getting-started
 * -> Implementing MPAndroidCharts:
 * 14. https://github.com/PhilJay/MPAndroidChart
 * 15. https://weeklycoding.com/mpandroidchart-documentation/
 * -> Fixing build issues caused by AnyCharts:
 * 16. https://stackoverflow.com/questions/69163511/build-was-configured-to-prefer-settings-repositories-over-project-repositories-b
 * -> For reading, writing and creating new csv file:
 * 17. https://github.com/jarell-santella/StressMeter
 * 18. https://www.baeldung.com/kotlin/csv-files
 * 19. https://stackoverflow.com/questions/51248872/kotlin-android-create-and-share-csv-file
 * 20. https://stackoverflow.com/questions/15711098/trying-to-create-a-file-in-android-open-failed-erofs-read-only-file-system
 * 21. https://github.com/dandar3/android-support-design/issues/5
 * 22. https://www.tutorialkart.com/kotlin/kotlin-check-if-file-exists/#gsc.tab=0
 * -> To get current date and time:
 * 23. https://www.baeldung.com/kotlin/current-date-time
 * -> How to add a border around a textView or Layout:
 * 24. https://www.tutorialspoint.com/how-do-i-put-a-border-around-an-android-textview
 * -> How to display data in a table using recyclerView:
 * 25. https://www.geeksforgeeks.org/how-to-display-data-in-tabular-form-using-android-recyclerview/
 * -> For debugging and queries regarding different functions:
 * 26. ChatGPT (Version 3.5) {https://chat.openai.com/auth/login}
 * -> Audio taken from:
 * 27. https://freesound.org/people/ValentinSosnitskiy/sounds/231341/
 * -> How to play a sound when the app is opened:
 * 28. https://stackoverflow.com/questions/29366403/play-a-sound-in-android-when-i-start-application
 * -> How to get the phone to vibrate when the application is opened:
 * 29. https://stackoverflow.com/questions/47880450/how-to-vibrate-android-device-on-button-click-using-vibrator-effects-using-kotli
 */

import android.content.Context
import android.media.MediaPlayer
import android.os.Build
import android.os.Bundle
import android.os.VibrationEffect
import android.os.Vibrator
import android.os.VibratorManager
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.vishavjit_harika.databinding.ActivityMainBinding
import com.google.android.material.navigation.NavigationView


class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding
    lateinit var musicPlayer: MediaPlayer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.appBarMain.toolbar)

        val drawerLayout: DrawerLayout = binding.drawerLayout
        val navView: NavigationView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_stress_meter, R.id.nav_results
            ), drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        musicPlayer = MediaPlayer.create(this, R.raw.guitar)
        musicPlayer.start()

        vibrate()

    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    private fun vibrate(){
        val vib = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            val vibratorManager =
                getSystemService(Context.VIBRATOR_MANAGER_SERVICE) as VibratorManager
            vibratorManager.defaultVibrator
        } else {
            @Suppress("DEPRECATION")
            getSystemService(VIBRATOR_SERVICE) as Vibrator
        }

        vib.vibrate(VibrationEffect.createOneShot(300, VibrationEffect.DEFAULT_AMPLITUDE) )
    }
}