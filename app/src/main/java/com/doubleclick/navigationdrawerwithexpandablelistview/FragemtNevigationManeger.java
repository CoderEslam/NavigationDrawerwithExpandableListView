package com.doubleclick.navigationdrawerwithexpandablelistview;

import android.os.Build;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class FragemtNevigationManeger implements NavigationManger {

    private static FragemtNevigationManeger  mInstance;
    private MainActivity mainActivity;
    private FragmentManager mFragmentManager;
    public static FragemtNevigationManeger getInstance(MainActivity mainActivity){
        if (mInstance==null){
            mInstance = new FragemtNevigationManeger();
            mInstance.configare(mainActivity);
        }
        return mInstance;
    }

    private void configare(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
        mFragmentManager = mainActivity.getSupportFragmentManager();

    }

    @Override
    public void showFragment(String title) {

        showFragment(BlankFragment.newInstance(title),false);
    }
    private void showFragment(Fragment blankFragment, boolean b){
        FragmentManager fm=mFragmentManager;
        FragmentTransaction ft = fm.beginTransaction().replace(R.id.continer,blankFragment);
        ft.addToBackStack(null);
        if (b){
            ft.commitAllowingStateLoss();
        }else {
            ft.commit();
        }
        fm.executePendingTransactions();
    }
}
