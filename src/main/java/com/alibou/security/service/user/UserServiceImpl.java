package com.alibou.security.service.user;

import com.alibou.security.DTO.ThongKePhatTuDTO;
import com.alibou.security.Entity.User;
import com.alibou.security.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private JavaMailSender mailSender;

    @Override
    public Optional<User> getUser(String email) {
        return userRepository.existsByEmail(email, null);
    }

    @Override
    public boolean existEmail(String email, Integer id) {
        return userRepository.existsByEmail(email, id).isPresent();
    }

    @Override
    public Optional<User> getUserById(int id) {
        return userRepository.findById(id);
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public void delete(int id) {
        userRepository.deleteById(id);
    }

    @Override
    public List<User> pagination(String phapDanh, String ten, Boolean daHoanTuc, String gioiTinh) {
        return userRepository.pagination(phapDanh, ten, daHoanTuc,gioiTinh);
    }

    @Override
    public List<ThongKePhatTuDTO> thongKe() {
        return userRepository.thongKeSoLanPhatTu();
    }

    @Override
    public List<User> getPhatTuByDaoTrangId(int daoTrangId) {
        return userRepository.GetPhatTuByDaoTrangId(daoTrangId);
    }


}
