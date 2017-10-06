f (${PACKAGE_NAME} != “”)package ${PACKAGE_NAME};#end

import android.databinding.Bindable;
import android.databinding.Observable;
import android.databinding.PropertyChangeRegistry;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

import org.parceler.Parcel;

import java.io.IOException;

import io.realm.KidRealmProxy;
import io.realm.RealmObject;
import io.realm.annotations.Ignore;

//created by vishal gupta

@Parcel(implementations = { ${NAME}RealmProxy.class },
       value = Parcel.Serialization.BEAN,
       analyze = { ${NAME}.class })
public class ${NAME} extends RealmObject implements Observable {

  private String test;

   @Bindable
   public String getTest() {
       return test;
   }

   public void setTest(String test) {
       this.test = test;
       if(!isManaged()){
           notifyPropertyChanged(BR.test);
       }

   }


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



   public static class CustomAdapter implements TypeAdapterFactory {

       @Override
       public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> type) {
           if (type.getRawType()!= ${NAME}.class) return null;

           TypeAdapter<${NAME}> defaultAdapter = (TypeAdapter<${NAME}>) gson.getDelegateAdapter(this, type);
           return (TypeAdapter<T>) new ${NAME}Adapter(defaultAdapter);
       }

       public class ${NAME}Adapter extends TypeAdapter<${NAME}> {

           protected TypeAdapter<${NAME}> defaultAdapter;


           public ${NAME}Adapter(TypeAdapter<${NAME}> defaultAdapter) {
               this.defaultAdapter = defaultAdapter;
           }

           @Override
           public void write(JsonWriter out, ${NAME} value) throws IOException {
               defaultAdapter.write(out, value);
           }

           @Override
           public ${NAME} read(JsonReader in) throws IOException {
           /*
           This is the critical part. So if the value is a string,
           Skip it (no exception) and return null.
           */
               if (in.peek() == JsonToken.STRING) {
                    in.skipValue();

                   ${NAME} model = new ${NAME}();
                   return model;
               }
               return defaultAdapter.read(in);
           }
       }
   }
}

