package ua.pl.alex.aliexpress.codes.dao;

import ua.pl.alex.aliexpress.codes.Entity.Coupon;
import ua.pl.alex.aliexpress.codes.dao.impl.CouponDaoImpl;
import ua.pl.alex.aliexpress.codes.helper.PropertyHolder;

/**
 * Created by Aleksandr on 12.09.2019.
 */
public class DaoFactory {
    private static DaoFactory instance=null;
    private Dao<Integer, Coupon> couponDao;

    private DaoFactory(){
        loadDaos();
    }

    public static DaoFactory getInstance(){
        if(instance == null){
            instance = new DaoFactory();
        }
        return instance;
    }

    private void loadDaos() {
        if(PropertyHolder.getInstance().isInMemory()){

        }else{
            couponDao = CouponDaoImpl.getInstance();

        }
    }

    public Dao<Integer, Coupon> getCouponDao() {
        return couponDao;
    }
}
