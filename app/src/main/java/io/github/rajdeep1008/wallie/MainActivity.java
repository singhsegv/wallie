package io.github.rajdeep1008.wallie;

import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import org.knowm.xchange.Exchange;
import org.knowm.xchange.ExchangeFactory;
import org.knowm.xchange.ExchangeSpecification;
import org.knowm.xchange.bitbay.Bitbay;
import org.knowm.xchange.bitbay.BitbayExchange;
import org.knowm.xchange.bitstamp.BitstampExchange;
import org.knowm.xchange.currency.CurrencyPair;
import org.knowm.xchange.dto.account.AccountInfo;
import org.knowm.xchange.dto.marketdata.Ticker;
import org.knowm.xchange.service.account.AccountService;
import org.knowm.xchange.service.marketdata.MarketDataService;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setElevation(0f);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

        StrictMode.setThreadPolicy(policy);

        TextView tv = (TextView) findViewById(R.id.tv);
        TextView tv2 = (TextView) findViewById(R.id.tv2);
        Exchange bitstamp = ExchangeFactory.INSTANCE.createExchange(BitstampExchange.class.getName());
        Exchange bitbay = ExchangeFactory.INSTANCE.createExchange(BitbayExchange.class.getName());

        MarketDataService marketDataService = bitstamp.getMarketDataService();
        MarketDataService marketDataService2 = bitbay.getMarketDataService();

        Ticker ticker = null;
        Ticker ticker2 = null;
        try {
            ticker = marketDataService.getTicker(CurrencyPair.BTC_USD);
            ticker2 = marketDataService2.getTicker(CurrencyPair.BTC_EUR);
        } catch (IOException e) {
            e.printStackTrace();
        }

 //      System.out.println(ticker.toString());
        tv.setText(ticker.toString());
        tv2.setText(ticker2.toString());

    }
}
