package io.github.rajdeep1008.wallie;

import android.content.Context;
import android.support.v4.util.Pair;

import org.knowm.xchange.Exchange;
import org.knowm.xchange.ExchangeFactory;
import org.knowm.xchange.ExchangeSpecification;
import org.knowm.xchange.service.marketdata.MarketDataService;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rajdeep1008 on 20/8/17.
 */

public class ExchangeUtils {

    public static MarketDataService getMarketData(ExchangeProperties exchange)
    {
        ExchangeSpecification exchangeSpec = new ExchangeSpecification(exchange.getClassName());
        exchangeSpec.setShouldLoadRemoteMetaData(false);
        Exchange exchangeInstance = ExchangeFactory.INSTANCE.createExchange(exchangeSpec);

        return exchangeInstance.getMarketDataService();
    }

    public static Pair<List<String>, List<String>> getAllDropdownItems(Context context)
    {
        return getDropdownItems(context, 0, true);
    }

    public static Pair<List<String>, List<String>> getDropdownItems(Context context, int serviceType)
    {
        return getDropdownItems(context, serviceType, false);
    }

    public static Pair<List<String>, List<String>> getDropdownItems(Context context, int serviceType, boolean includeAll)
    {
        String[] exchangeIds = context.getResources().getStringArray(R.array.exchangeId);
        List<String> dropdown = new ArrayList<>();
        List<String> dropdownIds = new ArrayList<>();

        for (String exchangeId : exchangeIds) {
            ExchangeProperties exchangeMetadata = new ExchangeProperties(context, exchangeId);

            if (includeAll || exchangeMetadata.supportsServiceType(serviceType)) {
                dropdown.add(exchangeMetadata.getExchangeName()); // Add exchange name
                dropdownIds.add(exchangeMetadata.getIdentifier());
            }
        }

        return new Pair(dropdown, dropdownIds);
    }
}
