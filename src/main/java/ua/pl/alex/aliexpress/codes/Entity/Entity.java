package ua.pl.alex.aliexpress.codes.Entity;

/**
 * Created by Aleksandr on 12.09.2019.
 */
public class Entity<T> {
    private T id;

    public void setId(T id) {
        this.id = id;
    }

    public T getId() {

        return id;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if(obj==null){
            return false;
        }
        if(!(obj instanceof Entity)){
            return false;
        }
        if(this==obj){
            return true;
        }
        Entity<T> entity=(Entity<T>)obj;
        return this.getId().equals(entity.getId());
    }
}
