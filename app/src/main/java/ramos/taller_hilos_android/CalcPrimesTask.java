package ramos.taller_hilos_android;

/**
 * Created by dptoredes on 10/08/17.
 */

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.AsyncTask;
import android.widget.TextView;

public class CalcPrimesTask extends AsyncTask<Integer, String, List<Integer>> {

    Activity activity;

    public CalcPrimesTask(Activity mainActivity) {
        activity = mainActivity;
    }

    @Override
    protected List<Integer> doInBackground(Integer... params) {
        int maxNum = params[0];
        List<Integer> primeList = new ArrayList<Integer>();
        for (int i = 2; i <= maxNum ; i++) {
            int maxCalc = (int)Math.sqrt(i);
            boolean isPrime = true;
            for (int j = 2; j <= maxCalc ; j++) {
                if (i % j == 0) {
                    isPrime = false;
                    break;
                }
            }
            if (isPrime) {
                primeList.add(i);
                publishProgress("Numero Primo " + i + " encontrado.");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                }
            }
        }
        return primeList;
    }

    @Override
    protected void onProgressUpdate(String... values) {
        TextView messageView = (TextView) activity.findViewById(R.id.txt_mostrar);
        messageView.setText(values[0]);
        super.onProgressUpdate(values);
    }

    @Override
    protected void onPostExecute(List<Integer> result) {
        TextView messageView = (TextView) activity.findViewById(R.id.txt_mostrar);
        messageView.setText("Se encontraron " + result.size() + " numeros primos.");
        super.onPostExecute(result);
    }


}
