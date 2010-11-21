package com.manning.aip.mymoviesfile.util;

import android.graphics.Bitmap;
import android.net.ConnectivityManager;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

@SuppressWarnings("serial")
public class ImageCache {

   private static final int IMAGE_CACHE_SIZE = 250;

   private final Map<String, Bitmap> cache;

   // HashMap decorator that only grows to X size
   // (note, using simple WeakHashMap is NOT a good cache, it uses weak references for *keys*)
   public ImageCache(final ConnectivityManager cMgr) {
      this.cache = Collections.synchronizedMap(new LinkedHashMap<String, Bitmap>(IMAGE_CACHE_SIZE + 1, .75F, true) {
         public boolean removeEldestEntry(Map.Entry<String, Bitmap> eldest) {
            return size() > IMAGE_CACHE_SIZE;
         }
      });
   }

   public Bitmap get(String urlString) {
      return this.cache.get(urlString);      
   }

   public void put(String urlString, Bitmap bitmap) {
      this.cache.put(urlString, bitmap);
   }   
}
