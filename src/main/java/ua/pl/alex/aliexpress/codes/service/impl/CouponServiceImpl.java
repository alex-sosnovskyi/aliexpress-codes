package ua.pl.alex.aliexpress.codes.service.impl;

import ua.pl.alex.aliexpress.codes.DTO.CouponDTO;
import ua.pl.alex.aliexpress.codes.Entity.Coupon;
import ua.pl.alex.aliexpress.codes.dao.Dao;
import ua.pl.alex.aliexpress.codes.dao.DaoFactory;
import ua.pl.alex.aliexpress.codes.datasource.DataSourceHikariImpl;
import ua.pl.alex.aliexpress.codes.service.api.Service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Aleksandr on 12.09.2019.
 */
public class CouponServiceImpl implements Service<Integer, CouponDTO> {
    private static CouponServiceImpl service;
    private Dao<Integer, Coupon> couponDao;
    private DataSourceHikariImpl dataSource;

    private CouponServiceImpl() {
        couponDao = DaoFactory.getInstance().getCouponDao();
        dataSource = DataSourceHikariImpl.getInstance();
    }

    public static synchronized CouponServiceImpl getInstance() {
        if (service == null) {
            service = new CouponServiceImpl();
        }

        return service;
    }

    public List<CouponDTO> getAllByCouponesByRegion(String reg) {
        List<CouponDTO> couponDTOList = new ArrayList<>();
        List<CouponDTO> all = getAll();
        for (CouponDTO current : all) {
            if (current.getRegion().equals(reg)) {
                couponDTOList.add(current);
            }
        }
        return couponDTOList;
    }

    @Override
    public List<CouponDTO> getAll() {
        List<Coupon> coupons = new ArrayList<>();
        //--------------------------------------------------
        //  coupons.addAll(couponDao.getAll());
        //----------------------------------------------------
        //DataSourceHikariImpl dataSource = DataSourceHikariImpl.getInstance();
        try (Connection connection = dataSource.getConnection(); Statement statement = connection.createStatement()) {
            ResultSet rs = statement.executeQuery("SELECT * FROM coupon ORDER BY rating");
            while (rs.next()) {

                String name = rs.getString("name");
                String description = rs.getString("description");
                String code = rs.getString("code");
                String link = rs.getString("link");
                String region = rs.getString("region");
                Date dayOn = rs.getDate("dayOn");
                Date dayOf = rs.getDate("dayOf");
                coupons.add(new Coupon(name, description, code, link, region, dayOn, dayOf));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        long currentTimeMillis = System.currentTimeMillis();
        Date now = new Date(currentTimeMillis);

        //========================test data==================================================
//        coupons.add(new Coupon("Скидка по промокоду -$5 на заказ от $40", "Получите Вашу скидку при заказах от 40$",
//                "828novo", "https://alitems.com/g/1e8d114494c3a5b5353c16525dc3e8/", "UA", new Date(getPastDate()), new Date(getFutureDate())));
//        coupons.add(new Coupon("Скидка по промокоду -$6 на заказ от $50", "Получите Вашу скидку при заказах от 50$",
//                "828novo", "https://alitems.com/g/1e8d114494c3a5b5353c16525dc3e8/", "UA", new Date(getPastDate()), new Date(getFutureDate())));
//        coupons.add(new Coupon("Скидка по промокоду -$7 на заказ от $60", "Получите Вашу скидку при заказах от 60$",
//                "", "https://alitems.com/g/1e8d114494c3a5b5353c16525dc3e8/", "UA", new Date(getPastDate()), new Date(getFutureDate())));
//        coupons.add(new Coupon("Скидка по промокоду -$8 на заказ от $80", "Получите Вашу скидку при заказах от 80$",
//                "828novo", "https://alitems.com/g/1e8d114494c3a5b5353c16525dc3e8/", "UA", new Date(getPastDate()), new Date(getFutureDate())));
//        coupons.add(new Coupon("Скидка по промокоду -$9 на заказ от $90", "Получите Вашу скидку при заказах от 90$",
//                "", "https://alitems.com/g/1e8d114494c3a5b5353c16525dc3e8/", "UA", new Date(getPastDate()), new Date(getFutureDate())));
//        coupons.add(new Coupon("Скидка по промокоду -$10 на заказ от $100", "Получите Вашу скидку при заказах от 100$",
//                "828novo", "https://alitems.com/g/1e8d114494c3a5b5353c16525dc3e8/", "UA", new Date(getPastDate()), new Date(getFutureDate())));
//        coupons.add(new Coupon("Скидка по промокоду -$11 на заказ от $140", "Получите Вашу скидку при заказах от 140$",
//                "", "https://alitems.com/g/1e8d114494c3a5b5353c16525dc3e8/", "UA", new Date(getPastDate()), new Date(getFutureDate())));
//        coupons.add(new Coupon("Скидка по промокоду -$12 на заказ от $200", "Получите Вашу скидку при заказах от 200$",
//                "828novo", "https://alitems.com/g/1e8d114494c3a5b5353c16525dc3e8/", "UA", new Date(getPastDate()), new Date(getFutureDate())));
        //====================================================================================
        List<CouponDTO> couponDTOList = new ArrayList<>();
        for (Coupon current : coupons) {
            if (now.before(current.getEndOf()) && now.after(current.getStartOn()))
                couponDTOList.add(new CouponDTO(current.getName(), current.getDescription(), current.getCode(),
                        current.getLink(), current.getRegion(), current.getStartOn(), current.getEndOf()));
        }
        return couponDTOList;
    }

    @Override
    public CouponDTO getById(Integer key) {
        return null;
    }

    @Override
    public void save(CouponDTO entity) {

    }

    @Override
    public void delete(Integer key) {

    }

    @Override
    public void update(CouponDTO entity) {

    }

    public long getPastDate() {
        long now = System.currentTimeMillis();
        long date = now - 48 * 60 * 60 * 1000;
        return date;
    }

    public long getFutureDate() {
        long now = System.currentTimeMillis();
        long date = now + 48 * 60 * 60 * 1000;
        return date;
    }
}
