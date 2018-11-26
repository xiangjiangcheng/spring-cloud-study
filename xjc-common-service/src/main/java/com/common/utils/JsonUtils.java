package com.common.utils;

import com.common.exception.BusiException;
import com.common.model.ErrorKey;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.sf.json.JSONArray;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.IOException;
import java.io.StringWriter;
import java.lang.reflect.Method;
import java.util.*;

public class JsonUtils {

    private static final ObjectMapper mapper = new ObjectMapper();

    static {
        mapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
        mapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true);
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
    }

    private JsonUtils() {
    }

    public static <T> T toBean(String json, Class<T> clazz) {
        try {
            T bean = (T) mapper.readValue(json, clazz);
            return bean;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    public static HashMap<String, Object> toBean(String json) {
        return toBean(json, HashMap.class);
    }

    @SuppressWarnings("unchecked")
    public static HashMap<String,String> toBeanStr(String json) {
        return toBean(json, HashMap.class);
    }

    @SuppressWarnings("unchecked")
    public static <T> T toBean(String json, TypeReference<T> tr){
        try {
            T bean = (T) mapper.readValue(json, tr);
            return bean;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String toJson(Object bean) {
        String json = null;
        JsonGenerator gen = null;
        StringWriter sw = new StringWriter();
        try {
            gen = new JsonFactory().createGenerator(sw);
            mapper.writeValue(gen, bean);
            json = sw.toString();
        } catch (IOException e) {
            throw new BusiException(ErrorKey.SYSTEM_ERROR);
        } finally {
            try {
                if (gen != null) {
                    gen.close();
                }
                if (sw != null) {
                    sw.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return json;
    }

    @SuppressWarnings("unchecked")
    public static List<Object> toList(String json) {
        return toBean(json, ArrayList.class);
    }


    public static Map<String, Object> transBean2Map(Object obj) {
        if (obj == null) {
            return null;
        }
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());
            PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
            for (PropertyDescriptor property : propertyDescriptors) {
                String key = property.getName();
                if (!key.equals("class")) {
                    Method getter = property.getReadMethod();
                    Object value = getter.invoke(obj);
                    map.put(key, value);
                }
            }
        } catch (Exception e) {
            System.out.println("transBean2Map Error " + e);
        }
        return map;
    }

    public static <T> List<T>json2ListBean(String json,Class<T>cls){
        JSONArray jArray= JSONArray.fromObject(json);
        Collection<T> collection = JSONArray.toCollection(jArray, cls);
        List<T> list = new ArrayList<T>();
        Iterator<T> it = collection.iterator();
        while (it.hasNext()) {
            T bean = (T) it.next();
            list.add(bean);
        }
        return list;
    }

    /**
     * 对象转json字符串
     */
    public static String convertObj2String(Object object) {
        String s = null;
        try {
            s = mapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return s;
    }

    /**
     * json字符串转对象
     */
    public static <T> T convertString2Obj(String s, Class<T> clazz) {
        T t = null;
        try {
            t = mapper.readValue(s, clazz);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return t;
    }
}
