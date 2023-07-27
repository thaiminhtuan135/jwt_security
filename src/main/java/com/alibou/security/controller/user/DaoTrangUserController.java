package com.alibou.security.controller.user;

import com.alibou.security.Entity.DaoTrang;
import com.alibou.security.service.daoTrang.DaoTrangService;
import com.alibou.security.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/user/dao-trang")
@RequiredArgsConstructor
public class DaoTrangUserController {

    @Autowired
    private DaoTrangService daoTrangService;
    @Autowired
    private UserService userService;
    @GetMapping
    public Page<DaoTrang> pagination(@RequestParam(defaultValue = "0") int pageNo,
                                     @RequestParam(defaultValue = "10") int pageSize,
                                     @RequestParam(required = false) String ten

    ) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        List<DaoTrang> list = new ArrayList<>();
        list = daoTrangService.getAll().stream().filter(daoTrang -> daoTrang.getDaKetThuc() == 1).collect(Collectors.toList());
        if (ten != null) {
            list = list.stream().filter(daoTrang -> daoTrang.getTen().contains(ten)).collect(Collectors.toList());
        }

        return new PageImpl<>(list, pageable, list.size());
    }

}
