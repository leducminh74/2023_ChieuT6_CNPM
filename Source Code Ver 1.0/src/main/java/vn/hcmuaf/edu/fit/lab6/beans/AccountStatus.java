package vn.hcmuaf.edu.fit.lab6.beans;

public class AccountStatus {
    private int sa_id;
    private String sa_name;

    public AccountStatus(int sa_id, String sa_name) {
        this.sa_id = sa_id;
        this.sa_name = sa_name;
    }

    public int getSa_id() {
        return sa_id;
    }

    public void setSa_id(int sa_id) {
        this.sa_id = sa_id;
    }

    public String getSa_name() {
        return sa_name;
    }

    public void setSa_name(String sa_name) {
        this.sa_name = sa_name;
    }

    @Override
    public String toString() {
        return "AccountStatus{" +
                "sa_id=" + sa_id +
                ", sa_name='" + sa_name + '\'' +
                '}';
    }
}
