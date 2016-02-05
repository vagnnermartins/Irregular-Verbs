package com.vagnnermartins.irregularverbs.util;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;

import java.io.Serializable;
import java.util.Map;
import java.util.Map.Entry;

public class NavegationUtil {

	public static final String EXTRAS = "extras";

	public static void startActivity(Activity activity, Class<?> contextoDestino){
		Intent i = new Intent(activity, contextoDestino);
		i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		activity.startActivity(i);
	}
	
	public static void startActivityExtras(Activity activity, Class clazz, Map<String, Serializable> extras){
		Intent intent = new Intent(activity, clazz);
		for (Entry<String, Serializable> current : extras.entrySet()) {
			intent.putExtra(current.getKey(), current.getValue());
		}
		activity.startActivity(intent);
	}
	
}
