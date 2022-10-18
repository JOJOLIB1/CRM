package com.jjj.crm.commons.constants;

import com.jjj.crm.commons.util.UUIDUtils;
import com.jjj.crm.workbench.pojo.*;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @className: com.jjj.crm.commons.constants.ConstantConvert
 * @description:
 * @author: 江骏杰
 * @create: 2022-10-17 20:42
 */
public class ConstantConvert {
    /**
     * CLUE_ID 线索ID
     */
    public static final String CLUE_ID = "clueId";

    public static final String CHANGE_USER = "user";

    public static final String IS_TRAN = "isTran";

    public static final String TRAN_OBJECT = "tran";

    /**
     * 转换动态的Remark对象
     * @param clazz 转换目标的class
     * @param clueRemark 转换源
     * @param id 所对应`一`的id
     * @return 对应的目标(动态CustomerRemark或ContactsRemark)
     * @param <T> 泛型T,需要clazz执行
     */
    public static <T> T convertRemark(Class<T> clazz, ClueRemark clueRemark, String id) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, InstantiationException {
        T t = clazz.newInstance();
        clazz.getDeclaredMethod("setId", String.class).invoke(t, UUIDUtils.getUUID());
        clazz.getDeclaredMethod("setNoteContent", String.class).invoke(t, clueRemark.getNoteContent());
        clazz.getDeclaredMethod("setCreateBy", String.class).invoke(t, clueRemark.getCreateBy());
        clazz.getDeclaredMethod("setCreateTime", String.class).invoke(t, clueRemark.getCreateTime());
        clazz.getDeclaredMethod("setEditBy", String.class).invoke(t, clueRemark.getEditBy());
        clazz.getDeclaredMethod("setEditTime", String.class).invoke(t, clueRemark.getEditTime());
        clazz.getDeclaredMethod("setEditFlag", String.class).invoke(t, clueRemark.getEditFlag());
        if (t instanceof CustomerRemark)
            clazz.getDeclaredMethod("setCustomerId", String.class).invoke(t, id);
        else if (t instanceof ContactsRemark)
            clazz.getDeclaredMethod("setContactsId", String.class).invoke(t, id);
        else if (t instanceof TranRemark)
            clazz.getDeclaredMethod("setTranId", String.class).invoke(t, id);
        return t;
    }


}
