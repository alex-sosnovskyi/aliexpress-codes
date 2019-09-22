package ua.pl.alex.aliexpress.codes.Entity;

import java.util.Date;

/**
 * Created by Aleksandr on 12.09.2019.
 */
public class Coupon extends Entity<Integer> {
    private String name;
    private String description;
    private String code;
    private String region;
    private Date startOn;
    private Date endOf;
    private String link;
    private int rating;


    public Coupon(String name, String description, String code, String link, String region, Date startOn, Date endOf) {
        this.name = name;
        this.description = description;
        this.code = code;
        this.link = link;
        this.region = region;
        this.startOn = startOn;
        this.endOf = endOf;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getCode() {
        return code;
    }

    public String getRegion() {
        return region;
    }

    public Date getStartOn() {
        return startOn;
    }

    public Date getEndOf() {
        return endOf;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public void setStartOn(Date startOn) {
        this.startOn = startOn;
    }

    public void setEndOf(Date endOf) {
        this.endOf = endOf;
    }

    public String getLink() {

        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

}
