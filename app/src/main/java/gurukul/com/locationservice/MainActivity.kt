package gurukul.com.locationservice

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var status = ContextCompat.checkSelfPermission(this@MainActivity,Manifest.permission.ACCESS_FINE_LOCATION)

        if(status == PackageManager.PERMISSION_GRANTED){
            accessLocation()
        }else{
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.ACCESS_COARSE_LOCATION),12)
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if(grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[1] == PackageManager.PERMISSION_GRANTED){
            accessLocation()
        }else{
            System.exit(0)
        }
    }

    @SuppressLint("MissingPermission")
    fun accessLocation(){
        var lManager: LocationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
        lManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER,1000L,1F, object : LocationListener {
            override fun onLocationChanged(location: Location?) {
                tvLat.text = "Lattitude: "+location?.latitude.toString()
                textView.text = "Longitude: "+location?.longitude.toString()
            }

            override fun onProviderDisabled(provider: String?) {

            }

            override fun onProviderEnabled(provider: String?) {

            }

            override fun onStatusChanged(provider: String?, status: Int, extras: Bundle?) {

            }
        })
    }
}
