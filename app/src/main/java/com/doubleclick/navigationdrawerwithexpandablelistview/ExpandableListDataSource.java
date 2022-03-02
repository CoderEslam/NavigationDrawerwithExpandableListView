package com.doubleclick.navigationdrawerwithexpandablelistview;

import android.content.Context;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class ExpandableListDataSource {

    /**
     * Returns fake data of films
     *
     * @param context
     * @return
     */
    public static Map<String, List<String>> getData(Context context) {
        Map<String, List<String>> expandableListData = new TreeMap<>();

        List<String> filmGenres = Arrays.asList(context.getResources().getStringArray(R.array.enginee));

        List<String> actionFilms = Arrays.asList(context.getResources().getStringArray(R.array.Civil));
        List<String> musicalFilms = Arrays.asList(context.getResources().getStringArray(R.array.Computer));
        List<String> dramaFilms = Arrays.asList(context.getResources().getStringArray(R.array.Electrical));
        List<String> thrillerFilms = Arrays.asList(context.getResources().getStringArray(R.array.Electronics));

        expandableListData.put(filmGenres.get(0), actionFilms);
        expandableListData.put(filmGenres.get(1), musicalFilms);
        expandableListData.put(filmGenres.get(2), dramaFilms);
        expandableListData.put(filmGenres.get(4), thrillerFilms);
        expandableListData.put(filmGenres.get(5), thrillerFilms);
        expandableListData.put(filmGenres.get(6), thrillerFilms);
        expandableListData.put(filmGenres.get(7), thrillerFilms);
//        expandableListData.put(filmGenres.get(8), thrillerFilms);
//        expandableListData.put(filmGenres.get(9), thrillerFilms);
//        expandableListData.put(filmGenres.get(10), thrillerFilms);
//        expandableListData.put(filmGenres.get(11), thrillerFilms);

        return expandableListData;
    }
}