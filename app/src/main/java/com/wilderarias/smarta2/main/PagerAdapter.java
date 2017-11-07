package com.wilderarias.smarta2.main;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.wilderarias.smarta2.inventario.InventarioFragment;
import com.wilderarias.smarta2.ruta.RutaFragment;


/**
 * Created by WilderArias on 10/29/2017.
 */

public class PagerAdapter extends FragmentPagerAdapter {

    public PagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {

        switch (position){
            case 0:
                RutaFragment rutaRutaFragment =new RutaFragment();
                return rutaRutaFragment;
            case 1:
                InventarioFragment inventarioInventarioFragment =new InventarioFragment();
                return inventarioInventarioFragment;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {

            switch (position){
                case 0:
                    return "Bello";
                case 1:
                    return "Inventario";
            }
        return super.getPageTitle(position);
    }
}
