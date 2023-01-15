package com.itheima.reggie.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.itheima.reggie.common.R;
import com.itheima.reggie.entity.Dish;
import com.itheima.reggie.service.impl.DishFlavorServiceImpl;
import com.itheima.reggie.service.impl.DishServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/dish")
public class DishController {

     @Autowired
     private DishFlavorServiceImpl dishFlavorService = new DishFlavorServiceImpl();

     @Autowired
     private DishServiceImpl dishService = new DishServiceImpl();

     @GetMapping("/page")
     public R<Page> page(int page,int pageSize){
          //构造分页构造器
          Page pageInfo = new Page(page,pageSize);

          //构造条件构造器
          LambdaQueryWrapper<Dish> queryWrapper = new LambdaQueryWrapper<>();
          //添加排序条件
          queryWrapper.orderByDesc(Dish::getSort).orderByDesc(Dish::getCreateTime);
          //分页查询
          dishService.page(pageInfo,queryWrapper);
     return R.success(pageInfo);
     }

}
