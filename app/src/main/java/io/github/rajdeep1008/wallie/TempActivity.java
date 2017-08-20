package io.github.rajdeep1008.wallie;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class TempActivity extends AppCompatActivity {

    String exchangeName = null;
    ExchangeProperties exchange;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle extras = getIntent().getExtras();
        exchangeName = (extras != null) ? extras.getString("exchangeKey") : null;

        Intent intent = new Intent(this, MainActivity.class);
        if(exchangeName != null) {
            exchange = new ExchangeProperties(this, exchangeName);
            if (exchange.supportsOrderbook())
                intent.putExtra("exchange", exchange.getIdentifier());
        }

        startActivity(intent);
    }

    public void onStop()
    {
        super.onStop();
        getIntent().removeExtra("exchangeKey");
    }

}
