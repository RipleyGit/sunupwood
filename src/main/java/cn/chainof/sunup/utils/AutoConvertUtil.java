package cn.chainof.sunup.utils;

import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
public class AutoConvertUtil {

    public static <T> T autoConvertTo(Object src,Class<T> clazz){
        if (src == null || clazz == null){
            return null;
        }
        //构建目标对象
        try {
            T target = clazz.newInstance();
            Map<String, Field> srcFieldMap = getAssignableFieldsMap(src);
            Map<String, Field> targetFieldMap = getAssignableFieldsMap(target);
            for (String srcFieldName : srcFieldMap.keySet()) {
                Field srcField = srcFieldMap.get(srcFieldName);
                if (srcField == null) {
                    continue;
                }
                // 变量名需要相同
                if (!targetFieldMap.keySet().contains(srcFieldName)) {
                    continue;
                }
                Field targetField = targetFieldMap.get(srcFieldName);
                if (targetField == null) {
                    continue;
                }
                // 类型需要相同
                if (!srcField.getType().equals(targetField.getType())) {
                    continue;
                }
                // 如果目标中有数据,则不进行赋值操作
                if (targetField.get(target) != null && targetField.get(target) != "") {
                    continue;
                }
                targetField.set(target, srcField.get(src));
            }
            return target;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new RuntimeException(e);
        }
    }

    private static Map<String, Field> getAssignableFieldsMap(Object obj) {
        if (obj == null) {
            return new HashMap<String, Field>(16);
        }
        Map<String, Field> fieldMap = new HashMap<String, Field>(16);
        for (Field field : obj.getClass().getDeclaredFields()) {
            // 过滤不需要拷贝的属性
            if (Modifier.isStatic(field.getModifiers()) || Modifier.isFinal(field.getModifiers())) {
                continue;
            }
            field.setAccessible(true);
            fieldMap.put(field.getName(), field);
        }
        return fieldMap;
    }

    public static <T> List<T> convert2List(List objList,Class<T> clazz){
        List<T> list = new ArrayList<>();
        for (Object obj:objList) {
            T t = autoConvertTo(obj, clazz);
            list.add(t);
        }
        return list;
    }
}
