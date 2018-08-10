package com.app.baseandroidproject.Utilities.phone;

import android.database.Cursor;
import android.provider.ContactsContract;
import android.support.v4.app.FragmentActivity;
import android.util.Log;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by root on 8/9/18.
 */



/*
* getcontact(Fragmentactivity,contactID)  useablity
*
*



                            Iterator it = contactIdMap.entrySet().iterator();
                            while (it.hasNext()) {
                                Map.Entry pair = (Map.Entry) it.next();
                                if (pair.getKey().toString().contains(s)) {

                                    mInviteContacts.add(new InviteContacts(pair.getValue().toString(),
                                            pair.getKey().toString(),
                                            "", ""));
                                }

                                // it.remove(); // avoids a ConcurrentModificationException
                            }

                            Log.d(TAG, "onEditorAction: " + mInviteContacts.size());

                            for (InviteContacts item : mInviteContacts) {
                                Disposable number = Observable.just(getContacts(item.getId())).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<String>() {
                                    @Override
                                    public void accept(String s) throws Exception {
                                        item.setNum(s);
                                    }
                                });
//                                    item.setNum(getContacts(item.getId()));
                            }

                            adapter.swapItems(mInviteContacts);

                        }
*
*
* */
public class PhoneUtitlities {

    public static final int PERMISSIONS_REQUEST_READ_CONTACTS = 100;

    /*
    * return name with id
    *
    * */

    public static Map<String, String> getHashMapNameAndID(FragmentActivity fragmentActivity) {
      return  getCursorToMap(getCursor(fragmentActivity));
    }



/*
* contactId : id from getHashMapNameAndID(FragmentActivity fragmentActivity)
*
* returns phone number
*
* */
    public static String getContacts(FragmentActivity fragmentActivity, String contactId) {

        String phoneNumber = "";
        try {
            Cursor cursor = fragmentActivity.getContentResolver()
                    .query(android.provider.ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                            new String[]{
                                    ContactsContract.CommonDataKinds.Phone.NUMBER,
                                    ContactsContract.CommonDataKinds.Phone.CONTACT_ID,
                                    ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME},
                            ContactsContract.CommonDataKinds.Phone.CONTACT_ID
                                    + " = ?",
                            new String[]{contactId}, null);
//        contactNumbers = new ArrayList<String>();
            while (cursor.moveToNext()) {
//            contactNumbers.add(cursor.getString(0));
                Log.d("number", cursor.getString(0));
                phoneNumber = cursor.getString(0);
            }

            cursor.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return phoneNumber;


    }

    private static Cursor getCursor(FragmentActivity fragmentActivity) {
        Cursor cursor = fragmentActivity.getContentResolver().query(
                android.provider.ContactsContract.Contacts.CONTENT_URI,
                new String[]{ContactsContract.Contacts.PHOTO_ID, ContactsContract.Contacts.DISPLAY_NAME, ContactsContract.Contacts._ID, ContactsContract.Contacts.HAS_PHONE_NUMBER},
                ContactsContract.Contacts.HAS_PHONE_NUMBER,
                null,
                ContactsContract.Contacts.DISPLAY_NAME);

        cursor.moveToFirst();

        return cursor;


    }

    private static Map<String, String> getCursorToMap(Cursor cursor) {

        Map<String, String> contactIdMaplist = new HashMap<>();

        Observable.just(cursor).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Cursor>() {
                    @Override
                    public void accept(Cursor cursor) throws Exception {
                        while (cursor.moveToNext()) {

                            //Log.d(TAG, "createView: "+ cursor.getString(2)+"\n");
                            String id = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID));
                            String name = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
                            //  Log.d(TAG, "createView: "+name+"\n");
                            if (cursor.getInt(cursor.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER)) > 0) {

                                contactIdMaplist.put(name, id);
                            }
                            //  contactsID.add(cursor.getString(2));
                        }
                    }
                });

        return contactIdMaplist;


    }
}
