package com.example.nasser.weather;
import android.app.Activity;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ListView;
import org.json.JSONArray;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.sql.Date;
import java.text.SimpleDateFormat;
import javax.net.ssl.HttpsURLConnection;

public class DownloadJsonTask extends AsyncTask<String, Void, String> {

    TemperatureValues temperatureValues;
    Temperature temperature;
    private Activity mContext;
    private ListView mListView;

    public DownloadJsonTask(ListView listView, Activity context) {
        mContext = context;
        mListView = listView;
        temperatureValues = TemperatureValues.getInstance();
    }

    @Override
    protected String doInBackground(String... urls) {
        InputStream stream = null;
        HttpsURLConnection connection = null;
        String result = null;
        try{
            URL url = new URL(urls[0]);
            connection = (HttpsURLConnection) url.openConnection();
            connection.setReadTimeout(3000);
            connection.setConnectTimeout(3000);
            connection.setRequestMethod("GET");
            connection.setDoInput(true);
            connection.connect();
            int responseCode = connection.getResponseCode();
            if(responseCode != HttpsURLConnection.HTTP_OK){
                throw new IOException("HTTP error connection" + responseCode);
            }
            stream = connection.getInputStream();
            if(stream != null){
                //Claa readStroeam to convert the result into String
                result = readStream(stream);
            }

        }catch(Exception e){
            e.printStackTrace();
        }
        //The returned value are passed to onPostExecute function
        return result;
    }

    @Override
    public void onPostExecute(String result) {
        jsonDeserializer(result);
        CityTemperature networkFragment = new CityTemperature();
        //Make sure we have the fragment's context
        if (mContext == null) {
            Log.e("JSONTask: ", "Context is null");
        } else {
            //Bind data with the CustomBaseAdapter class in the NetworkFragment
            CityTemperature.CustomBaseAdapter adapter = networkFragment.new CustomBaseAdapter(mContext);
            mListView.setAdapter(adapter);
            //The rest of the function just used to make sure we have the right data, it doesn't apear to the user
            for (int i = 0; i < temperatureValues.getTemperature().size(); i += 8) {
                Log.i("TemperValu ", Double.toString(temperatureValues.getTemperature().get(i).getValue()));
                Log.i("TemperDate ", convertDateToString(temperatureValues.getTemperature().get(i).getDate()));

                Log.i("onPost: ", "Done");
            }

        }
    }
    public void jsonDeserializer(String json_string){

        try {
            JSONObject jsonResponse = new JSONObject(json_string);
            JSONArray temperValues = jsonResponse.getJSONArray("list");

            for(int i = 0;i < 40; i += 8){
                JSONObject index = temperValues.getJSONObject(i);
                JSONObject main = index.getJSONObject("main");
                double value = main.getDouble("temp");
                String dateString = index.getString("dt_txt");
                Date dateSQL = convertStringToDate(dateString);
                temperature = new Temperature(dateSQL, value);
                temperatureValues.getTemperature().add(temperature);
                temperature = null;
            }
        }catch (Exception e){
            e.printStackTrace();
        }


    }

    //Convert the string from JSON string into Date
    public Date convertStringToDate(String dateString){
        java.util.Date date;
        java.sql.Date sqlDate;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try{
            date = dateFormat.parse(dateString);
            sqlDate = new java.sql.Date(date.getTime());
            return sqlDate;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }

    }

    public String convertDateToString(Date date){
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = df.format(date);
        return dateString;
    }

    public String readStream(InputStream stream) throws IOException, UnsupportedEncodingException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
        String readLine;
        StringBuffer buffer = new StringBuffer();
        while(((readLine = reader.readLine()) != null)){
            buffer.append(readLine);
        }
        return buffer.toString();
    }




}
