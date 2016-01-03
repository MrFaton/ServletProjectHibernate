package com.nixsolutions.ponarin;

import com.nixsolutions.ponarin.dao.RoleDao;
import com.nixsolutions.ponarin.dao.impl.HibernateRoleDao;
import com.nixsolutions.ponarin.entity.Role;
import com.nixsolutions.ponarin.utils.JPAUtils;

public class Ttest {
    public static void main(String[] args) {
        try {
            RoleDao roleDao = new HibernateRoleDao();
//            Role role = new Role();
//            role.setName("Admin");
//            roleDao.create(role);
//            roleDao.update(role);
            Role adminRole = roleDao.findByName("Admin");
            roleDao.remove(adminRole);
            adminRole = roleDao.findByName("Admin");
            System.out.println(adminRole);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        JPAUtils.closeFactory();
    }
}
