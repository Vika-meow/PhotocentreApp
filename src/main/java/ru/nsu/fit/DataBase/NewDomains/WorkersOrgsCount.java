package ru.nsu.fit.DataBase.NewDomains;


public class WorkersOrgsCount {
    public static final String PROP_PROFILE="profile";
    public static final String PROP_ADDRESS="address";
    public static final String PROP_COUNT="count";

    String profile;
    String address;
    Long count;

    public WorkersOrgsCount(String profile, String address, Long count) {
        this.profile = profile;
        this.address = address;
        this.count = count;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }
}
