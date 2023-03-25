package com.hmdp.service.impl;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.hmdp.dto.Result;
import com.hmdp.entity.ShopType;
import com.hmdp.mapper.ShopTypeMapper;
import com.hmdp.service.IShopTypeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hmdp.utils.RedisConstants;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zt
 * @since 2021-12-22
 */
@Service
public class ShopTypeServiceImpl extends ServiceImpl<ShopTypeMapper, ShopType> implements IShopTypeService {

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public Result queryShopList() {
        String key = RedisConstants.CACHE_SHOP_LIST_KEY;
        //1.从redis中查询商铺类型列表
        String shopTypeListJson = stringRedisTemplate.opsForValue().get(key);
        //2.存在，返回
        if (StrUtil.isNotBlank(shopTypeListJson)) {
            List<ShopType> shopTypeList = JSONUtil.toList(shopTypeListJson, ShopType.class);
            return Result.ok(shopTypeList);
        }
        //3.不存在，去查数据库
        List<ShopType> shopTypeList = query().orderByAsc("sort").list();
        //4.数据库中不存在，返回失败
        if (shopTypeList.isEmpty()) {
            return Result.fail("店铺列表不存在");
        }
        //5.数据库中存在，写入redis
        String redisShopTypeListJson = JSONUtil.toJsonStr(shopTypeList);
        stringRedisTemplate.opsForValue().set(key, redisShopTypeListJson, RedisConstants.CACHE_SHOP_LIST_TTL, TimeUnit.MINUTES);
        //返回
        return Result.ok(shopTypeList);
    }
}
