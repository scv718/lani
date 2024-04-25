package com.lani.serviceimpl;

import java.time.Duration;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lani.response.ResponseMapData;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RedisService {
    private final RedisTemplate<String, Object> redisTemplate;

    public void setValues(String key, String data) {
        ValueOperations<String, Object> values = redisTemplate.opsForValue();
        values.set(key, data);
    }

    public void setList(String key, List<ResponseMapData> list, Duration duration) {
        ListOperations<String, Object> values = redisTemplate.opsForList();
        // 리스트의 요소들을 전달합니다.
        values.rightPushAll(key, list);
        // 만료 시간을 설정합니다.
        redisTemplate.expire(key, duration);
    }

    public void setValues(String key, String data, Duration duration) {
        ValueOperations<String, Object> values = redisTemplate.opsForValue();
        values.set(key, data, duration);
    }

    @Transactional(readOnly = true)
    public String getValues(String key) {
        ValueOperations<String, Object> values = redisTemplate.opsForValue();
        if (values.get(key) == null) {
            return "false";
        }
        return (String) values.get(key);
    }

    public List<ResponseMapData> getList(String key) {
        ListOperations<String, Object> listOps = redisTemplate.opsForList();
        List<Object> rawList = listOps.range(key, 0, -1);

        List<Map<String, String>> flattenedList = rawList.stream()
                .flatMap(innerList -> ((List<Map<String, String>>) innerList).stream())
                .collect(Collectors.toList());

        List<ResponseMapData> resultList = flattenedList.stream()
                .map(data -> new ResponseMapData(
                        data.get("date"),
                        data.get("title"),
                        data.get("store"),
                        data.get("point_x"),
                        data.get("point_y"),
                        data.get("filePath"),
                        data.get("main_img")
                ))
                .collect(Collectors.toList());

        return resultList;
    }


    public void deleteValues(String key) {
        redisTemplate.delete(key);
    }

    public void expireValues(String key, int timeout) {
        redisTemplate.expire(key, timeout, TimeUnit.MILLISECONDS);
    }

    public void convertAndSend(String channel, String msg) {

    	redisTemplate.convertAndSend(channel, msg);
    	System.out.println(channel+" : "+msg);
    }

    public void setHashOps(String key, Map<String, String> data) {
        HashOperations<String, Object, Object> values = redisTemplate.opsForHash();
        values.putAll(key, data);
    }

    @Transactional(readOnly = true)
    public String getHashOps(String key, String hashKey) {
        HashOperations<String, Object, Object> values = redisTemplate.opsForHash();
        return Boolean.TRUE.equals(values.hasKey(key, hashKey)) ? (String) redisTemplate.opsForHash().get(key, hashKey) : "";
    }

    public void deleteHashOps(String key, String hashKey) {
        HashOperations<String, Object, Object> values = redisTemplate.opsForHash();
        values.delete(key, hashKey);
    }

    public boolean checkExistsValue(String value) {
        return !value.equals("false");
    }
}