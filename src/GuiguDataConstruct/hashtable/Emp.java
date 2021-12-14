package GuiguDataConstruct.hashtable;

/**
 * @title: Emp
 * @Author Tan
 * @Date: 2021/12/14 14:59
 * @Version 1.0
 */
// 构造 Emp 结点
public class Emp {
    public int id;
    public String name;
    public String address;
    public Emp next;

    public Emp(int id, String name, String address) {
        this.id = id;
        this.name = name;
        this.address = address;
    }

    @Override
    public String toString() {
        return "Emp{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}

