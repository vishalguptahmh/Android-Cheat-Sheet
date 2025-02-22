# Cheat sheet for Android ||  Android Cheat Sheet 


# Run Android studio faster 

add below lines in gradle.properties 

```groovy
org.gradle.configureondemand=true # this will run only main module on you can check timing before and after by running in cmd ./gradlew assembleDebug --profile
org.gradle.parallel=true # this will be usefull when you have multiple modules in project. 

org.gradle.jvmargs=-Xmx8192m -XX:MaxMetaspaceSize=512m -XX:+HeapDumpOnOutOfMemoryError -Dfile.encoding=UTF-8 # increasing heap size will make build faster

```


# Some Important Links

####  [Android Data Binding Tutorial](https://github.com/vishalguptahmh/Android-Data-Binding-Tutorial)
#### [Android code examples](https://github.com/IanDarwin/Android-Cookbook-Examples)
#### [Interview Questions](https://github.com/amitshekhariitbhu/android-interview-questions)
#### [HighLighKeyWord](https://github.com/vishalguptahmh/Android-Cheat-Sheet/wiki) 

<img src="https://github.com/vishalguptahmh/Android-Cheat-Sheet/blob/master/Images/hightlightword.jpg" width="25%">.

#### [Image Zoom Android](https://github.com/vishalguptahmh/Android-Cheat-Sheet/wiki)

<img src="https://github.com/vishalguptahmh/Android-Cheat-Sheet/raw/master/Images/imagepinchzoom.gif" width="20%">.

#### [Android Dagger 2 Tutorial](https://github.com/vishalguptahmh/Android-Dagger2-Tutorial)

#### Handle your phone from your pc using [scrcpy](https://github.com/Genymobile/scrcpy),it will help you to demostrate in video conferencing or you can use [vysor](https://www.vysor.io) or [Apower](https://www.apowersoft.com/phone-mirror) , it is paid but you can use some features in trial version.
<img src="https://github.com/vishalguptahmh/Android-Cheat-Sheet/raw/master/Images/scrcpy%20or%20visor.png" width="55%">.

#### [BIOMETRIC AND FACE AUTHENTICATION](https://github.com/vishalguptahmh/Android-device-authentication-biometric-and-face)
<img src="https://github.com/vishalguptahmh/Android-device-authentication-biometric-and-face/blob/master/viedo.gif" width="20%">.
<img src="https://github.com/vishalguptahmh/Android-device-authentication-biometric-and-face/blob/master/face.gif" width="17%">.


# Some Important Code Scripts

#### In Intent there must be a app to handle this rquest
```java  
if(intent.resolveActivity(getActivity().getPackageManager())!=null){
       startActivity(intent);
    }
    
```

#### For complete scroll of card in recycler view

```java 
    SnapHelper snapHelper = new StartSnapHelper();
    snapHelper.attachToRecyclerView(binding.recyclerView);
```


#### In Rotation Data remain Same

```xml 
     android:configChanges="orientation|keyboardHidden|screenSize"
```



#### For animation in visible and invisible layout

> apply to those layout for which layout length gets changes
```java 

    android:animateLayoutChanges="true"
```

#### For touch effect
```java
           android:foreground="?android:attr/selectableItemBackground"
```

#### To create Edittext mulitiline put this in edittext
```java
android:inputType="textMultiLine"
//put this in mainfeast > > activity

```

#### Add activity to backstack
It will start with Notification Activity and when you press back then MainActivity will open
```java
       Intent intentA=new Intent(context,MainActivity.class);
       Intent intentB=new Intent(context,NotificationActivity.class);
       
       TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
       stackBuilder.addNextIntent(intentA);
       stackBuilder.addNextIntent(intentB);
       context.startActivities(stackBuilder.getIntents());
```

#### To Remove Previous Intents

```java
 Intent intent = new Intent(getActivity(), DashboardActivity.class);
intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP  | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
startActivity(intent);
getActivity().finish();
                                        
 ```

#### Country code From Local Android
```java
String locale = context.getResources().getConfiguration().locale.getCountry();
```

#### Delay Timer
```java
    void delayFragmentClosing(){

        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                /* put code that will implemented after 2 sec */
              closeFragment();
            }
        }, 2000);
    }

```

#### Rs sign 
```xml
<string name="Rs">\u20B9</string>

```


#### Adding Multiple Name In A Model Using SerializableName


```java
//here we can use _id and driverId
   @SerializedName(value = "_id" , alternate = {"driverId"})
    private String driverId;
    
```
#### Encrypt and Decrypt Strings
```java
    public static String encrypt(String input) {
        // This is base64 encoding, which is not an encryption
        return Base64.encodeToString(input.getBytes(), Base64.DEFAULT);
    }

    public static String decrypt(String input) {
        return new String(Base64.decode(input, Base64.DEFAULT));
    }


```

#### Device Width

```java
    public int  displayWidth(){
        DisplayMetrics metrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(metrics);
        int width = metrics.widthPixels;
        int dp = Math.round(width / ((float)metrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT));
        return dp;
    }
    

```

#### convert px to dp and dp to px  
```java

public static float convertPixelsToDp(float px, Context context){
    Resources resources = context.getResources();
    DisplayMetrics metrics = resources.getDisplayMetrics();
    float dp = px / ((float)metrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT);
    return dp;
}

public static float convertDpToPixel(float dp, Context context){
    Resources resources = context.getResources();
    DisplayMetrics metrics = resources.getDisplayMetrics();
    float px = dp * ((float)metrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT);
    return px;
}

```


#### Check screen is off or on 
```java
private BroadcastReceiver screenStateReceiver = new BroadcastReceiver() {
   @Override
   public void onReceive(Context context, Intent intent) {
       if (intent.getAction().equals(Intent.ACTION_SCREEN_ON)) {
           Log.i("Websocket", "Screen ON");
           openConnection();
       } else if (intent.getAction().equals(Intent.ACTION_SCREEN_OFF)) {
           Log.i("Websocket", "Screen OFF");
           closeConnection();
       }
   }
};


```
#### For expandable view

```xml  
     <at.blogc.android.views.ExpandableTextView
                       android:id="@+id/comment"
                       android:layout_marginStart="@dimen/_4sdp"
                       android:layout_width="match_parent"
                       android:layout_height="wrap_content"
                       android:layout_centerVertical="true"
                       android:layout_toEndOf="@id/iconAttachmentFile"
                       android:clickable="true"
                       android:ellipsize="end"
                       android:maxLines="2"
                       android:onClick="@{model::resizeText}"
                       android:text="@{model.description}"
                       android:textSize="@dimen/_10sdp"
                       app:animation_duration="600"
    
    
                       />

```
```java

//now from code
    binding.comment.toggle();//to expand and collapse

```


#### on implements Obervable

```java  
       // FROM BASE OBSERVABLE
        @Ignore
        private transient PropertyChangeRegistry mCallbacks;
        @Override
        public void addOnPropertyChangedCallback(OnPropertyChangedCallback onPropertyChangedCallback) {
           if (mCallbacks == null) {
               mCallbacks = new PropertyChangeRegistry();
           }
           mCallbacks.add(onPropertyChangedCallback);
        }
    
        @Override
        public void removeOnPropertyChangedCallback(OnPropertyChangedCallback onPropertyChangedCallback) {
          if (mCallbacks != null) {
            mCallbacks.remove(onPropertyChangedCallback);
          }
    
       }
    
       public void notifyPropertyChanged(int fieldId) {
           if (mCallbacks != null) {
               mCallbacks.notifyCallbacks(this, fieldId, null);
           }
       }

```


#### Change the color of progressbar from xml

```xml
    <ProgressBar
                   android:indeterminate="true"
                   android:indeterminateTint="@color/white"
                   android:elevation="@dimen/_8sdp"
                   android:id="@+id/saveProgressBar"
                   android:layout_width="@dimen/_24sdp"
                   android:layout_height="@dimen/_24sdp"
                   android:layout_alignParentRight="true"
                   android:layout_marginEnd="@dimen/_12sdp"
                   android:layout_below="@+id/coverImage"
                   />

```



#### Gender spinner Selector

```java
    setupPopupMenu(constantUnit.getShow(),view).setOnMenuItemClickListener(item -> {
       view.setText(item.getTitle());
       userSpecificDetails.setGender(constantUnit.getServer().get(constantUnit.getServer().indexOf(item.getTitle().toString())));
       return false;
    });


    PopupMenu setupPopupMenu(List<String> array , View view ){
       PopupMenu popup = new PopupMenu(getContext(), view);
       for (String s : array)
       {
           popup.getMenu().add(s);
       }
       popup.show();
       return popup;
    }
```
```xml
    <android.support.design.widget.TextInputLayout
       android:id="@+id/profile_gender_container"
       android:layout_width="match_parent"
       android:layout_height="wrap_content"
       android:layout_marginEnd="@dimen/_16sdp"
       android:hint="Gender"                                            
       android:layout_marginStart="@dimen/_16sdp"
       foo:hintTextAppearance="@style/MyHintLayout">

       <EditText
           android:id="@+id/profile_gender"
           android:layout_width="match_parent"
           android:layout_height="wrap_content"
           android:backgroundTint="@color/grey_patient_background"
           android:editable="false"
           android:ellipsize="end"
           android:enabled="@{editor.makeEditable ? true : false}"
           android:focusable="false"
           android:focusableInTouchMode="false"
           
           android:inputType="textAutoComplete|textCapSentences"
           android:longClickable="false"
           android:text="@{model.gender}"
           android:textSize="@dimen/_12sdp" />
    </android.support.design.widget.TextInputLayout>

```

#### Spinner adapter 

```java
    /* --  spinner Department   -- */
    public void spinerDepartment(){

        List<String> list = new ArrayList<String>();
        list.add("list 4");
        list.add("list 5");
        list.add("list 6");

        ArrayAdapter<String> userTypeAdapter;
        
        userTypeAdapter= new ArrayAdapter<String>(getActivity(),
                R.layout.spinner_item, list);
        userTypeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.spinnerDepartment.setAdapter(userTypeAdapter);



        binding.spinnerDepartment.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                String item=adapterView.getItemAtPosition(i).toString();
                showSnackBar(item);

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


    }
```
```xml


       <android.support.v7.widget.AppCompatSpinner
                    android:id="@+id/spinnerDepartment"
                    android:layout_below="@id/txtDepartment"
                    android:layout_width="match_parent"
                    android:layout_marginTop="@dimen/_8sdp"
                    android:layout_weight="1"
                    android:layout_toEndOf="@id/UsertypeCenterPoint"
                    android:layout_height="wrap_content"
                    >
                </android.support.v7.widget.AppCompatSpinner>

```

#### Date

```java
    import android.text.format.DateFormat;
    String dayOfTheWeek = (String) DateFormat.format("EEEE EEE", date); // Thursday Thur
    String day = (String) DateFormat.format("dd", date); // 20
    String monthString = (String) DateFormat.format("MMM", date); // Jun 
    String monthNumber = (String) DateFormat.format("MM", date); // 06 
    String year = (String) DateFormat.format("yyyy", date); // 2013
    String time = (String) DateFormat.format(""h:mm a", date); // 12:08 PM
    

```

### Date formating according to today , next week , month like instagram

```java
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

  public static String getDateDifference(Calendar calendar) {
        Date d = calendar.getTime();
        Calendar lastMonth = Calendar.getInstance(Locale.getDefault());
        Calendar lastWeek = Calendar.getInstance(Locale.getDefault());
        Calendar recent = Calendar.getInstance(Locale.getDefault());
        lastMonth.add(Calendar.DAY_OF_MONTH, -(Calendar.DAY_OF_MONTH));
        lastWeek.add(Calendar.DAY_OF_MONTH, -7);
        recent.add(Calendar.DAY_OF_MONTH, -1);
        if (calendar.before(lastMonth)) {
            return new SimpleDateFormat("MMMM yyyy").format(d);
        } else if (calendar.after(lastMonth) && calendar.before(lastWeek)) {
            return "Last Month";
        } else if (calendar.after(lastWeek) && calendar.before(recent)) {
            return "Last Week";
        } else {
            return "Today";
        }
    }
    
```

#### Broadcast


```java
//to receview broadcast

    private void mLocalBroadcastUnregister() {
        LocalBroadcastManager.getInstance(getActivity()).unregisterReceiver(mMessageReciver);
    }

    private void mLocalBroadcastRegister() {
        LocalBroadcastManager.getInstance(getActivity()).registerReceiver(mMessageReciver, new IntentFilter("FLAG_NEW_REQUEST"));
        LocalBroadcastManager.getInstance(getActivity()).registerReceiver(mMessageReciver, new IntentFilter("FLAG_CUSTOMER_CANCELLED_REQUEST"));
    }

    private BroadcastReceiver mMessageReciver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {

            if(intent.getStringExtra("FLAG_CUSTOMER_CANCELLED_REQUEST")!=null){
              close();
            }

        }
    };
    
    //To send broad cast
    
     Intent intent = new Intent();
                        intent.putExtra("FLAG_NEW_REQUEST",FLAG_NEW_REQUEST);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
    
```


#### Add Event to Google Calender

```java
    public void addEvent( String title, String description, long startTime,
                         long endTime) {
       try {
           ContentResolver cr = getActivity().getContentResolver();
           ContentValues values = new ContentValues();
           values.put(CalendarContract.Events.DTSTART, startTime);
           values.put(CalendarContract.Events.DTEND, endTime);
           values.put(CalendarContract.Events.TITLE, title);
           values.put(CalendarContract.Events.DESCRIPTION, description);
           values.put(CalendarContract.Events.CALENDAR_ID, 1);
           values.put(CalendarContract.Events.HAS_ALARM,1);// 0 for false, 1 for true
           values.put(CalendarContract.Events.EVENT_TIMEZONE, Calendar.getInstance().getTimeZone().getID());
           if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.WRITE_CALENDAR) != PackageManager.PERMISSION_GRANTED) {
               // TODO: Consider calling
               //    ActivityCompat#requestPermissions
               // here to request the missing permissions, and then overriding
               //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
               //                                          int[] grantResults)
               // to handle the case where the user grants the permission. See the documentation
               // for ActivityCompat#requestPermissions for more details.
               return;
           }

           Uri uri = cr.insert(CalendarContract.Events.CONTENT_URI, values);
           long eventID = Long.parseLong(uri.getLastPathSegment());
           Log.i("scheduleFragment", "event id: " + eventID);
           showSnackBar("Event is Added to Calender");
       } catch (NumberFormatException e) {
           e.printStackTrace();
       }
    }
    

```





#### Alert Dialog Box

```java
    new AlertDialog.Builder(getActivity(), R.style.AlertDialogTheme)
           .setTitle("Sync Events To Calender ?")
           .setMessage("Your current Date events are added to calendar")
           .setPositiveButton("AGREE", new DialogInterface.OnClickListener() {
               @Override
               public void onClick(DialogInterface dialogInterface, int ii) {
                   addEventToCalender();
               }
           })
           .setNegativeButton("DISAGREE", new DialogInterface.OnClickListener() {
               @Override
               public void onClick(DialogInterface dialogInterface, int ii) {
                   dialogInterface.dismiss();
               }
           })
           .show();

```


#### Adapter to convert object to string

```java
    public static class CustomAdapter implements TypeAdapterFactory {
       @Override
       public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> type) {
           if (type.getRawType() != Kid.class) return null;
           
           TypeAdapter<Kid> defaultAdapter = (TypeAdapter<Kid>) gson.getDelegateAdapter(this, type);
           return (TypeAdapter<T>) new KidAdapter(defaultAdapter);
       }
       
       public class KidAdapter extends TypeAdapter<Kid> {
       
           protected TypeAdapter<Kid> defaultAdapter;
           
           public KidAdapter(TypeAdapter<Kid> defaultAdapter) {
               this.defaultAdapter = defaultAdapter;
           }
           
           @Override
           public void write(JsonWriter out, Kid value) throws IOException {
               defaultAdapter.write(out, value);
           }
           
           @Override
           public Kid read(JsonReader in) throws IOException {
           /*
           This is the critical part. So if the value is a string,
           Skip it (no exception) and return null.
           */
               if (in.peek() == JsonToken.STRING) {
                   // in.skipValue();
                   
                   Kid model = new Kid();
                   return model;
               }
               return defaultAdapter.read(in);
           }
       }
    }


```


#### DateAgo Function
```java
    public static String getDayAgoTime(String yyy_dd_MMTO_HH_MM_Z)
    {
       try {
           DateFormat utcFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
           utcFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
           Date date = utcFormat.parse(yyy_dd_MMTO_HH_MM_Z);
       long delta=date.getTime();
       long difference=0;
       Long mDate = java.lang.System.currentTimeMillis();
       
       if(mDate > delta)
       {
           difference= mDate - delta;
           final long seconds = difference/1000;
           final long minutes = seconds/60;
           final long hours = minutes/60;
           final long days = hours/24;
           final long months = days/31;
           final long years = days/365;
           
           if (seconds < 0)
           {
               return "not yet";
           }
           else if (seconds < 60)
           {
               return seconds == 1 ? "one second ago" : seconds + " seconds ago";
           }
           else if (seconds < 120)
           {
               return "a minute ago";
           }
           else if (seconds < 2700) // 45 * 60
           {
               return minutes + " minutes ago";
           }
           else if (seconds < 5400) // 90 * 60
           {
               return "an hour ago";
           }
           else if (seconds < 86400) // 24 * 60 * 60
           {
               return hours + " hours ago";
           }
           else if (seconds < 172800) // 48 * 60 * 60
           {
               return "yesterday";
           }
           else if (seconds < 2592000) // 30 * 24 * 60 * 60
           {
               return days + " days ago";
           }
           else if (seconds < 31104000) // 12 * 30 * 24 * 60 * 60
           {
               return months <= 1 ? "one month ago" : days + " months ago";
           }
           else
           {
               return years <= 1 ? "one year ago" : years + " years ago";
           }
       }
       }
       catch (Exception e){
           e.printStackTrace();
       }
       return null;
    }

```






#### EditText
```xml
    <android.support.design.widget.TextInputLayout
       android:layout_width="match_parent"
       android:layout_height="wrap_content"
       android:layout_marginEnd="@dimen/_16sdp"
       android:layout_marginStart="@dimen/_16sdp"
       android:layout_marginTop="@dimen/_8sdp"
       android:background="@android:color/transparent"
       android:hint="hint"//it is nescessary in os 8(oreo)
       android:clickable="true">
       
       <EditText
           android:lines="1"
           android:layout_width="match_parent"
           android:layout_height="wrap_content"
           android:layout_marginLeft="@dimen/_4sdp"
           android:layout_marginRight="@dimen/_4sdp"
           android:backgroundTint="@color/appBackgroundColor"
           android:ellipsize="end"
           android:inputType="textAutoComplete|textCapSentences"
           android:maxLength="50"
           android:text=""
           android:textSize="@dimen/_10sdp" />
           
    </android.support.design.widget.TextInputLayout>

```

#### Make dialogFragment full page

```xml
setStyle(DialogFragment.STYLE_NORMAL, R.style.MY_DIALOG);
<style name="MY.DIALOG" parent="android:Theme" >
   <item name="android:windowNoTitle">true</item>
   <item name="android:windowFullscreen">true</item>
   <item name="android:windowIsFloating">false</item>
</style>

```
```java
@Override
public void onStart() {
   super.onStart();
   Dialog d = getDialog();
   if (d!=null){
       int width = ViewGroup.LayoutParams.MATCH_PARENT;
       int height = ViewGroup.LayoutParams.MATCH_PARENT;
       d.getWindow().setLayout(width, height);
   }
}


```

#### Animation
```java
public class dashboard {

    Animation startRotateAnimation;

    public create view() {

        startRotateAnimation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.android_rotate_animation);

        startAnimation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.blinking_animation);

        onclick - > {
            binding.containerRecordingSign.setVisibility(View.VISIBLE);
            binding.containerRecordingSign.setAnimation(startAnimation);
            startAnimation.start();
        }

        dismissanimation {

            binding.containerRecordingSign.setVisibility(View.GONE);
            binding.containerRecordingSign.clearAnimation();
            //or
            binding.containerRecordingSign.setVisibility(View.GONE);
            binding.containerRecordingSign.clearAnimation();
            startAnimation.cancel();
            startAnimation.reset();
        }
    }
}
```

#### call Intent
```java

  binding.customerPhone.setOnClickListener(v->{

            if(PermissionUtils.isPhonePermissionGranted(getActivity())){
                Log.d(TAG, "callCustomer: ");
//            Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + "Your Phone_number"));
            Intent intent = new Intent(Intent.ACTION_CALL, Uri.fromParts("tel" , appData.getCheckOnGoingRide().getCustomerId().getPhone(),null));

                if(intent.resolveActivity(getActivity().getPackageManager())!=null){
                    startActivity(intent);
                }


            }

        });
        
```


###  send ssl certificate with retrofit

> #### Error CertPathValidatorException : Trust anchor for certificate path not found - Retrofit Android
> with link https://gist.github.com/nowke/75037c42171d9ea5ce87a49a982c4c39 or download [From here.](https://github.com/vishalguptahmh/AndroidCheatSheet/blob/master/self%20signing%20client%20builder%20certificate%20(Ok%20http%20ssl%20client%20certificate " Self Sigining Certificate ")




#### Text Color Percentage
![screenshot](https://github.com/vishalguptahmh/AndroidCheatSheet/blob/master/Screen%20Shot%202017-10-23%20at%2010.15.26%20AM.png)
