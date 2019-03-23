package net.tf.java8.optional;

import lombok.*;

import java.util.Optional;

/**
 * @author hy
 * @version 1.00
 * @time 2019/3/23 15:23
 * @desc
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Man {
    /**
     * 注意不能使用null初始化goddess
     */
    private Optional<Goddess> godness = Optional.empty();

    /**
     * 获取该男生心目中女神的名字，可能不存在
     */
    public static String getGodnessName(Optional<Man> optionalMan) {
        return optionalMan.orElse(new Man())
                .getGodness().orElse(new Goddess("默认的无女神"))
                .getName();
    }
}
