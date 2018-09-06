package com.baseandroidproject;

import android.text.Html;
import android.text.Spanned;
import android.text.TextUtils;
import android.util.Log;

import java.util.Arrays;

/**
 * Created by vishalguptahmh@gmail.com on 9/6/18.



 Spanned modifiedString = HighLightWord.getInstance()
                .boldText()//to bold text
                .setColor("#292929")//setcolor
                .highLight("started following you")//highlight string
                .toValues("following")//keyword to hightlight
                .build();//to build string

                textView.setText(modifiedString);


 */

public class HighLightWord {
    String TAG = getClass().getSimpleName().toString();
    String startWith = "";
    String endWith = "";
    String mainString;
    String[] valueList;
    boolean highLightNumber = false;


    public static HighLightWord getInstance() {
        return new HighLightWord();
    }

    public HighLightWord startWith(String startWith) {
        this.startWith = startWith;
        return this;
    }


    public HighLightWord setColor(String color) {
        this.startWith = startWith + "<font  color=\"" + color + "\" >";
        this.endWith = "</font>" + endWith;
        return this;
    }

    public HighLightWord boldText() {
        this.startWith = "<b>";
        this.endWith = "</b>";
        return this;
    }

    public HighLightWord endWith(String endWith) {
        this.endWith = endWith;
        return this;
    }

    public HighLightWord highLight(String string) {
        this.mainString = string;
        return this;
    }

    public HighLightWord toValues(String... valueList) {
        this.valueList = valueList;
        return this;
    }

    public HighLightWord highlighNumbers(boolean highlighNumbers) {
        this.highLightNumber = highlighNumbers;
        return this;
    }

    public Spanned build() {
        if (mainString != null && mainString.length() > 0 && valueList != null && valueList.length > 0) {
            String[] stringList = mainString.split(" ");
            for (int i = 0; i < stringList.length; i++) {

                for (int j = 0; j < valueList.length; j++) {

                    if (valueList[j] != null && valueList[j].length() > 0 && stringList[i] != null && stringList[i].length() > 0) {

                        if (stringList[i].toLowerCase().contains(valueList[j].toLowerCase())) {
                            stringList[i] = stringList[i].replaceAll(stringList[i], startWith + stringList[i] + endWith);

                        }

                        if (highLightNumber) {
                            if (TextUtils.isDigitsOnly(stringList[i])) {
                                stringList[i] = stringList[i].replaceAll(stringList[i], startWith + stringList[i] + endWith);
                            }

                        }
                    }

                }
            }
            mainString = Arrays.toString(stringList);
            mainString = mainString.replaceAll(",", "").replaceAll("\\[", "").replaceAll("\\]", "");
            Log.d(TAG, "build: " + mainString);
        }


        return Html.fromHtml(mainString);
    }

}
