package com.airfans.blog.entity.user;

import org.apache.ibatis.type.Alias;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@TableName("t_blog_userInfo")
@Alias("user")
@Data
@Setter
@Getter
public class UserEntity {
    /**
     * 主键ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @TableField("user_name")
    private String userName;

    @TableField("user_pwd")
    private String userPwd;
}
