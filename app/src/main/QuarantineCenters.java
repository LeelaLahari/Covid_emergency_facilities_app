package com.example.helpinghands_is;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.esri.arcgisruntime.ArcGISRuntimeEnvironment;
import com.esri.arcgisruntime.geometry.Point;
import com.esri.arcgisruntime.mapping.ArcGISMap;
import com.esri.arcgisruntime.mapping.Basemap;
import com.esri.arcgisruntime.mapping.view.GraphicsOverlay;
import com.esri.arcgisruntime.mapping.view.MapView;
import com.esri.arcgisruntime.symbology.SimpleMarkerSymbol;
import com.esri.arcgisruntime.symbology.SimpleRenderer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class QuarantineCenters extends AppCompatActivity {

    private MapView mapView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quarantine_centers);
        ArcGISRuntimeEnvironment.setApiKey("AAPK91cc1afb92224c2b8ce683ac1f482db5YUiu1kFckvZabO4T8G7PwgF7JgH52gSISyoOt90pnYPzPMh9zGAqfKFOpsZvQF5x");
        // Initialize the map view
        mapView = findViewById(R.id.Quarantine);

        // Load a basemap
        ArcGISMap map = new ArcGISMap(Basemap.createStreetsVector());

        // Set the map to the map view
        mapView.setMap(map);

        // Call a method to read CSV and add markers
        addMarkersFromCSV();
    }

    private void addMarkersFromCSV() {
        // TODO: Replace "your_csv_file.csv" with the actual CSV file in your project
        InputStream inputStream = getResources().openRawResource(R.raw.quar_data);
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

        try {
            // Create a graphics overlay for the markers
            GraphicsOverlay graphicsOverlay = new GraphicsOverlay();
            mapView.getGraphicsOverlays().add(graphicsOverlay);

            // Create a simple marker symbol
            SimpleMarkerSymbol markerSymbol = new SimpleMarkerSymbol(SimpleMarkerSymbol.Style.CIRCLE, 0xFFFF0000, 10);

            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                double latitude = Double.parseDouble(data[0]);
                double longitude = Double.parseDouble(data[1]);

                // Create a point geometry for the marker
                Point point = new Point(longitude, latitude);

                // Create a graphic with the point geometry and marker symbol
                graphicsOverlay.getGraphics().add(new com.esri.arcgisruntime.mapping.view.Graphic(point, markerSymbol));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        mapView.pause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mapView.resume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mapView.dispose();
    }
}