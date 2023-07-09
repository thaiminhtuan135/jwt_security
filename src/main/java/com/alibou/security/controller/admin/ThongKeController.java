package com.alibou.security.controller.admin;

//select u.email , count(ptdt.dao_trang_id)
//        from _user u
//        join phat_tu_dao_trang ptdt on u.id = ptdt.phat_tu_id
//        where da_tham_gia = true group by u.email
//        ;

import com.alibou.security.DTO.ThongKePhatTuDTO;
import com.alibou.security.service.user.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//select u.email , u.ten , count(ptdt.dao_trang_id)
//        from _user u
//        join phat_tu_dao_trang ptdt on u.id = ptdt.phat_tu_id
//        join dao_trang dt on dt.id = ptdt.dao_trang_id
//        where da_tham_gia = true
//        AND dt.thoi_gian_to_chuc >= '2022-01-01 00:00:00'
//        AND dt.thoi_gian_to_chuc <= '2022-12-31 23:59:59'
//        group by u.email
//        ;
@CrossOrigin
@RestController
@RequestMapping("/api/v1/admin/thong-ke")
@RequiredArgsConstructor
@Tag(name = "Thống kê")
public class ThongKeController {
    @Autowired
    private UserService userService;
    @GetMapping
    public List<?> thongKe() {
        List<ThongKePhatTuDTO> listPhatTu = userService.thongKe();
        List<Object[]> listOfLists = new ArrayList<>();
        String[] list1 = new String[listPhatTu.size()];
        Long[] list2 = new Long[listPhatTu.size()];
        for (int i = 0; i < listPhatTu.size(); i++) {
            list1[i] = listPhatTu.get(i).getEmail();
            list2[i] = listPhatTu.get(i).getSoLanThamGia();
        }
        listOfLists.add(list1);
        listOfLists.add(list2);

        return listOfLists;
    }
}
