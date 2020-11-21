package net.android.servicestesting.ui.fragments

import android.app.ActivityManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import net.android.servicestesting.RandomNumberService
import net.android.servicestesting.databinding.MainFragmentBinding

class MainFragment : Fragment() {

    lateinit var mainFragment : MainFragmentBinding


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mainFragment = MainFragmentBinding.inflate(inflater, container, false)
        mainFragment.callback = this
        return mainFragment.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        // Variable to hold service class name
        val serviceClass = RandomNumberService::class.java

        // Initialize a new Intent instance
        val intent = Intent(activity, serviceClass)

        // Button to start the service
        mainFragment.buttonStart.setOnClickListener{
            // If the service is not running then start it
            if (!isServiceRunning(serviceClass)) {
                // Start the service
                activity!!.startService(intent)

            } else {
                Toast.makeText(activity, "Service already running.", Toast.LENGTH_SHORT).show()
            }
        }


        // Button to stop the service
        mainFragment.buttonStop.setOnClickListener{
            // If the service is not running then start it
            if (isServiceRunning(serviceClass)) {
                // Stop the service
                activity!!.stopService(intent)
            } else {
                Toast.makeText(activity, "Service already stopped.", Toast.LENGTH_SHORT).show()

            }
        }


        // Get the service status
        mainFragment.buttonStats.setOnClickListener{
            if (isServiceRunning(serviceClass)) {
                Toast.makeText(activity, "Service is running.", Toast.LENGTH_SHORT).show()

            } else {
                Toast.makeText(activity, "Service is stopped.", Toast.LENGTH_SHORT).show()

            }

        }
    }

    // Custom method to determine whether a service is running
    private fun isServiceRunning(serviceClass: Class<*>): Boolean {
        val activityManager = activity!!.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager

        // Loop through the running services
        for (service in activityManager.getRunningServices(Integer.MAX_VALUE)) {
            if (serviceClass.name == service.service.className) {
                // If the service is running then return true
                return true
            }
        }
        return false
    }
}

