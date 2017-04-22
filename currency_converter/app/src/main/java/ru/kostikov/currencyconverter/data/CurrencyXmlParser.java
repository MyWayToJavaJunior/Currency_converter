package ru.kostikov.currencyconverter.data;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.HashMap;

import ru.kostikov.currencyconverter.R;
import ru.kostikov.currencyconverter.util.Injector;

/**
 * Created by user on 22.04.2017.
 */

public class CurrencyXmlParser {

    private static final String VALUTE    = "Valute";
    private static final String NUM_CODE  = "NumCode";
    private static final String CHAR_CODE = "CharCode";
    private static final String NOMINAL   = "Nominal";
    private static final String NAME      = "Name";
    private static final String VALUE     = "Value";

    /**
     * * Parse Xml with convert data
     * @param parser
     * @param dataMap
     * @return true - parse ok, false - parce not ok
     */
    public static boolean parseConvertDataXml(XmlPullParser parser, HashMap<String, CurrencyData> dataMap){
        int numCode = 0;
        int nominal = 0;
        float value = 0;
        String charCode = "";
        String name     = "";
        String currentTag = "";
        boolean result = true;

        if (parser != null && dataMap != null){
            try {
                while(parser.next() != XmlPullParser.END_DOCUMENT){

                    if (parser.getEventType() == XmlPullParser.START_TAG){
                        currentTag = parser.getName();
                    }


                    if (parser.getEventType() == XmlPullParser.TEXT){
                        if (currentTag.equals(NUM_CODE)){
                            numCode = Integer.valueOf(parser.getText());
                        } else if (currentTag.equals(CHAR_CODE)){
                            charCode = new String (parser.getText().getBytes(parser.getInputEncoding()), "UTF-8");
                        } else if (currentTag.equals(NOMINAL)){
                            nominal = Integer.valueOf(parser.getText());
                        } else if (currentTag.equals(NAME)){
                            name = new String (parser.getText().getBytes(parser.getInputEncoding()), "UTF-8");
                        } else if (currentTag.equals(VALUE)){
                            String str = parser.getText().replace(',', '.');
                            value = Float.valueOf(str);
                        }
                    }

                    if (parser.getEventType() == XmlPullParser.END_TAG
                            && parser.getName().equals(VALUTE)){
                        currentTag = "";
                        dataMap.put(charCode, new CurrencyData(numCode, nominal, value, charCode, name));
                    }

                }
            } catch (XmlPullParserException e) {
                e.printStackTrace();
                result = false;
            } catch (IOException e) {
                e.printStackTrace();
                result = false;
            }

            // Костыль
            dataMap.put("RUS", new CurrencyData(643, 1, 1, "RUB", "Российский рубль"));
        }



        return result;
    }
}
