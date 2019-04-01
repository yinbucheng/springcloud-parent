package cn.intellif.server.provider.entity;

import org.apache.ibatis.type.Alias;

import java.io.Serializable;

/**
 * @ClassName UserEntity
 * @Author buchengyin
 * @Date 2019/4/1 11:40
 **/
@Alias("user")
public class UserEntity implements Serializable {
    private Long id;
    private String name;
    private Integer age;
    private String gender;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
