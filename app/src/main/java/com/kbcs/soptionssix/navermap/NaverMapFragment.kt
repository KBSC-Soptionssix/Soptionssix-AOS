package com.kbcs.soptionssix.navermap

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.kbcs.soptionssix.R
import com.kbcs.soptionssix.databinding.FragmentNaverMapBinding
import com.naver.maps.geometry.LatLng
import com.naver.maps.map.CameraAnimation
import com.naver.maps.map.CameraUpdate
import com.naver.maps.map.MapView
import com.naver.maps.map.NaverMap
import com.naver.maps.map.OnMapReadyCallback
import com.naver.maps.map.overlay.Marker
import com.naver.maps.map.overlay.OverlayImage

class NaverMapFragment : Fragment(), OnMapReadyCallback {
    private var _mapView: MapView? = null
    private var _binding: FragmentNaverMapBinding? = null
    private var _naverMap: NaverMap? = null

    private val mapView: MapView get() = _mapView!!
    private val naverMap: NaverMap get() = _naverMap!!
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding =
            DataBindingUtil.inflate(layoutInflater, R.layout.fragment_naver_map, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _mapView = binding.naverMapView
        mapView.onCreate(savedInstanceState)
        mapView.getMapAsync(this)
    }

    override fun onMapReady(p0: NaverMap) {
        _naverMap = p0.apply {
            val cameraUpdate = CameraUpdate.scrollTo(LatLng(37.5005, 127.0281))
                .animate(CameraAnimation.Easing)
            minZoom = 17.0
            maxZoom = 19.0
            uiSettings.apply {
                isCompassEnabled = false
                isScaleBarEnabled = false
                isLocationButtonEnabled = false
                isLogoClickEnabled = false
                mapType = NaverMap.MapType.Basic
            }
            moveCamera(cameraUpdate)
        }
        Marker().apply {
            position = LatLng(37.5005, 127.0281)
            map = naverMap
            icon = OverlayImage.fromResource(R.drawable.ic_temp_location_on)
        }
    }

    override fun onStart() {
        super.onStart()
        mapView.onStart()
    }

    override fun onResume() {
        super.onResume()
        mapView.onResume()
    }

    override fun onPause() {
        super.onPause()
        mapView.onPause()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        mapView.onSaveInstanceState(outState)
    }

    override fun onStop() {
        super.onStop()
        mapView.onStop()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        mapView.onDestroy()
        _binding = null
        _mapView = null
        _naverMap = null
    }

    override fun onLowMemory() {
        super.onLowMemory()
        mapView.onLowMemory()
    }
}
